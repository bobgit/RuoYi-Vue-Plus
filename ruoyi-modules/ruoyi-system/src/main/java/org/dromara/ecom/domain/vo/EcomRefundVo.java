package org.dromara.ecom.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.ecom.domain.EcomRefund;
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
 * 退款视图对象 ecom_refund
 *
 * @author Bob Bai
 * @date 2026-02-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomRefund.class)
public class EcomRefundVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 退款ID
     */
    @ExcelProperty(value = "退款ID")
    private Long refundId;

    /**
     * 订单ID
     */
    @ExcelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 订单明细ID
     */
    @ExcelProperty(value = "订单明细ID")
    private Long orderItemId;

    /**
     * 退款类型
     */
    @ExcelProperty(value = "退款类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "refund_type")
    private String refundType;

    /**
     * 退款金额
     */
    @ExcelProperty(value = "退款金额")
    private Long refundAmount;

    /**
     * 退款原因
     */
    @ExcelProperty(value = "退款原因")
    private String refundReason;

    /**
     * 退款描述
     */
    @ExcelProperty(value = "退款描述")
    private String refundDesc;

    /**
     * 退款状态
     */
    @ExcelProperty(value = "退款状态")
    private String refundStatus;

    /**
     * 逆向结算
     */
    @ExcelProperty(value = "逆向结算", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String reverseSettlement;

    /**
     * 逆向库存
     */
    @ExcelProperty(value = "逆向库存", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String reverseStock;

    /**
     * 审核人员
     */
    @ExcelProperty(value = "审核人员")
    private Long auditBy;

    /**
     * 审核时间
     */
    @ExcelProperty(value = "审核时间")
    private Date auditTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
