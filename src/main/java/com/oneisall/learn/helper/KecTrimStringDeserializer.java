package com.oneisall.learn.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

import java.io.IOException;
import java.util.Optional;


/**
 * 反序列化时候，去掉前后空格
 *
 * @author liam
 * @version V1.0
 * @date 2020/1/13
 **/
public class KecTrimStringDeserializer extends StringDeserializer {

  @Override
  public String deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
    String text = super.deserialize(parser, ctxt);
    return Optional.ofNullable(text).map(String::trim).orElse(null);
  }
}