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
import org.dromara.ecom.domain.vo.EcomProductCategoryVo;
import org.dromara.ecom.domain.bo.EcomProductCategoryBo;
import org.dromara.ecom.service.IEcomProductCategoryService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 商品分类
 *
 * @author Bob Bai
 * @date 2026-01-27
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/productCategory")
public class EcomProductCategoryController extends BaseController {

    private final IEcomProductCategoryService ecomProductCategoryService;

    /**
     * 查询商品分类列表
     */
    @SaCheckPermission("ecom:productCategory:list")
    @GetMapping("/list")
    public TableDataInfo<EcomProductCategoryVo> list(EcomProductCategoryBo bo, PageQuery pageQuery) {
        return ecomProductCategoryService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品分类列表
     */
    @SaCheckPermission("ecom:productCategory:export")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomProductCategoryBo bo, HttpServletResponse response) {
        List<EcomProductCategoryVo> list = ecomProductCategoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品分类", EcomProductCategoryVo.class, response);
    }

    /**
     * 获取商品分类详细信息
     *
     * @param categoryId 主键
     */
    @SaCheckPermission("ecom:productCategory:query")
    @GetMapping("/{categoryId}")
    public R<EcomProductCategoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long categoryId) {
        return R.ok(ecomProductCategoryService.queryById(categoryId));
    }

    /**
     * 新增商品分类
     */
    @SaCheckPermission("ecom:productCategory:add")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomProductCategoryBo bo) {
        return toAjax(ecomProductCategoryService.insertByBo(bo));
    }

    /**
     * 修改商品分类
     */
    @SaCheckPermission("ecom:productCategory:edit")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomProductCategoryBo bo) {
        return toAjax(ecomProductCategoryService.updateByBo(bo));
    }

    /**
     * 删除商品分类
     *
     * @param categoryIds 主键串
     */
    @SaCheckPermission("ecom:productCategory:remove")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] categoryIds) {
        return toAjax(ecomProductCategoryService.deleteWithValidByIds(List.of(categoryIds), true));
    }
}
