package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomProductActivityVo;
import org.dromara.ecom.domain.bo.EcomProductActivityBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 活动商品关联Service接口
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
public interface IEcomProductActivityService {

    /**
     * 查询活动商品关联
     *
     * @param activityProductId 主键
     * @return 活动商品关联
     */
    EcomProductActivityVo queryById(Long activityProductId);

    /**
     * 分页查询活动商品关联列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 活动商品关联分页列表
     */
    TableDataInfo<EcomProductActivityVo> queryPageList(EcomProductActivityBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的活动商品关联列表
     *
     * @param bo 查询条件
     * @return 活动商品关联列表
     */
    List<EcomProductActivityVo> queryList(EcomProductActivityBo bo);

    /**
     * 新增活动商品关联
     *
     * @param bo 活动商品关联
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomProductActivityBo bo);

    /**
     * 修改活动商品关联
     *
     * @param bo 活动商品关联
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomProductActivityBo bo);

    /**
     * 校验并批量删除活动商品关联信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
