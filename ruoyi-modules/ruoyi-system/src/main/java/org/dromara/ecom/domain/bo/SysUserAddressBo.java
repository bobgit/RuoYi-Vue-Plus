package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.SysUserAddress;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 用户地址关联业务对象 sys_user_address
 *
 * @author Bob Ok
 * @date 2026-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AutoMapper(target = SysUserAddress.class, reverseConvertGenerate = false)
public class SysUserAddressBo  {// extends BaseEntity {

    /**
     * 用户地址id
     */
    @NotNull(message = "用户地址id不能为空", groups = { EditGroup.class })
    private Long userAddressId;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 地址ID
     */
    @NotNull(message = "地址ID不能为空", groups = { AddGroup.class, EditGroup.class })
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
