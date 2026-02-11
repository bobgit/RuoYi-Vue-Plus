package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomBrandVo;
import org.dromara.ecom.domain.bo.EcomBrandBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品品牌Service接口
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
public interface IEcomBrandService {

    /**
     * 查询商品品牌
     *
     * @param brandId 主键
     * @return 商品品牌
     */
    EcomBrandVo queryById(Long brandId);

    /**
     * 分页查询商品品牌列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品品牌分页列表
     */
    TableDataInfo<EcomBrandVo> queryPageList(EcomBrandBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的商品品牌列表
     *
     * @param bo 查询条件
     * @return 商品品牌列表
     */
    List<EcomBrandVo> queryList(EcomBrandBo bo);

    /**
     * 新增商品品牌
     *
     * @param bo 商品品牌
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomBrandBo bo);

    /**
     * 修改商品品牌
     *
     * @param bo 商品品牌
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomBrandBo bo);

    /**
     * 校验并批量删除商品品牌信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
