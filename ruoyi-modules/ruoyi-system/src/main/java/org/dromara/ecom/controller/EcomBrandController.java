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
import org.dromara.ecom.domain.vo.EcomBrandVo;
import org.dromara.ecom.domain.bo.EcomBrandBo;
import org.dromara.ecom.service.IEcomBrandService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 商品品牌
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/brand")
public class EcomBrandController extends BaseController {

    private final IEcomBrandService ecomBrandService;

    /**
     * 查询商品品牌列表
     */
    @SaCheckPermission("ecom:brand:list")
    @GetMapping("/list")
    public TableDataInfo<EcomBrandVo> list(EcomBrandBo bo, PageQuery pageQuery) {
        return ecomBrandService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品品牌列表
     */
    @SaCheckPermission("ecom:brand:export")
    @Log(title = "商品品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomBrandBo bo, HttpServletResponse response) {
        List<EcomBrandVo> list = ecomBrandService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品品牌", EcomBrandVo.class, response);
    }

    /**
     * 获取商品品牌详细信息
     *
     * @param brandId 主键
     */
    @SaCheckPermission("ecom:brand:query")
    @GetMapping("/{brandId}")
    public R<EcomBrandVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long brandId) {
        return R.ok(ecomBrandService.queryById(brandId));
    }

    /**
     * 新增商品品牌
     */
    @SaCheckPermission("ecom:brand:add")
    @Log(title = "商品品牌", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomBrandBo bo) {
        return toAjax(ecomBrandService.insertByBo(bo));
    }

    /**
     * 修改商品品牌
     */
    @SaCheckPermission("ecom:brand:edit")
    @Log(title = "商品品牌", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomBrandBo bo) {
        return toAjax(ecomBrandService.updateByBo(bo));
    }

    /**
     * 删除商品品牌
     *
     * @param brandIds 主键串
     */
    @SaCheckPermission("ecom:brand:remove")
    @Log(title = "商品品牌", businessType = BusinessType.DELETE)
    @DeleteMapping("/{brandIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] brandIds) {
        return toAjax(ecomBrandService.deleteWithValidByIds(List.of(brandIds), true));
    }
}
