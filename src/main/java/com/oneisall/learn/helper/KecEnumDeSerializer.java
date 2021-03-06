package com.oneisall.learn.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.oneisall.learn.java.utils.CommonMatchUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Optional;

/**
 * 定义枚举序列化Json规则
 *
 * @author : oneisall
 * @version : v1 2018/7/15 15:15
 */
public class KecEnumDeSerializer extends StdDeserializer<Enum<? extends EnumCode<?>>> {

  private Logger logger = LoggerFactory.getLogger(KecEnumDeSerializer.class);

  protected KecEnumDeSerializer() {
    super(Enum.class);
  }

  @SuppressWarnings("DuplicatedCode")
  @Override
  public Enum<? extends EnumCode<?>> deserialize(JsonParser jp, DeserializationContext context) throws IOException {
    Class<?> currentClass = Optional.ofNullable(jp.getParsingContext().getCurrentValue()).map(Object::getClass).orElse(null);
    if (currentClass == null) {
      logger.error("解析出错，currentClazz null");
      return null;
    }
    String currentName = jp.getCurrentName();
    Field field = CommonMatchUtil.findField(currentClass, currentName);
    if (field == null) {
      throw new IllegalArgumentException("can not find enum , this field " + currentName + " not found in the class " + currentClass + ")");
    }
    @SuppressWarnings("unchecked")
    Class<Enum<? extends EnumCode<?>>> declaringClass = (Class<Enum<? extends EnumCode<?>>>) field.getType();
    Integer code = jp.readValueAs(Integer.class);
    Enum<? extends EnumCode<?>> result = CommonMatchUtil.findByCode(declaringClass, code);
    if (result == null) {
      throw new IllegalArgumentException("can not find enum by this code :" + code);
    }
    return result;
  }
}
