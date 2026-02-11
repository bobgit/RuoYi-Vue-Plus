package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomLegalResponsibility;
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
 * 法律责任业务对象 ecom_legal_responsibility
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomLegalResponsibility.class, reverseConvertGenerate = false)
public class EcomLegalResponsibilityBo extends BaseEntity {

    /**
     * 法律责任ID
     */
    @NotNull(message = "法律责任ID不能为空", groups = { EditGroup.class })
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


}
