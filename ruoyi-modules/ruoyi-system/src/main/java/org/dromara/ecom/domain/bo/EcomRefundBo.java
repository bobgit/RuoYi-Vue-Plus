package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomRefund;
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
 * 退款业务对象 ecom_refund
 *
 * @author Bob Bai
 * @date 2026-02-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomRefund.class, reverseConvertGenerate = false)
public class EcomRefundBo extends BaseEntity {

    /**
     * 退款ID
     */
    @NotNull(message = "退款ID不能为空", groups = { EditGroup.class })
    private Long refundId;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * 订单明细ID
     */
    private Long orderItemId;

    /**
     * 退款类型
     */
    @NotBlank(message = "退款类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String refundType;

    /**
     * 退款金额
     */
    @NotNull(message = "退款金额不能为空", groups = { AddGroup.class, EditGroup.class })
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


}
