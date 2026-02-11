package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomBrand;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 商品品牌业务对象 ecom_brand
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomBrand.class, reverseConvertGenerate = false)
public class EcomBrandBo extends BaseEntity {

    /**
     * 品牌ID
     */
    @NotNull(message = "品牌ID不能为空", groups = { EditGroup.class })
    private Long brandId;

    /**
     * 品牌编码
     */
    private String brandCode;

    /**
     * 图标
     */
    private String brandLogo;

    /**
     * 官网
     */
    private String officialSite;

    /**
     * 品牌所属国家
     */
    private String countryCode;

    /**
     * 状态
     */
    private String status;

    /**
     * 排序权重
     */
    private Long sortOrder;

    /**
     * 备注
     */
    private String remark;


}
