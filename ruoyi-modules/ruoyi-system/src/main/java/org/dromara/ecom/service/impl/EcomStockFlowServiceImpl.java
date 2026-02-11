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
import org.dromara.ecom.domain.bo.EcomStockFlowBo;
import org.dromara.ecom.domain.vo.EcomStockFlowVo;
import org.dromara.ecom.domain.EcomStockFlow;
import org.dromara.ecom.mapper.EcomStockFlowMapper;
import org.dromara.ecom.service.IEcomStockFlowService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 库存流水Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-07
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomStockFlowServiceImpl implements IEcomStockFlowService {

    private final EcomStockFlowMapper baseMapper;

    /**
     * 查询库存流水
     *
     * @param stockFlowId 主键
     * @return 库存流水
     */
    @Override
    public EcomStockFlowVo queryById(Long stockFlowId){
        return baseMapper.selectVoById(stockFlowId);
    }

    /**
     * 分页查询库存流水列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 库存流水分页列表
     */
    @Override
    public TableDataInfo<EcomStockFlowVo> queryPageList(EcomStockFlowBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomStockFlow> lqw = buildQueryWrapper(bo);
        Page<EcomStockFlowVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的库存流水列表
     *
     * @param bo 查询条件
     * @return 库存流水列表
     */
    @Override
    public List<EcomStockFlowVo> queryList(EcomStockFlowBo bo) {
        LambdaQueryWrapper<EcomStockFlow> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomStockFlow> buildQueryWrapper(EcomStockFlowBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomStockFlow> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getStockFlowId() != null, EcomStockFlow::getStockFlowId, bo.getStockFlowId());
        lqw.orderByAsc(EcomStockFlow::getStockFlowId);
        lqw.eq(bo.getStockId() != null, EcomStockFlow::getStockId, bo.getStockId());
        lqw.eq(bo.getSkuId() != null, EcomStockFlow::getSkuId, bo.getSkuId());
        lqw.eq(StringUtils.isNotBlank(bo.getOwnerType()), EcomStockFlow::getOwnerType, bo.getOwnerType());
        lqw.eq(bo.getOwnerId() != null, EcomStockFlow::getOwnerId, bo.getOwnerId());
        lqw.eq(StringUtils.isNotBlank(bo.getLocationType()), EcomStockFlow::getLocationType, bo.getLocationType());
        lqw.eq(bo.getLocationAddressId() != null, EcomStockFlow::getLocationAddressId, bo.getLocationAddressId());
        lqw.eq(StringUtils.isNotBlank(bo.getStockBizType()), EcomStockFlow::getStockBizType, bo.getStockBizType());
        lqw.eq(bo.getBizId() != null, EcomStockFlow::getBizId, bo.getBizId());
        lqw.eq(bo.getChangeQty() != null, EcomStockFlow::getChangeQty, bo.getChangeQty());
        lqw.eq(bo.getBeforeQty() != null, EcomStockFlow::getBeforeQty, bo.getBeforeQty());
        lqw.eq(bo.getAfterQty() != null, EcomStockFlow::getAfterQty, bo.getAfterQty());
        lqw.eq(StringUtils.isNotBlank(bo.getStockType()), EcomStockFlow::getStockType, bo.getStockType());
        return lqw;
    }

    /**
     * 新增库存流水
     *
     * @param bo 库存流水
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomStockFlowBo bo) {
        EcomStockFlow add = MapstructUtils.convert(bo, EcomStockFlow.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setStockFlowId(add.getStockFlowId());
        }
        return flag;
    }

    /**
     * 修改库存流水
     *
     * @param bo 库存流水
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomStockFlowBo bo) {
        EcomStockFlow update = MapstructUtils.convert(bo, EcomStockFlow.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomStockFlow entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除库存流水信息
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
