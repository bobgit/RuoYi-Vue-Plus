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
import org.dromara.ecom.domain.bo.EcomSettlementBo;
import org.dromara.ecom.domain.vo.EcomSettlementVo;
import org.dromara.ecom.domain.EcomSettlement;
import org.dromara.ecom.mapper.EcomSettlementMapper;
import org.dromara.ecom.service.IEcomSettlementService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 结算Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomSettlementServiceImpl implements IEcomSettlementService {

    private final EcomSettlementMapper baseMapper;

    /**
     * 查询结算
     *
     * @param settlementId 主键
     * @return 结算
     */
    @Override
    public EcomSettlementVo queryById(Long settlementId){
        return baseMapper.selectVoById(settlementId);
    }

    /**
     * 分页查询结算列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 结算分页列表
     */
    @Override
    public TableDataInfo<EcomSettlementVo> queryPageList(EcomSettlementBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomSettlement> lqw = buildQueryWrapper(bo);
        Page<EcomSettlementVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的结算列表
     *
     * @param bo 查询条件
     * @return 结算列表
     */
    @Override
    public List<EcomSettlementVo> queryList(EcomSettlementBo bo) {
        LambdaQueryWrapper<EcomSettlement> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomSettlement> buildQueryWrapper(EcomSettlementBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomSettlement> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomSettlement::getSettlementId);
        lqw.eq(bo.getOrderId() != null, EcomSettlement::getOrderId, bo.getOrderId());
        lqw.eq(bo.getOrderItemId() != null, EcomSettlement::getOrderItemId, bo.getOrderItemId());
        lqw.eq(StringUtils.isNotBlank(bo.getOwnerType()), EcomSettlement::getOwnerType, bo.getOwnerType());
        lqw.eq(bo.getOwnerId() != null, EcomSettlement::getOwnerId, bo.getOwnerId());
        lqw.eq(bo.getOrderAmount() != null, EcomSettlement::getOrderAmount, bo.getOrderAmount());
        lqw.eq(bo.getPayAmount() != null, EcomSettlement::getPayAmount, bo.getPayAmount());
        lqw.eq(bo.getPlatformFee() != null, EcomSettlement::getPlatformFee, bo.getPlatformFee());
        lqw.eq(bo.getDeliveryFee() != null, EcomSettlement::getDeliveryFee, bo.getDeliveryFee());
        lqw.eq(bo.getCommissionFee() != null, EcomSettlement::getCommissionFee, bo.getCommissionFee());
        lqw.eq(bo.getSubsidyAmount() != null, EcomSettlement::getSubsidyAmount, bo.getSubsidyAmount());
        lqw.eq(bo.getSettlementAmount() != null, EcomSettlement::getSettlementAmount, bo.getSettlementAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getSettlementBatchNo()), EcomSettlement::getSettlementBatchNo, bo.getSettlementBatchNo());
        lqw.eq(bo.getSettlementTime() != null, EcomSettlement::getSettlementTime, bo.getSettlementTime());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), EcomSettlement::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增结算
     *
     * @param bo 结算
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomSettlementBo bo) {
        EcomSettlement add = MapstructUtils.convert(bo, EcomSettlement.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSettlementId(add.getSettlementId());
        }
        return flag;
    }

    /**
     * 修改结算
     *
     * @param bo 结算
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomSettlementBo bo) {
        EcomSettlement update = MapstructUtils.convert(bo, EcomSettlement.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomSettlement entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除结算信息
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
