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
import org.dromara.ecom.domain.bo.EcomI18nBo;
import org.dromara.ecom.domain.vo.EcomI18nVo;
import org.dromara.ecom.domain.EcomI18n;
import org.dromara.ecom.mapper.EcomI18nMapper;
import org.dromara.ecom.service.IEcomI18nService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品分类Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-02-06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomI18nServiceImpl implements IEcomI18nService {

    private final EcomI18nMapper baseMapper;

    /**
     * 查询商品分类
     *
     * @param i18nId 主键
     * @return 商品分类
     */
    @Override
    public EcomI18nVo queryById(Long i18nId){
        return baseMapper.selectVoById(i18nId);
    }

    /**
     * 分页查询商品分类列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品分类分页列表
     */
    @Override
    public TableDataInfo<EcomI18nVo> queryPageList(EcomI18nBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomI18n> lqw = buildQueryWrapper(bo);
        Page<EcomI18nVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的商品分类列表
     *
     * @param bo 查询条件
     * @return 商品分类列表
     */
    @Override
    public List<EcomI18nVo> queryList(EcomI18nBo bo) {
        LambdaQueryWrapper<EcomI18n> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomI18n> buildQueryWrapper(EcomI18nBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomI18n> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomI18n::getI18nId);
        lqw.eq(StringUtils.isNotBlank(bo.getBizType()), EcomI18n::getBizType, bo.getBizType());
        lqw.eq(bo.getBizId() != null, EcomI18n::getBizId, bo.getBizId());
        lqw.eq(StringUtils.isNotBlank(bo.getLang()), EcomI18n::getLang, bo.getLang());
        lqw.eq(StringUtils.isNotBlank(bo.getField()), EcomI18n::getField, bo.getField());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), EcomI18n::getContent, bo.getContent());
        return lqw;
    }

    /**
     * 新增商品分类
     *
     * @param bo 商品分类
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomI18nBo bo) {
        EcomI18n add = MapstructUtils.convert(bo, EcomI18n.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setI18nId(add.getI18nId());
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
    public Boolean updateByBo(EcomI18nBo bo) {
        EcomI18n update = MapstructUtils.convert(bo, EcomI18n.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomI18n entity){
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
