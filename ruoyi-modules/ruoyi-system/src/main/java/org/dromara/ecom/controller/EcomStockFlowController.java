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
import org.dromara.ecom.domain.vo.EcomStockFlowVo;
import org.dromara.ecom.domain.bo.EcomStockFlowBo;
import org.dromara.ecom.service.IEcomStockFlowService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 库存流水
 *
 * @author Bob Bai
 * @date 2026-02-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/stockFlow")
public class EcomStockFlowController extends BaseController {

    private final IEcomStockFlowService ecomStockFlowService;

    /**
     * 查询库存流水列表
     */
    @SaCheckPermission("ecom:stockFlow:list")
    @GetMapping("/list")
    public TableDataInfo<EcomStockFlowVo> list(EcomStockFlowBo bo, PageQuery pageQuery) {
        return ecomStockFlowService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出库存流水列表
     */
    @SaCheckPermission("ecom:stockFlow:export")
    @Log(title = "库存流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomStockFlowBo bo, HttpServletResponse response) {
        List<EcomStockFlowVo> list = ecomStockFlowService.queryList(bo);
        ExcelUtil.exportExcel(list, "库存流水", EcomStockFlowVo.class, response);
    }

    /**
     * 获取库存流水详细信息
     *
     * @param stockFlowId 主键
     */
    @SaCheckPermission("ecom:stockFlow:query")
    @GetMapping("/{stockFlowId}")
    public R<EcomStockFlowVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long stockFlowId) {
        return R.ok(ecomStockFlowService.queryById(stockFlowId));
    }

    /**
     * 新增库存流水
     */
    @SaCheckPermission("ecom:stockFlow:add")
    @Log(title = "库存流水", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomStockFlowBo bo) {
        return toAjax(ecomStockFlowService.insertByBo(bo));
    }

    /**
     * 修改库存流水
     */
    @SaCheckPermission("ecom:stockFlow:edit")
    @Log(title = "库存流水", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomStockFlowBo bo) {
        return toAjax(ecomStockFlowService.updateByBo(bo));
    }

    /**
     * 删除库存流水
     *
     * @param stockFlowIds 主键串
     */
    @SaCheckPermission("ecom:stockFlow:remove")
    @Log(title = "库存流水", businessType = BusinessType.DELETE)
    @DeleteMapping("/{stockFlowIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] stockFlowIds) {
        return toAjax(ecomStockFlowService.deleteWithValidByIds(List.of(stockFlowIds), true));
    }
}
