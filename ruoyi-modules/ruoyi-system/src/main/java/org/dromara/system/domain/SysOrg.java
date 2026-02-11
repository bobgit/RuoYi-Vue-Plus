package org.dromara.system.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 机构组织公司对象 sys_org
 *
 * @author Bob Ok
 * @date 2026-02-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_org")
public class SysOrg extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 机构组织公司ID
     */
    @TableId(value = "org_id")
    private Long orgId;

    /**
     * 机构组织公司编码
     */
    private String orgCode;

    /**
     * 机构组织公司完整编码
     */
    private String orgFullCode;

    /**
     * 机构组织公司名称
     */
    private String orgName;

    /**
     * 机构组织公司类型
     */
    private String orgType;

    /**
     * 业务类型
     */
    private String capabilityType;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * LOGO
     */
    private Long logo;

    /**
     * 简介
     */
    private String description;

    /**
     * 营业时间
     */
    private String businessHours;

    /**
     * 配送范围
     */
    private Long deliveryRadius;

    /**
     * 主体状态
     */
    private String orgStatus;

    /**
     * 父级机构
     */
    private Long parentId;

    /**
     * 管理员用户ID
     */
    private Long userId;

    /**
     * 管理员用户名
     */
    private String username;

    /**
     * 格式化地址
     */
    private String formattedAddress;

    /**
     * 纬度
     */
    private Long latitude;

    /**
     * 经度
     */
    private Long longitude;

    /**
     * 版本号
     */
    @Version
    private Long version;

    /**
     * 国际化地址
     */
    private Long addressId;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 营业状态
     */
    private String operationStatus;


}
