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
import org.dromara.ecom.domain.vo.EcomActivityVo;
import org.dromara.ecom.domain.bo.EcomActivityBo;
import org.dromara.ecom.service.IEcomActivityService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 活动
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ecom/activity")
public class EcomActivityController extends BaseController {

    private final IEcomActivityService ecomActivityService;

    /**
     * 查询活动列表
     */
    @SaCheckPermission("ecom:activity:list")
    @GetMapping("/list")
    public TableDataInfo<EcomActivityVo> list(EcomActivityBo bo, PageQuery pageQuery) {
        return ecomActivityService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出活动列表
     */
    @SaCheckPermission("ecom:activity:export")
    @Log(title = "活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(EcomActivityBo bo, HttpServletResponse response) {
        List<EcomActivityVo> list = ecomActivityService.queryList(bo);
        ExcelUtil.exportExcel(list, "活动", EcomActivityVo.class, response);
    }

    /**
     * 获取活动详细信息
     *
     * @param activityId 主键
     */
    @SaCheckPermission("ecom:activity:query")
    @GetMapping("/{activityId}")
    public R<EcomActivityVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long activityId) {
        return R.ok(ecomActivityService.queryById(activityId));
    }

    /**
     * 新增活动
     */
    @SaCheckPermission("ecom:activity:add")
    @Log(title = "活动", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody EcomActivityBo bo) {
        return toAjax(ecomActivityService.insertByBo(bo));
    }

    /**
     * 修改活动
     */
    @SaCheckPermission("ecom:activity:edit")
    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody EcomActivityBo bo) {
        return toAjax(ecomActivityService.updateByBo(bo));
    }

    /**
     * 删除活动
     *
     * @param activityIds 主键串
     */
    @SaCheckPermission("ecom:activity:remove")
    @Log(title = "活动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{activityIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] activityIds) {
        return toAjax(ecomActivityService.deleteWithValidByIds(List.of(activityIds), true));
    }
}
