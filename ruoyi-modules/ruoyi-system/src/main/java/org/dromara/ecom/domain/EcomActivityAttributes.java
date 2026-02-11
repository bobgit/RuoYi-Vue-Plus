package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 活动属性对象 ecom_activity_attributes
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_activity_attributes")
public class EcomActivityAttributes extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 属性ID
     */
    @TableId(value = "attr_id")
    private Long attrId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 属性键
     */
    private String attrKey;

    /**
     * 属性值
     */
    private String attrValue;

    /**
     * 属性类型
     */
    private String attrType;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;


}
