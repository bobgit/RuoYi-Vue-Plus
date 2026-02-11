package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomOrderDeliveryVo;
import org.dromara.ecom.domain.bo.EcomOrderDeliveryBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 订单配送专用Service接口
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
public interface IEcomOrderDeliveryService {

    /**
     * 查询订单配送专用
     *
     * @param orderDeliveryId 主键
     * @return 订单配送专用
     */
    EcomOrderDeliveryVo queryById(Long orderDeliveryId);

    /**
     * 分页查询订单配送专用列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 订单配送专用分页列表
     */
    TableDataInfo<EcomOrderDeliveryVo> queryPageList(EcomOrderDeliveryBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的订单配送专用列表
     *
     * @param bo 查询条件
     * @return 订单配送专用列表
     */
    List<EcomOrderDeliveryVo> queryList(EcomOrderDeliveryBo bo);

    /**
     * 新增订单配送专用
     *
     * @param bo 订单配送专用
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomOrderDeliveryBo bo);

    /**
     * 修改订单配送专用
     *
     * @param bo 订单配送专用
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomOrderDeliveryBo bo);

    /**
     * 校验并批量删除订单配送专用信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
