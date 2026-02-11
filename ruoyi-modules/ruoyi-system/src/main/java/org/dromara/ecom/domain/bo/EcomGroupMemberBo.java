package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomGroupMember;
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
 * 拼团团员业务对象 ecom_group_member
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomGroupMember.class, reverseConvertGenerate = false)
public class EcomGroupMemberBo extends BaseEntity {

    /**
     * 团员ID
     */
    @NotNull(message = "团员ID不能为空", groups = { EditGroup.class })
    private Long memberId;

    /**
     * 参团记录ID
     */
    @NotNull(message = "参团记录ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long recordId;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 备注
     */
    private String remark;


}
