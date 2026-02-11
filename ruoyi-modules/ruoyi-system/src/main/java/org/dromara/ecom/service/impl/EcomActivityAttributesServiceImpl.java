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
import org.dromara.ecom.domain.bo.EcomActivityAttributesBo;
import org.dromara.ecom.domain.vo.EcomActivityAttributesVo;
import org.dromara.ecom.domain.EcomActivityAttributes;
import org.dromara.ecom.mapper.EcomActivityAttributesMapper;
import org.dromara.ecom.service.IEcomActivityAttributesService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 活动属性Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomActivityAttributesServiceImpl implements IEcomActivityAttributesService {

    private final EcomActivityAttributesMapper baseMapper;

    /**
     * 查询活动属性
     *
     * @param attrId 主键
     * @return 活动属性
     */
    @Override
    public EcomActivityAttributesVo queryById(Long attrId){
        return baseMapper.selectVoById(attrId);
    }

    /**
     * 分页查询活动属性列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 活动属性分页列表
     */
    @Override
    public TableDataInfo<EcomActivityAttributesVo> queryPageList(EcomActivityAttributesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomActivityAttributes> lqw = buildQueryWrapper(bo);
        Page<EcomActivityAttributesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的活动属性列表
     *
     * @param bo 查询条件
     * @return 活动属性列表
     */
    @Override
    public List<EcomActivityAttributesVo> queryList(EcomActivityAttributesBo bo) {
        LambdaQueryWrapper<EcomActivityAttributes> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomActivityAttributes> buildQueryWrapper(EcomActivityAttributesBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomActivityAttributes> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomActivityAttributes::getAttrId);
        lqw.eq(bo.getActivityId() != null, EcomActivityAttributes::getActivityId, bo.getActivityId());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrKey()), EcomActivityAttributes::getAttrKey, bo.getAttrKey());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrValue()), EcomActivityAttributes::getAttrValue, bo.getAttrValue());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrType()), EcomActivityAttributes::getAttrType, bo.getAttrType());
        return lqw;
    }

    /**
     * 新增活动属性
     *
     * @param bo 活动属性
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomActivityAttributesBo bo) {
        EcomActivityAttributes add = MapstructUtils.convert(bo, EcomActivityAttributes.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAttrId(add.getAttrId());
        }
        return flag;
    }

    /**
     * 修改活动属性
     *
     * @param bo 活动属性
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomActivityAttributesBo bo) {
        EcomActivityAttributes update = MapstructUtils.convert(bo, EcomActivityAttributes.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomActivityAttributes entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除活动属性信息
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
