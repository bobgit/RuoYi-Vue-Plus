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
import org.dromara.ecom.domain.bo.EcomProductOrgBo;
import org.dromara.ecom.domain.vo.EcomProductOrgVo;
import org.dromara.ecom.domain.EcomProductOrg;
import org.dromara.ecom.mapper.EcomProductOrgMapper;
import org.dromara.ecom.service.IEcomProductOrgService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 机构组织商品Service业务层处理
 *
 * @author Bob Ok
 * @date 2026-01-27
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomProductOrgServiceImpl implements IEcomProductOrgService {

    private final EcomProductOrgMapper baseMapper;

    /**
     * 查询机构组织商品
     *
     * @param orgProductId 主键
     * @return 机构组织商品
     */
    @Override
    public EcomProductOrgVo queryById(Long orgProductId){
        return baseMapper.selectVoById(orgProductId);
    }

    /**
     * 分页查询机构组织商品列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 机构组织商品分页列表
     */
    @Override
    public TableDataInfo<EcomProductOrgVo> queryPageList(EcomProductOrgBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomProductOrg> lqw = buildQueryWrapper(bo);
        Page<EcomProductOrgVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的机构组织商品列表
     *
     * @param bo 查询条件
     * @return 机构组织商品列表
     */
    @Override
    public List<EcomProductOrgVo> queryList(EcomProductOrgBo bo) {
        LambdaQueryWrapper<EcomProductOrg> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomProductOrg> buildQueryWrapper(EcomProductOrgBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomProductOrg> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomProductOrg::getOrgProductId);
        lqw.eq(bo.getOrgId() != null, EcomProductOrg::getOrgId, bo.getOrgId());
        lqw.eq(bo.getSkuId() != null, EcomProductOrg::getSkuId, bo.getSkuId());
        lqw.eq(StringUtils.isNotBlank(bo.getOwnerType()), EcomProductOrg::getOwnerType, bo.getOwnerType());
        lqw.eq(bo.getOwnerId() != null, EcomProductOrg::getOwnerId, bo.getOwnerId());
        lqw.eq(StringUtils.isNotBlank(bo.getFulfillmentType()), EcomProductOrg::getFulfillmentType, bo.getFulfillmentType());
        lqw.eq(bo.getFulfillmentId() != null, EcomProductOrg::getFulfillmentId, bo.getFulfillmentId());
        lqw.eq(bo.getExpireTime() != null, EcomProductOrg::getExpireTime, bo.getExpireTime());
        lqw.eq(StringUtils.isNotBlank(bo.getOrgTitle()), EcomProductOrg::getOrgTitle, bo.getOrgTitle());
        lqw.eq(bo.getOrgPrice() != null, EcomProductOrg::getOrgPrice, bo.getOrgPrice());
        lqw.eq(bo.getOrgStock() != null, EcomProductOrg::getOrgStock, bo.getOrgStock());
        return lqw;
    }

    /**
     * 新增机构组织商品
     *
     * @param bo 机构组织商品
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomProductOrgBo bo) {
        EcomProductOrg add = MapstructUtils.convert(bo, EcomProductOrg.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setOrgProductId(add.getOrgProductId());
        }
        return flag;
    }

    /**
     * 修改机构组织商品
     *
     * @param bo 机构组织商品
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomProductOrgBo bo) {
        EcomProductOrg update = MapstructUtils.convert(bo, EcomProductOrg.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomProductOrg entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除机构组织商品信息
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
