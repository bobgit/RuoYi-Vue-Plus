package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 配送状态变更日志对象 ecom_order_delivery_log
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_order_delivery_log")
public class EcomOrderDeliveryLog extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配送日志Id
     */
    @TableId(value = "delivery_log_id")
    private Long deliveryLogId;

    /**
     * 外卖配送Id
     */
    private Long deliveryId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 起初状态
     */
    private String fromStatus;

    /**
     * 目前状态
     */
    private String toStatus;

    /**
     * 状态变更原因
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
