package org.dromara.ecom.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.ecom.domain.EcomGroupRecord;
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
 * 团购参团记录视图对象 ecom_group_record
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomGroupRecord.class)
public class EcomGroupRecordVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 参团记录ID
     */
    @ExcelProperty(value = "参团记录ID")
    private Long recordId;

    /**
     * 活动商品ID
     */
    @ExcelProperty(value = "活动商品ID")
    private Long activityProductId;

    /**
     * 团长责任人ID
     */
    @ExcelProperty(value = "团长责任人ID")
    private Long leaderId;

    /**
     * 当前参团人数
     */
    @ExcelProperty(value = "当前参团人数")
    private Long currentCount;

    /**
     * 目标成团人数
     */
    @ExcelProperty(value = "目标成团人数")
    private Long targetCount;

    /**
     * 成团状态
     */
    @ExcelProperty(value = "成团状态")
    private String groupStatus;

    /**
     * 成团截止时间
     */
    @ExcelProperty(value = "成团截止时间")
    private Date expireTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
