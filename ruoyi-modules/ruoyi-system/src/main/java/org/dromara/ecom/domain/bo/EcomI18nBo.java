package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomI18n;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 商品分类业务对象 ecom_i18n
 *
 * @author Bob Bai
 * @date 2026-02-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomI18n.class, reverseConvertGenerate = false)
public class EcomI18nBo extends BaseEntity {

    /**
     * 通用国际化ID
     */
    @NotNull(message = "通用国际化ID不能为空", groups = { EditGroup.class })
    private Long i18nId;

    /**
     * 业务表类型
     */
    @NotBlank(message = "业务表类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bizType;

    /**
     * 业务id
     */
    @NotNull(message = "业务id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long bizId;

    /**
     * 语言类型
     */
    @NotBlank(message = "语言类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String lang;

    /**
     * 字段
     */
    @NotBlank(message = "字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private String field;

    /**
     * 翻译内容
     */
    @NotBlank(message = "翻译内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;

    /**
     * 备注
     */
    private String remark;


}
