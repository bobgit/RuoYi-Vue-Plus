package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomOrderLogVo;
import org.dromara.ecom.domain.bo.EcomOrderLogBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 订单变更动态日志Service接口
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
public interface IEcomOrderLogService {

    /**
     * 查询订单变更动态日志
     *
     * @param orderLogId 主键
     * @return 订单变更动态日志
     */
    EcomOrderLogVo queryById(Long orderLogId);

    /**
     * 分页查询订单变更动态日志列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 订单变更动态日志分页列表
     */
    TableDataInfo<EcomOrderLogVo> queryPageList(EcomOrderLogBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的订单变更动态日志列表
     *
     * @param bo 查询条件
     * @return 订单变更动态日志列表
     */
    List<EcomOrderLogVo> queryList(EcomOrderLogBo bo);

    /**
     * 新增订单变更动态日志
     *
     * @param bo 订单变更动态日志
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomOrderLogBo bo);

    /**
     * 修改订单变更动态日志
     *
     * @param bo 订单变更动态日志
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomOrderLogBo bo);

    /**
     * 校验并批量删除订单变更动态日志信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
