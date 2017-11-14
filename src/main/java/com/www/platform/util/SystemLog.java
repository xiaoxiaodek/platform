package com.www.platform.util;

import java.lang.annotation.*;

/**
 * Created by upsmart on 17-11-14.
 * @desc 定义一个注解类用于注释每一个controller
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    String module()  default "";
    String methods()  default "";
}
