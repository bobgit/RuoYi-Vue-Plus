package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 活动商品关联对象 ecom_product_activity
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_product_activity")
public class EcomProductActivity extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关联ID
     */
    @TableId(value = "activity_product_id")
    private Long activityProductId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * SPU ID
     */
    private Long spuId;

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * 活动所属类型
     */
    private String ownerType;

    /**
     * 活动所属ID
     */
    private Long ownerId;

    /**
     * 履约类型
     */
    private String fulfillmentType;

    /**
     * 履约ID
     */
    private Long fulfillmentId;

    /**
     * 活动商品标题
     */
    private String activityTitle;

    /**
     * 活动价格
     */
    private Long activityPrice;

    /**
     * 活动配额库存
     */
    private Long activityStock;

    /**
     * 最小成团人数
     */
    private Long minGroupSize;

    /**
     * 最大成团人数
     */
    private Long maxGroupSize;

    /**
     * 每人限购数量
     */
    private Long limitPerUser;

    /**
     * 状态
     */
    private String status;

    /**
     * 审核状态
     */
    private String auditStatus;

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
