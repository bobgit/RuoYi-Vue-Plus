package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomFulfillmentTask;
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
 * 履约单业务对象 ecom_fulfillment_task
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomFulfillmentTask.class, reverseConvertGenerate = false)
public class EcomFulfillmentTaskBo extends BaseEntity {

    /**
     * 履约单ID
     */
    @NotNull(message = "履约单ID不能为空", groups = { EditGroup.class })
    private Long fulfillmentTaskId;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * 订单明细ID
     */
    @NotNull(message = "订单明细ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderItemId;

    /**
     * 履约方式类型
     */
    private String fulfillmentType;

    /**
     * 履约方式Id
     */
    private Long fulfillmentId;

    /**
     * 履约责任归属
     */
    @NotBlank(message = "履约责任归属不能为空", groups = { AddGroup.class, EditGroup.class })
    private String responsibleType;

    /**
     * 履约责任归属ID
     */
    @NotNull(message = "履约责任归属ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long responsibleId;

    /**
     * 物流公司
     */
    private String logisticsCompany;

    /**
     * 物流公司ID
     */
    private String logisticsNo;

    /**
     * 骑手ID
     */
    private Long riderId;

    /**
     * 自提码
     */
    private String pickupCode;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 优先级别
     */
    private Long priority;

    /**
     * 履约状态
     */
    private String fulfillmentStatus;

    /**
     * 分配时间
     */
    private Date assignTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 完成时间
     */
    private Date completeTime;

    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 备注
     */
    private String remark;


}
