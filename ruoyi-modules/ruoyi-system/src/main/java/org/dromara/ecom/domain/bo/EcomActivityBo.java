package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomActivity;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import org.dromara.common.translation.annotation.Translation;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.common.translation.constant.TransConstant;

/**
 * 活动业务对象 ecom_activity
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomActivity.class, reverseConvertGenerate = false)
public class EcomActivityBo extends BaseEntity {

    /**
     * 活动ID
     */
    @NotNull(message = "活动ID不能为空", groups = { EditGroup.class })
    private Long activityId;

    /**
     * 活动名称
     */
    @NotBlank(message = "活动名称不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 备注
     */
    private String remark;


}
