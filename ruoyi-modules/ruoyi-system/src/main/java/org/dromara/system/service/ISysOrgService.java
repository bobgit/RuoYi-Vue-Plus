package org.dromara.system.service;

import org.dromara.system.domain.vo.SysOrgVo;
import org.dromara.system.domain.bo.SysOrgBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 机构组织公司Service接口
 *
 * @author Bob Ok
 * @date 2026-02-11
 */
public interface ISysOrgService {

    /**
     * 查询机构组织公司
     *
     * @param orgId 主键
     * @return 机构组织公司
     */
    SysOrgVo queryById(Long orgId);

    /**
     * 分页查询机构组织公司列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 机构组织公司分页列表
     */
    TableDataInfo<SysOrgVo> queryPageList(SysOrgBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的机构组织公司列表
     *
     * @param bo 查询条件
     * @return 机构组织公司列表
     */
    List<SysOrgVo> queryList(SysOrgBo bo);

    /**
     * 新增机构组织公司
     *
     * @param bo 机构组织公司
     * @return 是否新增成功
     */
    Boolean insertByBo(SysOrgBo bo);

    /**
     * 修改机构组织公司
     *
     * @param bo 机构组织公司
     * @return 是否修改成功
     */
    Boolean updateByBo(SysOrgBo bo);

    /**
     * 校验并批量删除机构组织公司信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
