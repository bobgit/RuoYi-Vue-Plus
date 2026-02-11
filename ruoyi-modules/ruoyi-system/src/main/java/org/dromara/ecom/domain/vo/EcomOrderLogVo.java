package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.EcomOrderLog;
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
 * 订单变更动态日志视图对象 ecom_order_log
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomOrderLog.class)
public class EcomOrderLogVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单变更动态日志ID
     */
    @ExcelProperty(value = "订单变更动态日志ID")
    private Long orderLogId;

    /**
     * 订单ID
     */
    @ExcelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 订单事件类型
     */
    @ExcelProperty(value = "订单事件类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "order_event_type")
    private String orderEventType;

    /**
     * 起始状态
     */
    @ExcelProperty(value = "起始状态")
    private String fromStatus;

    /**
     * 终止状态
     */
    @ExcelProperty(value = "终止状态")
    private String toStatus;

    /**
     * 改变原因
     */
    @ExcelProperty(value = "改变原因")
    private String changeReason;

    /**
     * 操作类型
     */
    @ExcelProperty(value = "操作类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "operator_type")
    private String operatorType;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
