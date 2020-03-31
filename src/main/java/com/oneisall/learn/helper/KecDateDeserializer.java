package com.oneisall.learn.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
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

public class KecDateDeserializer extends StdDeserializer<Date> {

  private Logger logger = LoggerFactory.getLogger(KecDateDeserializer.class);

  protected KecDateDeserializer() {
    super(Date.class);
  }

  @Override
  public Date deserialize(JsonParser jp, DeserializationContext context) throws IOException {
    Class<?> currentClazz = Optional.ofNullable(jp.getParsingContext().getCurrentValue()).map(Object::getClass).orElse(null);
    if (currentClazz == null) {
      logger.error("解析日期出错，currentClazz null");
      return null;
    }
    String currentName = jp.getCurrentName();
    Field field = CommonMatchUtil.findField(currentClazz, currentName);
    if (field == null) {
      logger.error("解析日期出错，field null");
      return null;
    }
    DateFormatter dateFormatter = field.getAnnotation(DateFormatter.class);
    if (dateFormatter == null) {
      logger.error("解析日期出错，dateFormatter null");
      return null;
    }
    String text = jp.readValueAs(String.class);
    String pattern = dateFormatter.pattern();
    FastDateFormat instance = FastDateFormat.getInstance(pattern);
    try {
      return instance.parse(text);
    } catch (Exception e0) {
      logger.error("解析日期出错，{} ,尝试使用原始解析日期方法", e0.getMessage(), e0);
      long timestamp;
      try {
        timestamp = Long.parseLong(text);
        return new Date(timestamp);
      } catch (Exception e1) {
        logger.error("解析日期出错最终出错了，{}", e0.getMessage(), e1);
        throw new IllegalArgumentException("解析日期" + text + "出错最终出错了！");
      }
    }
  }

  /**
   * 自定义枚举转map
   *
   * @author : oneisall
   * @version : v1 2018/7/15 15:15
   */
  public static class EnumSerializer extends JsonSerializer<Enum2Map> {


    @Override
    public void serialize(Enum2Map value, JsonGenerator generator, SerializerProvider serializers) {
      Optional<Enum2Map> optional = Optional.of(value);
      optional.ifPresent(enumCommon -> {
        try {
          generator.writeObject(enumCommon.toMap());
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }
}
