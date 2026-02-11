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
import org.dromara.ecom.domain.bo.EcomActivityBo;
import org.dromara.ecom.domain.vo.EcomActivityVo;
import org.dromara.ecom.domain.EcomActivity;
import org.dromara.ecom.mapper.EcomActivityMapper;
import org.dromara.ecom.service.IEcomActivityService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 活动Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomActivityServiceImpl implements IEcomActivityService {

    private final EcomActivityMapper baseMapper;

    /**
     * 查询活动
     *
     * @param activityId 主键
     * @return 活动
     */
    @Override
    public EcomActivityVo queryById(Long activityId){
        return baseMapper.selectVoById(activityId);
    }

    /**
     * 分页查询活动列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 活动分页列表
     */
    @Override
    public TableDataInfo<EcomActivityVo> queryPageList(EcomActivityBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomActivity> lqw = buildQueryWrapper(bo);
        Page<EcomActivityVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的活动列表
     *
     * @param bo 查询条件
     * @return 活动列表
     */
    @Override
    public List<EcomActivityVo> queryList(EcomActivityBo bo) {
        LambdaQueryWrapper<EcomActivity> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomActivity> buildQueryWrapper(EcomActivityBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomActivity> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomActivity::getActivityId);
        lqw.like(StringUtils.isNotBlank(bo.getActivityName()), EcomActivity::getActivityName, bo.getActivityName());
        lqw.eq(StringUtils.isNotBlank(bo.getActivityType()), EcomActivity::getActivityType, bo.getActivityType());
        lqw.eq(StringUtils.isNotBlank(bo.getCoverImage()), EcomActivity::getCoverImage, bo.getCoverImage());
        lqw.eq(StringUtils.isNotBlank(bo.getActivityImage()), EcomActivity::getActivityImage, bo.getActivityImage());
        lqw.eq(StringUtils.isNotBlank(bo.getShareTitle()), EcomActivity::getShareTitle, bo.getShareTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getShareDescription()), EcomActivity::getShareDescription, bo.getShareDescription());
        lqw.eq(bo.getStartTime() != null, EcomActivity::getStartTime, bo.getStartTime());
        lqw.eq(bo.getEndTime() != null, EcomActivity::getEndTime, bo.getEndTime());
        lqw.eq(bo.getSuccessCount() != null, EcomActivity::getSuccessCount, bo.getSuccessCount());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), EcomActivity::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getAuditStatus()), EcomActivity::getAuditStatus, bo.getAuditStatus());
        return lqw;
    }

    /**
     * 新增活动
     *
     * @param bo 活动
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomActivityBo bo) {
        EcomActivity add = MapstructUtils.convert(bo, EcomActivity.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setActivityId(add.getActivityId());
        }
        return flag;
    }

    /**
     * 修改活动
     *
     * @param bo 活动
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomActivityBo bo) {
        EcomActivity update = MapstructUtils.convert(bo, EcomActivity.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomActivity entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除活动信息
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
