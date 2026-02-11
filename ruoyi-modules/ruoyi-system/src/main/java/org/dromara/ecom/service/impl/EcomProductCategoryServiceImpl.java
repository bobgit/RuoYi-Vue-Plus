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
import org.dromara.ecom.domain.bo.EcomProductCategoryBo;
import org.dromara.ecom.domain.vo.EcomProductCategoryVo;
import org.dromara.ecom.domain.EcomProductCategory;
import org.dromara.ecom.mapper.EcomProductCategoryMapper;
import org.dromara.ecom.service.IEcomProductCategoryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品分类Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-01-27
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomProductCategoryServiceImpl implements IEcomProductCategoryService {

    private final EcomProductCategoryMapper baseMapper;

    /**
     * 查询商品分类
     *
     * @param categoryId 主键
     * @return 商品分类
     */
    @Override
    public EcomProductCategoryVo queryById(Long categoryId){
        return baseMapper.selectVoById(categoryId);
    }

    /**
     * 分页查询商品分类列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品分类分页列表
     */
    @Override
    public TableDataInfo<EcomProductCategoryVo> queryPageList(EcomProductCategoryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomProductCategory> lqw = buildQueryWrapper(bo);
        Page<EcomProductCategoryVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的商品分类列表
     *
     * @param bo 查询条件
     * @return 商品分类列表
     */
    @Override
    public List<EcomProductCategoryVo> queryList(EcomProductCategoryBo bo) {
        LambdaQueryWrapper<EcomProductCategory> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomProductCategory> buildQueryWrapper(EcomProductCategoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomProductCategory> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomProductCategory::getCategoryId);
        lqw.eq(bo.getParentId() != null, EcomProductCategory::getParentId, bo.getParentId());
        lqw.like(StringUtils.isNotBlank(bo.getCategoryName()), EcomProductCategory::getCategoryName, bo.getCategoryName());
        lqw.eq(StringUtils.isNotBlank(bo.getCategoryCode()), EcomProductCategory::getCategoryCode, bo.getCategoryCode());
        lqw.eq(StringUtils.isNotBlank(bo.getCategoryCodePath()), EcomProductCategory::getCategoryCodePath, bo.getCategoryCodePath());
        lqw.eq(StringUtils.isNotBlank(bo.getIcon()), EcomProductCategory::getIcon, bo.getIcon());
        lqw.eq(StringUtils.isNotBlank(bo.getRuleTemplate()), EcomProductCategory::getRuleTemplate, bo.getRuleTemplate());
        lqw.eq(StringUtils.isNotBlank(bo.getSpecTemplate()), EcomProductCategory::getSpecTemplate, bo.getSpecTemplate());
        lqw.eq(StringUtils.isNotBlank(bo.getDeliveryTemplate()), EcomProductCategory::getDeliveryTemplate, bo.getDeliveryTemplate());
        lqw.eq(StringUtils.isNotBlank(bo.getAttributeSchema()), EcomProductCategory::getAttributeSchema, bo.getAttributeSchema());
        lqw.eq(StringUtils.isNotBlank(bo.getSpecialControl()), EcomProductCategory::getSpecialControl, bo.getSpecialControl());
        lqw.eq(bo.getSortOrder() != null, EcomProductCategory::getSortOrder, bo.getSortOrder());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), EcomProductCategory::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增商品分类
     *
     * @param bo 商品分类
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomProductCategoryBo bo) {
        EcomProductCategory add = MapstructUtils.convert(bo, EcomProductCategory.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCategoryId(add.getCategoryId());
        }
        return flag;
    }

    /**
     * 修改商品分类
     *
     * @param bo 商品分类
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomProductCategoryBo bo) {
        EcomProductCategory update = MapstructUtils.convert(bo, EcomProductCategory.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomProductCategory entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除商品分类信息
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
