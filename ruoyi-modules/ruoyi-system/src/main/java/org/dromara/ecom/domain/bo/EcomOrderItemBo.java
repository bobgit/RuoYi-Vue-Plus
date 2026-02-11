package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomOrderItem;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;

/**
 * 订单明细业务对象 ecom_order_item
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomOrderItem.class, reverseConvertGenerate = false)
public class EcomOrderItemBo extends BaseEntity {

    /**
     * 明细ID
     */
    @NotNull(message = "明细ID不能为空", groups = { EditGroup.class })
    private Long itemId;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * 活动商品ID
     */
    @NotNull(message = "活动商品ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long activityProductId;

    /**
     * SPU ID
     */
    @NotNull(message = "SPU ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long spuId;

    /**
     * SKU ID
     */
    @NotNull(message = "SKU ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long skuId;

    /**
     * spu名称
     */
    @NotBlank(message = "spu名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String spuName;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String skuName;

    /**
     * 商品图片
     */
    private String skuImage;

    /**
     * 活动Id
     */
    @NotNull(message = "活动Id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long activityId;

    /**
     * 经营归属类型
     */
    private String ownerType;

    /**
     * 经营归属ID
     */
    private Long ownerId;

    /**
     * 单价
     */
    private Long price;

    /**
     * 数量
     */
    private Long quantity;

    /**
     * 小计
     */
    private Long totalAmount;

    /**
     * 退款状态
     */
    private String refundStatus;

    /**
     * 退款金额
     */
    private Long refundAmount;

    /**
     * 备注
     */
    private String remark;


}
