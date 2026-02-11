package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomOrderTicket;
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
 * 门票/服务专用业务对象 ecom_order_ticket
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomOrderTicket.class, reverseConvertGenerate = false)
public class EcomOrderTicketBo extends BaseEntity {

    /**
     * 门票或服务Id
     */
    @NotNull(message = "门票或服务Id不能为空", groups = { EditGroup.class })
    private Long ticketId;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * 门票券码
     */
    @NotBlank(message = "门票券码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String codeNo;

    /**
     * 二维码二进制
     */
    private String qrCode;

    /**
     * 验证开始时间
     */
    private Date validStart;

    /**
     * 验证结束时间
     */
    private Date validEnd;

    /**
     * 状态
     */
    private String verifyStatus;

    /**
     * 备注
     */
    private String remark;


}
