package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomProductCategoryVo;
import org.dromara.ecom.domain.bo.EcomProductCategoryBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品分类Service接口
 *
 * @author Bob Bai
 * @date 2026-01-27
 */
public interface IEcomProductCategoryService {

    /**
     * 查询商品分类
     *
     * @param categoryId 主键
     * @return 商品分类
     */
    EcomProductCategoryVo queryById(Long categoryId);

    /**
     * 分页查询商品分类列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品分类分页列表
     */
    TableDataInfo<EcomProductCategoryVo> queryPageList(EcomProductCategoryBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的商品分类列表
     *
     * @param bo 查询条件
     * @return 商品分类列表
     */
    List<EcomProductCategoryVo> queryList(EcomProductCategoryBo bo);

    /**
     * 新增商品分类
     *
     * @param bo 商品分类
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomProductCategoryBo bo);

    /**
     * 修改商品分类
     *
     * @param bo 商品分类
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomProductCategoryBo bo);

    /**
     * 校验并批量删除商品分类信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
