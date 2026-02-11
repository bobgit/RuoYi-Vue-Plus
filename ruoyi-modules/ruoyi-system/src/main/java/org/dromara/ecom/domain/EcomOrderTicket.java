package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 门票/服务专用对象 ecom_order_ticket
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_order_ticket")
public class EcomOrderTicket extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 门票或服务Id
     */
    @TableId(value = "ticket_id")
    private Long ticketId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 门票券码
     */
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
     * 删除标志
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注
     */
    private String remark;


}
