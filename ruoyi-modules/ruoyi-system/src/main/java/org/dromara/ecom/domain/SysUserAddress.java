package org.dromara.ecom.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户地址关联对象 sys_user_address
 *
 * @author Bob Ok
 * @date 2026-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_address")
public class SysUserAddress  {//extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户地址id
     */
    @TableId(value = "user_address_id")
    private Long userAddressId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 地址ID
     */
    private Long addressId;

    /**
     * 是否默认
     */
    private String isDefault;

    /**
     * 标签
     */
    private String tag;


}
