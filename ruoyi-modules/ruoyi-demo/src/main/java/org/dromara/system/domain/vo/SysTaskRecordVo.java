package org.dromara.system.domain.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.system.domain.SysTaskRecord;

import java.io.Serial;
import java.io.Serializable;


/**
 * 系统任务状态记录机器视图对象 sys_task_record
 *
 * @author Lion Li
 * @date 2025-12-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysTaskRecord.class)
public class SysTaskRecordVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @ExcelProperty(value = "自增id")
    private Long id;

    /**
     * 任务类型
     */
    @ExcelProperty(value = "任务类型")
    private String taskType;

    /**
     * 数据
     */
    @ExcelProperty(value = "数据")
    private String data;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 错误消息
     */
    @ExcelProperty(value = "错误消息")
    private String errorMsg;

    /**
     * 重试次数
     */
    @ExcelProperty(value = "重试次数")
    private Long retryCount;


}
