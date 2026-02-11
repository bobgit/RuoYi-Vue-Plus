package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 商品分类对象 ecom_i18n
 *
 * @author Bob Bai
 * @date 2026-02-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_i18n")
public class EcomI18n extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 通用国际化ID
     */
    @TableId(value = "i18n_id")
    private Long i18nId;

    /**
     * 业务表类型
     */
    private String bizType;

    /**
     * 业务id
     */
    private Long bizId;

    /**
     * 语言类型
     */
    private String lang;

    /**
     * 字段
     */
    private String field;

    /**
     * 翻译内容
     */
    private String content;

    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注
     */
    private String remark;


}
