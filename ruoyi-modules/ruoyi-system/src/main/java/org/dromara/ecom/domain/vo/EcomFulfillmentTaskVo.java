package org.dromara.ecom.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.ecom.domain.EcomFulfillmentTask;
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
 * 履约单视图对象 ecom_fulfillment_task
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomFulfillmentTask.class)
public class EcomFulfillmentTaskVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 履约单ID
     */
    @ExcelProperty(value = "履约单ID")
    private Long fulfillmentTaskId;

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
     * 履约方式类型
     */
    @ExcelProperty(value = "履约方式类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "fulfillment_type")
    private String fulfillmentType;

    /**
     * 履约方式Id
     */
    @ExcelProperty(value = "履约方式Id")
    private Long fulfillmentId;

    /**
     * 履约责任归属
     */
    @ExcelProperty(value = "履约责任归属", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "responsible_type")
    private String responsibleType;

    /**
     * 履约责任归属ID
     */
    @ExcelProperty(value = "履约责任归属ID")
    private Long responsibleId;

    /**
     * 物流公司
     */
    @ExcelProperty(value = "物流公司")
    private String logisticsCompany;

    /**
     * 物流公司ID
     */
    @ExcelProperty(value = "物流公司ID")
    private String logisticsNo;

    /**
     * 骑手ID
     */
    @ExcelProperty(value = "骑手ID")
    private Long riderId;

    /**
     * 自提码
     */
    @ExcelProperty(value = "自提码")
    private String pickupCode;

    /**
     * 验证码
     */
    @ExcelProperty(value = "验证码")
    private String verifyCode;

    /**
     * 优先级别
     */
    @ExcelProperty(value = "优先级别")
    private Long priority;

    /**
     * 履约状态
     */
    @ExcelProperty(value = "履约状态")
    private String fulfillmentStatus;

    /**
     * 分配时间
     */
    @ExcelProperty(value = "分配时间")
    private Date assignTime;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 完成时间
     */
    @ExcelProperty(value = "完成时间")
    private Date completeTime;

    /**
     * 取消时间
     */
    @ExcelProperty(value = "取消时间")
    private Date cancelTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
