package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 退款对象 ecom_refund
 *
 * @author Bob Bai
 * @date 2026-02-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_refund")
public class EcomRefund extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 退款ID
     */
    @TableId(value = "refund_id")
    private Long refundId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单明细ID
     */
    private Long orderItemId;

    /**
     * 退款类型
     */
    private String refundType;

    /**
     * 退款金额
     */
    private Long refundAmount;

    /**
     * 退款原因
     */
    private String refundReason;

    /**
     * 退款描述
     */
    private String refundDesc;

    /**
     * 退款状态
     */
    private String refundStatus;

    /**
     * 逆向结算
     */
    private String reverseSettlement;

    /**
     * 逆向库存
     */
    private String reverseStock;

    /**
     * 审核人员
     */
    private Long auditBy;

    /**
     * 审核时间
     */
    private Date auditTime;

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
