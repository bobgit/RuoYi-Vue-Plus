package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomGroupRecord;
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
 * 团购参团记录业务对象 ecom_group_record
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomGroupRecord.class, reverseConvertGenerate = false)
public class EcomGroupRecordBo extends BaseEntity {

    /**
     * 参团记录ID
     */
    @NotNull(message = "参团记录ID不能为空", groups = { EditGroup.class })
    private Long recordId;

    /**
     * 活动商品ID
     */
    @NotNull(message = "活动商品ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long activityProductId;

    /**
     * 团长责任人ID
     */
    @NotNull(message = "团长责任人ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long leaderId;

    /**
     * 当前参团人数
     */
    private Long currentCount;

    /**
     * 目标成团人数
     */
    @NotNull(message = "目标成团人数不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 备注
     */
    private String remark;


}
