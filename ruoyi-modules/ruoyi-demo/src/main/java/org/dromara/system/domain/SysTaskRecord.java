package org.dromara.system.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 系统任务状态记录机器对象 sys_task_record
 *
 * @author Lion Li
 * @date 2025-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_task_record")
public class SysTaskRecord extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 重试次数
     */
    private Long retryCount;

    /**
     * 逻辑删除标志
     */
    @TableLogic
    private String delFlag;


}
