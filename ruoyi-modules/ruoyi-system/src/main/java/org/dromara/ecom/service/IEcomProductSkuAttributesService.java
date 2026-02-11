package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomProductSkuAttributesVo;
import org.dromara.ecom.domain.bo.EcomProductSkuAttributesBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品SKU属性Service接口
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
public interface IEcomProductSkuAttributesService {

    /**
     * 查询商品SKU属性
     *
     * @param skuAttrId 主键
     * @return 商品SKU属性
     */
    EcomProductSkuAttributesVo queryById(Long skuAttrId);

    /**
     * 分页查询商品SKU属性列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品SKU属性分页列表
     */
    TableDataInfo<EcomProductSkuAttributesVo> queryPageList(EcomProductSkuAttributesBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的商品SKU属性列表
     *
     * @param bo 查询条件
     * @return 商品SKU属性列表
     */
    List<EcomProductSkuAttributesVo> queryList(EcomProductSkuAttributesBo bo);

    /**
     * 新增商品SKU属性
     *
     * @param bo 商品SKU属性
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomProductSkuAttributesBo bo);

    /**
     * 修改商品SKU属性
     *
     * @param bo 商品SKU属性
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomProductSkuAttributesBo bo);

    /**
     * 校验并批量删除商品SKU属性信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
