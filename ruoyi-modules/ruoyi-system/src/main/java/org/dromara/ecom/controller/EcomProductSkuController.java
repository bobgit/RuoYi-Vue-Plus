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
import org.dromara.ecom.domain.vo.EcomProductSkuVo;
import org.dromara.ecom.domain.bo.EcomProductSkuBo;
import org.dromara.ecom.service.IEcomProductSkuService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * SKU库存单元
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/productSku")
public class EcomProductSkuController extends BaseController {

    private final IEcomProductSkuService ecomProductSkuService;

    /**
     * 查询SKU库存单元列表
     */
    @SaCheckPermission("ecom:productSku:list")
    @GetMapping("/list")
    public TableDataInfo<EcomProductSkuVo> list(EcomProductSkuBo bo, PageQuery pageQuery) {
        return ecomProductSkuService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出SKU库存单元列表
     */
    @SaCheckPermission("ecom:productSku:export")
    @Log(title = "SKU库存单元", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomProductSkuBo bo, HttpServletResponse response) {
        List<EcomProductSkuVo> list = ecomProductSkuService.queryList(bo);
        ExcelUtil.exportExcel(list, "SKU库存单元", EcomProductSkuVo.class, response);
    }

    /**
     * 获取SKU库存单元详细信息
     *
     * @param skuId 主键
     */
    @SaCheckPermission("ecom:productSku:query")
    @GetMapping("/{skuId}")
    public R<EcomProductSkuVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long skuId) {
        return R.ok(ecomProductSkuService.queryById(skuId));
    }

    /**
     * 新增SKU库存单元
     */
    @SaCheckPermission("ecom:productSku:add")
    @Log(title = "SKU库存单元", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomProductSkuBo bo) {
        return toAjax(ecomProductSkuService.insertByBo(bo));
    }

    /**
     * 修改SKU库存单元
     */
    @SaCheckPermission("ecom:productSku:edit")
    @Log(title = "SKU库存单元", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomProductSkuBo bo) {
        return toAjax(ecomProductSkuService.updateByBo(bo));
    }

    /**
     * 删除SKU库存单元
     *
     * @param skuIds 主键串
     */
    @SaCheckPermission("ecom:productSku:remove")
    @Log(title = "SKU库存单元", businessType = BusinessType.DELETE)
    @DeleteMapping("/{skuIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] skuIds) {
        return toAjax(ecomProductSkuService.deleteWithValidByIds(List.of(skuIds), true));
    }
}
