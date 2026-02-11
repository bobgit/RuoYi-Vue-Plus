package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 库存流水对象 ecom_stock_flow
 *
 * @author Bob Bai
 * @date 2026-02-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_stock_flow")
public class EcomStockFlow extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 库存流水ID
     */
    @TableId(value = "stock_flow_id")
    private Long stockFlowId;

    /**
     * 库存ID
     */
    private Long stockId;

    /**
     * sku Id
     */
    private Long skuId;

    /**
     * 经营归属类型
     */
    private String ownerType;

    /**
     * 经营归属ID
     */
    private Long ownerId;

    /**
     * 库存所在类型
     */
    private String locationType;

    /**
     * 库存所在地ID
     */
    private Long locationAddressId;

    /**
     * 库存业务类型
     */
    private String stockBizType;

    /**
     * 业务ID
     */
    private Long bizId;

    /**
     * 变化数量
     */
    private Long changeQty;

    /**
     * 之前数量
     */
    private Long beforeQty;

    /**
     * 之后数量
     */
    private Long afterQty;

    /**
     * 库存类型
     */
    private String stockType;

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
