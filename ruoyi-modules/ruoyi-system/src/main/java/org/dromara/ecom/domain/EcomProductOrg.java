package org.dromara.ecom.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 机构组织商品对象 ecom_product_org
 *
 * @author Bob Ok
 * @date 2026-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ecom_product_org")
public class EcomProductOrg implements Serializable {//extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 机构组织商品Id
     */
    @TableId(value = "org_product_id")
    private Long orgProductId;

    /**
     * 机构组织ID
     */
    private Long orgId;

    /**
     * 商品skuID
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
     * 过期时间
     */
    private Date expireTime;

    /**
     * 活动商品标题
     */
    private String orgTitle;

    /**
     * 活动价格
     */
    private Long orgPrice;

    /**
     * 活动配额库存
     */
    private Long orgStock;


}
