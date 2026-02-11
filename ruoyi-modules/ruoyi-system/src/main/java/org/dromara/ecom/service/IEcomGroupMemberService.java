package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomGroupMemberVo;
import org.dromara.ecom.domain.bo.EcomGroupMemberBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 拼团团员Service接口
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
public interface IEcomGroupMemberService {

    /**
     * 查询拼团团员
     *
     * @param memberId 主键
     * @return 拼团团员
     */
    EcomGroupMemberVo queryById(Long memberId);

    /**
     * 分页查询拼团团员列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 拼团团员分页列表
     */
    TableDataInfo<EcomGroupMemberVo> queryPageList(EcomGroupMemberBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的拼团团员列表
     *
     * @param bo 查询条件
     * @return 拼团团员列表
     */
    List<EcomGroupMemberVo> queryList(EcomGroupMemberBo bo);

    /**
     * 新增拼团团员
     *
     * @param bo 拼团团员
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomGroupMemberBo bo);

    /**
     * 修改拼团团员
     *
     * @param bo 拼团团员
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomGroupMemberBo bo);

    /**
     * 校验并批量删除拼团团员信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
