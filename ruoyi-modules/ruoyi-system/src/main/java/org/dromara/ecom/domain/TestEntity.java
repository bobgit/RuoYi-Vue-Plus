package org.dromara.ecom.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// 实体类  private Map<String, Object> ruleTemplate;  // 自动将JSONB数据映射为Map  如果private String ruleTemplate;  则不需要
//给@TableName注解增加了autoResultMap = true属性。这是一个关键设置，它告诉MyBatis-Plus自动为该实体生成一个ResultMap，以确保查询时能够正确地将结果集映射到实体类属性。
// 如果不加此设置，插入操作可能正常，但查询时data字段可能无法正确映射，导致取值为null。因此，在使用字段类型处理器时，务必开启autoResultMap。
@Data
@TableName(value = "test_jsonb") //, autoResultMap = true)
public class TestEntity {
    @TableId(type = IdType.AUTO)
    private Long id;


//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    //@@@@@11111111111111
    // 关键修改：字段类型改为 String（存储原始 JSON 字符串）
    @TableField(value = "rule_template")
    private String ruleTemplate; // 不是 Map


//    @TableField(value = "rule_template", typeHandler = JacksonTypeHandler.class)
//    private Map<String, Object> ruleTemplate;  // 自动将JSONB数据映射为Map


    // 关键：使用 JacksonTypeHandler 自动处理 JSONB
//    @TableField(typeHandler = JacksonTypeHandler.class)
//    @TableField(value = "rule_template", typeHandler = JsonbTypeHandler.class)
//    private Map<String, Object> ruleTemplate;


//    public String getRuleTemplate() {
//        return ruleTemplate;
//    }
//
//    public void setRuleTemplate(String ruleTemplate) {
//        this.ruleTemplate = ruleTemplate;
//    }

//    @@@@222222222222222


    //    // 必须提供 getter
//    public Map<String, Object> getRuleTemplate() {
//        if (ruleTemplateJson == null || ruleTemplateJson.isEmpty()) {
//            return new HashMap<>();
//        }
//        try {
//            // 直接用 Jackson 解析 JSON 字符串
//            return new com.fasterxml.jackson.databind.ObjectMapper()
//                .readValue(ruleTemplateJson, Map.class);
//        } catch (Exception e) {
//            return new HashMap<>();
//        }
//    }
//
//    // 无需 setter（MyBatis-Plus 会自动设置 ruleTemplateJson）
//    public void setRuleTemplateJson(String ruleTemplateJson) {
//        this.ruleTemplateJson = ruleTemplateJson;
//    }

/*

    // 关键：直接使用Map，无需额外处理 修复关键：使用 @TableField + JdbcType
//    @TableField(typeHandler = JsonbTypeHandler.class, jdbcType = JdbcType.VARCHAR)
    private Map<String, Object> ruleTemplate = new HashMap<>();

    public TestEntity() {
        // MyBatis 需要默认构造函数
    }

    // 必须提供带参数的构造函数
    public TestEntity(Long id, String ruleTemplateJson) {
        this.id = id;
        this.ruleTemplate = parseJson(ruleTemplateJson);
    }

    // 必须提供 getter/setter
    public Map<String, Object> getRuleTemplate() {
        return ruleTemplate;
    }
    public void setRuleTemplate(Map<String, Object> ruleTemplate) {
        this.ruleTemplate = ruleTemplate;
    }


    private Map<String, Object> parseJson(String json) {
        try {
            return new ObjectMapper().readValue(json, Map.class);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }*/
}
