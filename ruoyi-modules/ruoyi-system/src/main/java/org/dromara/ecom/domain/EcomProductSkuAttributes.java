package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 商品SKU属性对象 ecom_product_sku_attributes
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_product_sku_attributes")
public class EcomProductSkuAttributes extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 属性ID (主键)
     */
    @TableId(value = "sku_attr_id")
    private Long skuAttrId;

    /**
     * 商品sku ID
     */
    private Long skuId;

    /**
     * 属性键
     */
    private String attrKey;

    /**
     * 属性值
     */
    private String attrValue;

    /**
     * 属性类型:String,int,list
     */
    private String attrType;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;


}
