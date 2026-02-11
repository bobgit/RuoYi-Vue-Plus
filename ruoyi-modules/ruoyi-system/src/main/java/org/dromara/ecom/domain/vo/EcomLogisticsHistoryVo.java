package org.dromara.ecom.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.ecom.domain.EcomLogisticsHistory;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 物流跟踪记录视图对象 ecom_logistics_history
 *
 * @author Bob Bai
 * @date 2026-02-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomLogisticsHistory.class)
public class EcomLogisticsHistoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 跟踪ID
     */
    @ExcelProperty(value = "跟踪ID")
    private Long historyId;

    /**
     * 订单ID
     */
    @ExcelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 节点时间
     */
    @ExcelProperty(value = "节点时间")
    private Date nodeTime;

    /**
     * 节点状态
     */
    @ExcelProperty(value = "节点状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "logistics_status")
    private String nodeStatus;

    /**
     * 节点位置JSON
     */
    @ExcelProperty(value = "节点位置JSON")
    private String nodeLocation;

    /**
     * 操作人
     */
    @ExcelProperty(value = "操作人")
    private String operator;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
