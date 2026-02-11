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
import org.dromara.ecom.domain.bo.EcomOrderItemBo;
import org.dromara.ecom.domain.vo.EcomOrderItemVo;
import org.dromara.ecom.domain.EcomOrderItem;
import org.dromara.ecom.mapper.EcomOrderItemMapper;
import org.dromara.ecom.service.IEcomOrderItemService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 订单明细Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomOrderItemServiceImpl implements IEcomOrderItemService {

    private final EcomOrderItemMapper baseMapper;

    /**
     * 查询订单明细
     *
     * @param itemId 主键
     * @return 订单明细
     */
    @Override
    public EcomOrderItemVo queryById(Long itemId){
        return baseMapper.selectVoById(itemId);
    }

    /**
     * 分页查询订单明细列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 订单明细分页列表
     */
    @Override
    public TableDataInfo<EcomOrderItemVo> queryPageList(EcomOrderItemBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomOrderItem> lqw = buildQueryWrapper(bo);
        Page<EcomOrderItemVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的订单明细列表
     *
     * @param bo 查询条件
     * @return 订单明细列表
     */
    @Override
    public List<EcomOrderItemVo> queryList(EcomOrderItemBo bo) {
        LambdaQueryWrapper<EcomOrderItem> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomOrderItem> buildQueryWrapper(EcomOrderItemBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomOrderItem> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomOrderItem::getItemId);
        lqw.eq(bo.getOrderId() != null, EcomOrderItem::getOrderId, bo.getOrderId());
        lqw.eq(bo.getActivityProductId() != null, EcomOrderItem::getActivityProductId, bo.getActivityProductId());
        lqw.eq(bo.getSpuId() != null, EcomOrderItem::getSpuId, bo.getSpuId());
        lqw.eq(bo.getSkuId() != null, EcomOrderItem::getSkuId, bo.getSkuId());
        lqw.like(StringUtils.isNotBlank(bo.getSpuName()), EcomOrderItem::getSpuName, bo.getSpuName());
        lqw.like(StringUtils.isNotBlank(bo.getSkuName()), EcomOrderItem::getSkuName, bo.getSkuName());
        lqw.eq(StringUtils.isNotBlank(bo.getSkuImage()), EcomOrderItem::getSkuImage, bo.getSkuImage());
        lqw.eq(bo.getActivityId() != null, EcomOrderItem::getActivityId, bo.getActivityId());
        lqw.eq(StringUtils.isNotBlank(bo.getOwnerType()), EcomOrderItem::getOwnerType, bo.getOwnerType());
        lqw.eq(bo.getOwnerId() != null, EcomOrderItem::getOwnerId, bo.getOwnerId());
        lqw.eq(bo.getPrice() != null, EcomOrderItem::getPrice, bo.getPrice());
        lqw.eq(bo.getQuantity() != null, EcomOrderItem::getQuantity, bo.getQuantity());
        lqw.eq(bo.getTotalAmount() != null, EcomOrderItem::getTotalAmount, bo.getTotalAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundStatus()), EcomOrderItem::getRefundStatus, bo.getRefundStatus());
        lqw.eq(bo.getRefundAmount() != null, EcomOrderItem::getRefundAmount, bo.getRefundAmount());
        return lqw;
    }

    /**
     * 新增订单明细
     *
     * @param bo 订单明细
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomOrderItemBo bo) {
        EcomOrderItem add = MapstructUtils.convert(bo, EcomOrderItem.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setItemId(add.getItemId());
        }
        return flag;
    }

    /**
     * 修改订单明细
     *
     * @param bo 订单明细
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomOrderItemBo bo) {
        EcomOrderItem update = MapstructUtils.convert(bo, EcomOrderItem.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomOrderItem entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除订单明细信息
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
