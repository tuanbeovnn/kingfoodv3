package com.kingfood.backend.build;

import java.util.Map;

/**
 * BuildQueryUtils
 *
 */
public class BuildQueryUtils {

    public static String formatLikeStringSql(String value){
        String valueLike = escapeSQL(value);
        return "%"+valueLike+"%";
    }

    public static String escapeSQL(String value){
        String result = value.trim().replace("/", "\\/").replace("_", "\\_").replace("%", "\\%");
        return result;
    }

    public static StringBuilder createSqlString(Map<String, Object> properties) {
        StringBuilder result = new StringBuilder();
        properties.forEach((key , value) -> {
            if (value != null){
                if (value instanceof String) {
                    result.append(" AND LOWER("+key+") LIKE :"+key+" ");
                } else if (value instanceof Integer || value instanceof Long) {
                    result.append(" AND "+key+" = :"+key+"");
                }
            }
        });
        return result;
    }
}