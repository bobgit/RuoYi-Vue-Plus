//package org.dromara.common.mybatis.handler;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//import org.postgresql.util.PGobject;
//
//import java.sql.CallableStatement;
//;import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JsonbTypeHandler extends BaseTypeHandler<Map<String, Object>> {
//
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, Object> parameter, JdbcType jdbcType) throws SQLException {
//        PGobject jsonObject = new PGobject();
//        jsonObject.setType("jsonb");
//        try {
//            jsonObject.setValue(objectMapper.writeValueAsString(parameter));
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Failed to serialize Map to JSON", e);
//        }
//        ps.setObject(i, jsonObject);
//    }
//
//    @Override
//    public Map<String, Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        String json = rs.getString(columnName);
//        // 修复关键：直接返回解析结果，但使用 HashMap
//        if (json == null || json.isEmpty()) {
//            return new HashMap<>();
//        }
//        try {
//            // 重要修复：使用 HashMap.class 替代 Map.class
//            return objectMapper.readValue(json, HashMap.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new HashMap<>();
//        }
////        return parseJson(rs.getString(columnName));
//    }
//
//    @Override
//    public Map<String, Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        String json = rs.getString(columnIndex);
//        // 修复关键：直接返回解析结果，但使用 HashMap
//        if (json == null || json.isEmpty()) {
//            return new HashMap<>();
//        }
//        try {
//            // 重要修复：使用 HashMap.class 替代 Map.class
//            return objectMapper.readValue(json, HashMap.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new HashMap<>();
//        }
////        return parseJson(rs.getString(columnIndex));
//    }
//
//    @Override
//    public Map<String, Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        return parseJson(cs.getString(columnIndex));
//    }
//
//    private Map<String, Object> parseJson(String json) {
//        if (json == null || json.isEmpty()) {
////            return Map.of(); // 或返回 new HashMap<>()
//            return new HashMap<>();
//        }
//        try {
//            return objectMapper.readValue(json, HashMap.class);
////            return objectMapper.readValue(json, new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {});
//        } catch (Exception e) {
////            throw new RuntimeException("Failed to deserialize JSON to Map", e);
//            e.printStackTrace();
//            return new HashMap<>();
//        }
//    }
//}
//
//
