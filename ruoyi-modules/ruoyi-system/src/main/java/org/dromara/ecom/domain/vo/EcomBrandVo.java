package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.EcomBrand;
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
 * 商品品牌视图对象 ecom_brand
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomBrand.class)
public class EcomBrandVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 品牌ID
     */
    @ExcelProperty(value = "品牌ID")
    private Long brandId;

    /**
     * 品牌编码
     */
    @ExcelProperty(value = "品牌编码")
    private String brandCode;

    /**
     * 图标
     */
    @ExcelProperty(value = "图标")
    private String brandLogo;

    /**
     * 官网
     */
    @ExcelProperty(value = "官网")
    private String officialSite;

    /**
     * 品牌所属国家
     */
    @ExcelProperty(value = "品牌所属国家", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_ios_country")
    private String countryCode;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 排序权重
     */
    @ExcelProperty(value = "排序权重")
    private Long sortOrder;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
