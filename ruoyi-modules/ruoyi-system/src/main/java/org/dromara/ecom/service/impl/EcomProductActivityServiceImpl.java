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
import org.dromara.ecom.domain.bo.EcomProductActivityBo;
import org.dromara.ecom.domain.vo.EcomProductActivityVo;
import org.dromara.ecom.domain.EcomProductActivity;
import org.dromara.ecom.mapper.EcomProductActivityMapper;
import org.dromara.ecom.service.IEcomProductActivityService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 活动商品关联Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomProductActivityServiceImpl implements IEcomProductActivityService {

    private final EcomProductActivityMapper baseMapper;

    /**
     * 查询活动商品关联
     *
     * @param activityProductId 主键
     * @return 活动商品关联
     */
    @Override
    public EcomProductActivityVo queryById(Long activityProductId){
        return baseMapper.selectVoById(activityProductId);
    }

    /**
     * 分页查询活动商品关联列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 活动商品关联分页列表
     */
    @Override
    public TableDataInfo<EcomProductActivityVo> queryPageList(EcomProductActivityBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomProductActivity> lqw = buildQueryWrapper(bo);
        Page<EcomProductActivityVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的活动商品关联列表
     *
     * @param bo 查询条件
     * @return 活动商品关联列表
     */
    @Override
    public List<EcomProductActivityVo> queryList(EcomProductActivityBo bo) {
        LambdaQueryWrapper<EcomProductActivity> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomProductActivity> buildQueryWrapper(EcomProductActivityBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomProductActivity> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomProductActivity::getActivityProductId);
        lqw.eq(bo.getActivityId() != null, EcomProductActivity::getActivityId, bo.getActivityId());
        lqw.eq(bo.getSpuId() != null, EcomProductActivity::getSpuId, bo.getSpuId());
        lqw.eq(bo.getSkuId() != null, EcomProductActivity::getSkuId, bo.getSkuId());
        lqw.eq(StringUtils.isNotBlank(bo.getOwnerType()), EcomProductActivity::getOwnerType, bo.getOwnerType());
        lqw.eq(bo.getOwnerId() != null, EcomProductActivity::getOwnerId, bo.getOwnerId());
        lqw.eq(StringUtils.isNotBlank(bo.getFulfillmentType()), EcomProductActivity::getFulfillmentType, bo.getFulfillmentType());
        lqw.eq(bo.getFulfillmentId() != null, EcomProductActivity::getFulfillmentId, bo.getFulfillmentId());
        lqw.eq(StringUtils.isNotBlank(bo.getActivityTitle()), EcomProductActivity::getActivityTitle, bo.getActivityTitle());
        lqw.eq(bo.getActivityPrice() != null, EcomProductActivity::getActivityPrice, bo.getActivityPrice());
        lqw.eq(bo.getActivityStock() != null, EcomProductActivity::getActivityStock, bo.getActivityStock());
        lqw.eq(bo.getMinGroupSize() != null, EcomProductActivity::getMinGroupSize, bo.getMinGroupSize());
        lqw.eq(bo.getMaxGroupSize() != null, EcomProductActivity::getMaxGroupSize, bo.getMaxGroupSize());
        lqw.eq(bo.getLimitPerUser() != null, EcomProductActivity::getLimitPerUser, bo.getLimitPerUser());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), EcomProductActivity::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getAuditStatus()), EcomProductActivity::getAuditStatus, bo.getAuditStatus());
        return lqw;
    }

    /**
     * 新增活动商品关联
     *
     * @param bo 活动商品关联
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomProductActivityBo bo) {
        EcomProductActivity add = MapstructUtils.convert(bo, EcomProductActivity.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setActivityProductId(add.getActivityProductId());
        }
        return flag;
    }

    /**
     * 修改活动商品关联
     *
     * @param bo 活动商品关联
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomProductActivityBo bo) {
        EcomProductActivity update = MapstructUtils.convert(bo, EcomProductActivity.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomProductActivity entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除活动商品关联信息
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
