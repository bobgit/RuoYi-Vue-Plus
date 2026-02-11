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
import org.dromara.ecom.domain.vo.EcomOrderVo;
import org.dromara.ecom.domain.bo.EcomOrderBo;
import org.dromara.ecom.service.IEcomOrderService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 订单
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/order")
public class EcomOrderController extends BaseController {

    private final IEcomOrderService ecomOrderService;

    /**
     * 查询订单列表
     */
    @SaCheckPermission("ecom:order:list")
    @GetMapping("/list")
    public TableDataInfo<EcomOrderVo> list(EcomOrderBo bo, PageQuery pageQuery) {
        return ecomOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出订单列表
     */
    @SaCheckPermission("ecom:order:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomOrderBo bo, HttpServletResponse response) {
        List<EcomOrderVo> list = ecomOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "订单", EcomOrderVo.class, response);
    }

    /**
     * 获取订单详细信息
     *
     * @param orderId 主键
     */
    @SaCheckPermission("ecom:order:query")
    @GetMapping("/{orderId}")
    public R<EcomOrderVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long orderId) {
        return R.ok(ecomOrderService.queryById(orderId));
    }

    /**
     * 新增订单
     */
    @SaCheckPermission("ecom:order:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomOrderBo bo) {
        return toAjax(ecomOrderService.insertByBo(bo));
    }

    /**
     * 修改订单
     */
    @SaCheckPermission("ecom:order:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomOrderBo bo) {
        return toAjax(ecomOrderService.updateByBo(bo));
    }

    /**
     * 删除订单
     *
     * @param orderIds 主键串
     */
    @SaCheckPermission("ecom:order:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] orderIds) {
        return toAjax(ecomOrderService.deleteWithValidByIds(List.of(orderIds), true));
    }
}
