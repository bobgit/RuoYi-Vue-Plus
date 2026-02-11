package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomActivityAttributes;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 活动属性业务对象 ecom_activity_attributes
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomActivityAttributes.class, reverseConvertGenerate = false)
public class EcomActivityAttributesBo extends BaseEntity {

    /**
     * 属性ID
     */
    @NotNull(message = "属性ID不能为空", groups = { EditGroup.class })
    private Long attrId;

    /**
     * 活动ID
     */
    @NotNull(message = "活动ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long activityId;

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
     * 属性类型
     */
    private String attrType;


}
