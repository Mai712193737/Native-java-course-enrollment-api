package com.coursemanagement.util;

import com.coursemanagement.ecxception.ecxception;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    public static Map<String, Object> parseObject(String json) {
        if (json == null || json.isBlank()) {
            throw new ecxception("Request body is empty");
        }
        int[] pos = {0};
        Object value;
        try {
            value = parseValue(json, pos);
            skipWhitespace(json, pos);
        } catch (ecxception e) {
            throw e;
        } catch (Exception e) {
            throw new ecxception("Invalid JSON");
        }
        if (pos[0] != json.length()) {
            throw new ecxception("Invalid JSON");
        }
        if (!(value instanceof Map)) {
            throw new ecxception("Invalid JSON");
        }
        return (Map<String, Object>) value;
    }

    private static Object parseValue(String s, int[] pos) {
        skipWhitespace(s, pos);
        char c = s.charAt(pos[0]);
        if (c == '{') {
            return parseObjectValue(s, pos);
        }
        if (c == '"') {
            return parseStringValue(s, pos);
        }
        if (c == 't') {
            expect(s, pos, "true");
            return Boolean.TRUE;
        }
        if (c == 'f') {
            expect(s, pos, "false");
            return Boolean.FALSE;
        }
        if (c == 'n') {
            expect(s, pos, "null");
            return null;
        }
        return parseNumberValue(s, pos);
    }

    private static Map<String, Object> parseObjectValue(String s, int[] pos) {
        Map<String, Object> map = new LinkedHashMap<>();
        pos[0]++;
        skipWhitespace(s, pos);
        if (s.charAt(pos[0]) == '}') {
            pos[0]++;
            return map;
        }
        while (true) {
            skipWhitespace(s, pos);
            String key = parseStringValue(s, pos);
            skipWhitespace(s, pos);
            if (s.charAt(pos[0]) != ':') {
                throw new ecxception("Invalid JSON");
            }
            pos[0]++;
            Object value = parseValue(s, pos);
            map.put(key, value);
            skipWhitespace(s, pos);
            char c = s.charAt(pos[0]);
            if (c == ',') {
                pos[0]++;
                continue;
            }
            if (c == '}') {
                pos[0]++;
                break;
            }
            throw new ecxception("Invalid JSON");
        }
        return map;
    }

    private static String parseStringValue(String s, int[] pos) {
        if (s.charAt(pos[0]) != '"') {
            throw new ecxception("Invalid JSON");
        }
        pos[0]++;
        StringBuilder sb = new StringBuilder();
        while (s.charAt(pos[0]) != '"') {
            char c = s.charAt(pos[0]);
            if (c == '\\') {
                pos[0]++;
                char next = s.charAt(pos[0]);
                switch (next) {
                    case '"': sb.append('"'); break;
                    case '\\': sb.append('\\'); break;
                    case '/': sb.append('/'); break;
                    case 'n': sb.append('\n'); break;
                    case 't': sb.append('\t'); break;
                    case 'r': sb.append('\r'); break;
                    case 'b': sb.append('\b'); break;
                    case 'f': sb.append('\f'); break;
                    case 'u':
                        String hex = s.substring(pos[0] + 1, pos[0] + 5);
                        sb.append((char) Integer.parseInt(hex, 16));
                        pos[0] += 4;
                        break;
                    default:
                        throw new ecxception("Invalid JSON");
                }
                pos[0]++;
            } else {
                sb.append(c);
                pos[0]++;
            }
        }
        pos[0]++;
        return sb.toString();
    }

    private static BigDecimal parseNumberValue(String s, int[] pos) {
        int start = pos[0];
        while (pos[0] < s.length() && "-+.0123456789eE".indexOf(s.charAt(pos[0])) >= 0) {
            pos[0]++;
        }
        String number = s.substring(start, pos[0]);
        if (number.isEmpty()) {
            throw new ecxception("Invalid JSON");
        }
        return new BigDecimal(number);
    }

    private static void expect(String s, int[] pos, String literal) {
        if (pos[0] + literal.length() > s.length() || !s.substring(pos[0], pos[0] + literal.length()).equals(literal)) {
            throw new ecxception("Invalid JSON");
        }
        pos[0] += literal.length();
    }

    private static void skipWhitespace(String s, int[] pos) {
        while (pos[0] < s.length() && Character.isWhitespace(s.charAt(pos[0]))) {
            pos[0]++;
        }
    }

    public static boolean hasField(Map<String, Object> json, String key) {
        return json.containsKey(key) && json.get(key) != null;
    }

    public static String getString(Map<String, Object> json, String key) {
        if (!hasField(json, key)) {
            throw new ecxception("Missing required field: " + key);
        }
        Object value = json.get(key);
        if (!(value instanceof String)) {
            throw new ecxception("Field must be a string: " + key);
        }
        String text = (String) value;
        if (text.isBlank()) {
            throw new ecxception("Missing required field: " + key);
        }
        return text;
    }

    public static int getInt(Map<String, Object> json, String key) {
        if (!hasField(json, key)) {
            throw new ecxception("Missing required field: " + key);
        }
        Object value = json.get(key);
        if (!(value instanceof BigDecimal)) {
            throw new ecxception("Field must be a number: " + key);
        }
        return ((BigDecimal) value).intValue();
    }

    public static BigDecimal getDecimal(Map<String, Object> json, String key) {
        if (!hasField(json, key)) {
            throw new ecxception("Missing required field: " + key);
        }
        Object value = json.get(key);
        if (!(value instanceof BigDecimal)) {
            throw new ecxception("Field must be a number: " + key);
        }
        return (BigDecimal) value;
    }

    public static <T extends Enum<T>> T getEnum(Map<String, Object> json, String key, Class<T> enumClass) {
        String text = getString(json, key);
        try {
            return Enum.valueOf(enumClass, text);
        } catch (IllegalArgumentException e) {
            throw new ecxception("Invalid value for field: " + key);
        }
    }

    public static String escape(String value) {
        if (value == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char c : value.toCharArray()) {
            switch (c) {
                case '"': sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                case '\n': sb.append("\\n"); break;
                case '\t': sb.append("\\t"); break;
                case '\r': sb.append("\\r"); break;
                default: sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String toJson(Map<String, Object> fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            if (!first) {
                sb.append(",");
            }
            first = false;
            sb.append("\"").append(escape(entry.getKey())).append("\":");
            sb.append(writeValue(entry.getValue()));
        }
        sb.append("}");
        return sb.toString();
    }

    public static String toJsonArray(List<Map<String, Object>> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for (Map<String, Object> item : items) {
            if (!first) {
                sb.append(",");
            }
            first = false;
            sb.append(toJson(item));
        }
        sb.append("]");
        return sb.toString();
    }

    public static String errorToJson(String message) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("message", message);
        return toJson(fields);
    }

    @SuppressWarnings("unchecked")
    private static String writeValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "\"" + escape((String) value) + "\"";
        }
        if (value instanceof Number || value instanceof Boolean) {
            return value.toString();
        }
        if (value instanceof Enum) {
            return "\"" + escape(((Enum<?>) value).name()) + "\"";
        }
        if (value instanceof Map) {
            return toJson((Map<String, Object>) value);
        }
        return "\"" + escape(value.toString()) + "\"";
    }
}