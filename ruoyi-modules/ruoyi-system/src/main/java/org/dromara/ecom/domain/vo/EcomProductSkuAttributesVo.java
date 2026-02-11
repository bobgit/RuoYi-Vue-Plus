package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.EcomProductSkuAttributes;
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
 * 商品SKU属性视图对象 ecom_product_sku_attributes
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomProductSkuAttributes.class)
public class EcomProductSkuAttributesVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 属性ID (主键)
     */
    @ExcelProperty(value = "属性ID (主键)")
    private Long skuAttrId;

    /**
     * 商品sku ID
     */
    @ExcelProperty(value = "商品sku ID")
    private Long skuId;

    /**
     * 属性键
     */
    @ExcelProperty(value = "属性键")
    private String attrKey;

    /**
     * 属性值
     */
    @ExcelProperty(value = "属性值")
    private String attrValue;

    /**
     * 属性类型:String,int,list
     */
    @ExcelProperty(value = "属性类型:String,int,list", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "field_type")
    private String attrType;


}
