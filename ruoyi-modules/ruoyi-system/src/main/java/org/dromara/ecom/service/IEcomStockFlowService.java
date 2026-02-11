package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomStockFlowVo;
import org.dromara.ecom.domain.bo.EcomStockFlowBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 库存流水Service接口
 *
 * @author Bob Bai
 * @date 2026-02-07
 */
public interface IEcomStockFlowService {

    /**
     * 查询库存流水
     *
     * @param stockFlowId 主键
     * @return 库存流水
     */
    EcomStockFlowVo queryById(Long stockFlowId);

    /**
     * 分页查询库存流水列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 库存流水分页列表
     */
    TableDataInfo<EcomStockFlowVo> queryPageList(EcomStockFlowBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的库存流水列表
     *
     * @param bo 查询条件
     * @return 库存流水列表
     */
    List<EcomStockFlowVo> queryList(EcomStockFlowBo bo);

    /**
     * 新增库存流水
     *
     * @param bo 库存流水
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomStockFlowBo bo);

    /**
     * 修改库存流水
     *
     * @param bo 库存流水
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomStockFlowBo bo);

    /**
     * 校验并批量删除库存流水信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
