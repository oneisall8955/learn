package com.oneisall.learn.helper;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 序列化时候，枚举转MAP
 *
 * @author liam
 * @version V1.0
 * @date 2019/12/30
 **/
public interface Enum2Map {
  Map<String, Object> toMap();

  default Map<String, Object> toMap(int code, String text) {
    return ImmutableMap.<String, Object>builder()
            .put("code", code)
            .put("text", text)
            .build();
  }
}
