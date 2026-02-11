package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 通用库存对象 ecom_stock
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_stock")
public class EcomStock extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 库存ID
     */
    @TableId(value = "stock_id")
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
     * 物理存在的总数
     */
    private Long stockTotal;

    /**
     * 可卖库存
     */
    private Long stockAvailable;

    /**
     * 未完成履约
     */
    private Long stockLocked;

    /**
     * 安全库存
     */
    private Long safetyStock;

    /**
     * 状态
     */
    private String status;

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
