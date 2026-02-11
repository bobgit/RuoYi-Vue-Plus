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
import org.dromara.ecom.domain.bo.EcomOrderTicketBo;
import org.dromara.ecom.domain.vo.EcomOrderTicketVo;
import org.dromara.ecom.domain.EcomOrderTicket;
import org.dromara.ecom.mapper.EcomOrderTicketMapper;
import org.dromara.ecom.service.IEcomOrderTicketService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 门票/服务专用Service业务层处理
 *
 * @author Bob Bai
 * @date 2026-01-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EcomOrderTicketServiceImpl implements IEcomOrderTicketService {

    private final EcomOrderTicketMapper baseMapper;

    /**
     * 查询门票/服务专用
     *
     * @param ticketId 主键
     * @return 门票/服务专用
     */
    @Override
    public EcomOrderTicketVo queryById(Long ticketId){
        return baseMapper.selectVoById(ticketId);
    }

    /**
     * 分页查询门票/服务专用列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 门票/服务专用分页列表
     */
    @Override
    public TableDataInfo<EcomOrderTicketVo> queryPageList(EcomOrderTicketBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<EcomOrderTicket> lqw = buildQueryWrapper(bo);
        Page<EcomOrderTicketVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的门票/服务专用列表
     *
     * @param bo 查询条件
     * @return 门票/服务专用列表
     */
    @Override
    public List<EcomOrderTicketVo> queryList(EcomOrderTicketBo bo) {
        LambdaQueryWrapper<EcomOrderTicket> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<EcomOrderTicket> buildQueryWrapper(EcomOrderTicketBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<EcomOrderTicket> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(EcomOrderTicket::getTicketId);
        lqw.eq(bo.getOrderId() != null, EcomOrderTicket::getOrderId, bo.getOrderId());
        lqw.eq(StringUtils.isNotBlank(bo.getCodeNo()), EcomOrderTicket::getCodeNo, bo.getCodeNo());
        lqw.eq(StringUtils.isNotBlank(bo.getQrCode()), EcomOrderTicket::getQrCode, bo.getQrCode());
        lqw.eq(bo.getValidStart() != null, EcomOrderTicket::getValidStart, bo.getValidStart());
        lqw.eq(bo.getValidEnd() != null, EcomOrderTicket::getValidEnd, bo.getValidEnd());
        lqw.eq(StringUtils.isNotBlank(bo.getVerifyStatus()), EcomOrderTicket::getVerifyStatus, bo.getVerifyStatus());
        return lqw;
    }

    /**
     * 新增门票/服务专用
     *
     * @param bo 门票/服务专用
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(EcomOrderTicketBo bo) {
        EcomOrderTicket add = MapstructUtils.convert(bo, EcomOrderTicket.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTicketId(add.getTicketId());
        }
        return flag;
    }

    /**
     * 修改门票/服务专用
     *
     * @param bo 门票/服务专用
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(EcomOrderTicketBo bo) {
        EcomOrderTicket update = MapstructUtils.convert(bo, EcomOrderTicket.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(EcomOrderTicket entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除门票/服务专用信息
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
