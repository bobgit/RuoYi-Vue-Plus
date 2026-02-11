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
import org.dromara.ecom.domain.bo.EcomProductSpuBo;
import org.dromara.ecom.domain.vo.EcomProductSpuVo;
import org.dromara.ecom.domain.EcomProductSpu;
import org.dromara.ecom.mapper.EcomProductSpuMapper;
import org.dromara.ecom.service.IEcomProductSpuService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * SPU标准产品单元Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomProductSpuServiceImpl implements IEcomProductSpuService {

    private final EcomProductSpuMapper baseMapper;

    /**
     * 查询SPU标准产品单元
     *
     * @param spuId 主键
     * @return SPU标准产品单元
     */
    @Override
    public EcomProductSpuVo queryById(Long spuId){
        return baseMapper.selectVoById(spuId);
    }

    /**
     * 分页查询SPU标准产品单元列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SPU标准产品单元分页列表
     */
    @Override
    public TableDataInfo<EcomProductSpuVo> queryPageList(EcomProductSpuBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomProductSpu> lqw = buildQueryWrapper(bo);
        Page<EcomProductSpuVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的SPU标准产品单元列表
     *
     * @param bo 查询条件
     * @return SPU标准产品单元列表
     */
    @Override
    public List<EcomProductSpuVo> queryList(EcomProductSpuBo bo) {
        LambdaQueryWrapper<EcomProductSpu> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomProductSpu> buildQueryWrapper(EcomProductSpuBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomProductSpu> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomProductSpu::getSpuId);
        lqw.eq(bo.getCategoryId() != null, EcomProductSpu::getCategoryId, bo.getCategoryId());
        lqw.eq(bo.getBrandId() != null, EcomProductSpu::getBrandId, bo.getBrandId());
        lqw.eq(StringUtils.isNotBlank(bo.getOwnerType()), EcomProductSpu::getOwnerType, bo.getOwnerType());
        lqw.eq(bo.getOwnerId() != null, EcomProductSpu::getOwnerId, bo.getOwnerId());
        lqw.eq(StringUtils.isNotBlank(bo.getProductType()), EcomProductSpu::getProductType, bo.getProductType());
        lqw.like(StringUtils.isNotBlank(bo.getSpuName()), EcomProductSpu::getSpuName, bo.getSpuName());
        lqw.eq(StringUtils.isNotBlank(bo.getSpuDesc()), EcomProductSpu::getSpuDesc, bo.getSpuDesc());
        lqw.eq(StringUtils.isNotBlank(bo.getPackingList()), EcomProductSpu::getPackingList, bo.getPackingList());
        lqw.eq(StringUtils.isNotBlank(bo.getAfterService()), EcomProductSpu::getAfterService, bo.getAfterService());
        lqw.eq(StringUtils.isNotBlank(bo.getGenericSpec()), EcomProductSpu::getGenericSpec, bo.getGenericSpec());
        lqw.eq(StringUtils.isNotBlank(bo.getSpuImages()), EcomProductSpu::getSpuImages, bo.getSpuImages());
        lqw.eq(bo.getMinPrice() != null, EcomProductSpu::getMinPrice, bo.getMinPrice());
        lqw.eq(bo.getMaxPrice() != null, EcomProductSpu::getMaxPrice, bo.getMaxPrice());
        lqw.eq(bo.getTotalSales() != null, EcomProductSpu::getTotalSales, bo.getTotalSales());
        lqw.eq(bo.getTotalStock() != null, EcomProductSpu::getTotalStock, bo.getTotalStock());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), EcomProductSpu::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getAuditStatus()), EcomProductSpu::getAuditStatus, bo.getAuditStatus());
        return lqw;
    }

    /**
     * 新增SPU标准产品单元
     *
     * @param bo SPU标准产品单元
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomProductSpuBo bo) {
        EcomProductSpu add = MapstructUtils.convert(bo, EcomProductSpu.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSpuId(add.getSpuId());
        }
        return flag;
    }

    /**
     * 修改SPU标准产品单元
     *
     * @param bo SPU标准产品单元
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomProductSpuBo bo) {
        EcomProductSpu update = MapstructUtils.convert(bo, EcomProductSpu.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomProductSpu entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除SPU标准产品单元信息
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
