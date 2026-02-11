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
import org.dromara.ecom.domain.bo.EcomLogisticsHistoryBo;
import org.dromara.ecom.domain.vo.EcomLogisticsHistoryVo;
import org.dromara.ecom.domain.EcomLogisticsHistory;
import org.dromara.ecom.mapper.EcomLogisticsHistoryMapper;
import org.dromara.ecom.service.IEcomLogisticsHistoryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 物流跟踪记录Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-11
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomLogisticsHistoryServiceImpl implements IEcomLogisticsHistoryService {

    private final EcomLogisticsHistoryMapper baseMapper;

    /**
     * 查询物流跟踪记录
     *
     * @param historyId 主键
     * @return 物流跟踪记录
     */
    @Override
    public EcomLogisticsHistoryVo queryById(Long historyId){
        return baseMapper.selectVoById(historyId);
    }

    /**
     * 分页查询物流跟踪记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 物流跟踪记录分页列表
     */
    @Override
    public TableDataInfo<EcomLogisticsHistoryVo> queryPageList(EcomLogisticsHistoryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomLogisticsHistory> lqw = buildQueryWrapper(bo);
        Page<EcomLogisticsHistoryVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的物流跟踪记录列表
     *
     * @param bo 查询条件
     * @return 物流跟踪记录列表
     */
    @Override
    public List<EcomLogisticsHistoryVo> queryList(EcomLogisticsHistoryBo bo) {
        LambdaQueryWrapper<EcomLogisticsHistory> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomLogisticsHistory> buildQueryWrapper(EcomLogisticsHistoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomLogisticsHistory> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getHistoryId() != null, EcomLogisticsHistory::getHistoryId, bo.getHistoryId());
        lqw.orderByAsc(EcomLogisticsHistory::getHistoryId);
        lqw.eq(bo.getOrderId() != null, EcomLogisticsHistory::getOrderId, bo.getOrderId());
        lqw.eq(bo.getNodeTime() != null, EcomLogisticsHistory::getNodeTime, bo.getNodeTime());
        lqw.eq(StringUtils.isNotBlank(bo.getNodeStatus()), EcomLogisticsHistory::getNodeStatus, bo.getNodeStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getNodeLocation()), EcomLogisticsHistory::getNodeLocation, bo.getNodeLocation());
        lqw.eq(StringUtils.isNotBlank(bo.getOperator()), EcomLogisticsHistory::getOperator, bo.getOperator());
        return lqw;
    }

    /**
     * 新增物流跟踪记录
     *
     * @param bo 物流跟踪记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomLogisticsHistoryBo bo) {
        EcomLogisticsHistory add = MapstructUtils.convert(bo, EcomLogisticsHistory.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setHistoryId(add.getHistoryId());
        }
        return flag;
    }

    /**
     * 修改物流跟踪记录
     *
     * @param bo 物流跟踪记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomLogisticsHistoryBo bo) {
        EcomLogisticsHistory update = MapstructUtils.convert(bo, EcomLogisticsHistory.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomLogisticsHistory entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除物流跟踪记录信息
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
