package com.limn.log.aspect.annotation;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Target(value = ElementType.METHOD)
public @interface LogAspect {
}
