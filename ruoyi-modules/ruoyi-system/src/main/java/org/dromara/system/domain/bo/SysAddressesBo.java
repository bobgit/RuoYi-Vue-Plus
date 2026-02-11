package org.dromara.system.domain.bo;

import org.dromara.system.domain.SysAddresses;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 国际地址业务对象 sys_addresses
 *
 * @author Lion Li
 * @date 2026-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysAddresses.class, reverseConvertGenerate = false)
public class SysAddressesBo extends BaseEntity {

    /**
     * 地址ID
     */
    @NotNull(message = "地址ID不能为空", groups = { EditGroup.class })
    private Long addressId;

    /**
     * 国家代码
     */
    @NotBlank(message = "国家代码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String countryCode;

    /**
     * 都道府县
     */
    private String administrativeArea;

    /**
     * 城市
     */
    private String locality;

    /**
     * 市/区/郡
     */
    private String dependentLocality;

    /**
     * 街道
     */
    private String streetDetail;

    /**
     * 邮编
     */
    private String postalCode;

    /**
     * 地址名
     */
    @NotBlank(message = "地址名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String addressesName;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空", groups = { AddGroup.class, EditGroup.class })
    private String phone;

    /**
     * 其他附加信息
     */
    @NotBlank(message = "其他附加信息不能为空", groups = { AddGroup.class, EditGroup.class })
    private String other;

    /**
     * 格式化地址
     */
    private String formattedAddress;

    /**
     * 语言类型
     */
    private String lang;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 坐标系统类型
     */
    private String coordSystem;

    /**
     * 精度
     */
    private Long accuracyMeters;

    /**
     * 备注
     */
    private String remark;


}
