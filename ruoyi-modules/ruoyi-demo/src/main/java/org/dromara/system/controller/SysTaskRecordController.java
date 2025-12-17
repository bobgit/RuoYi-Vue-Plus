package org.dromara.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.system.service.ApiService;
import org.dromara.system.service.YourRecordService;
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
import org.dromara.system.domain.vo.SysTaskRecordVo;
import org.dromara.system.domain.bo.SysTaskRecordBo;
import org.dromara.system.service.ISysTaskRecordService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 系统任务状态记录机器
 *
 * @author Lion Li
 * @date 2025-12-11
 */
@SaIgnore
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/taskRecord")
public class SysTaskRecordController extends BaseController {

    private final ISysTaskRecordService sysTaskRecordService;
    private final YourRecordService service;
    private final ApiService apiService;

    @GetMapping("/createBatch")
    public R<String> createBatch(){
        String taskId = UUID.randomUUID().toString();
        // 把任务丢到内存队列（或 Redis/RabbitMQ）
//        service.doBatch(taskId);
        service.doBatchBean(taskId);
        return R.ok("目前的结果");
    }

    @GetMapping("/apiService")
    public R<String> apiService(String data) {
        String r = apiService.fetchData("http://localhost:8080/system/taskRecord/getPdfTest"); // 测试 5xx 重试
        return R.ok(r);
    }
    @GetMapping("/test")
    public R<Void> test(String data) {

        service.createTask(data);


        return R.ok("操作成功");
    }



    @GetMapping("/getPdfTest")
    public List<String> getPdfTest(String data) throws InterruptedException {
        List<String> list = new ArrayList<>();
        service.createTask(data);
        long sleepLong = RandomUtil.randomLong(5000,15000);
        Thread.sleep(sleepLong);
        String random = RandomUtil.randomString(8);
        String url = "https://www.sogou.com/pdfUrlNo:"+random;
//        System.out.println("休息处理修正时间为："+sleepLong+" 地址为：https://www.sogou.com/");
        list.add(url);
        return list;
    }
    /**
     * 发布消息
     *
     * @param key   通道Key
     * @param value 发送内容
     */
    @GetMapping("/pub")
    public R<Void> pub(String key, String value) {
        RedisUtils.publish(key, value, consumer -> {
            System.out.println("发布通道 => " + key + ", 发送值 => " + value);
        });
        return R.ok("操作成功");
    }

    /**
     * 订阅消息
     *
     * @param key 通道Key
     */
    @GetMapping("/sub")
    public R<Void> sub(String key) {
        RedisUtils.subscribe(key, String.class, msg -> {
            System.out.println("订阅通道 => " + key + ", 接收值 => " + msg);
        });
        return R.ok("操作成功");
    }

    /**
     * 查询系统任务状态记录机器列表
     */
    @SaCheckPermission("system:taskRecord:list")
    @GetMapping("/list")
    public TableDataInfo<SysTaskRecordVo> list(SysTaskRecordBo bo, PageQuery pageQuery) {
        return sysTaskRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出系统任务状态记录机器列表
     */
    @SaCheckPermission("system:taskRecord:export")
    @Log(title = "系统任务状态记录机器", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysTaskRecordBo bo, HttpServletResponse response) {
        List<SysTaskRecordVo> list = sysTaskRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "系统任务状态记录机器", SysTaskRecordVo.class, response);
    }

    /**
     * 获取系统任务状态记录机器详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:taskRecord:query")
    @GetMapping("/{id}")
    public R<SysTaskRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(sysTaskRecordService.queryById(id));
    }

    /**
     * 新增系统任务状态记录机器
     */
    @SaCheckPermission("system:taskRecord:add")
    @Log(title = "系统任务状态记录机器", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysTaskRecordBo bo) {
        return toAjax(sysTaskRecordService.insertByBo(bo));
    }

    /**
     * 修改系统任务状态记录机器
     */
    @SaCheckPermission("system:taskRecord:edit")
    @Log(title = "系统任务状态记录机器", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysTaskRecordBo bo) {
        return toAjax(sysTaskRecordService.updateByBo(bo));
    }

    /**
     * 删除系统任务状态记录机器
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:taskRecord:remove")
    @Log(title = "系统任务状态记录机器", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(sysTaskRecordService.deleteWithValidByIds(List.of(ids), true));
    }
}
