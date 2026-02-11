package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 法律责任对象 ecom_legal_responsibility
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_legal_responsibility")
public class EcomLegalResponsibility extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 法律责任ID
     */
    @TableId(value = "responsibility_id")
    private Long responsibilityId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单明细ID
     */
    private Long orderItemId;

    /**
     * 履约单ID
     */
    private Long fulfillmentTaskId;

    /**
     * 担责组织类型
     */
    private String responsibilityOrgType;

    /**
     * 担责组织ID
     */
    private Long responsibilityOrgId;

    /**
     * 担责范围
     */
    private String responsibilityScope;

    /**
     * 有效时间
     */
    private Date effectiveTime;

    /**
     * 是否冻结
     */
    private String frozen;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;


}
