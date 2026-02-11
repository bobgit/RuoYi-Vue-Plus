package org.dromara.ecom.domain.bo;

import org.dromara.ecom.domain.EcomProductCategory;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 商品分类业务对象 ecom_product_category
 *
 * @author Bob Bai
 * @date 2026-01-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = EcomProductCategory.class, reverseConvertGenerate = false)
public class EcomProductCategoryBo extends BaseEntity {

    /**
     * 分类ID
     */
    @NotNull(message = "分类ID不能为空", groups = { EditGroup.class })
    private Long categoryId;

    /**
     * 父分类ID
     */
    private Long parentId;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String categoryName;

    /**
     * 分类编码
     */
    private String categoryCode;

    /**
     * 编码路径
     */
    private String categoryCodePath;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 业务规则
     */
    private String ruleTemplate;

    /**
     * 规格模板
     */
    private String specTemplate;

    /**
     * 运费模板ID
     */
    private String deliveryTemplate;

    /**
     * 扩展属性
     */
    private String attributeSchema;

    /**
     * 特殊管控规则
     */
    private String specialControl;

    /**
     * 排序权重
     */
    private Long sortOrder;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
