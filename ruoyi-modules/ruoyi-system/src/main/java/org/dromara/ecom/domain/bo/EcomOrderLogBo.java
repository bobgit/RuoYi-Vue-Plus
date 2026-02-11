package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomOrderLog;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 订单变更动态日志业务对象 ecom_order_log
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomOrderLog.class, reverseConvertGenerate = false)
public class EcomOrderLogBo extends BaseEntity {

    /**
     * 订单变更动态日志ID
     */
    @NotNull(message = "订单变更动态日志ID不能为空", groups = { EditGroup.class })
    private Long orderLogId;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * 订单事件类型
     */
    private String orderEventType;

    /**
     * 起始状态
     */
    private String fromStatus;

    /**
     * 终止状态
     */
    private String toStatus;

    /**
     * 改变原因
     */
    private String changeReason;

    /**
     * 操作类型
     */
    private String operatorType;

    /**
     * 备注
     */
    private String remark;


}
