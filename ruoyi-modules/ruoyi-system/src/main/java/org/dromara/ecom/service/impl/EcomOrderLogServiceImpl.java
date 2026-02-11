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
import org.dromara.ecom.domain.bo.EcomOrderLogBo;
import org.dromara.ecom.domain.vo.EcomOrderLogVo;
import org.dromara.ecom.domain.EcomOrderLog;
import org.dromara.ecom.mapper.EcomOrderLogMapper;
import org.dromara.ecom.service.IEcomOrderLogService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 订单变更动态日志Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomOrderLogServiceImpl implements IEcomOrderLogService {

    private final EcomOrderLogMapper baseMapper;

    /**
     * 查询订单变更动态日志
     *
     * @param orderLogId 主键
     * @return 订单变更动态日志
     */
    @Override
    public EcomOrderLogVo queryById(Long orderLogId){
        return baseMapper.selectVoById(orderLogId);
    }

    /**
     * 分页查询订单变更动态日志列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 订单变更动态日志分页列表
     */
    @Override
    public TableDataInfo<EcomOrderLogVo> queryPageList(EcomOrderLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomOrderLog> lqw = buildQueryWrapper(bo);
        Page<EcomOrderLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的订单变更动态日志列表
     *
     * @param bo 查询条件
     * @return 订单变更动态日志列表
     */
    @Override
    public List<EcomOrderLogVo> queryList(EcomOrderLogBo bo) {
        LambdaQueryWrapper<EcomOrderLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomOrderLog> buildQueryWrapper(EcomOrderLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomOrderLog> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomOrderLog::getOrderLogId);
        lqw.eq(bo.getOrderId() != null, EcomOrderLog::getOrderId, bo.getOrderId());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderEventType()), EcomOrderLog::getOrderEventType, bo.getOrderEventType());
        lqw.eq(StringUtils.isNotBlank(bo.getFromStatus()), EcomOrderLog::getFromStatus, bo.getFromStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getToStatus()), EcomOrderLog::getToStatus, bo.getToStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getChangeReason()), EcomOrderLog::getChangeReason, bo.getChangeReason());
        lqw.eq(StringUtils.isNotBlank(bo.getOperatorType()), EcomOrderLog::getOperatorType, bo.getOperatorType());
        return lqw;
    }

    /**
     * 新增订单变更动态日志
     *
     * @param bo 订单变更动态日志
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomOrderLogBo bo) {
        EcomOrderLog add = MapstructUtils.convert(bo, EcomOrderLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setOrderLogId(add.getOrderLogId());
        }
        return flag;
    }

    /**
     * 修改订单变更动态日志
     *
     * @param bo 订单变更动态日志
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomOrderLogBo bo) {
        EcomOrderLog update = MapstructUtils.convert(bo, EcomOrderLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomOrderLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除订单变更动态日志信息
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
