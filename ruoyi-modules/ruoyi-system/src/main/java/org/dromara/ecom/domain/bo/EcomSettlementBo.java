package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomSettlement;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 结算业务对象 ecom_settlement
 *
 * @author Bob Bai
 * @date 2026-02-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomSettlement.class, reverseConvertGenerate = false)
public class EcomSettlementBo extends BaseEntity {

    /**
     * 结算ID
     */
    @NotNull(message = "结算ID不能为空", groups = { EditGroup.class })
    private Long settlementId;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * 订单明细ID
     */
    @NotNull(message = "订单明细ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderItemId;

    /**
     * 经营归属类型
     */
    @NotBlank(message = "经营归属类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ownerType;

    /**
     * 经营归属ID
     */
    @NotNull(message = "经营归属ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long ownerId;

    /**
     * 订单原价
     */
    @NotNull(message = "订单原价不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderAmount;

    /**
     * 实付金额
     */
    @NotNull(message = "实付金额不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "实际应结算不能为空", groups = { AddGroup.class, EditGroup.class })
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


}
