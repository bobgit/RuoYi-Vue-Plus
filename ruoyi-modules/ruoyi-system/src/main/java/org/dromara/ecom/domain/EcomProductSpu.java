package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * SPU标准产品单元对象 ecom_product_spu
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_product_spu")
public class EcomProductSpu extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * SPU ID
     */
    @TableId(value = "spu_id")
    private Long spuId;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 所属类型
     */
    private String ownerType;

    /**
     * 所属ID
     */
    private Long ownerId;

    /**
     * 产品业务类型
     */
    private String productType;

    /**
     * SPU名称
     */
    private String spuName;

    /**
     * SPU描述
     */
    private String spuDesc;

    /**
     * 商品打包信息
     */
    private String packingList;

    /**
     * 售后服务
     */
    private String afterService;

    /**
     * 规格说明
     */
    private String genericSpec;

    /**
     * SPU图片JSON
     */
    private String spuImages;

    /**
     * 最低价格
     */
    private Long minPrice;

    /**
     * 最高价格
     */
    private Long maxPrice;

    /**
     * 总销量
     */
    private Long totalSales;

    /**
     * 总库存
     */
    private Long totalStock;

    /**
     * 状态
     */
    private String status;

    /**
     * 审核状态
     */
    private String auditStatus;

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
