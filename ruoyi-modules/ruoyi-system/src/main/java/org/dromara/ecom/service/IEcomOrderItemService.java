package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomOrderItemVo;
import org.dromara.ecom.domain.bo.EcomOrderItemBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 订单明细Service接口
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
public interface IEcomOrderItemService {

    /**
     * 查询订单明细
     *
     * @param itemId 主键
     * @return 订单明细
     */
    EcomOrderItemVo queryById(Long itemId);

    /**
     * 分页查询订单明细列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 订单明细分页列表
     */
    TableDataInfo<EcomOrderItemVo> queryPageList(EcomOrderItemBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的订单明细列表
     *
     * @param bo 查询条件
     * @return 订单明细列表
     */
    List<EcomOrderItemVo> queryList(EcomOrderItemBo bo);

    /**
     * 新增订单明细
     *
     * @param bo 订单明细
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomOrderItemBo bo);

    /**
     * 修改订单明细
     *
     * @param bo 订单明细
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomOrderItemBo bo);

    /**
     * 校验并批量删除订单明细信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
