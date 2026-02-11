package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.EcomProductActivity;
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
 * 活动商品关联视图对象 ecom_product_activity
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomProductActivity.class)
public class EcomProductActivityVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关联ID
     */
    @ExcelProperty(value = "关联ID")
    private Long activityProductId;

    /**
     * 活动ID
     */
    @ExcelProperty(value = "活动ID")
    private Long activityId;

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
     * 活动所属类型
     */
    @ExcelProperty(value = "活动所属类型")
    private String ownerType;

    /**
     * 活动所属ID
     */
    @ExcelProperty(value = "活动所属ID")
    private Long ownerId;

    /**
     * 履约类型
     */
    @ExcelProperty(value = "履约类型")
    private String fulfillmentType;

    /**
     * 履约ID
     */
    @ExcelProperty(value = "履约ID")
    private Long fulfillmentId;

    /**
     * 活动商品标题
     */
    @ExcelProperty(value = "活动商品标题")
    private String activityTitle;

    /**
     * 活动价格
     */
    @ExcelProperty(value = "活动价格")
    private Long activityPrice;

    /**
     * 活动配额库存
     */
    @ExcelProperty(value = "活动配额库存")
    private Long activityStock;

    /**
     * 最小成团人数
     */
    @ExcelProperty(value = "最小成团人数")
    private Long minGroupSize;

    /**
     * 最大成团人数
     */
    @ExcelProperty(value = "最大成团人数")
    private Long maxGroupSize;

    /**
     * 每人限购数量
     */
    @ExcelProperty(value = "每人限购数量")
    private Long limitPerUser;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 审核状态
     */
    @ExcelProperty(value = "审核状态")
    private String auditStatus;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
