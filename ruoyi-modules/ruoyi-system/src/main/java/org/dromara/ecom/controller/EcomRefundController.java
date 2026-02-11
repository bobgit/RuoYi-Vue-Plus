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
import org.dromara.ecom.domain.vo.EcomRefundVo;
import org.dromara.ecom.domain.bo.EcomRefundBo;
import org.dromara.ecom.service.IEcomRefundService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 退款
 *
 * @author Bob Bai
 * @date 2026-02-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/refund")
public class EcomRefundController extends BaseController {

    private final IEcomRefundService ecomRefundService;

    /**
     * 查询退款列表
     */
    @SaCheckPermission("ecom:refund:list")
    @GetMapping("/list")
    public TableDataInfo<EcomRefundVo> list(EcomRefundBo bo, PageQuery pageQuery) {
        return ecomRefundService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出退款列表
     */
    @SaCheckPermission("ecom:refund:export")
    @Log(title = "退款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomRefundBo bo, HttpServletResponse response) {
        List<EcomRefundVo> list = ecomRefundService.queryList(bo);
        ExcelUtil.exportExcel(list, "退款", EcomRefundVo.class, response);
    }

    /**
     * 获取退款详细信息
     *
     * @param refundId 主键
     */
    @SaCheckPermission("ecom:refund:query")
    @GetMapping("/{refundId}")
    public R<EcomRefundVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long refundId) {
        return R.ok(ecomRefundService.queryById(refundId));
    }

    /**
     * 新增退款
     */
    @SaCheckPermission("ecom:refund:add")
    @Log(title = "退款", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomRefundBo bo) {
        return toAjax(ecomRefundService.insertByBo(bo));
    }

    /**
     * 修改退款
     */
    @SaCheckPermission("ecom:refund:edit")
    @Log(title = "退款", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomRefundBo bo) {
        return toAjax(ecomRefundService.updateByBo(bo));
    }

    /**
     * 删除退款
     *
     * @param refundIds 主键串
     */
    @SaCheckPermission("ecom:refund:remove")
    @Log(title = "退款", businessType = BusinessType.DELETE)
    @DeleteMapping("/{refundIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] refundIds) {
        return toAjax(ecomRefundService.deleteWithValidByIds(List.of(refundIds), true));
    }
}
