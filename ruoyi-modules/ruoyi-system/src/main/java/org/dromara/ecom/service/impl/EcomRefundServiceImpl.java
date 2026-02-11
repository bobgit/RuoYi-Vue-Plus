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
import org.dromara.ecom.domain.bo.EcomRefundBo;
import org.dromara.ecom.domain.vo.EcomRefundVo;
import org.dromara.ecom.domain.EcomRefund;
import org.dromara.ecom.mapper.EcomRefundMapper;
import org.dromara.ecom.service.IEcomRefundService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 退款Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-07
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomRefundServiceImpl implements IEcomRefundService {

    private final EcomRefundMapper baseMapper;

    /**
     * 查询退款
     *
     * @param refundId 主键
     * @return 退款
     */
    @Override
    public EcomRefundVo queryById(Long refundId){
        return baseMapper.selectVoById(refundId);
    }

    /**
     * 分页查询退款列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 退款分页列表
     */
    @Override
    public TableDataInfo<EcomRefundVo> queryPageList(EcomRefundBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomRefund> lqw = buildQueryWrapper(bo);
        Page<EcomRefundVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的退款列表
     *
     * @param bo 查询条件
     * @return 退款列表
     */
    @Override
    public List<EcomRefundVo> queryList(EcomRefundBo bo) {
        LambdaQueryWrapper<EcomRefund> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomRefund> buildQueryWrapper(EcomRefundBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomRefund> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getRefundId() != null, EcomRefund::getRefundId, bo.getRefundId());
        lqw.orderByAsc(EcomRefund::getRefundId);
        lqw.eq(bo.getOrderId() != null, EcomRefund::getOrderId, bo.getOrderId());
        lqw.eq(bo.getOrderItemId() != null, EcomRefund::getOrderItemId, bo.getOrderItemId());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundType()), EcomRefund::getRefundType, bo.getRefundType());
        lqw.eq(bo.getRefundAmount() != null, EcomRefund::getRefundAmount, bo.getRefundAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundReason()), EcomRefund::getRefundReason, bo.getRefundReason());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundDesc()), EcomRefund::getRefundDesc, bo.getRefundDesc());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundStatus()), EcomRefund::getRefundStatus, bo.getRefundStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getReverseSettlement()), EcomRefund::getReverseSettlement, bo.getReverseSettlement());
        lqw.eq(StringUtils.isNotBlank(bo.getReverseStock()), EcomRefund::getReverseStock, bo.getReverseStock());
        lqw.eq(bo.getAuditBy() != null, EcomRefund::getAuditBy, bo.getAuditBy());
        lqw.eq(bo.getAuditTime() != null, EcomRefund::getAuditTime, bo.getAuditTime());
        return lqw;
    }

    /**
     * 新增退款
     *
     * @param bo 退款
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomRefundBo bo) {
        EcomRefund add = MapstructUtils.convert(bo, EcomRefund.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRefundId(add.getRefundId());
        }
        return flag;
    }

    /**
     * 修改退款
     *
     * @param bo 退款
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomRefundBo bo) {
        EcomRefund update = MapstructUtils.convert(bo, EcomRefund.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomRefund entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除退款信息
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
