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
import org.dromara.ecom.domain.bo.EcomBrandBo;
import org.dromara.ecom.domain.vo.EcomBrandVo;
import org.dromara.ecom.domain.EcomBrand;
import org.dromara.ecom.mapper.EcomBrandMapper;
import org.dromara.ecom.service.IEcomBrandService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品品牌Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomBrandServiceImpl implements IEcomBrandService {

    private final EcomBrandMapper baseMapper;

    /**
     * 查询商品品牌
     *
     * @param brandId 主键
     * @return 商品品牌
     */
    @Override
    public EcomBrandVo queryById(Long brandId){
        return baseMapper.selectVoById(brandId);
    }

    /**
     * 分页查询商品品牌列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品品牌分页列表
     */
    @Override
    public TableDataInfo<EcomBrandVo> queryPageList(EcomBrandBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomBrand> lqw = buildQueryWrapper(bo);
        Page<EcomBrandVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的商品品牌列表
     *
     * @param bo 查询条件
     * @return 商品品牌列表
     */
    @Override
    public List<EcomBrandVo> queryList(EcomBrandBo bo) {
        LambdaQueryWrapper<EcomBrand> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomBrand> buildQueryWrapper(EcomBrandBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomBrand> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomBrand::getBrandId);
        lqw.eq(StringUtils.isNotBlank(bo.getBrandCode()), EcomBrand::getBrandCode, bo.getBrandCode());
        lqw.eq(StringUtils.isNotBlank(bo.getBrandLogo()), EcomBrand::getBrandLogo, bo.getBrandLogo());
        lqw.eq(StringUtils.isNotBlank(bo.getOfficialSite()), EcomBrand::getOfficialSite, bo.getOfficialSite());
        lqw.eq(StringUtils.isNotBlank(bo.getCountryCode()), EcomBrand::getCountryCode, bo.getCountryCode());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), EcomBrand::getStatus, bo.getStatus());
        lqw.eq(bo.getSortOrder() != null, EcomBrand::getSortOrder, bo.getSortOrder());
        return lqw;
    }

    /**
     * 新增商品品牌
     *
     * @param bo 商品品牌
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomBrandBo bo) {
        EcomBrand add = MapstructUtils.convert(bo, EcomBrand.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setBrandId(add.getBrandId());
        }
        return flag;
    }

    /**
     * 修改商品品牌
     *
     * @param bo 商品品牌
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomBrandBo bo) {
        EcomBrand update = MapstructUtils.convert(bo, EcomBrand.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomBrand entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除商品品牌信息
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
