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
import org.dromara.ecom.domain.vo.EcomLogisticsHistoryVo;
import org.dromara.ecom.domain.bo.EcomLogisticsHistoryBo;
import org.dromara.ecom.service.IEcomLogisticsHistoryService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 物流跟踪记录
 *
 * @author Bob Bai
 * @date 2026-02-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/logisticsHistory")
public class EcomLogisticsHistoryController extends BaseController {

    private final IEcomLogisticsHistoryService ecomLogisticsHistoryService;

    /**
     * 查询物流跟踪记录列表
     */
    @SaCheckPermission("ecom:logisticsHistory:list")
    @GetMapping("/list")
    public TableDataInfo<EcomLogisticsHistoryVo> list(EcomLogisticsHistoryBo bo, PageQuery pageQuery) {
        return ecomLogisticsHistoryService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出物流跟踪记录列表
     */
    @SaCheckPermission("ecom:logisticsHistory:export")
    @Log(title = "物流跟踪记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomLogisticsHistoryBo bo, HttpServletResponse response) {
        List<EcomLogisticsHistoryVo> list = ecomLogisticsHistoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "物流跟踪记录", EcomLogisticsHistoryVo.class, response);
    }

    /**
     * 获取物流跟踪记录详细信息
     *
     * @param historyId 主键
     */
    @SaCheckPermission("ecom:logisticsHistory:query")
    @GetMapping("/{historyId}")
    public R<EcomLogisticsHistoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long historyId) {
        return R.ok(ecomLogisticsHistoryService.queryById(historyId));
    }

    /**
     * 新增物流跟踪记录
     */
    @SaCheckPermission("ecom:logisticsHistory:add")
    @Log(title = "物流跟踪记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomLogisticsHistoryBo bo) {
        return toAjax(ecomLogisticsHistoryService.insertByBo(bo));
    }

    /**
     * 修改物流跟踪记录
     */
    @SaCheckPermission("ecom:logisticsHistory:edit")
    @Log(title = "物流跟踪记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomLogisticsHistoryBo bo) {
        return toAjax(ecomLogisticsHistoryService.updateByBo(bo));
    }

    /**
     * 删除物流跟踪记录
     *
     * @param historyIds 主键串
     */
    @SaCheckPermission("ecom:logisticsHistory:remove")
    @Log(title = "物流跟踪记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{historyIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] historyIds) {
        return toAjax(ecomLogisticsHistoryService.deleteWithValidByIds(List.of(historyIds), true));
    }
}
