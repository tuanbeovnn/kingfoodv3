package com.kingfood.backend.exceptions;

import com.kingfood.backend.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class contain logic common.
 *
 * @author DatDV
 */
public class CommonUtils {

    private CommonUtils() {
    }
    /**
     * Create instance
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> T createInstance(Class<T> clazz) throws Exception {
        Constructor<T> ctor = clazz.getConstructor();
        T object = ctor.newInstance(new Object[] { });
        return object;
    }

    /**
     * Copy Properties
     * @param sourceList
     * @param clazz
     * @param <T>
     * @param <S>
     * @return
     * @throws Exception
     */
    public static <T,S> List<T> copyProperties(List<S> sourceList, Class<T> clazz) throws Exception {
        List<T> targetList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sourceList)) {
            for (S source: sourceList) {
                T target = copyProperties(source, clazz);
                targetList.add(target);
            }
        }
        return targetList;
    }

    /**
     * Copy Properties
     * @param sourceList
     * @param clazz
     * @param ignoreProperties
     * @param <T>
     * @param <S>
     * @return
     * @throws Exception
     */
    public static <T,S> List<T> copyProperties(List<S> sourceList, Class<T> clazz, String... ignoreProperties) throws Exception {
        List<T> targetList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sourceList)) {
            for (S source: sourceList) {
                T target = copyProperties(source, clazz, ignoreProperties);
                targetList.add(target);
            }
        }
        return targetList;
    }

    /**
     * Copy Properties
     * @param source
     * @param clazz
     * @param <T>
     * @param <S>
     * @return
     * @throws Exception
     */
    public static <T,S> T copyProperties(S source, Class<T> clazz) throws Exception {
        if (source != null) {
            T target = createInstance(clazz);
            BeanUtils.copyProperties(source, target);
            return target;
        }
        return null;
    }

    /**
     * Copy Properties
     * @param source
     * @param clazz
     * @param ignoreProperties
     * @param <T>
     * @param <S>
     * @return
     * @throws Exception
     */
    public static <T,S> T copyProperties(S source, Class<T> clazz, String... ignoreProperties) throws Exception {
        if (source != null) {
            T target = createInstance(clazz);
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        }
        return null;
    }
    /**
     * Create error code.
     *
     * @param item - item error.
     * @param errorCode - error code.
     * @return error map.
     */
    public static Map<String, Object> putError(String item, String errorCode) {
        return putError(item, errorCode, null);
    }

    /**
     * Create error code.
     *
     * @param item - item error.
     * @param errorCode - error code.
     * @param rowId - row error.
     * @return error map.
     */
    public static Map<String, Object> putError(String item, String errorCode, Integer rowId) {
        Map<String, Object> error = new HashMap<>();
        error.put(Constants.ERROR_ITEM, item);
        error.put(Constants.ERROR_CODE, errorCode);
        error.put(Constants.ROW_ID, rowId != null ? rowId : 0);
        return error;
    }

    /**
     * convert string to list long
     *
     * @param inpuString
     * @param separator
     * @return
     */
    public static List<Long> string2ListLong(String inpuString, String separator) {
        List<Long> outPutList = new ArrayList<>();

        if (inpuString != null && !"".equals(inpuString.trim()) && separator != null && !"".equals(separator.trim())) {
            String[] idArr = inpuString.split(separator);
            for (String idArr1 : idArr) {
                if (idArr1 != null && !"".equals(idArr1.trim())) {
                    outPutList.add(Long.parseLong(idArr1.trim()));
                }
            }
        }

        return outPutList;
    }
    /**
     * convert string to list long
     *
     * @param inpuString
     * @param separator
     * @return
     */
    public static List<Integer> string2ListInteger(String inpuString, String separator) {
        List<Integer> outPutList = new ArrayList<>();

        if (inpuString != null && !"".equals(inpuString.trim()) && separator != null && !"".equals(separator.trim())) {
            String[] idArr = inpuString.split(separator);
            for (String idArr1 : idArr) {
                if (idArr1 != null && !"".equals(idArr1.trim())) {
                    outPutList.add(Integer.parseInt(idArr1.trim()));
                }
            }
        }

        return outPutList;
    }

    /**
     * check field type is text type for field default
     *
     * @param fieldName
     * @return
     */
    public static boolean isDefaultTextType(String fieldName) {
        Map<String, String> columnTextTypeMap = new HashMap<>();

        // employee
        columnTextTypeMap.put("employee_full_name", "");
        columnTextTypeMap.put("employee_surname", "");
        columnTextTypeMap.put("employee_name", "");
        columnTextTypeMap.put("employee_surname_kana", "");
        columnTextTypeMap.put("employee_name_kana", "");
        columnTextTypeMap.put("email", "");
        columnTextTypeMap.put("telephone_number", "");
        columnTextTypeMap.put("cellphone_number", "");
        columnTextTypeMap.put("employee_managers", "");
        columnTextTypeMap.put("employee_subordinates", "");
        columnTextTypeMap.put("employee_departments", "");
        columnTextTypeMap.put("employee_positions", "");
        columnTextTypeMap.put("employee_groups", "");

        // product
        columnTextTypeMap.put("product_name", "");
        columnTextTypeMap.put("memo", "");
        columnTextTypeMap.put("product_relations", "");

        // customer
        columnTextTypeMap.put("customer_name", "");
        columnTextTypeMap.put("customer_alias_name", "");
        columnTextTypeMap.put("customer_parent", "");
        columnTextTypeMap.put("phone_number", "");

        // product trading
        columnTextTypeMap.put("progress_name", "");

        return columnTextTypeMap.get(fieldName) != null;
    }

    public static boolean isDefaultNumberType(String fieldName) {
        Map<String, String> columnNumberTypeMap = new HashMap<>();

        // employee
        columnNumberTypeMap.put("language_id", "");
        columnNumberTypeMap.put("timezone_id", "");
        columnNumberTypeMap.put("employee_id", "");
        columnNumberTypeMap.put("created_user", "");
        columnNumberTypeMap.put("updated_user", "");
        columnNumberTypeMap.put("employee_status", "");
        columnNumberTypeMap.put("is_admin", "");
        columnNumberTypeMap.put("position_order", "");


        // product
        columnNumberTypeMap.put("product_id", "");
        columnNumberTypeMap.put("is_display", "");
        columnNumberTypeMap.put("is_set", "");
        columnNumberTypeMap.put("product_category_id", "");
        columnNumberTypeMap.put("product_type_id", "");
        columnNumberTypeMap.put("unit_price", "");

        // schedule
        columnNumberTypeMap.put("calendar_id", "");
        columnNumberTypeMap.put("task_id", "");
        columnNumberTypeMap.put("customer_id", "");
        columnNumberTypeMap.put("milestone_id", "");
        columnNumberTypeMap.put("parent_id", "");
        columnNumberTypeMap.put("status", "");
        columnNumberTypeMap.put("is_done", "");
        columnNumberTypeMap.put("item_division", "");

        // task
        columnNumberTypeMap.put("tasks.parent_id", "");
        columnNumberTypeMap.put("tasks.task_id", "");
        columnNumberTypeMap.put("tasks.status", "");
        columnNumberTypeMap.put("tasks.customer_id", "");
        columnNumberTypeMap.put("tasks.milestone_id", "");
        columnNumberTypeMap.put("prd_tra.products_tradings_id", "");

        // product trading
        columnNumberTypeMap.put("product_trading_id", "");
        columnNumberTypeMap.put("quantity", "");
        columnNumberTypeMap.put("price", "");
        columnNumberTypeMap.put("amount", "");

        // activity
        columnNumberTypeMap.put("activity_duration", "");
        columnNumberTypeMap.put("activity_id", "");
        columnNumberTypeMap.put("activity_format_id", "");
        columnNumberTypeMap.put("next_schedule_id", "");
        columnNumberTypeMap.put("scenario_id", "");

        return columnNumberTypeMap.get(fieldName) != null;
    }
    /**
     * Convert String to Long value
     * @param value
     * @return
     */
    public static Long longValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return Long.valueOf(value);
    }

    /**
     * Add unique list
     * @param <T>
     * @param item
     * @return
     */
    public static <T> List<T> addUniqueList(List<T> list, T item) {
        if (item == null) {
            return list;
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        if (!list.contains(item)) {
            list.add(item);
        }
        return list;
    }
    /**
     * Add unique list
     * @param list
     * @param items
     * @return
     */
    public static <T> List<T> addUniqueList(List<T> list, List<T> items) {
        if (items == null) {
            return list;
        }
        for (T item: items) {
            list = addUniqueList(list, item);
        }
        return list;
    }
    /**
     * Null Value
     * @param str
     * @return
     */
    public static String NVL(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }

    /**
     * Null Value
     * @param value
     * @param defaultValue
     * @return
     */
    public static String NVL(String value, String defaultValue) {
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }

    /**
     * Convert list long to string
     *
     * @param listIds
     * @return
     */
    public static String listLongToString(List<Long> listIds) {
        StringBuilder fieldValueBuilder = new StringBuilder();
        fieldValueBuilder.append("[");
        for (int i = 0; i < listIds.size(); i++) {
            if (i > 0) {
                fieldValueBuilder.append(Constants.COMMA);
            }
            fieldValueBuilder.append("\"").append(listIds.get(i)).append("\"");
        }
        fieldValueBuilder.append("]");
        return fieldValueBuilder.toString();
    }

    /**
     * Long value null
     *
     * @param value
     * @return
     */
    public static Long NVL(Long value) {
        if (value == null) {
            return 0L;
        }
        return value;
    }
}
