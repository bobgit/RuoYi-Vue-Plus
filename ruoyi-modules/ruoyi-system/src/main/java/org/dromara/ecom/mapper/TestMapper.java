package org.dromara.ecom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.dromara.ecom.domain.TestEntity;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface TestMapper extends BaseMapper<TestEntity> {
//
//    @Select("SELECT * FROM test_jsonb WHERE rule_template ->> 'rule_name' = #{ruleName}")
//    List<TestJsonb> selectByRuleName(@Param("ruleName") String ruleName);
//
//    @Select("SELECT * FROM example WHERE id @> '{\"name\": #{name}}'::jsonb")
//    TestEntity selectById(@Param("name") String name);

//    @Results({
//        @Result(property = "id", column = "id"),
//        @Result(property = "ruleTemplate", column = "rule_template",
//            javaType = HashMap.class,
//            typeHandler = JsonbTypeHandler.class)
//    })
//    @Select("SELECT * FROM test_jsonb WHERE id = #{id}")
//    TestEntity selectById(@Param("id") Long id);

}
