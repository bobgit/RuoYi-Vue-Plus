package org.dromara.ecom.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.ecom.domain.EcomOrder;
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
 * 订单视图对象 ecom_order
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomOrder.class)
public class EcomOrderVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @ExcelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 订单唯一号
     */
    @ExcelProperty(value = "订单唯一号")
    private String orderSn;

    /**
     * 买家用户id
     */
    @ExcelProperty(value = "买家用户id")
    private Long buyerUserId;

    /**
     * 参团记录ID
     */
    @ExcelProperty(value = "参团记录ID")
    private Long recordId;

    /**
     * 父订单ID
     */
    @ExcelProperty(value = "父订单ID")
    private Long parentOrderId;

    /**
     * 公司ID
     */
    @ExcelProperty(value = "公司ID")
    private Long orgId;

    /**
     * 团长ID
     */
    @ExcelProperty(value = "团长ID")
    private Long leaderId;

    /**
     * 卖家ID
     */
    @ExcelProperty(value = "卖家ID")
    private Long sellerUserId;

    /**
     * 产品业务类型
     */
    @ExcelProperty(value = "产品业务类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "product_type")
    private String productType;

    /**
     * 扩展
     */
    @ExcelProperty(value = "扩展")
    private String extendJson;

    /**
     * 活动Id
     */
    @ExcelProperty(value = "活动Id")
    private Long activityId;

    /**
     * 活动产品Id
     */
    @ExcelProperty(value = "活动产品Id")
    private Long activityProductId;

    /**
     * 经营归属类型
     */
    @ExcelProperty(value = "经营归属类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "owner_type")
    private String ownerType;

    /**
     * 经营归属ID
     */
    @ExcelProperty(value = "经营归属ID")
    private Long ownerId;

    /**
     * 履约执行类型
     */
    @ExcelProperty(value = "履约执行类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "fulfillment_type")
    private String fulfillmentType;

    /**
     * 履约执行ID
     */
    @ExcelProperty(value = "履约执行ID")
    private Long fulfillmentId;

    /**
     * 责任主体类型
     */
    @ExcelProperty(value = "责任主体类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "responsible_type")
    private String responsibleType;

    /**
     * 责任主体ID
     */
    @ExcelProperty(value = "责任主体ID")
    private Long responsibleId;

    /**
     * 货币地区
     */
    @ExcelProperty(value = "货币地区", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_ios_country")
    private String currency;

    /**
     * 货币位置
     */
    @ExcelProperty(value = "货币位置")
    private String locale;

    /**
     * 订单状态
     */
    @ExcelProperty(value = "订单状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "order_status")
    private String orderStatus;

    /**
     * 支付状态
     */
    @ExcelProperty(value = "支付状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "pay_status")
    private String payStatus;

    /**
     * 支付金额
     */
    @ExcelProperty(value = "支付金额")
    private Long payAmount;

    /**
     * 支付时间
     */
    @ExcelProperty(value = "支付时间")
    private Date payTime;

    /**
     * 支付方式
     */
    @ExcelProperty(value = "支付方式")
    private String payWay;

    /**
     * 买家留言
     */
    @ExcelProperty(value = "买家留言")
    private String buyerMessage;

    /**
     * 自动确认天数
     */
    @ExcelProperty(value = "自动确认天数")
    private Long autoConfirmDays;

    /**
     * 确认收货时间
     */
    @ExcelProperty(value = "确认收货时间")
    private Date confirmTime;

    /**
     * 国家代码
     */
    @ExcelProperty(value = "国家代码", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_ios_country")
    private String countryCode;

    /**
     * 都道府县
     */
    @ExcelProperty(value = "都道府县")
    private String administrativeArea;

    /**
     * 一级行政区
     */
    @ExcelProperty(value = "一级行政区")
    private String locality;

    /**
     * 二级行政区
     */
    @ExcelProperty(value = "二级行政区")
    private String dependentLocality;

    /**
     * 街道
     */
    @ExcelProperty(value = "街道")
    private String streetDetail;

    /**
     * 邮编
     */
    @ExcelProperty(value = "邮编")
    private String postalCode;

    /**
     * 地址名
     */
    @ExcelProperty(value = "地址名")
    private String addressesName;

    /**
     * 电话
     */
    @ExcelProperty(value = "电话")
    private String phone;

    /**
     * 其他附加信息
     */
    @ExcelProperty(value = "其他附加信息")
    private String other;

    /**
     * 格式化地址
     */
    @ExcelProperty(value = "格式化地址")
    private String formattedAddress;

    /**
     * 语言类型
     */
    @ExcelProperty(value = "语言类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_ios_country")
    private String lang;

    /**
     * 纬度
     */
    @ExcelProperty(value = "纬度")
    private Long latitude;

    /**
     * 经度
     */
    @ExcelProperty(value = "经度")
    private Long longitude;

    /**
     * 退款状态
     */
    @ExcelProperty(value = "退款状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "refund_status")
    private String refundStatus;

    /**
     * 退款金额
     */
    @ExcelProperty(value = "退款金额")
    private Long refundAmount;

    /**
     * 订单来源（小程序/H5/APP）
     */
    @ExcelProperty(value = "订单来源", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "小=程序/H5/APP")
    private String source;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
