package org.dromara.ecom.domain.vo;

import org.dromara.ecom.domain.SysUserAddress;
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
 * 用户地址关联视图对象 sys_user_address
 *
 * @author Bob Ok
 * @date 2026-02-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysUserAddress.class)
public class SysUserAddressVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户地址id
     */
    @ExcelProperty(value = "用户地址id")
    private Long userAddressId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
     * 地址ID
     */
    @ExcelProperty(value = "地址ID")
    private Long addressId;

    /**
     * 是否默认
     */
    @ExcelProperty(value = "是否默认", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String isDefault;

    /**
     * 标签
     */
    @ExcelProperty(value = "标签")
    private String tag;


}
