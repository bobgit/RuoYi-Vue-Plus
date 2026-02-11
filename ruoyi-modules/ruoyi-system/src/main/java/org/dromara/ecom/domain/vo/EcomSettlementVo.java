package org.dromara.ecom.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.ecom.domain.EcomSettlement;
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
 * 结算视图对象 ecom_settlement
 *
 * @author Bob Bai
 * @date 2026-02-06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomSettlement.class)
public class EcomSettlementVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 结算ID
     */
    @ExcelProperty(value = "结算ID")
    private Long settlementId;

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
     * 经营归属类型
     */
    @ExcelProperty(value = "经营归属类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "owner_type")
    private String ownerType;

    /**
     * 经营归属ID
     */
    @ExcelProperty(value = "经营归属ID")
    private Long ownerId;

    /**
     * 订单原价
     */
    @ExcelProperty(value = "订单原价")
    private Long orderAmount;

    /**
     * 实付金额
     */
    @ExcelProperty(value = "实付金额")
    private Long payAmount;

    /**
     * 平台服务费
     */
    @ExcelProperty(value = "平台服务费")
    private Long platformFee;

    /**
     * 配送费
     */
    @ExcelProperty(value = "配送费")
    private Long deliveryFee;

    /**
     * 抽佣
     */
    @ExcelProperty(value = "抽佣")
    private Long commissionFee;

    /**
     * 平台补贴
     */
    @ExcelProperty(value = "平台补贴")
    private Long subsidyAmount;

    /**
     * 实际应结算
     */
    @ExcelProperty(value = "实际应结算")
    private Long settlementAmount;

    /**
     * 结算批次
     */
    @ExcelProperty(value = "结算批次")
    private String settlementBatchNo;

    /**
     * 结算周期时间
     */
    @ExcelProperty(value = "结算周期时间")
    private Date settlementTime;

    /**
     * 结算状态
     */
    @ExcelProperty(value = "结算状态")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
