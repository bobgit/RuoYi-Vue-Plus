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
import org.dromara.ecom.domain.vo.EcomProductSpuVo;
import org.dromara.ecom.domain.bo.EcomProductSpuBo;
import org.dromara.ecom.service.IEcomProductSpuService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * SPU标准产品单元
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/productSpu")
public class EcomProductSpuController extends BaseController {

    private final IEcomProductSpuService ecomProductSpuService;

    /**
     * 查询SPU标准产品单元列表
     */
    @SaCheckPermission("ecom:productSpu:list")
    @GetMapping("/list")
    public TableDataInfo<EcomProductSpuVo> list(EcomProductSpuBo bo, PageQuery pageQuery) {
        return ecomProductSpuService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出SPU标准产品单元列表
     */
    @SaCheckPermission("ecom:productSpu:export")
    @Log(title = "SPU标准产品单元", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomProductSpuBo bo, HttpServletResponse response) {
        List<EcomProductSpuVo> list = ecomProductSpuService.queryList(bo);
        ExcelUtil.exportExcel(list, "SPU标准产品单元", EcomProductSpuVo.class, response);
    }

    /**
     * 获取SPU标准产品单元详细信息
     *
     * @param spuId 主键
     */
    @SaCheckPermission("ecom:productSpu:query")
    @GetMapping("/{spuId}")
    public R<EcomProductSpuVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long spuId) {
        return R.ok(ecomProductSpuService.queryById(spuId));
    }

    /**
     * 新增SPU标准产品单元
     */
    @SaCheckPermission("ecom:productSpu:add")
    @Log(title = "SPU标准产品单元", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomProductSpuBo bo) {
        return toAjax(ecomProductSpuService.insertByBo(bo));
    }

    /**
     * 修改SPU标准产品单元
     */
    @SaCheckPermission("ecom:productSpu:edit")
    @Log(title = "SPU标准产品单元", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomProductSpuBo bo) {
        return toAjax(ecomProductSpuService.updateByBo(bo));
    }

    /**
     * 删除SPU标准产品单元
     *
     * @param spuIds 主键串
     */
    @SaCheckPermission("ecom:productSpu:remove")
    @Log(title = "SPU标准产品单元", businessType = BusinessType.DELETE)
    @DeleteMapping("/{spuIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] spuIds) {
        return toAjax(ecomProductSpuService.deleteWithValidByIds(List.of(spuIds), true));
    }
}
