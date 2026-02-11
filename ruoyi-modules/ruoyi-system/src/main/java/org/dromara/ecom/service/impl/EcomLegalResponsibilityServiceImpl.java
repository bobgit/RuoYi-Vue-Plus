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
import org.dromara.ecom.domain.bo.EcomLegalResponsibilityBo;
import org.dromara.ecom.domain.vo.EcomLegalResponsibilityVo;
import org.dromara.ecom.domain.EcomLegalResponsibility;
import org.dromara.ecom.mapper.EcomLegalResponsibilityMapper;
import org.dromara.ecom.service.IEcomLegalResponsibilityService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 法律责任Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomLegalResponsibilityServiceImpl implements IEcomLegalResponsibilityService {

    private final EcomLegalResponsibilityMapper baseMapper;

    /**
     * 查询法律责任
     *
     * @param responsibilityId 主键
     * @return 法律责任
     */
    @Override
    public EcomLegalResponsibilityVo queryById(Long responsibilityId){
        return baseMapper.selectVoById(responsibilityId);
    }

    /**
     * 分页查询法律责任列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 法律责任分页列表
     */
    @Override
    public TableDataInfo<EcomLegalResponsibilityVo> queryPageList(EcomLegalResponsibilityBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomLegalResponsibility> lqw = buildQueryWrapper(bo);
        Page<EcomLegalResponsibilityVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的法律责任列表
     *
     * @param bo 查询条件
     * @return 法律责任列表
     */
    @Override
    public List<EcomLegalResponsibilityVo> queryList(EcomLegalResponsibilityBo bo) {
        LambdaQueryWrapper<EcomLegalResponsibility> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomLegalResponsibility> buildQueryWrapper(EcomLegalResponsibilityBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomLegalResponsibility> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomLegalResponsibility::getResponsibilityId);
        lqw.eq(bo.getOrderId() != null, EcomLegalResponsibility::getOrderId, bo.getOrderId());
        lqw.eq(bo.getOrderItemId() != null, EcomLegalResponsibility::getOrderItemId, bo.getOrderItemId());
        lqw.eq(bo.getFulfillmentTaskId() != null, EcomLegalResponsibility::getFulfillmentTaskId, bo.getFulfillmentTaskId());
        lqw.eq(StringUtils.isNotBlank(bo.getResponsibilityOrgType()), EcomLegalResponsibility::getResponsibilityOrgType, bo.getResponsibilityOrgType());
        lqw.eq(bo.getResponsibilityOrgId() != null, EcomLegalResponsibility::getResponsibilityOrgId, bo.getResponsibilityOrgId());
        lqw.eq(StringUtils.isNotBlank(bo.getResponsibilityScope()), EcomLegalResponsibility::getResponsibilityScope, bo.getResponsibilityScope());
        lqw.eq(bo.getEffectiveTime() != null, EcomLegalResponsibility::getEffectiveTime, bo.getEffectiveTime());
        lqw.eq(StringUtils.isNotBlank(bo.getFrozen()), EcomLegalResponsibility::getFrozen, bo.getFrozen());
        return lqw;
    }

    /**
     * 新增法律责任
     *
     * @param bo 法律责任
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomLegalResponsibilityBo bo) {
        EcomLegalResponsibility add = MapstructUtils.convert(bo, EcomLegalResponsibility.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setResponsibilityId(add.getResponsibilityId());
        }
        return flag;
    }

    /**
     * 修改法律责任
     *
     * @param bo 法律责任
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomLegalResponsibilityBo bo) {
        EcomLegalResponsibility update = MapstructUtils.convert(bo, EcomLegalResponsibility.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomLegalResponsibility entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除法律责任信息
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
