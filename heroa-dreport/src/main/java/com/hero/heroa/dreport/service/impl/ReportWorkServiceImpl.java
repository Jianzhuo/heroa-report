package com.hero.heroa.dreport.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.Query;

import com.hero.heroa.dreport.dao.ReportWorkDao;
import com.hero.heroa.dreport.entity.ReportWorkEntity;
import com.hero.heroa.dreport.service.ReportWorkService;


@Service("reportWorkService")
public class ReportWorkServiceImpl extends ServiceImpl<ReportWorkDao, ReportWorkEntity> implements ReportWorkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReportWorkEntity> page = this.page(
                new Query<ReportWorkEntity>().getPage(params),
                new QueryWrapper<ReportWorkEntity>()
        );

        return new PageUtils(page);
    }

}