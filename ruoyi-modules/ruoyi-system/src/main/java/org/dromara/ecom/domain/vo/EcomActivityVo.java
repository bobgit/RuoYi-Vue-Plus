package org.dromara.ecom.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.ecom.domain.EcomActivity;
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
 * 活动视图对象 ecom_activity
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomActivity.class)
public class EcomActivityVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @ExcelProperty(value = "活动ID")
    private Long activityId;

    /**
     * 活动名称
     */
    @ExcelProperty(value = "活动名称")
    private String activityName;

    /**
     * 活动类型
     */
    @ExcelProperty(value = "活动类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "product_type")
    private String activityType;

    /**
     * 活动封面图
     */
    @ExcelProperty(value = "活动封面图")
    private String coverImage;

    /**
     * 活动封面图Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "coverImage")
    private String coverImageUrl;
    /**
     * 活动详情图
     */
    @ExcelProperty(value = "活动详情图")
    private String activityImage;

    /**
     * 分享标题
     */
    @ExcelProperty(value = "分享标题")
    private String shareTitle;

    /**
     * 分享描述
     */
    @ExcelProperty(value = "分享描述")
    private String shareDescription;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 成功团购次数
     */
    @ExcelProperty(value = "成功团购次数")
    private Long successCount;

    /**
     * 活动状态
     */
    @ExcelProperty(value = "活动状态")
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
