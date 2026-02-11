package org.dromara.ecom.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.ecom.domain.EcomProductOrg;
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
 * 机构组织商品视图对象 ecom_product_org
 *
 * @author Bob Ok
 * @date 2026-01-27
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomProductOrg.class)
public class EcomProductOrgVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 机构组织商品Id
     */
    @ExcelProperty(value = "机构组织商品Id")
    private Long orgProductId;

    /**
     * 机构组织ID
     */
    @ExcelProperty(value = "机构组织ID")
    private Long orgId;

    /**
     * 商品skuID
     */
    @ExcelProperty(value = "商品skuID")
    private Long skuId;

    /**
     * 活动所属类型
     */
    @ExcelProperty(value = "活动所属类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "owner_type")
    private String ownerType;

    /**
     * 活动所属ID
     */
    @ExcelProperty(value = "活动所属ID")
    private Long ownerId;

    /**
     * 履约类型
     */
    @ExcelProperty(value = "履约类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "fulfillment_type")
    private String fulfillmentType;

    /**
     * 履约ID
     */
    @ExcelProperty(value = "履约ID")
    private Long fulfillmentId;

    /**
     * 过期时间
     */
    @ExcelProperty(value = "过期时间")
    private Date expireTime;

    /**
     * 活动商品标题
     */
    @ExcelProperty(value = "活动商品标题")
    private String orgTitle;

    /**
     * 活动价格
     */
    @ExcelProperty(value = "活动价格")
    private Long orgPrice;

    /**
     * 活动配额库存
     */
    @ExcelProperty(value = "活动配额库存")
    private Long orgStock;


}
