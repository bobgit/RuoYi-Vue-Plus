package org.dromara.ecom.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.dromara.ecom.domain.bo.SysUserAddressBo;
import org.dromara.ecom.domain.vo.SysUserAddressVo;
import org.dromara.ecom.domain.SysUserAddress;
import org.dromara.ecom.mapper.SysUserAddressMapper;
import org.dromara.ecom.service.ISysUserAddressService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户地址关联Service业务层处理
 *
 * @author Bob Ok
 * @date 2026-02-11
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysUserAddressServiceImpl implements ISysUserAddressService {

    private final SysUserAddressMapper baseMapper;

    /**
     * 查询用户地址关联
     *
     * @param userAddressId 主键
     * @return 用户地址关联
     */
    @Override
    public SysUserAddressVo queryById(Long userAddressId){
        return baseMapper.selectVoById(userAddressId);
    }

    /**
     * 分页查询用户地址关联列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 用户地址关联分页列表
     */
    @Override
    public TableDataInfo<SysUserAddressVo> queryPageList(SysUserAddressBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysUserAddress> lqw = buildQueryWrapper(bo);
        Page<SysUserAddressVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的用户地址关联列表
     *
     * @param bo 查询条件
     * @return 用户地址关联列表
     */
    @Override
    public List<SysUserAddressVo> queryList(SysUserAddressBo bo) {
        LambdaQueryWrapper<SysUserAddress> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysUserAddress> buildQueryWrapper(SysUserAddressBo bo) {

        LambdaQueryWrapper<SysUserAddress> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserAddressId() != null, SysUserAddress::getUserAddressId, bo.getUserAddressId());
        lqw.orderByAsc(SysUserAddress::getUserAddressId);
        lqw.eq(bo.getUserId() != null, SysUserAddress::getUserId, bo.getUserId());
        lqw.eq(bo.getAddressId() != null, SysUserAddress::getAddressId, bo.getAddressId());
        lqw.eq(StringUtils.isNotBlank(bo.getIsDefault()), SysUserAddress::getIsDefault, bo.getIsDefault());
        lqw.eq(StringUtils.isNotBlank(bo.getTag()), SysUserAddress::getTag, bo.getTag());
        return lqw;
    }

    /**
     * 新增用户地址关联
     *
     * @param bo 用户地址关联
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SysUserAddressBo bo) {
        SysUserAddress add = MapstructUtils.convert(bo, SysUserAddress.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUserAddressId(add.getUserAddressId());
        }
        return flag;
    }

    /**
     * 修改用户地址关联
     *
     * @param bo 用户地址关联
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysUserAddressBo bo) {
        SysUserAddress update = MapstructUtils.convert(bo, SysUserAddress.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysUserAddress entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除用户地址关联信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
