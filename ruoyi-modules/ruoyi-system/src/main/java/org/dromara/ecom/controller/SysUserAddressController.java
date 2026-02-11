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
import org.dromara.ecom.domain.vo.SysUserAddressVo;
import org.dromara.ecom.domain.bo.SysUserAddressBo;
import org.dromara.ecom.service.ISysUserAddressService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 用户地址关联
 *
 * @author Bob Ok
 * @date 2026-02-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/userAddress")
public class SysUserAddressController extends BaseController {

    private final ISysUserAddressService sysUserAddressService;

    /**
     * 查询用户地址关联列表
     */
    @SaCheckPermission("ecom:userAddress:list")
    @GetMapping("/list")
    public TableDataInfo<SysUserAddressVo> list(SysUserAddressBo bo, PageQuery pageQuery) {
        return sysUserAddressService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户地址关联列表
     */
    @SaCheckPermission("ecom:userAddress:export")
    @Log(title = "用户地址关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysUserAddressBo bo, HttpServletResponse response) {
        List<SysUserAddressVo> list = sysUserAddressService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户地址关联", SysUserAddressVo.class, response);
    }

    /**
     * 获取用户地址关联详细信息
     *
     * @param userAddressId 主键
     */
    @SaCheckPermission("ecom:userAddress:query")
    @GetMapping("/{userAddressId}")
    public R<SysUserAddressVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long userAddressId) {
        return R.ok(sysUserAddressService.queryById(userAddressId));
    }

    /**
     * 新增用户地址关联
     */
    @SaCheckPermission("ecom:userAddress:add")
    @Log(title = "用户地址关联", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysUserAddressBo bo) {
        return toAjax(sysUserAddressService.insertByBo(bo));
    }

    /**
     * 修改用户地址关联
     */
    @SaCheckPermission("ecom:userAddress:edit")
    @Log(title = "用户地址关联", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysUserAddressBo bo) {
        return toAjax(sysUserAddressService.updateByBo(bo));
    }

    /**
     * 删除用户地址关联
     *
     * @param userAddressIds 主键串
     */
    @SaCheckPermission("ecom:userAddress:remove")
    @Log(title = "用户地址关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userAddressIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] userAddressIds) {
        return toAjax(sysUserAddressService.deleteWithValidByIds(List.of(userAddressIds), true));
    }
}
