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
import org.dromara.ecom.domain.vo.EcomProductActivityVo;
import org.dromara.ecom.domain.bo.EcomProductActivityBo;
import org.dromara.ecom.service.IEcomProductActivityService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 活动商品关联
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/productActivity")
public class EcomProductActivityController extends BaseController {

    private final IEcomProductActivityService ecomProductActivityService;

    /**
     * 查询活动商品关联列表
     */
    @SaCheckPermission("ecom:productActivity:list")
    @GetMapping("/list")
    public TableDataInfo<EcomProductActivityVo> list(EcomProductActivityBo bo, PageQuery pageQuery) {
        return ecomProductActivityService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出活动商品关联列表
     */
    @SaCheckPermission("ecom:productActivity:export")
    @Log(title = "活动商品关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomProductActivityBo bo, HttpServletResponse response) {
        List<EcomProductActivityVo> list = ecomProductActivityService.queryList(bo);
        ExcelUtil.exportExcel(list, "活动商品关联", EcomProductActivityVo.class, response);
    }

    /**
     * 获取活动商品关联详细信息
     *
     * @param activityProductId 主键
     */
    @SaCheckPermission("ecom:productActivity:query")
    @GetMapping("/{activityProductId}")
    public R<EcomProductActivityVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long activityProductId) {
        return R.ok(ecomProductActivityService.queryById(activityProductId));
    }

    /**
     * 新增活动商品关联
     */
    @SaCheckPermission("ecom:productActivity:add")
    @Log(title = "活动商品关联", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomProductActivityBo bo) {
        return toAjax(ecomProductActivityService.insertByBo(bo));
    }

    /**
     * 修改活动商品关联
     */
    @SaCheckPermission("ecom:productActivity:edit")
    @Log(title = "活动商品关联", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomProductActivityBo bo) {
        return toAjax(ecomProductActivityService.updateByBo(bo));
    }

    /**
     * 删除活动商品关联
     *
     * @param activityProductIds 主键串
     */
    @SaCheckPermission("ecom:productActivity:remove")
    @Log(title = "活动商品关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityProductIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] activityProductIds) {
        return toAjax(ecomProductActivityService.deleteWithValidByIds(List.of(activityProductIds), true));
    }
}
