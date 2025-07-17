package com.gec.obwiki.util;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CopyUtil {
    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            return null;
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }

    public static <T> List<T> copyList(List source, Class<T> clazz) {
        List<T> target = null;
        if (source != null && !source.isEmpty()) {
            target = (List<T>) source.stream().map(obj -> copy(obj, clazz)).collect(Collectors.toList());
        }
        return target;
    }
} 