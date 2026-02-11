package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomOrderDeliveryLogVo;
import org.dromara.ecom.domain.bo.EcomOrderDeliveryLogBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 配送状态变更日志Service接口
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
public interface IEcomOrderDeliveryLogService {

    /**
     * 查询配送状态变更日志
     *
     * @param deliveryLogId 主键
     * @return 配送状态变更日志
     */
    EcomOrderDeliveryLogVo queryById(Long deliveryLogId);

    /**
     * 分页查询配送状态变更日志列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 配送状态变更日志分页列表
     */
    TableDataInfo<EcomOrderDeliveryLogVo> queryPageList(EcomOrderDeliveryLogBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的配送状态变更日志列表
     *
     * @param bo 查询条件
     * @return 配送状态变更日志列表
     */
    List<EcomOrderDeliveryLogVo> queryList(EcomOrderDeliveryLogBo bo);

    /**
     * 新增配送状态变更日志
     *
     * @param bo 配送状态变更日志
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomOrderDeliveryLogBo bo);

    /**
     * 修改配送状态变更日志
     *
     * @param bo 配送状态变更日志
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomOrderDeliveryLogBo bo);

    /**
     * 校验并批量删除配送状态变更日志信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
