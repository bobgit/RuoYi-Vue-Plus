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
import org.dromara.ecom.domain.bo.EcomOrderDeliveryBo;
import org.dromara.ecom.domain.vo.EcomOrderDeliveryVo;
import org.dromara.ecom.domain.EcomOrderDelivery;
import org.dromara.ecom.mapper.EcomOrderDeliveryMapper;
import org.dromara.ecom.service.IEcomOrderDeliveryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 订单配送专用Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomOrderDeliveryServiceImpl implements IEcomOrderDeliveryService {

    private final EcomOrderDeliveryMapper baseMapper;

    /**
     * 查询订单配送专用
     *
     * @param orderDeliveryId 主键
     * @return 订单配送专用
     */
    @Override
    public EcomOrderDeliveryVo queryById(Long orderDeliveryId){
        return baseMapper.selectVoById(orderDeliveryId);
    }

    /**
     * 分页查询订单配送专用列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 订单配送专用分页列表
     */
    @Override
    public TableDataInfo<EcomOrderDeliveryVo> queryPageList(EcomOrderDeliveryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomOrderDelivery> lqw = buildQueryWrapper(bo);
        Page<EcomOrderDeliveryVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的订单配送专用列表
     *
     * @param bo 查询条件
     * @return 订单配送专用列表
     */
    @Override
    public List<EcomOrderDeliveryVo> queryList(EcomOrderDeliveryBo bo) {
        LambdaQueryWrapper<EcomOrderDelivery> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomOrderDelivery> buildQueryWrapper(EcomOrderDeliveryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomOrderDelivery> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomOrderDelivery::getOrderDeliveryId);
        lqw.eq(bo.getOrderId() != null, EcomOrderDelivery::getOrderId, bo.getOrderId());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderType()), EcomOrderDelivery::getOrderType, bo.getOrderType());
        lqw.like(StringUtils.isNotBlank(bo.getRiderName()), EcomOrderDelivery::getRiderName, bo.getRiderName());
        lqw.eq(StringUtils.isNotBlank(bo.getRiderId()), EcomOrderDelivery::getRiderId, bo.getRiderId());
        lqw.eq(StringUtils.isNotBlank(bo.getRiderPhone()), EcomOrderDelivery::getRiderPhone, bo.getRiderPhone());
        lqw.eq(bo.getDeliveryDistance() != null, EcomOrderDelivery::getDeliveryDistance, bo.getDeliveryDistance());
        lqw.eq(StringUtils.isNotBlank(bo.getDeliveryStatus()), EcomOrderDelivery::getDeliveryStatus, bo.getDeliveryStatus());
        lqw.eq(bo.getExpectedTime() != null, EcomOrderDelivery::getExpectedTime, bo.getExpectedTime());
        lqw.eq(bo.getActualTime() != null, EcomOrderDelivery::getActualTime, bo.getActualTime());
        return lqw;
    }

    /**
     * 新增订单配送专用
     *
     * @param bo 订单配送专用
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomOrderDeliveryBo bo) {
        EcomOrderDelivery add = MapstructUtils.convert(bo, EcomOrderDelivery.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setOrderDeliveryId(add.getOrderDeliveryId());
        }
        return flag;
    }

    /**
     * 修改订单配送专用
     *
     * @param bo 订单配送专用
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomOrderDeliveryBo bo) {
        EcomOrderDelivery update = MapstructUtils.convert(bo, EcomOrderDelivery.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomOrderDelivery entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除订单配送专用信息
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
