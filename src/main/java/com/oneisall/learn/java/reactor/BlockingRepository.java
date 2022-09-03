package com.oneisall.learn.java.reactor;

import java.util.List;

/**
 * @author liuzhicong
 **/
public interface BlockingRepository<T> {
    void save(T t);

    T findFirst();

    List<T> findAll();

    T findById(String id);
}
