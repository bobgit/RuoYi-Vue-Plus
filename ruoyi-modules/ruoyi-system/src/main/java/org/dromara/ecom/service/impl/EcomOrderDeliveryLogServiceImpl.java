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
import org.dromara.ecom.domain.bo.EcomOrderDeliveryLogBo;
import org.dromara.ecom.domain.vo.EcomOrderDeliveryLogVo;
import org.dromara.ecom.domain.EcomOrderDeliveryLog;
import org.dromara.ecom.mapper.EcomOrderDeliveryLogMapper;
import org.dromara.ecom.service.IEcomOrderDeliveryLogService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 配送状态变更日志Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomOrderDeliveryLogServiceImpl implements IEcomOrderDeliveryLogService {

    private final EcomOrderDeliveryLogMapper baseMapper;

    /**
     * 查询配送状态变更日志
     *
     * @param deliveryLogId 主键
     * @return 配送状态变更日志
     */
    @Override
    public EcomOrderDeliveryLogVo queryById(Long deliveryLogId){
        return baseMapper.selectVoById(deliveryLogId);
    }

    /**
     * 分页查询配送状态变更日志列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 配送状态变更日志分页列表
     */
    @Override
    public TableDataInfo<EcomOrderDeliveryLogVo> queryPageList(EcomOrderDeliveryLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomOrderDeliveryLog> lqw = buildQueryWrapper(bo);
        Page<EcomOrderDeliveryLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的配送状态变更日志列表
     *
     * @param bo 查询条件
     * @return 配送状态变更日志列表
     */
    @Override
    public List<EcomOrderDeliveryLogVo> queryList(EcomOrderDeliveryLogBo bo) {
        LambdaQueryWrapper<EcomOrderDeliveryLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomOrderDeliveryLog> buildQueryWrapper(EcomOrderDeliveryLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomOrderDeliveryLog> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomOrderDeliveryLog::getDeliveryLogId);
        lqw.eq(bo.getDeliveryId() != null, EcomOrderDeliveryLog::getDeliveryId, bo.getDeliveryId());
        lqw.eq(bo.getOrderId() != null, EcomOrderDeliveryLog::getOrderId, bo.getOrderId());
        lqw.eq(StringUtils.isNotBlank(bo.getFromStatus()), EcomOrderDeliveryLog::getFromStatus, bo.getFromStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getToStatus()), EcomOrderDeliveryLog::getToStatus, bo.getToStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getChangeReason()), EcomOrderDeliveryLog::getChangeReason, bo.getChangeReason());
        lqw.eq(StringUtils.isNotBlank(bo.getOperatorType()), EcomOrderDeliveryLog::getOperatorType, bo.getOperatorType());
        return lqw;
    }

    /**
     * 新增配送状态变更日志
     *
     * @param bo 配送状态变更日志
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomOrderDeliveryLogBo bo) {
        EcomOrderDeliveryLog add = MapstructUtils.convert(bo, EcomOrderDeliveryLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setDeliveryLogId(add.getDeliveryLogId());
        }
        return flag;
    }

    /**
     * 修改配送状态变更日志
     *
     * @param bo 配送状态变更日志
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomOrderDeliveryLogBo bo) {
        EcomOrderDeliveryLog update = MapstructUtils.convert(bo, EcomOrderDeliveryLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomOrderDeliveryLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除配送状态变更日志信息
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
