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
import org.dromara.ecom.domain.vo.EcomOrderTicketVo;
import org.dromara.ecom.domain.bo.EcomOrderTicketBo;
import org.dromara.ecom.service.IEcomOrderTicketService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 门票/服务专用
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/orderTicket")
public class EcomOrderTicketController extends BaseController {

    private final IEcomOrderTicketService ecomOrderTicketService;

    /**
     * 查询门票/服务专用列表
     */
    @SaCheckPermission("ecom:orderTicket:list")
    @GetMapping("/list")
    public TableDataInfo<EcomOrderTicketVo> list(EcomOrderTicketBo bo, PageQuery pageQuery) {
        return ecomOrderTicketService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出门票/服务专用列表
     */
    @SaCheckPermission("ecom:orderTicket:export")
    @Log(title = "门票/服务专用", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomOrderTicketBo bo, HttpServletResponse response) {
        List<EcomOrderTicketVo> list = ecomOrderTicketService.queryList(bo);
        ExcelUtil.exportExcel(list, "门票/服务专用", EcomOrderTicketVo.class, response);
    }

    /**
     * 获取门票/服务专用详细信息
     *
     * @param ticketId 主键
     */
    @SaCheckPermission("ecom:orderTicket:query")
    @GetMapping("/{ticketId}")
    public R<EcomOrderTicketVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long ticketId) {
        return R.ok(ecomOrderTicketService.queryById(ticketId));
    }

    /**
     * 新增门票/服务专用
     */
    @SaCheckPermission("ecom:orderTicket:add")
    @Log(title = "门票/服务专用", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomOrderTicketBo bo) {
        return toAjax(ecomOrderTicketService.insertByBo(bo));
    }

    /**
     * 修改门票/服务专用
     */
    @SaCheckPermission("ecom:orderTicket:edit")
    @Log(title = "门票/服务专用", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomOrderTicketBo bo) {
        return toAjax(ecomOrderTicketService.updateByBo(bo));
    }

    /**
     * 删除门票/服务专用
     *
     * @param ticketIds 主键串
     */
    @SaCheckPermission("ecom:orderTicket:remove")
    @Log(title = "门票/服务专用", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ticketIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ticketIds) {
        return toAjax(ecomOrderTicketService.deleteWithValidByIds(List.of(ticketIds), true));
    }
}
