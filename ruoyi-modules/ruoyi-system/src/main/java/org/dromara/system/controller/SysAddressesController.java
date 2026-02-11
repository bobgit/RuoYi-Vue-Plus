package org.dromara.system.controller;

import java.util.List;

import cn.dev33.satoken.annotation.SaIgnore;
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
import org.dromara.system.domain.vo.SysAddressesVo;
import org.dromara.system.domain.bo.SysAddressesBo;
import org.dromara.system.service.ISysAddressesService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 国际地址
 *
 * @author Lion Li
 * @date 2026-01-05
 */
@SaIgnore // 关键注解
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/addresses")
public class SysAddressesController extends BaseController {

    private final ISysAddressesService sysAddressesService;

    /**
     * 查询国际地址列表
     */
    @SaCheckPermission("system:addresses:list")
    @GetMapping("/list")
    public TableDataInfo<SysAddressesVo> list(SysAddressesBo bo, PageQuery pageQuery) {
        return sysAddressesService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出国际地址列表
     */
    @SaCheckPermission("system:addresses:export")
    @Log(title = "国际地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysAddressesBo bo, HttpServletResponse response) {
        List<SysAddressesVo> list = sysAddressesService.queryList(bo);
        ExcelUtil.exportExcel(list, "国际地址", SysAddressesVo.class, response);
    }

    /**
     * 获取国际地址详细信息
     *
     * @param addressId 主键
     */
    @SaCheckPermission("system:addresses:query")
    @GetMapping("/{addressId}")
    public R<SysAddressesVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long addressId) {
        return R.ok(sysAddressesService.queryById(addressId));
    }

    /**
     * 新增国际地址
     */
    @SaCheckPermission("system:addresses:add")
    @Log(title = "国际地址", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysAddressesBo bo) {
        return toAjax(sysAddressesService.insertByBo(bo));
    }

    /**
     * 修改国际地址
     */
    @SaCheckPermission("system:addresses:edit")
    @Log(title = "国际地址", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysAddressesBo bo) {
        return toAjax(sysAddressesService.updateByBo(bo));
    }

    /**
     * 删除国际地址
     *
     * @param addressIds 主键串
     */
    @SaCheckPermission("system:addresses:remove")
    @Log(title = "国际地址", businessType = BusinessType.DELETE)
    @DeleteMapping("/{addressIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] addressIds) {
        return toAjax(sysAddressesService.deleteWithValidByIds(List.of(addressIds), true));
    }
}
