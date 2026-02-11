package org.dromara.system.service;

import org.dromara.system.domain.vo.SysAddressesVo;
import org.dromara.system.domain.bo.SysAddressesBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 国际地址Service接口
 *
 * @author Lion Li
 * @date 2026-01-05
 */
public interface ISysAddressesService {

    /**
     * 查询国际地址
     *
     * @param addressId 主键
     * @return 国际地址
     */
    SysAddressesVo queryById(Long addressId);

    /**
     * 分页查询国际地址列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 国际地址分页列表
     */
    TableDataInfo<SysAddressesVo> queryPageList(SysAddressesBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的国际地址列表
     *
     * @param bo 查询条件
     * @return 国际地址列表
     */
    List<SysAddressesVo> queryList(SysAddressesBo bo);

    /**
     * 新增国际地址
     *
     * @param bo 国际地址
     * @return 是否新增成功
     */
    Boolean insertByBo(SysAddressesBo bo);

    /**
     * 修改国际地址
     *
     * @param bo 国际地址
     * @return 是否修改成功
     */
    Boolean updateByBo(SysAddressesBo bo);

    /**
     * 校验并批量删除国际地址信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
