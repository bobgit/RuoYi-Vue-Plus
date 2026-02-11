package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomProductActivity;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 活动商品关联业务对象 ecom_product_activity
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomProductActivity.class, reverseConvertGenerate = false)
public class EcomProductActivityBo extends BaseEntity {

    /**
     * 关联ID
     */
    @NotNull(message = "关联ID不能为空", groups = { EditGroup.class })
    private Long activityProductId;

    /**
     * 活动ID
     */
    @NotNull(message = "活动ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long activityId;

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
    @NotBlank(message = "活动商品标题不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 备注
     */
    private String remark;


}
