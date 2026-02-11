package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomGroupRecordVo;
import org.dromara.ecom.domain.bo.EcomGroupRecordBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 团购参团记录Service接口
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
public interface IEcomGroupRecordService {

    /**
     * 查询团购参团记录
     *
     * @param recordId 主键
     * @return 团购参团记录
     */
    EcomGroupRecordVo queryById(Long recordId);

    /**
     * 分页查询团购参团记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 团购参团记录分页列表
     */
    TableDataInfo<EcomGroupRecordVo> queryPageList(EcomGroupRecordBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的团购参团记录列表
     *
     * @param bo 查询条件
     * @return 团购参团记录列表
     */
    List<EcomGroupRecordVo> queryList(EcomGroupRecordBo bo);

    /**
     * 新增团购参团记录
     *
     * @param bo 团购参团记录
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomGroupRecordBo bo);

    /**
     * 修改团购参团记录
     *
     * @param bo 团购参团记录
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomGroupRecordBo bo);

    /**
     * 校验并批量删除团购参团记录信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
