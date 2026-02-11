package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.EcomI18n;
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
 * 商品分类视图对象 ecom_i18n
 *
 * @author Bob Bai
 * @date 2026-02-06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomI18n.class)
public class EcomI18nVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 通用国际化ID
     */
    @ExcelProperty(value = "通用国际化ID")
    private Long i18nId;

    /**
     * 业务表类型
     */
    @ExcelProperty(value = "业务表类型")
    private String bizType;

    /**
     * 业务id
     */
    @ExcelProperty(value = "业务id")
    private Long bizId;

    /**
     * 语言类型
     */
    @ExcelProperty(value = "语言类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_ios_country")
    private String lang;

    /**
     * 字段
     */
    @ExcelProperty(value = "字段")
    private String field;

    /**
     * 翻译内容
     */
    @ExcelProperty(value = "翻译内容")
    private String content;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
