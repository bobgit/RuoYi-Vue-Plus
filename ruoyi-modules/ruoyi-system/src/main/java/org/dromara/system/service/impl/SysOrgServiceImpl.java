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
import org.dromara.system.domain.bo.SysOrgBo;
import org.dromara.system.domain.vo.SysOrgVo;
import org.dromara.system.domain.SysOrg;
import org.dromara.system.mapper.SysOrgMapper;
import org.dromara.system.service.ISysOrgService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 机构组织公司Service业务层处理
 *
 * @author Bob Ok
 * @date 2026-02-11
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysOrgServiceImpl implements ISysOrgService {

    private final SysOrgMapper baseMapper;

    /**
     * 查询机构组织公司
     *
     * @param orgId 主键
     * @return 机构组织公司
     */
    @Override
    public SysOrgVo queryById(Long orgId){
        return baseMapper.selectVoById(orgId);
    }

    /**
     * 分页查询机构组织公司列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 机构组织公司分页列表
     */
    @Override
    public TableDataInfo<SysOrgVo> queryPageList(SysOrgBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysOrg> lqw = buildQueryWrapper(bo);
        Page<SysOrgVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的机构组织公司列表
     *
     * @param bo 查询条件
     * @return 机构组织公司列表
     */
    @Override
    public List<SysOrgVo> queryList(SysOrgBo bo) {
        LambdaQueryWrapper<SysOrg> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysOrg> buildQueryWrapper(SysOrgBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysOrg> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SysOrg::getOrgId);
        lqw.eq(StringUtils.isNotBlank(bo.getOrgCode()), SysOrg::getOrgCode, bo.getOrgCode());
        lqw.eq(StringUtils.isNotBlank(bo.getOrgFullCode()), SysOrg::getOrgFullCode, bo.getOrgFullCode());
        lqw.like(StringUtils.isNotBlank(bo.getOrgName()), SysOrg::getOrgName, bo.getOrgName());
        lqw.eq(StringUtils.isNotBlank(bo.getOrgType()), SysOrg::getOrgType, bo.getOrgType());
        lqw.eq(StringUtils.isNotBlank(bo.getCapabilityType()), SysOrg::getCapabilityType, bo.getCapabilityType());
        lqw.eq(StringUtils.isNotBlank(bo.getPrincipal()), SysOrg::getPrincipal, bo.getPrincipal());
        lqw.eq(StringUtils.isNotBlank(bo.getPhone()), SysOrg::getPhone, bo.getPhone());
        lqw.eq(StringUtils.isNotBlank(bo.getEmail()), SysOrg::getEmail, bo.getEmail());
        lqw.eq(bo.getLogo() != null, SysOrg::getLogo, bo.getLogo());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), SysOrg::getDescription, bo.getDescription());
        lqw.eq(StringUtils.isNotBlank(bo.getBusinessHours()), SysOrg::getBusinessHours, bo.getBusinessHours());
        lqw.eq(bo.getDeliveryRadius() != null, SysOrg::getDeliveryRadius, bo.getDeliveryRadius());
        lqw.eq(StringUtils.isNotBlank(bo.getOrgStatus()), SysOrg::getOrgStatus, bo.getOrgStatus());
        lqw.eq(bo.getParentId() != null, SysOrg::getParentId, bo.getParentId());
        lqw.eq(bo.getUserId() != null, SysOrg::getUserId, bo.getUserId());
        lqw.like(StringUtils.isNotBlank(bo.getUsername()), SysOrg::getUsername, bo.getUsername());
        lqw.eq(StringUtils.isNotBlank(bo.getFormattedAddress()), SysOrg::getFormattedAddress, bo.getFormattedAddress());
        lqw.eq(bo.getLatitude() != null, SysOrg::getLatitude, bo.getLatitude());
        lqw.eq(bo.getLongitude() != null, SysOrg::getLongitude, bo.getLongitude());
        lqw.eq(bo.getAddressId() != null, SysOrg::getAddressId, bo.getAddressId());
        lqw.eq(StringUtils.isNotBlank(bo.getOperationStatus()), SysOrg::getOperationStatus, bo.getOperationStatus());
        return lqw;
    }

    /**
     * 新增机构组织公司
     *
     * @param bo 机构组织公司
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SysOrgBo bo) {
        SysOrg add = MapstructUtils.convert(bo, SysOrg.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setOrgId(add.getOrgId());
        }
        return flag;
    }

    /**
     * 修改机构组织公司
     *
     * @param bo 机构组织公司
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysOrgBo bo) {
        SysOrg update = MapstructUtils.convert(bo, SysOrg.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysOrg entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除机构组织公司信息
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
