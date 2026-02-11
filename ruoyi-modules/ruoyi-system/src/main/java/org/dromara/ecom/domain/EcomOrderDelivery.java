package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 订单配送专用对象 ecom_order_delivery
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_order_delivery")
public class EcomOrderDelivery extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单配送Id
     */
    @TableId(value = "order_delivery_id")
    private Long orderDeliveryId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单业务类型
     */
    private String orderType;

    /**
     * 骑手名字
     */
    private String riderName;

    /**
     * 骑手Id
     */
    private String riderId;

    /**
     * 骑手电话
     */
    private String riderPhone;

    /**
     * 距离目的
     */
    private Long deliveryDistance;

    /**
     * 配送状态
     */
    private String deliveryStatus;

    /**
     * 期望时间
     */
    private Date expectedTime;

    /**
     * 交付时间
     */
    private Date actualTime;

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
