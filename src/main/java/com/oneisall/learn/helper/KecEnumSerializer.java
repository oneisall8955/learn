package com.oneisall.learn.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Optional;

/**
 * 定义枚举序列化Json规则
 *
 * @author : oneisall
 * @version : v1 2018/7/15 15:15
 */
public class KecEnumSerializer extends JsonSerializer<Enum2Map> {


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
