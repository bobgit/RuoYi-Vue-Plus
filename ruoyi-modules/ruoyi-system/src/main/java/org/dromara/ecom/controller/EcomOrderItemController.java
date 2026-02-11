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
import org.dromara.ecom.domain.vo.EcomOrderItemVo;
import org.dromara.ecom.domain.bo.EcomOrderItemBo;
import org.dromara.ecom.service.IEcomOrderItemService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 订单明细
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/orderItem")
public class EcomOrderItemController extends BaseController {

    private final IEcomOrderItemService ecomOrderItemService;

    /**
     * 查询订单明细列表
     */
    @SaCheckPermission("ecom:orderItem:list")
    @GetMapping("/list")
    public TableDataInfo<EcomOrderItemVo> list(EcomOrderItemBo bo, PageQuery pageQuery) {
        return ecomOrderItemService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出订单明细列表
     */
    @SaCheckPermission("ecom:orderItem:export")
    @Log(title = "订单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomOrderItemBo bo, HttpServletResponse response) {
        List<EcomOrderItemVo> list = ecomOrderItemService.queryList(bo);
        ExcelUtil.exportExcel(list, "订单明细", EcomOrderItemVo.class, response);
    }

    /**
     * 获取订单明细详细信息
     *
     * @param itemId 主键
     */
    @SaCheckPermission("ecom:orderItem:query")
    @GetMapping("/{itemId}")
    public R<EcomOrderItemVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long itemId) {
        return R.ok(ecomOrderItemService.queryById(itemId));
    }

    /**
     * 新增订单明细
     */
    @SaCheckPermission("ecom:orderItem:add")
    @Log(title = "订单明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomOrderItemBo bo) {
        return toAjax(ecomOrderItemService.insertByBo(bo));
    }

    /**
     * 修改订单明细
     */
    @SaCheckPermission("ecom:orderItem:edit")
    @Log(title = "订单明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomOrderItemBo bo) {
        return toAjax(ecomOrderItemService.updateByBo(bo));
    }

    /**
     * 删除订单明细
     *
     * @param itemIds 主键串
     */
    @SaCheckPermission("ecom:orderItem:remove")
    @Log(title = "订单明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{itemIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] itemIds) {
        return toAjax(ecomOrderItemService.deleteWithValidByIds(List.of(itemIds), true));
    }
}
