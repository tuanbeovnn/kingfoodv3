package com.kingfood.backend.FormUtils;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class FormUtils {

    public static final int DEFAULT_PAGE = 1;
    public static final String PAGE_PARAMETER = "page";
    public static final int DEFAULT_SIZE = 10;
    public static final String SIZE_PARAMETER = "size";

    /**
     * toPageable
     *TER
     * @param model
     * @return
     * @throws IllegalAccessException
     */
    public static Pageable toPageable(Map<String , String> model) {
       int page = convertStringToInteger(model.get(PAGE_PARAMETER), DEFAULT_PAGE);
       int size = convertStringToInteger(model.get(SIZE_PARAMETER), DEFAULT_SIZE);
        return PageRequest.of(page - 1, size);
    }

    private static int convertStringToInteger(String input, int defaultValue) {
        return StringUtils.isEmpty(input) ? defaultValue : Integer.parseInt(input);
    }

    /**
     * toModel
     *
     * @param clazz
     * @param model
     * @param <T>
     * @return
     */
    public static <T> T toModel(Class<T> clazz, Map<String , String> model) {
        T object = null;
        try {
            object = clazz.newInstance();
            BeanUtils.populate(object, model);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.print(e.getMessage());
        }
        return object;
    }
}
