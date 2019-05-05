package com.oneisall.learn.java.basic.io;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        //String applicationPath = "D:\\Workspace\\learn\\src\\main\\resources\\application.properties";
        /*String applicationPath = "classpath:application.properties";
        Properties applicationProps = new OrderedProperties();
        FileInputStream propsFileIn = new FileInputStream(applicationPath);
        applicationProps.load(new InputStreamReader(propsFileIn, StandardCharsets.UTF_8));
        String env = args != null && args.length > 0 ? args[0] : null;
        if (env != null) {
            System.out.println("检测外部传递了active:" + env);
            System.out.println("正修改active参数...");
            applicationProps.setProperty("spring.profiles.active", env);
            BufferedWriter propsFileOut = new BufferedWriter(new FileWriter(applicationPath));
            applicationProps.store(propsFileOut, null);
            System.out.println("修改active参数完成...");
        } else {
            System.out.println("使用默认active参数...");
        }
        env = applicationProps.getProperty("spring.profiles.active");
        System.out.println("请检测:spring.profiles.active=" + env);
        System.out.println("请检测:spring.cache.ehcache.config=" + applicationProps.getProperty("spring.cache.ehcache.config"));*/
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        assert url != null;
        String path = url.getFile();
        System.out.println(path);
        String applicationPath = "/application.properties";
        String configPath = "config.properties";
        OrderedProperties applicationProps = new OrderedProperties();
        OrderedProperties configProps = new OrderedProperties();
//        FileInputStream propsFileIn = new FileInputStream(applicationPath);
//        applicationProps.load(new InputStreamReader(propsFileIn, StandardCharsets.UTF_8));

        InputStream  applicationIn = Class.forName(Main.class.getName()).getResourceAsStream(applicationPath);
        applicationProps.load(new InputStreamReader(applicationIn, StandardCharsets.UTF_8));

        InputStream  configIn = Class.forName(Main.class.getName()).getResourceAsStream(configPath);
        configProps.load(new InputStreamReader(configIn, StandardCharsets.UTF_8));

        String env = args != null && args.length > 0 ? args[0] : null;
        if (env != null) {
            System.out.println("检测外部传递了active:" + env);
            System.out.println("正修改active参数...");
            applicationProps.setProperty("spring.profiles.active", env);
            BufferedWriter propsFileOut = new BufferedWriter(new FileWriter("D:\\Workspace\\learn\\src\\main\\resources\\application.properties"));
            applicationProps.store(propsFileOut, null);

            configProps.setProperty("spring.profiles.active", env);
            BufferedWriter configFileOut = new BufferedWriter(new FileWriter("D:\\Workspace\\learn\\src\\main\\java\\com\\oneisall\\config.properties"));
            configProps.store(configFileOut, null);
            System.out.println("修改active参数完成...");
        } else {
            System.out.println("使用默认active参数...");
        }
        env = applicationProps.getProperty("spring.profiles.active");
        System.out.println("请检测:spring.profiles.active=" + env);
        System.out.println("请检测:spring.cache.ehcache.config=" + applicationProps.getProperty("spring.cache.ehcache.config"));

        env = configProps.getProperty("spring.profiles.active");
        System.out.println("请检测:spring.profiles.active=" + env);
        System.out.println("请检测:spring.cache.ehcache.config=" + configProps.getProperty("spring.cache.ehcache.config"));

    }
}

class OrderedProperties extends Properties {
    private final LinkedHashSet<Object> keys = new LinkedHashSet<>();

    @Override
    public synchronized Enumeration<Object> keys() {
        return Collections.enumeration(keys);
    }

    @Override
    public synchronized Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }

    @Override
    public Set<Object> keySet() {
        return keys;
    }

    @Override
    public Set<String> stringPropertyNames() {
        Set<String> set = new LinkedHashSet<>();
        for (Object key : this.keys) {
            set.add((String) key);
        }
        return set;
    }

    @Override
    public void store(Writer writer, String comments) throws IOException {
        customStore0((writer instanceof BufferedWriter)?(BufferedWriter)writer
                        : new BufferedWriter(writer));
    }

    private void customStore0(BufferedWriter bw)
            throws IOException {
        synchronized (this) {
            for (Enumeration e = keys(); e.hasMoreElements();) {
                String key = (String) e.nextElement();
                String val = (String) get(key);
                bw.write(key + "=" + val);
                bw.newLine();
            }
        }
        bw.flush();
    }
}