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
import org.dromara.ecom.domain.bo.EcomProductSkuAttributesBo;
import org.dromara.ecom.domain.vo.EcomProductSkuAttributesVo;
import org.dromara.ecom.domain.EcomProductSkuAttributes;
import org.dromara.ecom.mapper.EcomProductSkuAttributesMapper;
import org.dromara.ecom.service.IEcomProductSkuAttributesService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品SKU属性Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomProductSkuAttributesServiceImpl implements IEcomProductSkuAttributesService {

    private final EcomProductSkuAttributesMapper baseMapper;

    /**
     * 查询商品SKU属性
     *
     * @param skuAttrId 主键
     * @return 商品SKU属性
     */
    @Override
    public EcomProductSkuAttributesVo queryById(Long skuAttrId){
        return baseMapper.selectVoById(skuAttrId);
    }

    /**
     * 分页查询商品SKU属性列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品SKU属性分页列表
     */
    @Override
    public TableDataInfo<EcomProductSkuAttributesVo> queryPageList(EcomProductSkuAttributesBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomProductSkuAttributes> lqw = buildQueryWrapper(bo);
        Page<EcomProductSkuAttributesVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的商品SKU属性列表
     *
     * @param bo 查询条件
     * @return 商品SKU属性列表
     */
    @Override
    public List<EcomProductSkuAttributesVo> queryList(EcomProductSkuAttributesBo bo) {
        LambdaQueryWrapper<EcomProductSkuAttributes> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomProductSkuAttributes> buildQueryWrapper(EcomProductSkuAttributesBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomProductSkuAttributes> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomProductSkuAttributes::getSkuAttrId);
        lqw.eq(bo.getSkuId() != null, EcomProductSkuAttributes::getSkuId, bo.getSkuId());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrKey()), EcomProductSkuAttributes::getAttrKey, bo.getAttrKey());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrValue()), EcomProductSkuAttributes::getAttrValue, bo.getAttrValue());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrType()), EcomProductSkuAttributes::getAttrType, bo.getAttrType());
        return lqw;
    }

    /**
     * 新增商品SKU属性
     *
     * @param bo 商品SKU属性
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomProductSkuAttributesBo bo) {
        EcomProductSkuAttributes add = MapstructUtils.convert(bo, EcomProductSkuAttributes.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSkuAttrId(add.getSkuAttrId());
        }
        return flag;
    }

    /**
     * 修改商品SKU属性
     *
     * @param bo 商品SKU属性
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomProductSkuAttributesBo bo) {
        EcomProductSkuAttributes update = MapstructUtils.convert(bo, EcomProductSkuAttributes.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomProductSkuAttributes entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除商品SKU属性信息
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
