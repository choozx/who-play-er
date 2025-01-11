package com.yjh.whoplayer.support;

/**
 * Created by Will on 2022-05-03.
 */
public interface CodeEnum<T>{
    T getCode();


    String getKey();
    default String getComment() {
        return getKey();
    }
}
