package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.translation.annotation.Translation;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.common.translation.constant.TransConstant;

import java.io.Serial;

/**
 * 活动对象 ecom_activity
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_activity")
public class EcomActivity extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @TableId(value = "activity_id")
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动类型
     */
    private String activityType;

    /**
     * 活动封面图
     */
    private String coverImage;

    /**
     * 活动详情图
     */
    private String activityImage;

    /**
     * 分享标题
     */
    private String shareTitle;

    /**
     * 分享描述
     */
    private String shareDescription;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 成功团购次数
     */
    private Long successCount;

    /**
     * 活动状态
     */
    private String status;

    /**
     * 审核状态
     */
    private String auditStatus;

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
