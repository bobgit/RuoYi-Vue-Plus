package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 团购参团记录对象 ecom_group_record
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_group_record")
public class EcomGroupRecord extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 参团记录ID
     */
    @TableId(value = "record_id")
    private Long recordId;

    /**
     * 活动商品ID
     */
    private Long activityProductId;

    /**
     * 团长责任人ID
     */
    private Long leaderId;

    /**
     * 当前参团人数
     */
    private Long currentCount;

    /**
     * 目标成团人数
     */
    private Long targetCount;

    /**
     * 成团状态
     */
    private String groupStatus;

    /**
     * 成团截止时间
     */
    private Date expireTime;

    /**
     * 乐观版本
     */
    @Version
    private Long version;

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
