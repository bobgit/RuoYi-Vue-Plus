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
import org.dromara.ecom.domain.vo.EcomI18nVo;
import org.dromara.ecom.domain.bo.EcomI18nBo;
import org.dromara.ecom.service.IEcomI18nService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 商品分类
 *
 * @author Bob Bai
 * @date 2026-02-06
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/i18n")
public class EcomI18nController extends BaseController {

    private final IEcomI18nService ecomI18nService;

    /**
     * 查询商品分类列表
     */
    @SaCheckPermission("ecom:i18n:list")
    @GetMapping("/list")
    public TableDataInfo<EcomI18nVo> list(EcomI18nBo bo, PageQuery pageQuery) {
        return ecomI18nService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品分类列表
     */
    @SaCheckPermission("ecom:i18n:export")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomI18nBo bo, HttpServletResponse response) {
        List<EcomI18nVo> list = ecomI18nService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品分类", EcomI18nVo.class, response);
    }

    /**
     * 获取商品分类详细信息
     *
     * @param i18nId 主键
     */
    @SaCheckPermission("ecom:i18n:query")
    @GetMapping("/{i18nId}")
    public R<EcomI18nVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long i18nId) {
        return R.ok(ecomI18nService.queryById(i18nId));
    }

    /**
     * 新增商品分类
     */
    @SaCheckPermission("ecom:i18n:add")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomI18nBo bo) {
        return toAjax(ecomI18nService.insertByBo(bo));
    }

    /**
     * 修改商品分类
     */
    @SaCheckPermission("ecom:i18n:edit")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomI18nBo bo) {
        return toAjax(ecomI18nService.updateByBo(bo));
    }

    /**
     * 删除商品分类
     *
     * @param i18nIds 主键串
     */
    @SaCheckPermission("ecom:i18n:remove")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{i18nIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] i18nIds) {
        return toAjax(ecomI18nService.deleteWithValidByIds(List.of(i18nIds), true));
    }
}
