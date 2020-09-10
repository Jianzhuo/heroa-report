package com.hero.heroa.dreport.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.Query;

import com.hero.heroa.dreport.dao.WorklocationDao;
import com.hero.heroa.dreport.entity.WorklocationEntity;
import com.hero.heroa.dreport.service.WorklocationService;


@Service("worklocationService")
public class WorklocationServiceImpl extends ServiceImpl<WorklocationDao, WorklocationEntity> implements WorklocationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<WorklocationEntity> page = this.page(
                new Query<WorklocationEntity>().getPage(params),
                new QueryWrapper<WorklocationEntity>().like(StringUtils.isNotBlank(key),"location_name", key)
        );

        return new PageUtils(page);
    }

}