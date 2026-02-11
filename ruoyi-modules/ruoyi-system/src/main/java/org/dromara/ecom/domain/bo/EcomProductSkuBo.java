package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomProductSku;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * SKU库存单元业务对象 ecom_product_sku
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomProductSku.class, reverseConvertGenerate = false)
public class EcomProductSkuBo extends BaseEntity {

    /**
     * SKU ID
     */
    @NotNull(message = "SKU ID不能为空", groups = { EditGroup.class })
    private Long skuId;

    /**
     * SPU ID
     */
    @NotNull(message = "SPU ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long spuId;

    /**
     * SKU名称
     */
    @NotBlank(message = "SKU名称不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 备注
     */
    private String remark;


}
