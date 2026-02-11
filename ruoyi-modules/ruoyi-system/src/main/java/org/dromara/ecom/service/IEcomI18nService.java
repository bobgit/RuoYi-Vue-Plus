package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomI18nVo;
import org.dromara.ecom.domain.bo.EcomI18nBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品分类Service接口
 *
 * @author Bob Bai
 * @date 2026-02-06
 */
public interface IEcomI18nService {

    /**
     * 查询商品分类
     *
     * @param i18nId 主键
     * @return 商品分类
     */
    EcomI18nVo queryById(Long i18nId);

    /**
     * 分页查询商品分类列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品分类分页列表
     */
    TableDataInfo<EcomI18nVo> queryPageList(EcomI18nBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的商品分类列表
     *
     * @param bo 查询条件
     * @return 商品分类列表
     */
    List<EcomI18nVo> queryList(EcomI18nBo bo);

    /**
     * 新增商品分类
     *
     * @param bo 商品分类
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomI18nBo bo);

    /**
     * 修改商品分类
     *
     * @param bo 商品分类
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomI18nBo bo);

    /**
     * 校验并批量删除商品分类信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
