package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 物流跟踪记录对象 ecom_logistics_history
 *
 * @author Bob Bai
 * @date 2026-02-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_logistics_history")
public class EcomLogisticsHistory extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 跟踪ID
     */
    @TableId(value = "history_id")
    private Long historyId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 节点时间
     */
    private Date nodeTime;

    /**
     * 节点状态
     */
    private String nodeStatus;

    /**
     * 节点位置JSON
     */
    private String nodeLocation;

    /**
     * 操作人
     */
    private String operator;

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
