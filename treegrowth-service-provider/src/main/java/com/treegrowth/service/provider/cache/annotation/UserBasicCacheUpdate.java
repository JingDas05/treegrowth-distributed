package com.treegrowth.service.provider.cache.annotation;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Caching(put = @CachePut(key = UserBasicCache.KEY, value = UserBasicCache.VALUE))
public @interface UserBasicCacheUpdate {

}
