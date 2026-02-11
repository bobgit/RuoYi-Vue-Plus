package org.dromara.system.domain.bo;

import org.dromara.system.domain.SysOrg;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 机构组织公司业务对象 sys_org
 *
 * @author Bob Ok
 * @date 2026-02-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysOrg.class, reverseConvertGenerate = false)
public class SysOrgBo extends BaseEntity {

    /**
     * 机构组织公司ID
     */
    @NotNull(message = "机构组织公司ID不能为空", groups = { EditGroup.class })
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
    @NotBlank(message = "机构组织公司名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgName;

    /**
     * 机构组织公司类型
     */
    @NotBlank(message = "机构组织公司类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orgType;

    /**
     * 业务类型
     */
    @NotBlank(message = "业务类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String capabilityType;

    /**
     * 负责人
     */
    @NotBlank(message = "负责人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String principal;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "父级机构不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long parentId;

    /**
     * 管理员用户ID
     */
    @NotNull(message = "管理员用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 管理员用户名
     */
    @NotBlank(message = "管理员用户名不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 国际化地址
     */
    private Long addressId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 营业状态
     */
    private String operationStatus;


}
