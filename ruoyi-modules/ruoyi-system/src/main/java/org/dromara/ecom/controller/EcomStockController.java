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
import org.dromara.ecom.domain.vo.EcomStockVo;
import org.dromara.ecom.domain.bo.EcomStockBo;
import org.dromara.ecom.service.IEcomStockService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 通用库存
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/stock")
public class EcomStockController extends BaseController {

    private final IEcomStockService ecomStockService;

    /**
     * 查询通用库存列表
     */
    @SaCheckPermission("ecom:stock:list")
    @GetMapping("/list")
    public TableDataInfo<EcomStockVo> list(EcomStockBo bo, PageQuery pageQuery) {
        return ecomStockService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出通用库存列表
     */
    @SaCheckPermission("ecom:stock:export")
    @Log(title = "通用库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomStockBo bo, HttpServletResponse response) {
        List<EcomStockVo> list = ecomStockService.queryList(bo);
        ExcelUtil.exportExcel(list, "通用库存", EcomStockVo.class, response);
    }

    /**
     * 获取通用库存详细信息
     *
     * @param stockId 主键
     */
    @SaCheckPermission("ecom:stock:query")
    @GetMapping("/{stockId}")
    public R<EcomStockVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long stockId) {
        return R.ok(ecomStockService.queryById(stockId));
    }

    /**
     * 新增通用库存
     */
    @SaCheckPermission("ecom:stock:add")
    @Log(title = "通用库存", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomStockBo bo) {
        return toAjax(ecomStockService.insertByBo(bo));
    }

    /**
     * 修改通用库存
     */
    @SaCheckPermission("ecom:stock:edit")
    @Log(title = "通用库存", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomStockBo bo) {
        return toAjax(ecomStockService.updateByBo(bo));
    }

    /**
     * 删除通用库存
     *
     * @param stockIds 主键串
     */
    @SaCheckPermission("ecom:stock:remove")
    @Log(title = "通用库存", businessType = BusinessType.DELETE)
    @DeleteMapping("/{stockIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] stockIds) {
        return toAjax(ecomStockService.deleteWithValidByIds(List.of(stockIds), true));
    }
}
