package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.SysUserAddressVo;
import org.dromara.ecom.domain.bo.SysUserAddressBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 用户地址关联Service接口
 *
 * @author Bob Ok
 * @date 2026-02-11
 */
public interface ISysUserAddressService {

    /**
     * 查询用户地址关联
     *
     * @param userAddressId 主键
     * @return 用户地址关联
     */
    SysUserAddressVo queryById(Long userAddressId);

    /**
     * 分页查询用户地址关联列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 用户地址关联分页列表
     */
    TableDataInfo<SysUserAddressVo> queryPageList(SysUserAddressBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的用户地址关联列表
     *
     * @param bo 查询条件
     * @return 用户地址关联列表
     */
    List<SysUserAddressVo> queryList(SysUserAddressBo bo);

    /**
     * 新增用户地址关联
     *
     * @param bo 用户地址关联
     * @return 是否新增成功
     */
    Boolean insertByBo(SysUserAddressBo bo);

    /**
     * 修改用户地址关联
     *
     * @param bo 用户地址关联
     * @return 是否修改成功
     */
    Boolean updateByBo(SysUserAddressBo bo);

    /**
     * 校验并批量删除用户地址关联信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
