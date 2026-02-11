package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomStock;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 通用库存业务对象 ecom_stock
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomStock.class, reverseConvertGenerate = false)
public class EcomStockBo extends BaseEntity {

    /**
     * 库存ID
     */
    @NotNull(message = "库存ID不能为空", groups = { EditGroup.class })
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
     * 物理存在的总数
     */
    @NotNull(message = "物理存在的总数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long stockTotal;

    /**
     * 可卖库存
     */
    @NotNull(message = "可卖库存不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long stockAvailable;

    /**
     * 未完成履约
     */
    @NotNull(message = "未完成履约不能为空", groups = { AddGroup.class, EditGroup.class })
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


}
