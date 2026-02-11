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
import org.dromara.ecom.domain.vo.EcomOrderDeliveryLogVo;
import org.dromara.ecom.domain.bo.EcomOrderDeliveryLogBo;
import org.dromara.ecom.service.IEcomOrderDeliveryLogService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 配送状态变更日志
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/orderDeliveryLog")
public class EcomOrderDeliveryLogController extends BaseController {

    private final IEcomOrderDeliveryLogService ecomOrderDeliveryLogService;

    /**
     * 查询配送状态变更日志列表
     */
    @SaCheckPermission("ecom:orderDeliveryLog:list")
    @GetMapping("/list")
    public TableDataInfo<EcomOrderDeliveryLogVo> list(EcomOrderDeliveryLogBo bo, PageQuery pageQuery) {
        return ecomOrderDeliveryLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出配送状态变更日志列表
     */
    @SaCheckPermission("ecom:orderDeliveryLog:export")
    @Log(title = "配送状态变更日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomOrderDeliveryLogBo bo, HttpServletResponse response) {
        List<EcomOrderDeliveryLogVo> list = ecomOrderDeliveryLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "配送状态变更日志", EcomOrderDeliveryLogVo.class, response);
    }

    /**
     * 获取配送状态变更日志详细信息
     *
     * @param deliveryLogId 主键
     */
    @SaCheckPermission("ecom:orderDeliveryLog:query")
    @GetMapping("/{deliveryLogId}")
    public R<EcomOrderDeliveryLogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long deliveryLogId) {
        return R.ok(ecomOrderDeliveryLogService.queryById(deliveryLogId));
    }

    /**
     * 新增配送状态变更日志
     */
    @SaCheckPermission("ecom:orderDeliveryLog:add")
    @Log(title = "配送状态变更日志", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomOrderDeliveryLogBo bo) {
        return toAjax(ecomOrderDeliveryLogService.insertByBo(bo));
    }

    /**
     * 修改配送状态变更日志
     */
    @SaCheckPermission("ecom:orderDeliveryLog:edit")
    @Log(title = "配送状态变更日志", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomOrderDeliveryLogBo bo) {
        return toAjax(ecomOrderDeliveryLogService.updateByBo(bo));
    }

    /**
     * 删除配送状态变更日志
     *
     * @param deliveryLogIds 主键串
     */
    @SaCheckPermission("ecom:orderDeliveryLog:remove")
    @Log(title = "配送状态变更日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deliveryLogIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] deliveryLogIds) {
        return toAjax(ecomOrderDeliveryLogService.deleteWithValidByIds(List.of(deliveryLogIds), true));
    }
}
