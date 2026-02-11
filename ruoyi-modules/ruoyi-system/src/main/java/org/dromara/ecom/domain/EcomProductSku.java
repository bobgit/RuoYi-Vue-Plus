package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * SKU库存单元对象 ecom_product_sku
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_product_sku")
public class EcomProductSku extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * SKU ID
     */
    @TableId(value = "sku_id")
    private Long skuId;

    /**
     * SPU ID
     */
    private Long spuId;

    /**
     * SKU名称
     */
    private String skuName;

    /**
     * 规格JSON
     */
    private String skuSpec;

    /**
     * 成本价格
     */
    private Long costPrice;

    /**
     * 市场价
     */
    private Long marketPrice;

    /**
     * 销售价
     */
    private Long price;

    /**
     * 库存策略类型
     */
    private String stockPolicyType;

    /**
     * 单品销量
     */
    private Long stockQuantity;

    /**
     * 已售数量
     */
    private Long soldQuantity;

    /**
     * 重量
     */
    private Long weight;

    /**
     * 长
     */
    private Long length;

    /**
     * 宽
     */
    private Long width;

    /**
     * 高
     */
    private Long height;

    /**
     * 二维码
     */
    private String barCode;

    /**
     * 编码
     */
    private String skuCode;

    /**
     * 状态
     */
    private String status;

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
