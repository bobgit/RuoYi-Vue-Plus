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
import org.dromara.ecom.domain.bo.EcomFulfillmentTaskBo;
import org.dromara.ecom.domain.vo.EcomFulfillmentTaskVo;
import org.dromara.ecom.domain.EcomFulfillmentTask;
import org.dromara.ecom.mapper.EcomFulfillmentTaskMapper;
import org.dromara.ecom.service.IEcomFulfillmentTaskService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 履约单Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomFulfillmentTaskServiceImpl implements IEcomFulfillmentTaskService {

    private final EcomFulfillmentTaskMapper baseMapper;

    /**
     * 查询履约单
     *
     * @param fulfillmentTaskId 主键
     * @return 履约单
     */
    @Override
    public EcomFulfillmentTaskVo queryById(Long fulfillmentTaskId){
        return baseMapper.selectVoById(fulfillmentTaskId);
    }

    /**
     * 分页查询履约单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 履约单分页列表
     */
    @Override
    public TableDataInfo<EcomFulfillmentTaskVo> queryPageList(EcomFulfillmentTaskBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomFulfillmentTask> lqw = buildQueryWrapper(bo);
        Page<EcomFulfillmentTaskVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的履约单列表
     *
     * @param bo 查询条件
     * @return 履约单列表
     */
    @Override
    public List<EcomFulfillmentTaskVo> queryList(EcomFulfillmentTaskBo bo) {
        LambdaQueryWrapper<EcomFulfillmentTask> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomFulfillmentTask> buildQueryWrapper(EcomFulfillmentTaskBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomFulfillmentTask> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomFulfillmentTask::getFulfillmentTaskId);
        lqw.eq(bo.getOrderId() != null, EcomFulfillmentTask::getOrderId, bo.getOrderId());
        lqw.eq(bo.getOrderItemId() != null, EcomFulfillmentTask::getOrderItemId, bo.getOrderItemId());
        lqw.eq(StringUtils.isNotBlank(bo.getFulfillmentType()), EcomFulfillmentTask::getFulfillmentType, bo.getFulfillmentType());
        lqw.eq(bo.getFulfillmentId() != null, EcomFulfillmentTask::getFulfillmentId, bo.getFulfillmentId());
        lqw.eq(StringUtils.isNotBlank(bo.getResponsibleType()), EcomFulfillmentTask::getResponsibleType, bo.getResponsibleType());
        lqw.eq(bo.getResponsibleId() != null, EcomFulfillmentTask::getResponsibleId, bo.getResponsibleId());
        lqw.eq(StringUtils.isNotBlank(bo.getLogisticsCompany()), EcomFulfillmentTask::getLogisticsCompany, bo.getLogisticsCompany());
        lqw.eq(StringUtils.isNotBlank(bo.getLogisticsNo()), EcomFulfillmentTask::getLogisticsNo, bo.getLogisticsNo());
        lqw.eq(bo.getRiderId() != null, EcomFulfillmentTask::getRiderId, bo.getRiderId());
        lqw.eq(StringUtils.isNotBlank(bo.getPickupCode()), EcomFulfillmentTask::getPickupCode, bo.getPickupCode());
        lqw.eq(StringUtils.isNotBlank(bo.getVerifyCode()), EcomFulfillmentTask::getVerifyCode, bo.getVerifyCode());
        lqw.eq(bo.getPriority() != null, EcomFulfillmentTask::getPriority, bo.getPriority());
        lqw.eq(StringUtils.isNotBlank(bo.getFulfillmentStatus()), EcomFulfillmentTask::getFulfillmentStatus, bo.getFulfillmentStatus());
        lqw.eq(bo.getAssignTime() != null, EcomFulfillmentTask::getAssignTime, bo.getAssignTime());
        lqw.eq(bo.getStartTime() != null, EcomFulfillmentTask::getStartTime, bo.getStartTime());
        lqw.eq(bo.getCompleteTime() != null, EcomFulfillmentTask::getCompleteTime, bo.getCompleteTime());
        lqw.eq(bo.getCancelTime() != null, EcomFulfillmentTask::getCancelTime, bo.getCancelTime());
        return lqw;
    }

    /**
     * 新增履约单
     *
     * @param bo 履约单
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomFulfillmentTaskBo bo) {
        EcomFulfillmentTask add = MapstructUtils.convert(bo, EcomFulfillmentTask.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setFulfillmentTaskId(add.getFulfillmentTaskId());
        }
        return flag;
    }

    /**
     * 修改履约单
     *
     * @param bo 履约单
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomFulfillmentTaskBo bo) {
        EcomFulfillmentTask update = MapstructUtils.convert(bo, EcomFulfillmentTask.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomFulfillmentTask entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除履约单信息
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
