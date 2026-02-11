package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.EcomStock;
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
 * 通用库存视图对象 ecom_stock
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomStock.class)
public class EcomStockVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 库存ID
     */
    @ExcelProperty(value = "库存ID")
    private Long stockId;

    /**
     * sku Id
     */
    @ExcelProperty(value = "sku Id")
    private Long skuId;

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
     * 库存所在类型
     */
    @ExcelProperty(value = "库存所在类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "stock_location_type")
    private String locationType;

    /**
     * 库存所在地ID
     */
    @ExcelProperty(value = "库存所在地ID")
    private Long locationAddressId;

    /**
     * 物理存在的总数
     */
    @ExcelProperty(value = "物理存在的总数")
    private Long stockTotal;

    /**
     * 可卖库存
     */
    @ExcelProperty(value = "可卖库存")
    private Long stockAvailable;

    /**
     * 未完成履约
     */
    @ExcelProperty(value = "未完成履约")
    private Long stockLocked;

    /**
     * 安全库存
     */
    @ExcelProperty(value = "安全库存")
    private Long safetyStock;

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
