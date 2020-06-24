package org.simpleframework.util;

import java.util.Collection;
import java.util.Map;

public class ValidationUtil {

    /**
     * Collection 是否为Null 或者 size == 0
     *
     * @param obj Collection
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> obj){
        return obj == null || obj.isEmpty();
    }

    /**
     * String 是否为Null 或者 ""
     *
     * @param obj String
     * @return 是否为空
     */
    public static  boolean isEmpty(String obj){
        return (obj == null || "".equals(obj));
    }

    /**
     * Array 是否为Null 或者 size == 0
     *
     * @param obj Array
     * @return 是否为空
     */
    public static  boolean isEmpty(Object[] obj){
        return (obj == null || obj.length == 0);
    }

    /**
     * Array 是否为Null 或者 size == 0
     *
     * @param obj Array
     * @return 是否为空
     */
    public static  boolean isEmpty(Map<?, ?> obj){
        return (obj == null || obj.isEmpty());
    }
}
