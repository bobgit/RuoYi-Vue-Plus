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
import org.dromara.ecom.domain.vo.EcomOrderDeliveryVo;
import org.dromara.ecom.domain.bo.EcomOrderDeliveryBo;
import org.dromara.ecom.service.IEcomOrderDeliveryService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 订单配送专用
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/orderDelivery")
public class EcomOrderDeliveryController extends BaseController {

    private final IEcomOrderDeliveryService ecomOrderDeliveryService;

    /**
     * 查询订单配送专用列表
     */
    @SaCheckPermission("ecom:orderDelivery:list")
    @GetMapping("/list")
    public TableDataInfo<EcomOrderDeliveryVo> list(EcomOrderDeliveryBo bo, PageQuery pageQuery) {
        return ecomOrderDeliveryService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出订单配送专用列表
     */
    @SaCheckPermission("ecom:orderDelivery:export")
    @Log(title = "订单配送专用", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomOrderDeliveryBo bo, HttpServletResponse response) {
        List<EcomOrderDeliveryVo> list = ecomOrderDeliveryService.queryList(bo);
        ExcelUtil.exportExcel(list, "订单配送专用", EcomOrderDeliveryVo.class, response);
    }

    /**
     * 获取订单配送专用详细信息
     *
     * @param orderDeliveryId 主键
     */
    @SaCheckPermission("ecom:orderDelivery:query")
    @GetMapping("/{orderDeliveryId}")
    public R<EcomOrderDeliveryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long orderDeliveryId) {
        return R.ok(ecomOrderDeliveryService.queryById(orderDeliveryId));
    }

    /**
     * 新增订单配送专用
     */
    @SaCheckPermission("ecom:orderDelivery:add")
    @Log(title = "订单配送专用", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomOrderDeliveryBo bo) {
        return toAjax(ecomOrderDeliveryService.insertByBo(bo));
    }

    /**
     * 修改订单配送专用
     */
    @SaCheckPermission("ecom:orderDelivery:edit")
    @Log(title = "订单配送专用", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomOrderDeliveryBo bo) {
        return toAjax(ecomOrderDeliveryService.updateByBo(bo));
    }

    /**
     * 删除订单配送专用
     *
     * @param orderDeliveryIds 主键串
     */
    @SaCheckPermission("ecom:orderDelivery:remove")
    @Log(title = "订单配送专用", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderDeliveryIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] orderDeliveryIds) {
        return toAjax(ecomOrderDeliveryService.deleteWithValidByIds(List.of(orderDeliveryIds), true));
    }
}
