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
import org.dromara.ecom.domain.vo.EcomGroupMemberVo;
import org.dromara.ecom.domain.bo.EcomGroupMemberBo;
import org.dromara.ecom.service.IEcomGroupMemberService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 拼团团员
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/groupMember")
public class EcomGroupMemberController extends BaseController {

    private final IEcomGroupMemberService ecomGroupMemberService;

    /**
     * 查询拼团团员列表
     */
    @SaCheckPermission("ecom:groupMember:list")
    @GetMapping("/list")
    public TableDataInfo<EcomGroupMemberVo> list(EcomGroupMemberBo bo, PageQuery pageQuery) {
        return ecomGroupMemberService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出拼团团员列表
     */
    @SaCheckPermission("ecom:groupMember:export")
    @Log(title = "拼团团员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomGroupMemberBo bo, HttpServletResponse response) {
        List<EcomGroupMemberVo> list = ecomGroupMemberService.queryList(bo);
        ExcelUtil.exportExcel(list, "拼团团员", EcomGroupMemberVo.class, response);
    }

    /**
     * 获取拼团团员详细信息
     *
     * @param memberId 主键
     */
    @SaCheckPermission("ecom:groupMember:query")
    @GetMapping("/{memberId}")
    public R<EcomGroupMemberVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long memberId) {
        return R.ok(ecomGroupMemberService.queryById(memberId));
    }

    /**
     * 新增拼团团员
     */
    @SaCheckPermission("ecom:groupMember:add")
    @Log(title = "拼团团员", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomGroupMemberBo bo) {
        return toAjax(ecomGroupMemberService.insertByBo(bo));
    }

    /**
     * 修改拼团团员
     */
    @SaCheckPermission("ecom:groupMember:edit")
    @Log(title = "拼团团员", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomGroupMemberBo bo) {
        return toAjax(ecomGroupMemberService.updateByBo(bo));
    }

    /**
     * 删除拼团团员
     *
     * @param memberIds 主键串
     */
    @SaCheckPermission("ecom:groupMember:remove")
    @Log(title = "拼团团员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{memberIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] memberIds) {
        return toAjax(ecomGroupMemberService.deleteWithValidByIds(List.of(memberIds), true));
    }
}
