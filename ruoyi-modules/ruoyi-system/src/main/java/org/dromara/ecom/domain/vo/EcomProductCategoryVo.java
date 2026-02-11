package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.EcomProductCategory;
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
 * 商品分类视图对象 ecom_product_category
 *
 * @author Bob Bai
 * @date 2026-01-27
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomProductCategory.class)
public class EcomProductCategoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @ExcelProperty(value = "分类ID")
    private Long categoryId;

    /**
     * 父分类ID
     */
    @ExcelProperty(value = "父分类ID")
    private Long parentId;

    /**
     * 分类名称
     */
    @ExcelProperty(value = "分类名称")
    private String categoryName;

    /**
     * 分类编码
     */
    @ExcelProperty(value = "分类编码")
    private String categoryCode;

    /**
     * 编码路径
     */
    @ExcelProperty(value = "编码路径")
    private String categoryCodePath;

    /**
     * 分类图标
     */
    @ExcelProperty(value = "分类图标")
    private String icon;

    /**
     * 业务规则
     */
    @ExcelProperty(value = "业务规则")
    private String ruleTemplate;

    /**
     * 规格模板
     */
    @ExcelProperty(value = "规格模板")
    private String specTemplate;

    /**
     * 运费模板ID
     */
    @ExcelProperty(value = "运费模板ID")
    private String deliveryTemplate;

    /**
     * 扩展属性
     */
    @ExcelProperty(value = "扩展属性")
    private String attributeSchema;

    /**
     * 特殊管控规则
     */
    @ExcelProperty(value = "特殊管控规则")
    private String specialControl;

    /**
     * 排序权重
     */
    @ExcelProperty(value = "排序权重")
    private Long sortOrder;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
