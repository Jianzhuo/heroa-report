package com.hero.heroa.dreport.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.Query;

import com.hero.heroa.dreport.dao.WorkUserDao;
import com.hero.heroa.dreport.entity.WorkUserEntity;
import com.hero.heroa.dreport.service.WorkUserService;


@Service("workUserService")
public class WorkUserServiceImpl extends ServiceImpl<WorkUserDao, WorkUserEntity> implements WorkUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WorkUserEntity> page = this.page(
                new Query<WorkUserEntity>().getPage(params),
                new QueryWrapper<WorkUserEntity>()
        );

        return new PageUtils(page);
    }

}