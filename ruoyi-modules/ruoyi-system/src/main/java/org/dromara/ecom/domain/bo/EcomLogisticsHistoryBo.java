package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomLogisticsHistory;
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
 * 物流跟踪记录业务对象 ecom_logistics_history
 *
 * @author Bob Bai
 * @date 2026-02-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomLogisticsHistory.class, reverseConvertGenerate = false)
public class EcomLogisticsHistoryBo extends BaseEntity {

    /**
     * 跟踪ID
     */
    @NotNull(message = "跟踪ID不能为空", groups = { EditGroup.class })
    private Long historyId;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * 节点时间
     */
    @NotNull(message = "节点时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date nodeTime;

    /**
     * 节点状态
     */
    @NotBlank(message = "节点状态不能为空", groups = { AddGroup.class, EditGroup.class })
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


}
