package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomStockVo;
import org.dromara.ecom.domain.bo.EcomStockBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 通用库存Service接口
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
public interface IEcomStockService {

    /**
     * 查询通用库存
     *
     * @param stockId 主键
     * @return 通用库存
     */
    EcomStockVo queryById(Long stockId);

    /**
     * 分页查询通用库存列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 通用库存分页列表
     */
    TableDataInfo<EcomStockVo> queryPageList(EcomStockBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的通用库存列表
     *
     * @param bo 查询条件
     * @return 通用库存列表
     */
    List<EcomStockVo> queryList(EcomStockBo bo);

    /**
     * 新增通用库存
     *
     * @param bo 通用库存
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomStockBo bo);

    /**
     * 修改通用库存
     *
     * @param bo 通用库存
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomStockBo bo);

    /**
     * 校验并批量删除通用库存信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
