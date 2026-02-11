package org.dromara.ecom.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.ecom.domain.EcomOrderTicket;
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
 * 门票/服务专用视图对象 ecom_order_ticket
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomOrderTicket.class)
public class EcomOrderTicketVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 门票或服务Id
     */
    @ExcelProperty(value = "门票或服务Id")
    private Long ticketId;

    /**
     * 订单ID
     */
    @ExcelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 门票券码
     */
    @ExcelProperty(value = "门票券码")
    private String codeNo;

    /**
     * 二维码二进制
     */
    @ExcelProperty(value = "二维码二进制")
    private String qrCode;

    /**
     * 验证开始时间
     */
    @ExcelProperty(value = "验证开始时间")
    private Date validStart;

    /**
     * 验证结束时间
     */
    @ExcelProperty(value = "验证结束时间")
    private Date validEnd;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String verifyStatus;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
