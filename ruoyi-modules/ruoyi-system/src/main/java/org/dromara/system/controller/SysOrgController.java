package org.dromara.system.controller;

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
import org.dromara.system.domain.vo.SysOrgVo;
import org.dromara.system.domain.bo.SysOrgBo;
import org.dromara.system.service.ISysOrgService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 机构组织公司
 *
 * @author Bob Ok
 * @date 2026-02-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/org")
public class SysOrgController extends BaseController {

    private final ISysOrgService sysOrgService;

    /**
     * 查询机构组织公司列表
     */
    @SaCheckPermission("system:org:list")
    @GetMapping("/list")
    public TableDataInfo<SysOrgVo> list(SysOrgBo bo, PageQuery pageQuery) {
        return sysOrgService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出机构组织公司列表
     */
    @SaCheckPermission("system:org:export")
    @Log(title = "机构组织公司", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysOrgBo bo, HttpServletResponse response) {
        List<SysOrgVo> list = sysOrgService.queryList(bo);
        ExcelUtil.exportExcel(list, "机构组织公司", SysOrgVo.class, response);
    }

    /**
     * 获取机构组织公司详细信息
     *
     * @param orgId 主键
     */
    @SaCheckPermission("system:org:query")
    @GetMapping("/{orgId}")
    public R<SysOrgVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long orgId) {
        return R.ok(sysOrgService.queryById(orgId));
    }

    /**
     * 新增机构组织公司
     */
    @SaCheckPermission("system:org:add")
    @Log(title = "机构组织公司", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysOrgBo bo) {
        return toAjax(sysOrgService.insertByBo(bo));
    }

    /**
     * 修改机构组织公司
     */
    @SaCheckPermission("system:org:edit")
    @Log(title = "机构组织公司", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysOrgBo bo) {
        return toAjax(sysOrgService.updateByBo(bo));
    }

    /**
     * 删除机构组织公司
     *
     * @param orgIds 主键串
     */
    @SaCheckPermission("system:org:remove")
    @Log(title = "机构组织公司", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orgIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] orgIds) {
        return toAjax(sysOrgService.deleteWithValidByIds(List.of(orgIds), true));
    }
}
