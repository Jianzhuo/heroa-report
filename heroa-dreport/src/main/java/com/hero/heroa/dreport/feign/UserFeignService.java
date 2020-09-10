package com.hero.heroa.dreport.feign;

import com.hero.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("heroa-renren")
public interface UserFeignService {
    @GetMapping(value ="renren-fast/app/userInfo")
    public R info();
}
