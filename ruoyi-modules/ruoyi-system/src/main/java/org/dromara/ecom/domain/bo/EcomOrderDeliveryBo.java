package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomOrderDelivery;
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
 * 订单配送专用业务对象 ecom_order_delivery
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomOrderDelivery.class, reverseConvertGenerate = false)
public class EcomOrderDeliveryBo extends BaseEntity {

    /**
     * 订单配送Id
     */
    @NotNull(message = "订单配送Id不能为空", groups = { EditGroup.class })
    private Long orderDeliveryId;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * 订单业务类型
     */
    @NotBlank(message = "订单业务类型不能为空", groups = { AddGroup.class, EditGroup.class })
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


}
