package com.kingfood.backend.FormUtils;


import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class FormUtils {

    /**
     * toPageable
     *
     * @param model
     * @return
     * @throws IllegalAccessException
     */
    public static Pageable toPageable(Map<String , String> model) {
        int page = 1;
        int size = 10;

        if (model.get("page") != null && !model.get("page").equals("")) {
            page = Integer.parseInt(model.get("page"));
        }
        if (model.get("size") != null && !model.get("size").equals("")) {
            size = Integer.parseInt(model.get("size"));
        }
        return PageRequest.of(page - 1, size);
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
