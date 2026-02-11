package org.dromara.ecom.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.ecom.domain.vo.EcomSettlementVo;
import org.dromara.ecom.domain.bo.EcomSettlementBo;
import org.dromara.ecom.service.IEcomSettlementService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 结算
 *
 * @author Bob Bai
 * @date 2026-02-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/settlement")
public class EcomSettlementController extends BaseController {

    private final IEcomSettlementService ecomSettlementService;

    /**
     * 查询结算列表
     */
    @SaCheckPermission("ecom:settlement:list")
    @GetMapping("/list")
    public TableDataInfo<EcomSettlementVo> list(EcomSettlementBo bo, PageQuery pageQuery) {
        return ecomSettlementService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出结算列表
     */
    @SaCheckPermission("ecom:settlement:export")
    @Log(title = "结算", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomSettlementBo bo, HttpServletResponse response) {
        List<EcomSettlementVo> list = ecomSettlementService.queryList(bo);
        ExcelUtil.exportExcel(list, "结算", EcomSettlementVo.class, response);
    }

    /**
     * 获取结算详细信息
     *
     * @param settlementId 主键
     */
    @SaCheckPermission("ecom:settlement:query")
    @GetMapping("/{settlementId}")
    public R<EcomSettlementVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long settlementId) {
        return R.ok(ecomSettlementService.queryById(settlementId));
    }

    /**
     * 新增结算
     */
    @SaCheckPermission("ecom:settlement:add")
    @Log(title = "结算", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomSettlementBo bo) {
        return toAjax(ecomSettlementService.insertByBo(bo));
    }

    /**
     * 修改结算
     */
    @SaCheckPermission("ecom:settlement:edit")
    @Log(title = "结算", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomSettlementBo bo) {
        return toAjax(ecomSettlementService.updateByBo(bo));
    }

    /**
     * 删除结算
     *
     * @param settlementIds 主键串
     */
    @SaCheckPermission("ecom:settlement:remove")
    @Log(title = "结算", businessType = BusinessType.DELETE)
    @DeleteMapping("/{settlementIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] settlementIds) {
        return toAjax(ecomSettlementService.deleteWithValidByIds(List.of(settlementIds), true));
    }
}
