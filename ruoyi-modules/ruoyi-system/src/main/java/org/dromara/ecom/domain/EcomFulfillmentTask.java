package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 履约单对象 ecom_fulfillment_task
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_fulfillment_task")
public class EcomFulfillmentTask extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 履约单ID
     */
    @TableId(value = "fulfillment_task_id")
    private Long fulfillmentTaskId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单明细ID
     */
    private Long orderItemId;

    /**
     * 履约方式类型
     */
    private String fulfillmentType;

    /**
     * 履约方式Id
     */
    private Long fulfillmentId;

    /**
     * 履约责任归属
     */
    private String responsibleType;

    /**
     * 履约责任归属ID
     */
    private Long responsibleId;

    /**
     * 物流公司
     */
    private String logisticsCompany;

    /**
     * 物流公司ID
     */
    private String logisticsNo;

    /**
     * 骑手ID
     */
    private Long riderId;

    /**
     * 自提码
     */
    private String pickupCode;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 优先级别
     */
    private Long priority;

    /**
     * 履约状态
     */
    private String fulfillmentStatus;

    /**
     * 分配时间
     */
    private Date assignTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 完成时间
     */
    private Date completeTime;

    /**
     * 取消时间
     */
    private Date cancelTime;

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
