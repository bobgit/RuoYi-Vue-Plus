package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomLegalResponsibilityVo;
import org.dromara.ecom.domain.bo.EcomLegalResponsibilityBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 法律责任Service接口
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
public interface IEcomLegalResponsibilityService {

    /**
     * 查询法律责任
     *
     * @param responsibilityId 主键
     * @return 法律责任
     */
    EcomLegalResponsibilityVo queryById(Long responsibilityId);

    /**
     * 分页查询法律责任列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 法律责任分页列表
     */
    TableDataInfo<EcomLegalResponsibilityVo> queryPageList(EcomLegalResponsibilityBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的法律责任列表
     *
     * @param bo 查询条件
     * @return 法律责任列表
     */
    List<EcomLegalResponsibilityVo> queryList(EcomLegalResponsibilityBo bo);

    /**
     * 新增法律责任
     *
     * @param bo 法律责任
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomLegalResponsibilityBo bo);

    /**
     * 修改法律责任
     *
     * @param bo 法律责任
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomLegalResponsibilityBo bo);

    /**
     * 校验并批量删除法律责任信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
