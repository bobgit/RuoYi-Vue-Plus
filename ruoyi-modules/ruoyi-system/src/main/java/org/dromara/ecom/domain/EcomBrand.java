package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 商品品牌对象 ecom_brand
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_brand")
public class EcomBrand extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 品牌ID
     */
    @TableId(value = "brand_id")
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
     * 删除标志
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注
     */
    private String remark;


}
