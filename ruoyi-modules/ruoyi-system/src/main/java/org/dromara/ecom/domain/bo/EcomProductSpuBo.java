package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomProductSpu;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * SPU标准产品单元业务对象 ecom_product_spu
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomProductSpu.class, reverseConvertGenerate = false)
public class EcomProductSpuBo extends BaseEntity {

    /**
     * SPU ID
     */
    @NotNull(message = "SPU ID不能为空", groups = { EditGroup.class })
    private Long spuId;

    /**
     * 分类ID
     */
    @NotNull(message = "分类ID不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotBlank(message = "SPU名称不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 备注
     */
    private String remark;


}
