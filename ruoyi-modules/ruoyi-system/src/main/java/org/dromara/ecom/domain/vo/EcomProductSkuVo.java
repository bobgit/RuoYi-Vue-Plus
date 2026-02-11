package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.EcomProductSku;
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
 * SKU库存单元视图对象 ecom_product_sku
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomProductSku.class)
public class EcomProductSkuVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * SKU ID
     */
    @ExcelProperty(value = "SKU ID")
    private Long skuId;

    /**
     * SPU ID
     */
    @ExcelProperty(value = "SPU ID")
    private Long spuId;

    /**
     * SKU名称
     */
    @ExcelProperty(value = "SKU名称")
    private String skuName;

    /**
     * 规格JSON
     */
    @ExcelProperty(value = "规格JSON")
    private String skuSpec;

    /**
     * 成本价格
     */
    @ExcelProperty(value = "成本价格")
    private Long costPrice;

    /**
     * 市场价
     */
    @ExcelProperty(value = "市场价")
    private Long marketPrice;

    /**
     * 销售价
     */
    @ExcelProperty(value = "销售价")
    private Long price;

    /**
     * 库存策略类型
     */
    @ExcelProperty(value = "库存策略类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "stock_policy_type")
    private String stockPolicyType;

    /**
     * 单品销量
     */
    @ExcelProperty(value = "单品销量")
    private Long stockQuantity;

    /**
     * 已售数量
     */
    @ExcelProperty(value = "已售数量")
    private Long soldQuantity;

    /**
     * 重量
     */
    @ExcelProperty(value = "重量")
    private Long weight;

    /**
     * 长
     */
    @ExcelProperty(value = "长")
    private Long length;

    /**
     * 宽
     */
    @ExcelProperty(value = "宽")
    private Long width;

    /**
     * 高
     */
    @ExcelProperty(value = "高")
    private Long height;

    /**
     * 二维码
     */
    @ExcelProperty(value = "二维码")
    private String barCode;

    /**
     * 编码
     */
    @ExcelProperty(value = "编码")
    private String skuCode;

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
