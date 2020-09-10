package com.hero.heroa.dreport.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.Query;

import com.hero.heroa.dreport.dao.WorkDao;
import com.hero.heroa.dreport.entity.WorkEntity;
import com.hero.heroa.dreport.service.WorkService;


@Service("workService")
public class WorkServiceImpl extends ServiceImpl<WorkDao, WorkEntity> implements WorkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorkEntity> page = this.page(
                new Query<WorkEntity>().getPage(params),
                new QueryWrapper<WorkEntity>()
        );

        return new PageUtils(page);
    }

}