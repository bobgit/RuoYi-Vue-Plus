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
import org.dromara.ecom.domain.bo.EcomStockBo;
import org.dromara.ecom.domain.vo.EcomStockVo;
import org.dromara.ecom.domain.EcomStock;
import org.dromara.ecom.mapper.EcomStockMapper;
import org.dromara.ecom.service.IEcomStockService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 通用库存Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomStockServiceImpl implements IEcomStockService {

    private final EcomStockMapper baseMapper;

    /**
     * 查询通用库存
     *
     * @param stockId 主键
     * @return 通用库存
     */
    @Override
    public EcomStockVo queryById(Long stockId){
        return baseMapper.selectVoById(stockId);
    }

    /**
     * 分页查询通用库存列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 通用库存分页列表
     */
    @Override
    public TableDataInfo<EcomStockVo> queryPageList(EcomStockBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomStock> lqw = buildQueryWrapper(bo);
        Page<EcomStockVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的通用库存列表
     *
     * @param bo 查询条件
     * @return 通用库存列表
     */
    @Override
    public List<EcomStockVo> queryList(EcomStockBo bo) {
        LambdaQueryWrapper<EcomStock> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomStock> buildQueryWrapper(EcomStockBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomStock> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomStock::getStockId);
        lqw.eq(bo.getSkuId() != null, EcomStock::getSkuId, bo.getSkuId());
        lqw.eq(StringUtils.isNotBlank(bo.getOwnerType()), EcomStock::getOwnerType, bo.getOwnerType());
        lqw.eq(bo.getOwnerId() != null, EcomStock::getOwnerId, bo.getOwnerId());
        lqw.eq(StringUtils.isNotBlank(bo.getLocationType()), EcomStock::getLocationType, bo.getLocationType());
        lqw.eq(bo.getLocationAddressId() != null, EcomStock::getLocationAddressId, bo.getLocationAddressId());
        lqw.eq(bo.getStockTotal() != null, EcomStock::getStockTotal, bo.getStockTotal());
        lqw.eq(bo.getStockAvailable() != null, EcomStock::getStockAvailable, bo.getStockAvailable());
        lqw.eq(bo.getStockLocked() != null, EcomStock::getStockLocked, bo.getStockLocked());
        lqw.eq(bo.getSafetyStock() != null, EcomStock::getSafetyStock, bo.getSafetyStock());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), EcomStock::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增通用库存
     *
     * @param bo 通用库存
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomStockBo bo) {
        EcomStock add = MapstructUtils.convert(bo, EcomStock.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setStockId(add.getStockId());
        }
        return flag;
    }

    /**
     * 修改通用库存
     *
     * @param bo 通用库存
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomStockBo bo) {
        EcomStock update = MapstructUtils.convert(bo, EcomStock.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomStock entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除通用库存信息
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
