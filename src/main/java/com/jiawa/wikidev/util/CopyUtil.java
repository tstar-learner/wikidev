package com.jiawa.wikidev.util;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CopyUtil {

    /**
     * 单体复制
     * @param source
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source,Class<T> clazz){
        if (source==null){
            return null;
        }
        T obj=null;
        try {
            obj=clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source,obj);
        return obj;
    }

    /*
    列表复制
     */
    public static <T> List<T> copyList(List source,Class<T> clazz){
        List<T> target=new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)){
            for (Object o :
                    source) {
                T copy = copy(o, clazz);
                target.add(copy);
            }
        }
        return target;
    }
}
