package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomOrderTicketVo;
import org.dromara.ecom.domain.bo.EcomOrderTicketBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 门票/服务专用Service接口
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
public interface IEcomOrderTicketService {

    /**
     * 查询门票/服务专用
     *
     * @param ticketId 主键
     * @return 门票/服务专用
     */
    EcomOrderTicketVo queryById(Long ticketId);

    /**
     * 分页查询门票/服务专用列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 门票/服务专用分页列表
     */
    TableDataInfo<EcomOrderTicketVo> queryPageList(EcomOrderTicketBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的门票/服务专用列表
     *
     * @param bo 查询条件
     * @return 门票/服务专用列表
     */
    List<EcomOrderTicketVo> queryList(EcomOrderTicketBo bo);

    /**
     * 新增门票/服务专用
     *
     * @param bo 门票/服务专用
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomOrderTicketBo bo);

    /**
     * 修改门票/服务专用
     *
     * @param bo 门票/服务专用
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomOrderTicketBo bo);

    /**
     * 校验并批量删除门票/服务专用信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
