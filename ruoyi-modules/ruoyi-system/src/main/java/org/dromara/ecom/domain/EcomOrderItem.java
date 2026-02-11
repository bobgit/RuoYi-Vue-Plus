package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;

import java.io.Serial;

/**
 * 订单明细对象 ecom_order_item
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_order_item")
public class EcomOrderItem extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 明细ID
     */
    @TableId(value = "item_id")
    private Long itemId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 活动商品ID
     */
    private Long activityProductId;

    /**
     * SPU ID
     */
    private Long spuId;

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * spu名称
     */
    private String spuName;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 商品图片
     */
    private String skuImage;

    /**
     * 活动Id
     */
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
     * 删除标志
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注
     */
    private String remark;


}
