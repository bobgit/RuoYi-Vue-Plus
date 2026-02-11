package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 结算对象 ecom_settlement
 *
 * @author Bob Bai
 * @date 2026-02-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_settlement")
public class EcomSettlement extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 结算ID
     */
    @TableId(value = "settlement_id")
    private Long settlementId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单明细ID
     */
    private Long orderItemId;

    /**
     * 经营归属类型
     */
    private String ownerType;

    /**
     * 经营归属ID
     */
    private Long ownerId;

    /**
     * 订单原价
     */
    private Long orderAmount;

    /**
     * 实付金额
     */
    private Long payAmount;

    /**
     * 平台服务费
     */
    private Long platformFee;

    /**
     * 配送费
     */
    private Long deliveryFee;

    /**
     * 抽佣
     */
    private Long commissionFee;

    /**
     * 平台补贴
     */
    private Long subsidyAmount;

    /**
     * 实际应结算
     */
    private Long settlementAmount;

    /**
     * 结算批次
     */
    private String settlementBatchNo;

    /**
     * 结算周期时间
     */
    private Date settlementTime;

    /**
     * 结算状态
     */
    private String status;

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
