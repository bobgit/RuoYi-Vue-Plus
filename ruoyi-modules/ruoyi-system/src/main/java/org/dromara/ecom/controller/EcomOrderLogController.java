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
import org.dromara.ecom.domain.vo.EcomOrderLogVo;
import org.dromara.ecom.domain.bo.EcomOrderLogBo;
import org.dromara.ecom.service.IEcomOrderLogService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 订单变更动态日志
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/orderLog")
public class EcomOrderLogController extends BaseController {

    private final IEcomOrderLogService ecomOrderLogService;

    /**
     * 查询订单变更动态日志列表
     */
    @SaCheckPermission("ecom:orderLog:list")
    @GetMapping("/list")
    public TableDataInfo<EcomOrderLogVo> list(EcomOrderLogBo bo, PageQuery pageQuery) {
        return ecomOrderLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出订单变更动态日志列表
     */
    @SaCheckPermission("ecom:orderLog:export")
    @Log(title = "订单变更动态日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomOrderLogBo bo, HttpServletResponse response) {
        List<EcomOrderLogVo> list = ecomOrderLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "订单变更动态日志", EcomOrderLogVo.class, response);
    }

    /**
     * 获取订单变更动态日志详细信息
     *
     * @param orderLogId 主键
     */
    @SaCheckPermission("ecom:orderLog:query")
    @GetMapping("/{orderLogId}")
    public R<EcomOrderLogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long orderLogId) {
        return R.ok(ecomOrderLogService.queryById(orderLogId));
    }

    /**
     * 新增订单变更动态日志
     */
    @SaCheckPermission("ecom:orderLog:add")
    @Log(title = "订单变更动态日志", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomOrderLogBo bo) {
        return toAjax(ecomOrderLogService.insertByBo(bo));
    }

    /**
     * 修改订单变更动态日志
     */
    @SaCheckPermission("ecom:orderLog:edit")
    @Log(title = "订单变更动态日志", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomOrderLogBo bo) {
        return toAjax(ecomOrderLogService.updateByBo(bo));
    }

    /**
     * 删除订单变更动态日志
     *
     * @param orderLogIds 主键串
     */
    @SaCheckPermission("ecom:orderLog:remove")
    @Log(title = "订单变更动态日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderLogIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] orderLogIds) {
        return toAjax(ecomOrderLogService.deleteWithValidByIds(List.of(orderLogIds), true));
    }
}
