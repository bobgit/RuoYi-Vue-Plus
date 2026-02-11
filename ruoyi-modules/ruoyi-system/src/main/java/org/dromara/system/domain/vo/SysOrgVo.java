package org.dromara.system.domain.vo;

import org.dromara.system.domain.SysOrg;
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
 * 机构组织公司视图对象 sys_org
 *
 * @author Bob Ok
 * @date 2026-02-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysOrg.class)
public class SysOrgVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 机构组织公司ID
     */
    @ExcelProperty(value = "机构组织公司ID")
    private Long orgId;

    /**
     * 机构组织公司编码
     */
    @ExcelProperty(value = "机构组织公司编码")
    private String orgCode;

    /**
     * 机构组织公司完整编码
     */
    @ExcelProperty(value = "机构组织公司完整编码")
    private String orgFullCode;

    /**
     * 机构组织公司名称
     */
    @ExcelProperty(value = "机构组织公司名称")
    private String orgName;

    /**
     * 机构组织公司类型
     */
    @ExcelProperty(value = "机构组织公司类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_org_type")
    private String orgType;

    /**
     * 业务类型
     */
    @ExcelProperty(value = "业务类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_capability_type")
    private String capabilityType;

    /**
     * 负责人
     */
    @ExcelProperty(value = "负责人")
    private String principal;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String phone;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱")
    private String email;

    /**
     * LOGO
     */
    @ExcelProperty(value = "LOGO")
    private Long logo;

    /**
     * 简介
     */
    @ExcelProperty(value = "简介")
    private String description;

    /**
     * 营业时间
     */
    @ExcelProperty(value = "营业时间")
    private String businessHours;

    /**
     * 配送范围
     */
    @ExcelProperty(value = "配送范围")
    private Long deliveryRadius;

    /**
     * 主体状态
     */
    @ExcelProperty(value = "主体状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_org_status")
    private String orgStatus;

    /**
     * 父级机构
     */
    @ExcelProperty(value = "父级机构")
    private Long parentId;

    /**
     * 管理员用户ID
     */
    @ExcelProperty(value = "管理员用户ID")
    private Long userId;

    /**
     * 管理员用户名
     */
    @ExcelProperty(value = "管理员用户名")
    private String username;

    /**
     * 格式化地址
     */
    @ExcelProperty(value = "格式化地址")
    private String formattedAddress;

    /**
     * 纬度
     */
    @ExcelProperty(value = "纬度")
    private Long latitude;

    /**
     * 经度
     */
    @ExcelProperty(value = "经度")
    private Long longitude;

    /**
     * 国际化地址
     */
    @ExcelProperty(value = "国际化地址")
    private Long addressId;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 营业状态
     */
    @ExcelProperty(value = "营业状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "org_operation_status")
    private String operationStatus;


}
