package org.dromara.ecom.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.ecom.domain.EcomLegalResponsibility;
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
 * 法律责任视图对象 ecom_legal_responsibility
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomLegalResponsibility.class)
public class EcomLegalResponsibilityVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 法律责任ID
     */
    @ExcelProperty(value = "法律责任ID")
    private Long responsibilityId;

    /**
     * 订单ID
     */
    @ExcelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 订单明细ID
     */
    @ExcelProperty(value = "订单明细ID")
    private Long orderItemId;

    /**
     * 履约单ID
     */
    @ExcelProperty(value = "履约单ID")
    private Long fulfillmentTaskId;

    /**
     * 担责组织类型
     */
    @ExcelProperty(value = "担责组织类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "responsible_type")
    private String responsibilityOrgType;

    /**
     * 担责组织ID
     */
    @ExcelProperty(value = "担责组织ID")
    private Long responsibilityOrgId;

    /**
     * 担责范围
     */
    @ExcelProperty(value = "担责范围")
    private String responsibilityScope;

    /**
     * 有效时间
     */
    @ExcelProperty(value = "有效时间")
    private Date effectiveTime;

    /**
     * 是否冻结
     */
    @ExcelProperty(value = "是否冻结", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String frozen;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
