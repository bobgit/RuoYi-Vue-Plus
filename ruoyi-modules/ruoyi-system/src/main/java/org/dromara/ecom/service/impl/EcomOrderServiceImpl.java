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
import org.dromara.ecom.domain.bo.EcomOrderBo;
import org.dromara.ecom.domain.vo.EcomOrderVo;
import org.dromara.ecom.domain.EcomOrder;
import org.dromara.ecom.mapper.EcomOrderMapper;
import org.dromara.ecom.service.IEcomOrderService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 订单Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomOrderServiceImpl implements IEcomOrderService {

    private final EcomOrderMapper baseMapper;

    /**
     * 查询订单
     *
     * @param orderId 主键
     * @return 订单
     */
    @Override
    public EcomOrderVo queryById(Long orderId){
        return baseMapper.selectVoById(orderId);
    }

    /**
     * 分页查询订单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 订单分页列表
     */
    @Override
    public TableDataInfo<EcomOrderVo> queryPageList(EcomOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomOrder> lqw = buildQueryWrapper(bo);
        Page<EcomOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的订单列表
     *
     * @param bo 查询条件
     * @return 订单列表
     */
    @Override
    public List<EcomOrderVo> queryList(EcomOrderBo bo) {
        LambdaQueryWrapper<EcomOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomOrder> buildQueryWrapper(EcomOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomOrder> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomOrder::getOrderId);
        lqw.eq(StringUtils.isNotBlank(bo.getOrderSn()), EcomOrder::getOrderSn, bo.getOrderSn());
        lqw.eq(bo.getBuyerUserId() != null, EcomOrder::getBuyerUserId, bo.getBuyerUserId());
        lqw.eq(bo.getRecordId() != null, EcomOrder::getRecordId, bo.getRecordId());
        lqw.eq(bo.getParentOrderId() != null, EcomOrder::getParentOrderId, bo.getParentOrderId());
        lqw.eq(bo.getOrgId() != null, EcomOrder::getOrgId, bo.getOrgId());
        lqw.eq(bo.getLeaderId() != null, EcomOrder::getLeaderId, bo.getLeaderId());
        lqw.eq(bo.getSellerUserId() != null, EcomOrder::getSellerUserId, bo.getSellerUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getProductType()), EcomOrder::getProductType, bo.getProductType());
        lqw.eq(StringUtils.isNotBlank(bo.getExtendJson()), EcomOrder::getExtendJson, bo.getExtendJson());
        lqw.eq(bo.getActivityId() != null, EcomOrder::getActivityId, bo.getActivityId());
        lqw.eq(bo.getActivityProductId() != null, EcomOrder::getActivityProductId, bo.getActivityProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getOwnerType()), EcomOrder::getOwnerType, bo.getOwnerType());
        lqw.eq(bo.getOwnerId() != null, EcomOrder::getOwnerId, bo.getOwnerId());
        lqw.eq(StringUtils.isNotBlank(bo.getFulfillmentType()), EcomOrder::getFulfillmentType, bo.getFulfillmentType());
        lqw.eq(bo.getFulfillmentId() != null, EcomOrder::getFulfillmentId, bo.getFulfillmentId());
        lqw.eq(StringUtils.isNotBlank(bo.getResponsibleType()), EcomOrder::getResponsibleType, bo.getResponsibleType());
        lqw.eq(bo.getResponsibleId() != null, EcomOrder::getResponsibleId, bo.getResponsibleId());
        lqw.eq(StringUtils.isNotBlank(bo.getCurrency()), EcomOrder::getCurrency, bo.getCurrency());
        lqw.eq(StringUtils.isNotBlank(bo.getLocale()), EcomOrder::getLocale, bo.getLocale());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderStatus()), EcomOrder::getOrderStatus, bo.getOrderStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getPayStatus()), EcomOrder::getPayStatus, bo.getPayStatus());
        lqw.eq(bo.getPayAmount() != null, EcomOrder::getPayAmount, bo.getPayAmount());
        lqw.eq(bo.getPayTime() != null, EcomOrder::getPayTime, bo.getPayTime());
        lqw.eq(StringUtils.isNotBlank(bo.getPayWay()), EcomOrder::getPayWay, bo.getPayWay());
        lqw.eq(StringUtils.isNotBlank(bo.getBuyerMessage()), EcomOrder::getBuyerMessage, bo.getBuyerMessage());
        lqw.eq(bo.getAutoConfirmDays() != null, EcomOrder::getAutoConfirmDays, bo.getAutoConfirmDays());
        lqw.eq(bo.getConfirmTime() != null, EcomOrder::getConfirmTime, bo.getConfirmTime());
        lqw.eq(StringUtils.isNotBlank(bo.getCountryCode()), EcomOrder::getCountryCode, bo.getCountryCode());
        lqw.eq(StringUtils.isNotBlank(bo.getAdministrativeArea()), EcomOrder::getAdministrativeArea, bo.getAdministrativeArea());
        lqw.eq(StringUtils.isNotBlank(bo.getLocality()), EcomOrder::getLocality, bo.getLocality());
        lqw.eq(StringUtils.isNotBlank(bo.getDependentLocality()), EcomOrder::getDependentLocality, bo.getDependentLocality());
        lqw.eq(StringUtils.isNotBlank(bo.getStreetDetail()), EcomOrder::getStreetDetail, bo.getStreetDetail());
        lqw.eq(StringUtils.isNotBlank(bo.getPostalCode()), EcomOrder::getPostalCode, bo.getPostalCode());
        lqw.like(StringUtils.isNotBlank(bo.getAddressesName()), EcomOrder::getAddressesName, bo.getAddressesName());
        lqw.eq(StringUtils.isNotBlank(bo.getPhone()), EcomOrder::getPhone, bo.getPhone());
        lqw.eq(StringUtils.isNotBlank(bo.getOther()), EcomOrder::getOther, bo.getOther());
        lqw.eq(StringUtils.isNotBlank(bo.getFormattedAddress()), EcomOrder::getFormattedAddress, bo.getFormattedAddress());
        lqw.eq(StringUtils.isNotBlank(bo.getLang()), EcomOrder::getLang, bo.getLang());
        lqw.eq(bo.getLatitude() != null, EcomOrder::getLatitude, bo.getLatitude());
        lqw.eq(bo.getLongitude() != null, EcomOrder::getLongitude, bo.getLongitude());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundStatus()), EcomOrder::getRefundStatus, bo.getRefundStatus());
        lqw.eq(bo.getRefundAmount() != null, EcomOrder::getRefundAmount, bo.getRefundAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getSource()), EcomOrder::getSource, bo.getSource());
        return lqw;
    }

    /**
     * 新增订单
     *
     * @param bo 订单
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomOrderBo bo) {
        EcomOrder add = MapstructUtils.convert(bo, EcomOrder.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setOrderId(add.getOrderId());
        }
        return flag;
    }

    /**
     * 修改订单
     *
     * @param bo 订单
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomOrderBo bo) {
        EcomOrder update = MapstructUtils.convert(bo, EcomOrder.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomOrder entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除订单信息
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
