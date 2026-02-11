package org.dromara.system.service.impl;

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
import org.dromara.system.domain.bo.SysAddressesBo;
import org.dromara.system.domain.vo.SysAddressesVo;
import org.dromara.system.domain.SysAddresses;
import org.dromara.system.mapper.SysAddressesMapper;
import org.dromara.system.service.ISysAddressesService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 国际地址Service业务层处理
 *
 * @author Lion Li
 * @date 2026-01-05
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysAddressesServiceImpl implements ISysAddressesService {

    private final SysAddressesMapper baseMapper;

    /**
     * 查询国际地址
     *
     * @param addressId 主键
     * @return 国际地址
     */
    @Override
    public SysAddressesVo queryById(Long addressId){
        return baseMapper.selectVoById(addressId);
    }

    /**
     * 分页查询国际地址列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 国际地址分页列表
     */
    @Override
    public TableDataInfo<SysAddressesVo> queryPageList(SysAddressesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysAddresses> lqw = buildQueryWrapper(bo);
        Page<SysAddressesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的国际地址列表
     *
     * @param bo 查询条件
     * @return 国际地址列表
     */
    @Override
    public List<SysAddressesVo> queryList(SysAddressesBo bo) {
        LambdaQueryWrapper<SysAddresses> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysAddresses> buildQueryWrapper(SysAddressesBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysAddresses> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SysAddresses::getAddressId);
        lqw.eq(StringUtils.isNotBlank(bo.getCountryCode()), SysAddresses::getCountryCode, bo.getCountryCode());
        lqw.eq(StringUtils.isNotBlank(bo.getAdministrativeArea()), SysAddresses::getAdministrativeArea, bo.getAdministrativeArea());
        lqw.eq(StringUtils.isNotBlank(bo.getLocality()), SysAddresses::getLocality, bo.getLocality());
        lqw.eq(StringUtils.isNotBlank(bo.getDependentLocality()), SysAddresses::getDependentLocality, bo.getDependentLocality());
        lqw.eq(StringUtils.isNotBlank(bo.getStreetDetail()), SysAddresses::getStreetDetail, bo.getStreetDetail());
        lqw.eq(StringUtils.isNotBlank(bo.getPostalCode()), SysAddresses::getPostalCode, bo.getPostalCode());
        lqw.like(StringUtils.isNotBlank(bo.getAddressesName()), SysAddresses::getAddressesName, bo.getAddressesName());
        lqw.eq(StringUtils.isNotBlank(bo.getPhone()), SysAddresses::getPhone, bo.getPhone());
        lqw.eq(StringUtils.isNotBlank(bo.getOther()), SysAddresses::getOther, bo.getOther());
        lqw.eq(StringUtils.isNotBlank(bo.getFormattedAddress()), SysAddresses::getFormattedAddress, bo.getFormattedAddress());
        lqw.eq(StringUtils.isNotBlank(bo.getLang()), SysAddresses::getLang, bo.getLang());
        lqw.eq(bo.getLatitude() != null, SysAddresses::getLatitude, bo.getLatitude());
        lqw.eq(bo.getLongitude() != null, SysAddresses::getLongitude, bo.getLongitude());
        lqw.eq(StringUtils.isNotBlank(bo.getCoordSystem()), SysAddresses::getCoordSystem, bo.getCoordSystem());
        lqw.eq(bo.getAccuracyMeters() != null, SysAddresses::getAccuracyMeters, bo.getAccuracyMeters());
        return lqw;
    }

    /**
     * 新增国际地址
     *
     * @param bo 国际地址
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SysAddressesBo bo) {
        SysAddresses add = MapstructUtils.convert(bo, SysAddresses.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAddressId(add.getAddressId());
        }
        return flag;
    }

    /**
     * 修改国际地址
     *
     * @param bo 国际地址
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysAddressesBo bo) {
        SysAddresses update = MapstructUtils.convert(bo, SysAddresses.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysAddresses entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除国际地址信息
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
