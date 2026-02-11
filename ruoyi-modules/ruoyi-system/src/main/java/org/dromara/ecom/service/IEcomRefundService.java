package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomRefundVo;
import org.dromara.ecom.domain.bo.EcomRefundBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 退款Service接口
 *
 * @author Bob Bai
 * @date 2026-02-07
 */
public interface IEcomRefundService {

    /**
     * 查询退款
     *
     * @param refundId 主键
     * @return 退款
     */
    EcomRefundVo queryById(Long refundId);

    /**
     * 分页查询退款列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 退款分页列表
     */
    TableDataInfo<EcomRefundVo> queryPageList(EcomRefundBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的退款列表
     *
     * @param bo 查询条件
     * @return 退款列表
     */
    List<EcomRefundVo> queryList(EcomRefundBo bo);

    /**
     * 新增退款
     *
     * @param bo 退款
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomRefundBo bo);

    /**
     * 修改退款
     *
     * @param bo 退款
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomRefundBo bo);

    /**
     * 校验并批量删除退款信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
