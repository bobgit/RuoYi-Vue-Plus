package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomFulfillmentTaskVo;
import org.dromara.ecom.domain.bo.EcomFulfillmentTaskBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 履约单Service接口
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
public interface IEcomFulfillmentTaskService {

    /**
     * 查询履约单
     *
     * @param fulfillmentTaskId 主键
     * @return 履约单
     */
    EcomFulfillmentTaskVo queryById(Long fulfillmentTaskId);

    /**
     * 分页查询履约单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 履约单分页列表
     */
    TableDataInfo<EcomFulfillmentTaskVo> queryPageList(EcomFulfillmentTaskBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的履约单列表
     *
     * @param bo 查询条件
     * @return 履约单列表
     */
    List<EcomFulfillmentTaskVo> queryList(EcomFulfillmentTaskBo bo);

    /**
     * 新增履约单
     *
     * @param bo 履约单
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomFulfillmentTaskBo bo);

    /**
     * 修改履约单
     *
     * @param bo 履约单
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomFulfillmentTaskBo bo);

    /**
     * 校验并批量删除履约单信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
