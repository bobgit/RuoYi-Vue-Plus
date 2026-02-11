package org.dromara.ecom.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.ecom.domain.EcomOrderItem;
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
 * 订单明细视图对象 ecom_order_item
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomOrderItem.class)
public class EcomOrderItemVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 明细ID
     */
    @ExcelProperty(value = "明细ID")
    private Long itemId;

    /**
     * 订单ID
     */
    @ExcelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 活动商品ID
     */
    @ExcelProperty(value = "活动商品ID")
    private Long activityProductId;

    /**
     * SPU ID
     */
    @ExcelProperty(value = "SPU ID")
    private Long spuId;

    /**
     * SKU ID
     */
    @ExcelProperty(value = "SKU ID")
    private Long skuId;

    /**
     * spu名称
     */
    @ExcelProperty(value = "spu名称")
    private String spuName;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    private String skuName;

    /**
     * 商品图片
     */
    @ExcelProperty(value = "商品图片")
    private String skuImage;

    /**
     * 商品图片Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "skuImage")
    private String skuImageUrl;
    /**
     * 活动Id
     */
    @ExcelProperty(value = "活动Id")
    private Long activityId;

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
     * 单价
     */
    @ExcelProperty(value = "单价")
    private Long price;

    /**
     * 数量
     */
    @ExcelProperty(value = "数量")
    private Long quantity;

    /**
     * 小计
     */
    @ExcelProperty(value = "小计")
    private Long totalAmount;

    /**
     * 退款状态
     */
    @ExcelProperty(value = "退款状态")
    private String refundStatus;

    /**
     * 退款金额
     */
    @ExcelProperty(value = "退款金额")
    private Long refundAmount;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
