package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 拼团团员对象 ecom_group_member
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_group_member")
public class EcomGroupMember extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 团员ID
     */
    @TableId(value = "member_id")
    private Long memberId;

    /**
     * 参团记录ID
     */
    private Long recordId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 加入时间
     */
    private Date joinTime;

    /**
     * 状态
     */
    private String status;

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
