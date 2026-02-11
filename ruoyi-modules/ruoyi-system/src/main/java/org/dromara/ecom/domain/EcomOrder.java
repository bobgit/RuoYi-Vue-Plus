package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 订单对象 ecom_order
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_order")
public class EcomOrder extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "order_id")
    private Long orderId;

    /**
     * 订单唯一号
     */
    private String orderSn;

    /**
     * 买家用户id
     */
    private Long buyerUserId;

    /**
     * 参团记录ID
     */
    private Long recordId;

    /**
     * 父订单ID
     */
    private Long parentOrderId;

    /**
     * 公司ID
     */
    private Long orgId;

    /**
     * 团长ID
     */
    private Long leaderId;

    /**
     * 卖家ID
     */
    private Long sellerUserId;

    /**
     * 产品业务类型
     */
    private String productType;

    /**
     * 扩展
     */
    private String extendJson;

    /**
     * 活动Id
     */
    private Long activityId;

    /**
     * 活动产品Id
     */
    private Long activityProductId;

    /**
     * 经营归属类型
     */
    private String ownerType;

    /**
     * 经营归属ID
     */
    private Long ownerId;

    /**
     * 履约执行类型
     */
    private String fulfillmentType;

    /**
     * 履约执行ID
     */
    private Long fulfillmentId;

    /**
     * 责任主体类型
     */
    private String responsibleType;

    /**
     * 责任主体ID
     */
    private Long responsibleId;

    /**
     * 货币地区
     */
    private String currency;

    /**
     * 货币位置
     */
    private String locale;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 支付金额
     */
    private Long payAmount;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 支付方式
     */
    private String payWay;

    /**
     * 买家留言
     */
    private String buyerMessage;

    /**
     * 自动确认天数
     */
    private Long autoConfirmDays;

    /**
     * 确认收货时间
     */
    private Date confirmTime;

    /**
     * 国家代码
     */
    private String countryCode;

    /**
     * 都道府县
     */
    private String administrativeArea;

    /**
     * 一级行政区
     */
    private String locality;

    /**
     * 二级行政区
     */
    private String dependentLocality;

    /**
     * 街道
     */
    private String streetDetail;

    /**
     * 邮编
     */
    private String postalCode;

    /**
     * 地址名
     */
    private String addressesName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 其他附加信息
     */
    private String other;

    /**
     * 格式化地址
     */
    private String formattedAddress;

    /**
     * 语言类型
     */
    private String lang;

    /**
     * 纬度
     */
    private Long latitude;

    /**
     * 经度
     */
    private Long longitude;

    /**
     * 退款状态
     */
    private String refundStatus;

    /**
     * 退款金额
     */
    private Long refundAmount;

    /**
     * 订单来源（小程序/H5/APP）
     */
    private String source;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注
     */
    private String remark;


}
