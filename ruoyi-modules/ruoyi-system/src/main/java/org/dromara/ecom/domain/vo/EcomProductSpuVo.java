package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.EcomProductSpu;
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
 * SPU标准产品单元视图对象 ecom_product_spu
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = EcomProductSpu.class)
public class EcomProductSpuVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * SPU ID
     */
    @ExcelProperty(value = "SPU ID")
    private Long spuId;

    /**
     * 分类ID
     */
    @ExcelProperty(value = "分类ID")
    private Long categoryId;

    /**
     * 品牌ID
     */
    @ExcelProperty(value = "品牌ID")
    private Long brandId;

    /**
     * 所属类型
     */
    @ExcelProperty(value = "所属类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "owner_type")
    private String ownerType;

    /**
     * 所属ID
     */
    @ExcelProperty(value = "所属ID")
    private Long ownerId;

    /**
     * 产品业务类型
     */
    @ExcelProperty(value = "产品业务类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_capability_type")
    private String productType;

    /**
     * SPU名称
     */
    @ExcelProperty(value = "SPU名称")
    private String spuName;

    /**
     * SPU描述
     */
    @ExcelProperty(value = "SPU描述")
    private String spuDesc;

    /**
     * 商品打包信息
     */
    @ExcelProperty(value = "商品打包信息")
    private String packingList;

    /**
     * 售后服务
     */
    @ExcelProperty(value = "售后服务")
    private String afterService;

    /**
     * 规格说明
     */
    @ExcelProperty(value = "规格说明")
    private String genericSpec;

    /**
     * SPU图片JSON
     */
    @ExcelProperty(value = "SPU图片JSON")
    private String spuImages;

    /**
     * 最低价格
     */
    @ExcelProperty(value = "最低价格")
    private Long minPrice;

    /**
     * 最高价格
     */
    @ExcelProperty(value = "最高价格")
    private Long maxPrice;

    /**
     * 总销量
     */
    @ExcelProperty(value = "总销量")
    private Long totalSales;

    /**
     * 总库存
     */
    @ExcelProperty(value = "总库存")
    private Long totalStock;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
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
