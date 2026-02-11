package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomOrderVo;
import org.dromara.ecom.domain.bo.EcomOrderBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 订单Service接口
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
public interface IEcomOrderService {

    /**
     * 查询订单
     *
     * @param orderId 主键
     * @return 订单
     */
    EcomOrderVo queryById(Long orderId);

    /**
     * 分页查询订单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 订单分页列表
     */
    TableDataInfo<EcomOrderVo> queryPageList(EcomOrderBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的订单列表
     *
     * @param bo 查询条件
     * @return 订单列表
     */
    List<EcomOrderVo> queryList(EcomOrderBo bo);

    /**
     * 新增订单
     *
     * @param bo 订单
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomOrderBo bo);

    /**
     * 修改订单
     *
     * @param bo 订单
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomOrderBo bo);

    /**
     * 校验并批量删除订单信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
