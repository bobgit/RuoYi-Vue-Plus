package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomProductOrg;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 机构组织商品业务对象 ecom_product_org
 *
 * @author Bob Ok
 * @date 2026-01-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomProductOrg.class, reverseConvertGenerate = false)
public class EcomProductOrgBo extends BaseEntity {

    /**
     * 机构组织商品Id
     */
    @NotNull(message = "机构组织商品Id不能为空", groups = { EditGroup.class })
    private Long orgProductId;

    /**
     * 机构组织ID
     */
    @NotNull(message = "机构组织ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orgId;

    /**
     * 商品skuID
     */
    @NotNull(message = "商品skuID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long skuId;

    /**
     * 活动所属类型
     */
    private String ownerType;

    /**
     * 活动所属ID
     */
    private Long ownerId;

    /**
     * 履约类型
     */
    private String fulfillmentType;

    /**
     * 履约ID
     */
    private Long fulfillmentId;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 活动商品标题
     */
    private String orgTitle;

    /**
     * 活动价格
     */
    @NotNull(message = "活动价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orgPrice;

    /**
     * 活动配额库存
     */
    @NotNull(message = "活动配额库存不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orgStock;


}
