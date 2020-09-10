package com.hero.heroa.dreport.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.Query;

import com.hero.heroa.dreport.dao.WorkcontentDao;
import com.hero.heroa.dreport.entity.WorkcontentEntity;
import com.hero.heroa.dreport.service.WorkcontentService;


@Service("workcontentService")
public class WorkcontentServiceImpl extends ServiceImpl<WorkcontentDao, WorkcontentEntity> implements WorkcontentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<WorkcontentEntity> page = this.page(
                new Query<WorkcontentEntity>().getPage(params),
                new QueryWrapper<WorkcontentEntity>().like(StringUtils.isNotBlank(key),"content_name", key)
        );

        return new PageUtils(page);
    }

}