package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomProductSkuAttributes;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 商品SKU属性业务对象 ecom_product_sku_attributes
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomProductSkuAttributes.class, reverseConvertGenerate = false)
public class EcomProductSkuAttributesBo extends BaseEntity {

    /**
     * 属性ID (主键)
     */
    @NotNull(message = "属性ID (主键)不能为空", groups = { EditGroup.class })
    private Long skuAttrId;

    /**
     * 商品sku ID
     */
    @NotNull(message = "商品sku ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long skuId;

    /**
     * 属性键
     */
    @NotBlank(message = "属性键不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrKey;

    /**
     * 属性值
     */
    @NotBlank(message = "属性值不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrValue;

    /**
     * 属性类型:String,int,list
     */
    private String attrType;


}
