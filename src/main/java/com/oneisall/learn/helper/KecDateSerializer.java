package com.oneisall.learn.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.oneisall.learn.java.utils.CommonMatchUtil;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Optional;

/**
 * 序列化日期
 *
 * @author liam
 * @version V1.0
 * @date 2020/1/8
 **/
public class KecDateSerializer extends JsonSerializer<Date> {

  private Logger logger = LoggerFactory.getLogger(KecDateSerializer.class);

  @Override
  public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    Class<?> currentClazz = Optional.ofNullable(gen.getCurrentValue()).map(Object::getClass).orElse(null);
    if (currentClazz == null) {
      logger.error("格式化日期出错，currentClazz null");
      return;
    }
    JsonStreamContext outputContext = gen.getOutputContext();
    if (outputContext == null) {
      logger.error("格式化日期出错，outputContext null");
      return;
    }
    String currentName = outputContext.getCurrentName();
    Field field = CommonMatchUtil.findField(currentClazz, currentName);
    if (field == null) {
      logger.error("格式化日期出错，field null");
      return;
    }
    DateFormatter dateFormatter = field.getAnnotation(DateFormatter.class);
    if (dateFormatter == null) {
      logger.error("格式化日期出错，dateFormatter null");
      return;
    }
    String pattern = dateFormatter.pattern();

    Optional<Date> optional = Optional.of(value);
    optional.ifPresent(date -> {
      try {
        FastDateFormat dateFormat = FastDateFormat.getInstance(pattern);
        gen.writeObject(dateFormat.format(date));
      } catch (Exception e) {
        logger.error("格式化日期出错，发生异常", e);
      }
    });
  }

  /**
   * 根据code转枚举
   *
   * @author : oneisall
   * @version : v1 2018/7/15 15:15
   */
  public static class EnumDeSerializer extends StdDeserializer<Enum<? extends EnumCode<?>>> {

      private Logger logger = LoggerFactory.getLogger(EnumDeSerializer.class);

      protected EnumDeSerializer() {
          super(Enum.class);
      }

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
          Object code = jp.readValueAs(Object.class);
          Enum<? extends EnumCode<?>> result = CommonMatchUtil.findByCode(declaringClass, code);
          if (result == null) {
              throw new IllegalArgumentException("can not find enum by this code :" + code);
          }
          return result;
      }
  }
}