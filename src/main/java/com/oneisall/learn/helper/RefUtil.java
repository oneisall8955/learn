package com.oneisall.learn.helper;

import com.oneisall.learn.java.common.Result;
import com.oneisall.learn.java.utils.CommonMatchUtil;
import com.oneisall.learn.java.utils.StringImproveUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * 反射 工具，一些扩展
 *
 * @author liam
 * @version V1.0
 * @date 2020/1/17
 **/
public class RefUtil {

    private static Logger logger = LoggerFactory.getLogger(RefUtil.class);
    /** 类的所有 public set 开头的方法 */
    private final static Map<Class<?>, Map<String, Method>> allSetMethodsCache = new ConcurrentHashMap<>();
    /** 类的某个成员变量对应的set方法 */
    private final static Map<Class<?>, Map<String, Result<Method>>> specialSetMethodCache = new ConcurrentHashMap<>();

    public static Method offerSetMethod(Class<?> clazz, String fieldName) {
        Map<String, Result<Method>> forThisClazzSetMethodCache = specialSetMethodCache.computeIfAbsent(clazz, key -> new ConcurrentHashMap<>());
        Result<Method> cacheResult = forThisClazzSetMethodCache.get(fieldName);
        if (cacheResult != null) {
            Method cacheData = cacheResult.getData();
            if (cacheData == null) {
                logger.warn("获取方法出错，原因：{}", cacheResult.getMsg());
            }
            return cacheData;
        }
        // method 为 null，证明是第一次在这个类中查找
        Map<String, Method> forThisClassSetMethodCache = allSetMethodsCache.computeIfAbsent(clazz, key -> {
            // 递归到父类Object，查找set开头，且参数只有一个的方法
            Class<?> tempClass = clazz;
            Map<String, Method> temMap = new HashMap<>();
            while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
                Method[] declaredMethods = tempClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    if (method.getName().startsWith("set") && method.getParameterTypes().length == 1) {
                        if (!temMap.containsKey(method.getName())) {
                            temMap.put(method.getName(), method);
                        }
                    }
                }
                tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
            }
            return temMap;
        });
        // 从类的Set方法缓存中获取这个field的方法
        String methodName = "set" + StringImproveUtil.firstWorldToUpperCase(fieldName);
        Method result = forThisClassSetMethodCache.get(methodName);
        if (result == null) {
            String msg = "类 " + clazz + " 找不到 " + methodName + " 方法";
            logger.warn("获取方法出错，原因：{}", msg);
            forThisClazzSetMethodCache.put(fieldName, Result.failed(msg));
        } else {
            forThisClazzSetMethodCache.put(fieldName, Result.success(result));
        }
        return result;
    }

    private static final Map<Class<?>, Map<String, Result<Field>>> fieldCache = new ConcurrentHashMap<>();

    public static Field offerField(Class<?> clazz, String fieldName) {
        Map<String, Result<Field>> forThisClassFiledCache = fieldCache.computeIfAbsent(clazz, key -> new ConcurrentHashMap<>());
        Result<Field> cacheResult = forThisClassFiledCache.get(fieldName);
        if (cacheResult != null) {
            Field cacheData = cacheResult.getData();
            if (cacheData == null) {
                logger.warn("获取属性出错，原因：{}", cacheResult.getMsg());
            }
            return cacheData;
        }
        Class<?> tempClass = clazz;
        Field result = null;
        while (tempClass != null) {
            //当父类为null的时候说明到达了最上层的父类(Object类).
            try {
                result = tempClass.getDeclaredField(fieldName);
                if (result != null) {
                    break;
                }
            } catch (NoSuchFieldException ignored) {
                logger.info("从{}类或父类{}获取不了{}属性", clazz, tempClass, fieldName);
            }
            //得到父类,然后赋给自己
            tempClass = tempClass.getSuperclass();
        }
        if (result == null) {
            String msg = "从" + clazz + "类及递归所有父类均获取不了" + fieldName + "属性";
            forThisClassFiledCache.put(fieldName, Result.failed(msg));
            logger.info(msg);
        } else {
            forThisClassFiledCache.put(fieldName, Result.success(result));
        }
        return result;
    }

    private static Pattern integerPattern = Pattern.compile("^[0-9]+$");

    public static <T> Result<T> parse4param(Map<String, String> requestMap, Class<T> clazz) {
        Set<Map.Entry<String, String>> params = requestMap.entrySet();
        try {
            Constructor<T> constructor = clazz.getConstructor();
            T instance = constructor.newInstance();
            for (Map.Entry<String, String> param : params) {
                String fieldName = param.getKey();
                String fieldValue = param.getValue().trim();
                if (StringUtils.isBlank(fieldValue)) {
                    continue;
                }
                Method method = RefUtil.offerSetMethod(clazz, fieldName);
                if (method == null) {
                    continue;
                }
                Class<?> parameterType = method.getParameterTypes()[0];
                // 获取了setXXX 方法及参数类型，现在需要做的是将字符串的值转换成 形如byte,short,int,long,float,double,char,boolean及
                // 包装类型，额外的常用的如，Date，BigDecimal，枚举，则额外做转换
                Field field = RefUtil.offerField(clazz, fieldName);
                if (!field.getType().equals(parameterType)) {
                    return Result.failed(method.getName() + "方法的参数" + parameterType + "与属性" + fieldName + "的类型" + field.getType() + "不相同");
                }
                // 暂时先实现 int,long,String,Date,BigDecimal，枚举的映射
                Object value = null;
                try {
                    // 使用策略模式
                    if (parameterType.equals(int.class) || parameterType.equals(Integer.class)) {
                        value = Integer.valueOf(fieldValue);
                    } else if (parameterType.equals(long.class) || parameterType.equals(Long.class)) {
                        value = Long.valueOf(fieldValue);
                    } else if (parameterType.equals(String.class)) {
                        value = fieldValue;
                    } else if (parameterType.equals(Date.class)) {
                        // 注解
                        DateFormatter dateFormatter = field.getAnnotation(DateFormatter.class);
                        if (dateFormatter != null) {
                            String pattern = dateFormatter.pattern();
                            FastDateFormat format = FastDateFormat.getInstance(pattern);
                            value = format.parse(fieldValue);
                        } else {
                            // 前端默认传递秒
                            long timestamp = Long.parseLong(fieldValue) * 1000L;
                            value = new Date(timestamp);
                        }
                    } else if (parameterType.equals(BigDecimal.class)) {
                        value = new BigDecimal(fieldValue);
                    } else if (parameterType.isEnum()) {
                        if (EnumCode.class.isAssignableFrom(parameterType) && integerPattern.matcher(fieldValue).matches()) {
                          // TODO 根据 EnumCode 的泛型获取！！！
                            @SuppressWarnings({"unchecked"})
                            Class<Enum<? extends EnumCode<?>>> enumClass = (Class<Enum<? extends EnumCode<?>>>) parameterType;
                            value = CommonMatchUtil.findByCode(enumClass, Integer.parseInt(fieldValue));
                        }
                        if (value == null) {
                            @SuppressWarnings("unchecked")
                            Class<Enum<?>> enumClass = (Class<Enum<?>>) parameterType;
                            value = RefUtil.offerEnum(enumClass, fieldValue);
                        }
                    } else if (parameterType.equals(Currency.class)) {
                        value = Currency.getInstance(fieldValue);
                    } else {
                        logger.info("未实现对{}类的映射构造，跳过", parameterType);
                    }
                    method.invoke(instance, value);
                } catch (Exception e) {
                    logger.error("参数" + fieldName + "的值" + fieldValue + "转换成类型" + parameterType + "失败,消息{}", e.getMessage(), e);
                    return Result.failed("参数" + fieldName + "的值" + fieldValue + "转换成类型" + parameterType + "失败");
                }
            }
            return Result.success(instance);
        } catch (Exception e) {
            logger.error("转换实体异常,{}", e.getMessage(), e);
            return Result.failed("转换实体异常");
        }
    }

    @SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
    private static final Map<Class<?>, Map<String, Enum<?>>> allEnumCache = new ConcurrentHashMap<>();
    @SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
    private static final Map<Class<?>, Map<String, Result<Enum<?>>>> specialEnumCache = new ConcurrentHashMap<>();

    @SuppressWarnings("AlibabaCollectionInitShouldAssignCapacity")
    public static Enum<?> offerEnum(Class<Enum<?>> enumClass, String name) {
        Map<String, Result<Enum<?>>> forThisEnumClassCache = specialEnumCache.computeIfAbsent(enumClass, key -> new ConcurrentHashMap<>());
        Result<Enum<?>> cacheResult = forThisEnumClassCache.get(name);
        if (cacheResult != null) {
            Enum<?> cacheData = cacheResult.getData();
            if (cacheData == null) {
                logger.warn("获取枚举出错，原因：{}", cacheResult.getMsg());
            }
            return cacheData;
        }
        // enumerate 为 null，证明是第一次在这个类中查找
        Map<String, Enum<?>> forThisEnumCache = allEnumCache.computeIfAbsent(enumClass, key -> {
            Enum<?>[] enumValues = enumClass.getEnumConstants();
            Map<String, Enum<?>> cache = new HashMap<>(enumValues.length);
            for (Enum<?> enumValue : enumValues) {
                cache.put(enumValue.name(), enumValue);
            }
            return cache;
        });
        Enum<?> result = forThisEnumCache.get(name);
        if (result == null) {
            String msg = "类 " + enumClass + " 找不到 " + name + " 枚举实例";
            logger.warn("获取枚举实例出错，原因：{}", msg);
            forThisEnumClassCache.put(name, Result.failed(msg));
        } else {
            forThisEnumClassCache.put(name, Result.success(result));
        }
        return result;
    }

    /**
     * 判断一个变量为基础数据类型， string 当作基础数据类型
     *
     * @param clazz 判断对象
     */
    public static boolean isBaseType(Class<?> clazz) {
        return isBaseType(clazz, true);
    }

    /**
     * 判断一个变量为基础数据类型， string可以特殊处理
     *
     * @param className 判断对象
     * @param incString 是否把 String 当基础数据类型判断
     */
    public static boolean isBaseType(Class<?> className, boolean incString) {
        if (incString && className.equals(String.class)) {
            return true;
        }
        return className.equals(Integer.class) || className.equals(int.class) ||
                className.equals(Byte.class) || className.equals(byte.class) ||
                className.equals(Long.class) || className.equals(long.class) ||
                className.equals(Double.class) || className.equals(double.class) ||
                className.equals(Float.class) || className.equals(float.class) ||
                className.equals(Character.class) || className.equals(char.class) ||
                className.equals(Short.class) || className.equals(short.class) ||
                className.equals(Boolean.class) || className.equals(boolean.class);
    }

}