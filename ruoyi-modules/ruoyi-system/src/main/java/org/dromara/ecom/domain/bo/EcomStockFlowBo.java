package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomStockFlow;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 库存流水业务对象 ecom_stock_flow
 *
 * @author Bob Bai
 * @date 2026-02-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomStockFlow.class, reverseConvertGenerate = false)
public class EcomStockFlowBo extends BaseEntity {

    /**
     * 库存流水ID
     */
    @NotNull(message = "库存流水ID不能为空", groups = { EditGroup.class })
    private Long stockFlowId;

    /**
     * 库存ID
     */
    @NotNull(message = "库存ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long stockId;

    /**
     * sku Id
     */
    @NotNull(message = "sku Id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long skuId;

    /**
     * 经营归属类型
     */
    @NotBlank(message = "经营归属类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ownerType;

    /**
     * 经营归属ID
     */
    @NotNull(message = "经营归属ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long ownerId;

    /**
     * 库存所在类型
     */
    @NotBlank(message = "库存所在类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String locationType;

    /**
     * 库存所在地ID
     */
    @NotNull(message = "库存所在地ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long locationAddressId;

    /**
     * 库存业务类型
     */
    @NotBlank(message = "库存业务类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String stockBizType;

    /**
     * 业务ID
     */
    private Long bizId;

    /**
     * 变化数量
     */
    @NotNull(message = "变化数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long changeQty;

    /**
     * 之前数量
     */
    @NotNull(message = "之前数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long beforeQty;

    /**
     * 之后数量
     */
    @NotNull(message = "之后数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long afterQty;

    /**
     * 库存类型
     */
    @NotBlank(message = "库存类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String stockType;

    /**
     * 备注
     */
    private String remark;


}
