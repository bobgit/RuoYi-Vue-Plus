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
import org.dromara.ecom.domain.vo.EcomActivityAttributesVo;
import org.dromara.ecom.domain.bo.EcomActivityAttributesBo;
import org.dromara.ecom.service.IEcomActivityAttributesService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 活动属性
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/activityAttributes")
public class EcomActivityAttributesController extends BaseController {

    private final IEcomActivityAttributesService ecomActivityAttributesService;

    /**
     * 查询活动属性列表
     */
    @SaCheckPermission("ecom:activityAttributes:list")
    @GetMapping("/list")
    public TableDataInfo<EcomActivityAttributesVo> list(EcomActivityAttributesBo bo, PageQuery pageQuery) {
        return ecomActivityAttributesService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出活动属性列表
     */
    @SaCheckPermission("ecom:activityAttributes:export")
    @Log(title = "活动属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomActivityAttributesBo bo, HttpServletResponse response) {
        List<EcomActivityAttributesVo> list = ecomActivityAttributesService.queryList(bo);
        ExcelUtil.exportExcel(list, "活动属性", EcomActivityAttributesVo.class, response);
    }

    /**
     * 获取活动属性详细信息
     *
     * @param attrId 主键
     */
    @SaCheckPermission("ecom:activityAttributes:query")
    @GetMapping("/{attrId}")
    public R<EcomActivityAttributesVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long attrId) {
        return R.ok(ecomActivityAttributesService.queryById(attrId));
    }

    /**
     * 新增活动属性
     */
    @SaCheckPermission("ecom:activityAttributes:add")
    @Log(title = "活动属性", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomActivityAttributesBo bo) {
        return toAjax(ecomActivityAttributesService.insertByBo(bo));
    }

    /**
     * 修改活动属性
     */
    @SaCheckPermission("ecom:activityAttributes:edit")
    @Log(title = "活动属性", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomActivityAttributesBo bo) {
        return toAjax(ecomActivityAttributesService.updateByBo(bo));
    }

    /**
     * 删除活动属性
     *
     * @param attrIds 主键串
     */
    @SaCheckPermission("ecom:activityAttributes:remove")
    @Log(title = "活动属性", businessType = BusinessType.DELETE)
    @DeleteMapping("/{attrIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] attrIds) {
        return toAjax(ecomActivityAttributesService.deleteWithValidByIds(List.of(attrIds), true));
    }
}
