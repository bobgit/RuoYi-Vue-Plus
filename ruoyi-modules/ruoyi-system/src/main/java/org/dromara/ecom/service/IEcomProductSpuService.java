package org.dromara.ecom.service;

import org.dromara.ecom.domain.vo.EcomProductSpuVo;
import org.dromara.ecom.domain.bo.EcomProductSpuBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * SPU标准产品单元Service接口
 *
 * @author Bob Bai
 * @date 2026-02-05
 */
public interface IEcomProductSpuService {

    /**
     * 查询SPU标准产品单元
     *
     * @param spuId 主键
     * @return SPU标准产品单元
     */
    EcomProductSpuVo queryById(Long spuId);

    /**
     * 分页查询SPU标准产品单元列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SPU标准产品单元分页列表
     */
    TableDataInfo<EcomProductSpuVo> queryPageList(EcomProductSpuBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SPU标准产品单元列表
     *
     * @param bo 查询条件
     * @return SPU标准产品单元列表
     */
    List<EcomProductSpuVo> queryList(EcomProductSpuBo bo);

    /**
     * 新增SPU标准产品单元
     *
     * @param bo SPU标准产品单元
     * @return 是否新增成功
     */
    Boolean insertByBo(EcomProductSpuBo bo);

    /**
     * 修改SPU标准产品单元
     *
     * @param bo SPU标准产品单元
     * @return 是否修改成功
     */
    Boolean updateByBo(EcomProductSpuBo bo);

    /**
     * 校验并批量删除SPU标准产品单元信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
