package org.dromara.system.domain.bo;

import org.dromara.system.domain.SysTaskRecord;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 系统任务状态记录机器业务对象 sys_task_record
 *
 * @author Lion Li
 * @date 2025-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysTaskRecord.class, reverseConvertGenerate = false)
public class SysTaskRecordBo extends BaseEntity {

    /**
     * 自增id
     */
    @NotNull(message = "自增id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 数据
     */
    private String data;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 重试次数
     */
    private Long retryCount;


}
