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
import org.dromara.ecom.domain.vo.EcomProductSkuAttributesVo;
import org.dromara.ecom.domain.bo.EcomProductSkuAttributesBo;
import org.dromara.ecom.service.IEcomProductSkuAttributesService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 商品SKU属性
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/productSkuAttributes")
public class EcomProductSkuAttributesController extends BaseController {

    private final IEcomProductSkuAttributesService ecomProductSkuAttributesService;

    /**
     * 查询商品SKU属性列表
     */
    @SaCheckPermission("ecom:productSkuAttributes:list")
    @GetMapping("/list")
    public TableDataInfo<EcomProductSkuAttributesVo> list(EcomProductSkuAttributesBo bo, PageQuery pageQuery) {
        return ecomProductSkuAttributesService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品SKU属性列表
     */
    @SaCheckPermission("ecom:productSkuAttributes:export")
    @Log(title = "商品SKU属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomProductSkuAttributesBo bo, HttpServletResponse response) {
        List<EcomProductSkuAttributesVo> list = ecomProductSkuAttributesService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品SKU属性", EcomProductSkuAttributesVo.class, response);
    }

    /**
     * 获取商品SKU属性详细信息
     *
     * @param skuAttrId 主键
     */
    @SaCheckPermission("ecom:productSkuAttributes:query")
    @GetMapping("/{skuAttrId}")
    public R<EcomProductSkuAttributesVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long skuAttrId) {
        return R.ok(ecomProductSkuAttributesService.queryById(skuAttrId));
    }

    /**
     * 新增商品SKU属性
     */
    @SaCheckPermission("ecom:productSkuAttributes:add")
    @Log(title = "商品SKU属性", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomProductSkuAttributesBo bo) {
        return toAjax(ecomProductSkuAttributesService.insertByBo(bo));
    }

    /**
     * 修改商品SKU属性
     */
    @SaCheckPermission("ecom:productSkuAttributes:edit")
    @Log(title = "商品SKU属性", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomProductSkuAttributesBo bo) {
        return toAjax(ecomProductSkuAttributesService.updateByBo(bo));
    }

    /**
     * 删除商品SKU属性
     *
     * @param skuAttrIds 主键串
     */
    @SaCheckPermission("ecom:productSkuAttributes:remove")
    @Log(title = "商品SKU属性", businessType = BusinessType.DELETE)
    @DeleteMapping("/{skuAttrIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] skuAttrIds) {
        return toAjax(ecomProductSkuAttributesService.deleteWithValidByIds(List.of(skuAttrIds), true));
    }
}
