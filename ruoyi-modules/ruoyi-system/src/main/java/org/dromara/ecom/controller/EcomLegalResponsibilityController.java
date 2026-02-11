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
import org.dromara.ecom.domain.vo.EcomLegalResponsibilityVo;
import org.dromara.ecom.domain.bo.EcomLegalResponsibilityBo;
import org.dromara.ecom.service.IEcomLegalResponsibilityService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 法律责任
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/legalResponsibility")
public class EcomLegalResponsibilityController extends BaseController {

    private final IEcomLegalResponsibilityService ecomLegalResponsibilityService;

    /**
     * 查询法律责任列表
     */
    @SaCheckPermission("ecom:legalResponsibility:list")
    @GetMapping("/list")
    public TableDataInfo<EcomLegalResponsibilityVo> list(EcomLegalResponsibilityBo bo, PageQuery pageQuery) {
        return ecomLegalResponsibilityService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出法律责任列表
     */
    @SaCheckPermission("ecom:legalResponsibility:export")
    @Log(title = "法律责任", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomLegalResponsibilityBo bo, HttpServletResponse response) {
        List<EcomLegalResponsibilityVo> list = ecomLegalResponsibilityService.queryList(bo);
        ExcelUtil.exportExcel(list, "法律责任", EcomLegalResponsibilityVo.class, response);
    }

    /**
     * 获取法律责任详细信息
     *
     * @param responsibilityId 主键
     */
    @SaCheckPermission("ecom:legalResponsibility:query")
    @GetMapping("/{responsibilityId}")
    public R<EcomLegalResponsibilityVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long responsibilityId) {
        return R.ok(ecomLegalResponsibilityService.queryById(responsibilityId));
    }

    /**
     * 新增法律责任
     */
    @SaCheckPermission("ecom:legalResponsibility:add")
    @Log(title = "法律责任", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomLegalResponsibilityBo bo) {
        return toAjax(ecomLegalResponsibilityService.insertByBo(bo));
    }

    /**
     * 修改法律责任
     */
    @SaCheckPermission("ecom:legalResponsibility:edit")
    @Log(title = "法律责任", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomLegalResponsibilityBo bo) {
        return toAjax(ecomLegalResponsibilityService.updateByBo(bo));
    }

    /**
     * 删除法律责任
     *
     * @param responsibilityIds 主键串
     */
    @SaCheckPermission("ecom:legalResponsibility:remove")
    @Log(title = "法律责任", businessType = BusinessType.DELETE)
    @DeleteMapping("/{responsibilityIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] responsibilityIds) {
        return toAjax(ecomLegalResponsibilityService.deleteWithValidByIds(List.of(responsibilityIds), true));
    }
}
