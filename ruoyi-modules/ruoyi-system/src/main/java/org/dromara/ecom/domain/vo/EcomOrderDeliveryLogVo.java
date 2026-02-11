package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.EcomOrderDeliveryLog;
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
 * 配送状态变更日志视图对象 ecom_order_delivery_log
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomOrderDeliveryLog.class)
public class EcomOrderDeliveryLogVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配送日志Id
     */
    @ExcelProperty(value = "配送日志Id")
    private Long deliveryLogId;

    /**
     * 外卖配送Id
     */
    @ExcelProperty(value = "外卖配送Id")
    private Long deliveryId;

    /**
     * 订单ID
     */
    @ExcelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 起初状态
     */
    @ExcelProperty(value = "起初状态")
    private String fromStatus;

    /**
     * 目前状态
     */
    @ExcelProperty(value = "目前状态")
    private String toStatus;

    /**
     * 状态变更原因
     */
    @ExcelProperty(value = "状态变更原因")
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
