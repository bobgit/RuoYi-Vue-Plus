package org.dromara.ecom.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.ecom.domain.EcomGroupMember;
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
 * 拼团团员视图对象 ecom_group_member
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomGroupMember.class)
public class EcomGroupMemberVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 团员ID
     */
    @ExcelProperty(value = "团员ID")
    private Long memberId;

    /**
     * 参团记录ID
     */
    @ExcelProperty(value = "参团记录ID")
    private Long recordId;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 订单ID
     */
    @ExcelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 加入时间
     */
    @ExcelProperty(value = "加入时间")
    private Date joinTime;

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
