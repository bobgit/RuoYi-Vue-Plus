package org.dromara.system.service;

import org.dromara.system.domain.SysTaskRecord;
import org.dromara.system.domain.vo.SysTaskRecordVo;
import org.dromara.system.domain.bo.SysTaskRecordBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 系统任务状态记录机器Service接口
 *
 * @author Lion Li
 * @date 2025-12-11
 */
public interface ISysTaskRecordService {


    public boolean insertOrUpdate(SysTaskRecord sysTaskRecord);
    /**
     * 查询系统任务状态记录机器
     *
     * @param id 主键
     * @return 系统任务状态记录机器
     */
    SysTaskRecordVo queryById(Long id);

    /**
     * 分页查询系统任务状态记录机器列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 系统任务状态记录机器分页列表
     */
    TableDataInfo<SysTaskRecordVo> queryPageList(SysTaskRecordBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的系统任务状态记录机器列表
     *
     * @param bo 查询条件
     * @return 系统任务状态记录机器列表
     */
    List<SysTaskRecordVo> queryList(SysTaskRecordBo bo);


    List<SysTaskRecord> findByStatusAndRetryCountLessThan(String status,Long retryCount);
    /**
     * 新增系统任务状态记录机器
     *
     * @param bo 系统任务状态记录机器
     * @return 是否新增成功
     */
    Boolean insertByBo(SysTaskRecordBo bo);

    /**
     * 修改系统任务状态记录机器
     *
     * @param bo 系统任务状态记录机器
     * @return 是否修改成功
     */
    Boolean updateByBo(SysTaskRecordBo bo);

    /**
     * 校验并批量删除系统任务状态记录机器信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
