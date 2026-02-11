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
import org.dromara.ecom.domain.bo.EcomProductSkuBo;
import org.dromara.ecom.domain.vo.EcomProductSkuVo;
import org.dromara.ecom.domain.EcomProductSku;
import org.dromara.ecom.mapper.EcomProductSkuMapper;
import org.dromara.ecom.service.IEcomProductSkuService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * SKU库存单元Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomProductSkuServiceImpl implements IEcomProductSkuService {

    private final EcomProductSkuMapper baseMapper;

    /**
     * 查询SKU库存单元
     *
     * @param skuId 主键
     * @return SKU库存单元
     */
    @Override
    public EcomProductSkuVo queryById(Long skuId){
        return baseMapper.selectVoById(skuId);
    }

    /**
     * 分页查询SKU库存单元列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SKU库存单元分页列表
     */
    @Override
    public TableDataInfo<EcomProductSkuVo> queryPageList(EcomProductSkuBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomProductSku> lqw = buildQueryWrapper(bo);
        Page<EcomProductSkuVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的SKU库存单元列表
     *
     * @param bo 查询条件
     * @return SKU库存单元列表
     */
    @Override
    public List<EcomProductSkuVo> queryList(EcomProductSkuBo bo) {
        LambdaQueryWrapper<EcomProductSku> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomProductSku> buildQueryWrapper(EcomProductSkuBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomProductSku> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomProductSku::getSkuId);
        lqw.eq(bo.getSpuId() != null, EcomProductSku::getSpuId, bo.getSpuId());
        lqw.like(StringUtils.isNotBlank(bo.getSkuName()), EcomProductSku::getSkuName, bo.getSkuName());
        lqw.eq(StringUtils.isNotBlank(bo.getSkuSpec()), EcomProductSku::getSkuSpec, bo.getSkuSpec());
        lqw.eq(bo.getCostPrice() != null, EcomProductSku::getCostPrice, bo.getCostPrice());
        lqw.eq(bo.getMarketPrice() != null, EcomProductSku::getMarketPrice, bo.getMarketPrice());
        lqw.eq(bo.getPrice() != null, EcomProductSku::getPrice, bo.getPrice());
        lqw.eq(StringUtils.isNotBlank(bo.getStockPolicyType()), EcomProductSku::getStockPolicyType, bo.getStockPolicyType());
        lqw.eq(bo.getStockQuantity() != null, EcomProductSku::getStockQuantity, bo.getStockQuantity());
        lqw.eq(bo.getSoldQuantity() != null, EcomProductSku::getSoldQuantity, bo.getSoldQuantity());
        lqw.eq(bo.getWeight() != null, EcomProductSku::getWeight, bo.getWeight());
        lqw.eq(bo.getLength() != null, EcomProductSku::getLength, bo.getLength());
        lqw.eq(bo.getWidth() != null, EcomProductSku::getWidth, bo.getWidth());
        lqw.eq(bo.getHeight() != null, EcomProductSku::getHeight, bo.getHeight());
        lqw.eq(StringUtils.isNotBlank(bo.getBarCode()), EcomProductSku::getBarCode, bo.getBarCode());
        lqw.eq(StringUtils.isNotBlank(bo.getSkuCode()), EcomProductSku::getSkuCode, bo.getSkuCode());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), EcomProductSku::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增SKU库存单元
     *
     * @param bo SKU库存单元
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomProductSkuBo bo) {
        EcomProductSku add = MapstructUtils.convert(bo, EcomProductSku.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSkuId(add.getSkuId());
        }
        return flag;
    }

    /**
     * 修改SKU库存单元
     *
     * @param bo SKU库存单元
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomProductSkuBo bo) {
        EcomProductSku update = MapstructUtils.convert(bo, EcomProductSku.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomProductSku entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除SKU库存单元信息
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
