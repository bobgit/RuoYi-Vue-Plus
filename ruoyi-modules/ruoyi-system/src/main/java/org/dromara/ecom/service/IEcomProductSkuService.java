package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomProductSkuVo;
import org.dromara.ecom.domain.bo.EcomProductSkuBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * SKU库存单元Service接口
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
public interface IEcomProductSkuService {

    /**
     * 查询SKU库存单元
     *
     * @param skuId 主键
     * @return SKU库存单元
     */
    EcomProductSkuVo queryById(Long skuId);

    /**
     * 分页查询SKU库存单元列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SKU库存单元分页列表
     */
    TableDataInfo<EcomProductSkuVo> queryPageList(EcomProductSkuBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SKU库存单元列表
     *
     * @param bo 查询条件
     * @return SKU库存单元列表
     */
    List<EcomProductSkuVo> queryList(EcomProductSkuBo bo);

    /**
     * 新增SKU库存单元
     *
     * @param bo SKU库存单元
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomProductSkuBo bo);

    /**
     * 修改SKU库存单元
     *
     * @param bo SKU库存单元
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomProductSkuBo bo);

    /**
     * 校验并批量删除SKU库存单元信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
