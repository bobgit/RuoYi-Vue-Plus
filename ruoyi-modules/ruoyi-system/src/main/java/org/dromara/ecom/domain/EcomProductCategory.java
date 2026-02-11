package org.dromara.ecom.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 商品分类对象 ecom_product_category
 *
 * @author Bob Bai
 * @date 2026-01-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ecom_product_category")
public class EcomProductCategory extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId(value = "category_id")
    private Long categoryId;

    /**
     * 父分类ID
     */
    private Long parentId;

    /**
     * 分类名称
     */
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
     * 删除标志
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注
     */
    private String remark;


}
