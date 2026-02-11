package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomSettlementVo;
import org.dromara.ecom.domain.bo.EcomSettlementBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 结算Service接口
 *
 * @author Bob Bai
 * @date 2026-02-06
 */
public interface IEcomSettlementService {

    /**
     * 查询结算
     *
     * @param settlementId 主键
     * @return 结算
     */
    EcomSettlementVo queryById(Long settlementId);

    /**
     * 分页查询结算列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 结算分页列表
     */
    TableDataInfo<EcomSettlementVo> queryPageList(EcomSettlementBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的结算列表
     *
     * @param bo 查询条件
     * @return 结算列表
     */
    List<EcomSettlementVo> queryList(EcomSettlementBo bo);

    /**
     * 新增结算
     *
     * @param bo 结算
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomSettlementBo bo);

    /**
     * 修改结算
     *
     * @param bo 结算
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomSettlementBo bo);

    /**
     * 校验并批量删除结算信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
