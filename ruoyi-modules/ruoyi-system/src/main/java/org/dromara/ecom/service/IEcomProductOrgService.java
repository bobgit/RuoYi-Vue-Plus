package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomProductOrgVo;
import org.dromara.ecom.domain.bo.EcomProductOrgBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 机构组织商品Service接口
 *
 * @author Bob Ok
 * @date 2026-01-27
 */
public interface IEcomProductOrgService {

    /**
     * 查询机构组织商品
     *
     * @param orgProductId 主键
     * @return 机构组织商品
     */
    EcomProductOrgVo queryById(Long orgProductId);

    /**
     * 分页查询机构组织商品列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 机构组织商品分页列表
     */
    TableDataInfo<EcomProductOrgVo> queryPageList(EcomProductOrgBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的机构组织商品列表
     *
     * @param bo 查询条件
     * @return 机构组织商品列表
     */
    List<EcomProductOrgVo> queryList(EcomProductOrgBo bo);

    /**
     * 新增机构组织商品
     *
     * @param bo 机构组织商品
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomProductOrgBo bo);

    /**
     * 修改机构组织商品
     *
     * @param bo 机构组织商品
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomProductOrgBo bo);

    /**
     * 校验并批量删除机构组织商品信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
