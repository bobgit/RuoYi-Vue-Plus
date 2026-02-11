package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.EcomActivityAttributes;
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
 * 活动属性视图对象 ecom_activity_attributes
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomActivityAttributes.class)
public class EcomActivityAttributesVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 属性ID
     */
    @ExcelProperty(value = "属性ID")
    private Long attrId;

    /**
     * 活动ID
     */
    @ExcelProperty(value = "活动ID")
    private Long activityId;

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
     * 属性类型
     */
    @ExcelProperty(value = "属性类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "field_type")
    private String attrType;


}
