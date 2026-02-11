package org.dromara.ecom.controller;

import cn.hutool.json.JSONUtil;
import org.dromara.ecom.domain.TestEntity;
import org.dromara.ecom.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestMapper testMapper;

//    @PostMapping("/save")
//    public String save(@RequestBody Map<String, Object> request) {
//        // 前端传入：{"ruleTemplate": {"a": "aaa"}}
//        TestEntity entity = new TestEntity();
//        entity.setRuleTemplate((Map<String, Object>) request.get("ruleTemplate"));
//        testMapper.insert(entity);
//        return "Success";
//    }


    @PostMapping("/save")
    public String save(@RequestBody TestEntity entity) {
        // 前端传入：{"ruleTemplate": {"a": "aaa"}}
//        TestEntity entity = new TestEntity();
//        entity.setRuleTemplate((Map<String, Object>) request.get("ruleTemplate"));
        testMapper.insert(entity);
        return "Success";
    }

    @GetMapping("/get/{id}")
    public TestEntity get(@PathVariable Long id) {
        TestEntity testEntity = testMapper.selectById(id);
        String testEntityJson = JSONUtil.toJsonStr(testEntity);
        System.out.println("testEntityJson="+testEntityJson);
        // 修复关键：这里会返回非空 Map
        System.out.println("Entity ruleTemplate: " + testEntity.getRuleTemplate());
        return testEntity;
    }
}


/*

    @TableField(value = "rule_template")
    private String ruleTemplate;
{"ruleTemplate":"{\"actions\": [{\"type\": \"discount\", \"value\": 0.9}], \"metadata\": {\"enabled\": true, \"createdBy\": \"admin\"}, \"ruleName\": \"VIP客户折扣\",     \"conditions\": [{\"field\": \"amount\", \"value\": 1000, \"operator\": \">=\"}]}"}
http://localhost/dev-api/api/save
用如下的不行：
{
  "ruleTemplate": {
  "ruleName": "VIP客户折扣",
  "conditions": [{"field": "amount", "operator": ">=", "value": 1000}],
  "actions": [{"type": "discount", "value": 0.9}],
  "metadata": {"createdBy": "admin", "enabled": true}
    }
}







 */
