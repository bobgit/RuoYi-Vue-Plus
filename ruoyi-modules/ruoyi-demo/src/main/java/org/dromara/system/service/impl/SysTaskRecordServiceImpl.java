package org.dromara.system.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.dromara.system.domain.bo.SysTaskRecordBo;
import org.dromara.system.domain.vo.SysTaskRecordVo;
import org.dromara.system.domain.SysTaskRecord;
import org.dromara.system.mapper.SysTaskRecordMapper;
import org.dromara.system.service.ISysTaskRecordService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 系统任务状态记录机器Service业务层处理
 *
 * @author Lion Li
 * @date 2025-12-11
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysTaskRecordServiceImpl implements ISysTaskRecordService {

    private final SysTaskRecordMapper baseMapper;

    public boolean insertOrUpdate(SysTaskRecord sysTaskRecord){
        return baseMapper.insertOrUpdate(sysTaskRecord);
    }
    /**
     * 查询系统任务状态记录机器
     *
     * @param id 主键
     * @return 系统任务状态记录机器
     */
    @Override
    public SysTaskRecordVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询系统任务状态记录机器列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 系统任务状态记录机器分页列表
     */
    @Override
    public TableDataInfo<SysTaskRecordVo> queryPageList(SysTaskRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysTaskRecord> lqw = buildQueryWrapper(bo);
        Page<SysTaskRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的系统任务状态记录机器列表
     *
     * @param bo 查询条件
     * @return 系统任务状态记录机器列表
     */
    @Override
    public List<SysTaskRecordVo> queryList(SysTaskRecordBo bo) {
        LambdaQueryWrapper<SysTaskRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    @Override
    public List<SysTaskRecord> findByStatusAndRetryCountLessThan(String status,Long retryCount) {
        LambdaQueryWrapper<SysTaskRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(status),SysTaskRecord::getStatus,status).le(SysTaskRecord::getRetryCount,retryCount);
        lqw.orderByAsc(SysTaskRecord::getId);
        return baseMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<SysTaskRecord> buildQueryWrapper(SysTaskRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysTaskRecord> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SysTaskRecord::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getTaskType()), SysTaskRecord::getTaskType, bo.getTaskType());
        lqw.eq(StringUtils.isNotBlank(bo.getData()), SysTaskRecord::getData, bo.getData());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SysTaskRecord::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getErrorMsg()), SysTaskRecord::getErrorMsg, bo.getErrorMsg());
        lqw.eq(bo.getRetryCount() != null, SysTaskRecord::getRetryCount, bo.getRetryCount());
        return lqw;
    }

    /**
     * 新增系统任务状态记录机器
     *
     * @param bo 系统任务状态记录机器
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SysTaskRecordBo bo) {
        SysTaskRecord add = MapstructUtils.convert(bo, SysTaskRecord.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }


    /**
     * 修改系统任务状态记录机器
     *
     * @param bo 系统任务状态记录机器
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysTaskRecordBo bo) {
        SysTaskRecord update = MapstructUtils.convert(bo, SysTaskRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysTaskRecord entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除系统任务状态记录机器信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
