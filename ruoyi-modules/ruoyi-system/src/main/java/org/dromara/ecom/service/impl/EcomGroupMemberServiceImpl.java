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
import org.dromara.ecom.domain.bo.EcomGroupMemberBo;
import org.dromara.ecom.domain.vo.EcomGroupMemberVo;
import org.dromara.ecom.domain.EcomGroupMember;
import org.dromara.ecom.mapper.EcomGroupMemberMapper;
import org.dromara.ecom.service.IEcomGroupMemberService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 拼团团员Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomGroupMemberServiceImpl implements IEcomGroupMemberService {

    private final EcomGroupMemberMapper baseMapper;

    /**
     * 查询拼团团员
     *
     * @param memberId 主键
     * @return 拼团团员
     */
    @Override
    public EcomGroupMemberVo queryById(Long memberId){
        return baseMapper.selectVoById(memberId);
    }

    /**
     * 分页查询拼团团员列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 拼团团员分页列表
     */
    @Override
    public TableDataInfo<EcomGroupMemberVo> queryPageList(EcomGroupMemberBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomGroupMember> lqw = buildQueryWrapper(bo);
        Page<EcomGroupMemberVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的拼团团员列表
     *
     * @param bo 查询条件
     * @return 拼团团员列表
     */
    @Override
    public List<EcomGroupMemberVo> queryList(EcomGroupMemberBo bo) {
        LambdaQueryWrapper<EcomGroupMember> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomGroupMember> buildQueryWrapper(EcomGroupMemberBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomGroupMember> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomGroupMember::getMemberId);
        lqw.eq(bo.getRecordId() != null, EcomGroupMember::getRecordId, bo.getRecordId());
        lqw.eq(bo.getUserId() != null, EcomGroupMember::getUserId, bo.getUserId());
        lqw.eq(bo.getOrderId() != null, EcomGroupMember::getOrderId, bo.getOrderId());
        lqw.eq(bo.getJoinTime() != null, EcomGroupMember::getJoinTime, bo.getJoinTime());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), EcomGroupMember::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增拼团团员
     *
     * @param bo 拼团团员
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomGroupMemberBo bo) {
        EcomGroupMember add = MapstructUtils.convert(bo, EcomGroupMember.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setMemberId(add.getMemberId());
        }
        return flag;
    }

    /**
     * 修改拼团团员
     *
     * @param bo 拼团团员
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomGroupMemberBo bo) {
        EcomGroupMember update = MapstructUtils.convert(bo, EcomGroupMember.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomGroupMember entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除拼团团员信息
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
