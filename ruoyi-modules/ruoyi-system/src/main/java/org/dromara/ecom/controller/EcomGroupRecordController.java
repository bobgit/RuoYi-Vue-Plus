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
import org.dromara.ecom.domain.vo.EcomGroupRecordVo;
import org.dromara.ecom.domain.bo.EcomGroupRecordBo;
import org.dromara.ecom.service.IEcomGroupRecordService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 团购参团记录
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/groupRecord")
public class EcomGroupRecordController extends BaseController {

    private final IEcomGroupRecordService ecomGroupRecordService;

    /**
     * 查询团购参团记录列表
     */
    @SaCheckPermission("ecom:groupRecord:list")
    @GetMapping("/list")
    public TableDataInfo<EcomGroupRecordVo> list(EcomGroupRecordBo bo, PageQuery pageQuery) {
        return ecomGroupRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出团购参团记录列表
     */
    @SaCheckPermission("ecom:groupRecord:export")
    @Log(title = "团购参团记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomGroupRecordBo bo, HttpServletResponse response) {
        List<EcomGroupRecordVo> list = ecomGroupRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "团购参团记录", EcomGroupRecordVo.class, response);
    }

    /**
     * 获取团购参团记录详细信息
     *
     * @param recordId 主键
     */
    @SaCheckPermission("ecom:groupRecord:query")
    @GetMapping("/{recordId}")
    public R<EcomGroupRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long recordId) {
        return R.ok(ecomGroupRecordService.queryById(recordId));
    }

    /**
     * 新增团购参团记录
     */
    @SaCheckPermission("ecom:groupRecord:add")
    @Log(title = "团购参团记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomGroupRecordBo bo) {
        return toAjax(ecomGroupRecordService.insertByBo(bo));
    }

    /**
     * 修改团购参团记录
     */
    @SaCheckPermission("ecom:groupRecord:edit")
    @Log(title = "团购参团记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomGroupRecordBo bo) {
        return toAjax(ecomGroupRecordService.updateByBo(bo));
    }

    /**
     * 删除团购参团记录
     *
     * @param recordIds 主键串
     */
    @SaCheckPermission("ecom:groupRecord:remove")
    @Log(title = "团购参团记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] recordIds) {
        return toAjax(ecomGroupRecordService.deleteWithValidByIds(List.of(recordIds), true));
    }
}
