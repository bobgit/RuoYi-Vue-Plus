package org.dromara.ecom.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.ecom.domain.EcomOrderDelivery;
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
 * 订单配送专用视图对象 ecom_order_delivery
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomOrderDelivery.class)
public class EcomOrderDeliveryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单配送Id
     */
    @ExcelProperty(value = "订单配送Id")
    private Long orderDeliveryId;

    /**
     * 订单ID
     */
    @ExcelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 订单业务类型
     */
    @ExcelProperty(value = "订单业务类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "order_status")
    private String orderType;

    /**
     * 骑手名字
     */
    @ExcelProperty(value = "骑手名字")
    private String riderName;

    /**
     * 骑手Id
     */
    @ExcelProperty(value = "骑手Id")
    private String riderId;

    /**
     * 骑手电话
     */
    @ExcelProperty(value = "骑手电话")
    private String riderPhone;

    /**
     * 距离目的
     */
    @ExcelProperty(value = "距离目的")
    private Long deliveryDistance;

    /**
     * 配送状态
     */
    @ExcelProperty(value = "配送状态")
    private String deliveryStatus;

    /**
     * 期望时间
     */
    @ExcelProperty(value = "期望时间")
    private Date expectedTime;

    /**
     * 交付时间
     */
    @ExcelProperty(value = "交付时间")
    private Date actualTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
