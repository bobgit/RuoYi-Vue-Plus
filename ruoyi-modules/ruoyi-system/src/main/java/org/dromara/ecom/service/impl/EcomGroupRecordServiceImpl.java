package org.dromara.ecom.service.impl;

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
import org.dromara.ecom.domain.bo.EcomGroupRecordBo;
import org.dromara.ecom.domain.vo.EcomGroupRecordVo;
import org.dromara.ecom.domain.EcomGroupRecord;
import org.dromara.ecom.mapper.EcomGroupRecordMapper;
import org.dromara.ecom.service.IEcomGroupRecordService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 团购参团记录Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomGroupRecordServiceImpl implements IEcomGroupRecordService {

    private final EcomGroupRecordMapper baseMapper;

    /**
     * 查询团购参团记录
     *
     * @param recordId 主键
     * @return 团购参团记录
     */
    @Override
    public EcomGroupRecordVo queryById(Long recordId){
        return baseMapper.selectVoById(recordId);
    }

    /**
     * 分页查询团购参团记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 团购参团记录分页列表
     */
    @Override
    public TableDataInfo<EcomGroupRecordVo> queryPageList(EcomGroupRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomGroupRecord> lqw = buildQueryWrapper(bo);
        Page<EcomGroupRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的团购参团记录列表
     *
     * @param bo 查询条件
     * @return 团购参团记录列表
     */
    @Override
    public List<EcomGroupRecordVo> queryList(EcomGroupRecordBo bo) {
        LambdaQueryWrapper<EcomGroupRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomGroupRecord> buildQueryWrapper(EcomGroupRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomGroupRecord> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomGroupRecord::getRecordId);
        lqw.eq(bo.getActivityProductId() != null, EcomGroupRecord::getActivityProductId, bo.getActivityProductId());
        lqw.eq(bo.getLeaderId() != null, EcomGroupRecord::getLeaderId, bo.getLeaderId());
        lqw.eq(bo.getCurrentCount() != null, EcomGroupRecord::getCurrentCount, bo.getCurrentCount());
        lqw.eq(bo.getTargetCount() != null, EcomGroupRecord::getTargetCount, bo.getTargetCount());
        lqw.eq(StringUtils.isNotBlank(bo.getGroupStatus()), EcomGroupRecord::getGroupStatus, bo.getGroupStatus());
        lqw.eq(bo.getExpireTime() != null, EcomGroupRecord::getExpireTime, bo.getExpireTime());
        return lqw;
    }

    /**
     * 新增团购参团记录
     *
     * @param bo 团购参团记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomGroupRecordBo bo) {
        EcomGroupRecord add = MapstructUtils.convert(bo, EcomGroupRecord.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRecordId(add.getRecordId());
        }
        return flag;
    }

    /**
     * 修改团购参团记录
     *
     * @param bo 团购参团记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomGroupRecordBo bo) {
        EcomGroupRecord update = MapstructUtils.convert(bo, EcomGroupRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomGroupRecord entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除团购参团记录信息
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
