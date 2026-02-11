package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.EcomStockFlow;
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
 * 库存流水视图对象 ecom_stock_flow
 *
 * @author Bob Bai
 * @date 2026-02-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomStockFlow.class)
public class EcomStockFlowVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 库存流水ID
     */
    @ExcelProperty(value = "库存流水ID")
    private Long stockFlowId;

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
     * 库存业务类型
     */
    @ExcelProperty(value = "库存业务类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "stock_biz_type")
    private String stockBizType;

    /**
     * 业务ID
     */
    @ExcelProperty(value = "业务ID")
    private Long bizId;

    /**
     * 变化数量
     */
    @ExcelProperty(value = "变化数量")
    private Long changeQty;

    /**
     * 之前数量
     */
    @ExcelProperty(value = "之前数量")
    private Long beforeQty;

    /**
     * 之后数量
     */
    @ExcelProperty(value = "之后数量")
    private Long afterQty;

    /**
     * 库存类型
     */
    @ExcelProperty(value = "库存类型")
    private String stockType;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
