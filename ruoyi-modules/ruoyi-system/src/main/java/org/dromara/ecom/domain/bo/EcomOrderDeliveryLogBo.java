package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomOrderDeliveryLog;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 配送状态变更日志业务对象 ecom_order_delivery_log
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomOrderDeliveryLog.class, reverseConvertGenerate = false)
public class EcomOrderDeliveryLogBo extends BaseEntity {

    /**
     * 配送日志Id
     */
    @NotNull(message = "配送日志Id不能为空", groups = { EditGroup.class })
    private Long deliveryLogId;

    /**
     * 外卖配送Id
     */
    private Long deliveryId;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空", groups = { AddGroup.class, EditGroup.class })
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


}
