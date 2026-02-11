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
import org.dromara.ecom.domain.vo.EcomProductOrgVo;
import org.dromara.ecom.domain.bo.EcomProductOrgBo;
import org.dromara.ecom.service.IEcomProductOrgService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 机构组织商品
 *
 * @author Bob Ok
 * @date 2026-01-27
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/productOrg")
public class EcomProductOrgController extends BaseController {

    private final IEcomProductOrgService ecomProductOrgService;

    /**
     * 查询机构组织商品列表
     */
    @SaCheckPermission("ecom:productOrg:list")
    @GetMapping("/list")
    public TableDataInfo<EcomProductOrgVo> list(EcomProductOrgBo bo, PageQuery pageQuery) {
        return ecomProductOrgService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出机构组织商品列表
     */
    @SaCheckPermission("ecom:productOrg:export")
    @Log(title = "机构组织商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomProductOrgBo bo, HttpServletResponse response) {
        List<EcomProductOrgVo> list = ecomProductOrgService.queryList(bo);
        ExcelUtil.exportExcel(list, "机构组织商品", EcomProductOrgVo.class, response);
    }

    /**
     * 获取机构组织商品详细信息
     *
     * @param orgProductId 主键
     */
    @SaCheckPermission("ecom:productOrg:query")
    @GetMapping("/{orgProductId}")
    public R<EcomProductOrgVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long orgProductId) {
        return R.ok(ecomProductOrgService.queryById(orgProductId));
    }

    /**
     * 新增机构组织商品
     */
    @SaCheckPermission("ecom:productOrg:add")
    @Log(title = "机构组织商品", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomProductOrgBo bo) {
        return toAjax(ecomProductOrgService.insertByBo(bo));
    }

    /**
     * 修改机构组织商品
     */
    @SaCheckPermission("ecom:productOrg:edit")
    @Log(title = "机构组织商品", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomProductOrgBo bo) {
        return toAjax(ecomProductOrgService.updateByBo(bo));
    }

    /**
     * 删除机构组织商品
     *
     * @param orgProductIds 主键串
     */
    @SaCheckPermission("ecom:productOrg:remove")
    @Log(title = "机构组织商品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orgProductIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] orgProductIds) {
        return toAjax(ecomProductOrgService.deleteWithValidByIds(List.of(orgProductIds), true));
    }
}
