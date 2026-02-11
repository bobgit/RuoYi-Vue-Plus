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
import org.dromara.ecom.domain.vo.EcomFulfillmentTaskVo;
import org.dromara.ecom.domain.bo.EcomFulfillmentTaskBo;
import org.dromara.ecom.service.IEcomFulfillmentTaskService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 履约单
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/fulfillmentTask")
public class EcomFulfillmentTaskController extends BaseController {

    private final IEcomFulfillmentTaskService ecomFulfillmentTaskService;

    /**
     * 查询履约单列表
     */
    @SaCheckPermission("ecom:fulfillmentTask:list")
    @GetMapping("/list")
    public TableDataInfo<EcomFulfillmentTaskVo> list(EcomFulfillmentTaskBo bo, PageQuery pageQuery) {
        return ecomFulfillmentTaskService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出履约单列表
     */
    @SaCheckPermission("ecom:fulfillmentTask:export")
    @Log(title = "履约单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomFulfillmentTaskBo bo, HttpServletResponse response) {
        List<EcomFulfillmentTaskVo> list = ecomFulfillmentTaskService.queryList(bo);
        ExcelUtil.exportExcel(list, "履约单", EcomFulfillmentTaskVo.class, response);
    }

    /**
     * 获取履约单详细信息
     *
     * @param fulfillmentTaskId 主键
     */
    @SaCheckPermission("ecom:fulfillmentTask:query")
    @GetMapping("/{fulfillmentTaskId}")
    public R<EcomFulfillmentTaskVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long fulfillmentTaskId) {
        return R.ok(ecomFulfillmentTaskService.queryById(fulfillmentTaskId));
    }

    /**
     * 新增履约单
     */
    @SaCheckPermission("ecom:fulfillmentTask:add")
    @Log(title = "履约单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomFulfillmentTaskBo bo) {
        return toAjax(ecomFulfillmentTaskService.insertByBo(bo));
    }

    /**
     * 修改履约单
     */
    @SaCheckPermission("ecom:fulfillmentTask:edit")
    @Log(title = "履约单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomFulfillmentTaskBo bo) {
        return toAjax(ecomFulfillmentTaskService.updateByBo(bo));
    }

    /**
     * 删除履约单
     *
     * @param fulfillmentTaskIds 主键串
     */
    @SaCheckPermission("ecom:fulfillmentTask:remove")
    @Log(title = "履约单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fulfillmentTaskIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] fulfillmentTaskIds) {
        return toAjax(ecomFulfillmentTaskService.deleteWithValidByIds(List.of(fulfillmentTaskIds), true));
    }
}
