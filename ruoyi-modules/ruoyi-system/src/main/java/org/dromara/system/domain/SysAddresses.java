package org.dromara.system.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 国际地址对象 sys_addresses
 *
 * @author Lion Li
 * @date 2026-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_addresses")
public class SysAddresses extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 地址ID
     */
    @TableId(value = "address_id")
    private Long addressId;

    /**
     * 国家代码
     */
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
    private String addressesName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 其他附加信息
     */
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
     * 删除标志
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注
     */
    private String remark;


}
