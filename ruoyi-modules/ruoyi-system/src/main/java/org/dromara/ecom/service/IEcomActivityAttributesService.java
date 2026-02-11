package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomActivityAttributesVo;
import org.dromara.ecom.domain.bo.EcomActivityAttributesBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 活动属性Service接口
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
public interface IEcomActivityAttributesService {

    /**
     * 查询活动属性
     *
     * @param attrId 主键
     * @return 活动属性
     */
    EcomActivityAttributesVo queryById(Long attrId);

    /**
     * 分页查询活动属性列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 活动属性分页列表
     */
    TableDataInfo<EcomActivityAttributesVo> queryPageList(EcomActivityAttributesBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的活动属性列表
     *
     * @param bo 查询条件
     * @return 活动属性列表
     */
    List<EcomActivityAttributesVo> queryList(EcomActivityAttributesBo bo);

    /**
     * 新增活动属性
     *
     * @param bo 活动属性
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomActivityAttributesBo bo);

    /**
     * 修改活动属性
     *
     * @param bo 活动属性
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomActivityAttributesBo bo);

    /**
     * 校验并批量删除活动属性信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
