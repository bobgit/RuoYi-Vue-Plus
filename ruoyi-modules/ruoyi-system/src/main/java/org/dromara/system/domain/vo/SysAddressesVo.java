package org.dromara.system.domain.vo;

import org.dromara.system.domain.SysAddresses;
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
 * 国际地址视图对象 sys_addresses
 *
 * @author Lion Li
 * @date 2026-01-05
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysAddresses.class)
public class SysAddressesVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 地址ID
     */
    @ExcelProperty(value = "地址ID")
    private Long addressId;

    /**
     * 国家代码
     */
    @ExcelProperty(value = "国家代码", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_ios_country")
    private String countryCode;

    /**
     * 都道府县
     */
    @ExcelProperty(value = "都道府县")
    private String administrativeArea;

    /**
     * 城市
     */
    @ExcelProperty(value = "城市")
    private String locality;

    /**
     * 市/区/郡
     */
    @ExcelProperty(value = "市/区/郡")
    private String dependentLocality;

    /**
     * 街道
     */
    @ExcelProperty(value = "街道")
    private String streetDetail;

    /**
     * 邮编
     */
    @ExcelProperty(value = "邮编")
    private String postalCode;

    /**
     * 地址名
     */
    @ExcelProperty(value = "地址名")
    private String addressesName;

    /**
     * 电话
     */
    @ExcelProperty(value = "电话")
    private String phone;

    /**
     * 其他附加信息
     */
    @ExcelProperty(value = "其他附加信息")
    private String other;

    /**
     * 格式化地址
     */
    @ExcelProperty(value = "格式化地址")
    private String formattedAddress;

    /**
     * 语言类型
     */
    @ExcelProperty(value = "语言类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_ios_country")
    private String lang;

    /**
     * 纬度
     */
    @ExcelProperty(value = "纬度")
    private Double latitude;

    /**
     * 经度
     */
    @ExcelProperty(value = "经度")
    private Double longitude;

    /**
     * 坐标系统类型
     */
    @ExcelProperty(value = "坐标系统类型")
    private String coordSystem;

    /**
     * 精度
     */
    @ExcelProperty(value = "精度")
    private Long accuracyMeters;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
