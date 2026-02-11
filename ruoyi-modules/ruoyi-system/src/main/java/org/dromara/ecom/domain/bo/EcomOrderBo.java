package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomOrder;
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
 * 订单业务对象 ecom_order
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomOrder.class, reverseConvertGenerate = false)
public class EcomOrderBo extends BaseEntity {

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空", groups = { EditGroup.class })
    private Long orderId;

    /**
     * 订单唯一号
     */
    @NotBlank(message = "订单唯一号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderSn;

    /**
     * 买家用户id
     */
    @NotNull(message = "买家用户id不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "活动Id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long activityId;

    /**
     * 活动产品Id
     */
    @NotNull(message = "活动产品Id不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotBlank(message = "国家代码不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotBlank(message = "地址名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String addressesName;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空", groups = { AddGroup.class, EditGroup.class })
    private String phone;

    /**
     * 其他附加信息
     */
    @NotBlank(message = "其他附加信息不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 备注
     */
    private String remark;


}
