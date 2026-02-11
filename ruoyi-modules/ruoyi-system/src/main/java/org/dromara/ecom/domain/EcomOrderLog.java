package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 订单变更动态日志对象 ecom_order_log
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_order_log")
public class EcomOrderLog extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单变更动态日志ID
     */
    @TableId(value = "order_log_id")
    private Long orderLogId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单事件类型
     */
    private String orderEventType;

    /**
     * 起始状态
     */
    private String fromStatus;

    /**
     * 终止状态
     */
    private String toStatus;

    /**
     * 改变原因
     */
    private String changeReason;

    /**
     * 操作类型
     */
    private String operatorType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;


}
