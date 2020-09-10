package com.hero.heroa.dreport.service.impl;

import com.hero.heroa.dreport.entity.ReportWorkEntity;
import com.hero.heroa.dreport.entity.WorkEntity;
import com.hero.heroa.dreport.entity.WorkUserEntity;
import com.hero.heroa.dreport.service.ReportWorkService;
import com.hero.heroa.dreport.service.WorkService;
import com.hero.heroa.dreport.service.WorkUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.Query;

import com.hero.heroa.dreport.dao.ReportDao;
import com.hero.heroa.dreport.entity.ReportEntity;
import com.hero.heroa.dreport.service.ReportService;


@Service("reportService")
public class ReportServiceImpl extends ServiceImpl<ReportDao, ReportEntity> implements ReportService {

    @Autowired
    private ReportWorkService reportWorkService;
    @Autowired
    private WorkService workService;
    @Autowired
    private WorkUserService workUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<ReportEntity> page = this.page(
                new Query<ReportEntity>().getPage(params),
                new QueryWrapper<ReportEntity>()
                        .orderByDesc("report_id")
                        .like(StringUtils.isNotBlank(key),"report_time", key).or()
                        .eq(StringUtils.isNotBlank(key),"report_user_name", key)

        );

        PageUtils pu = new PageUtils(page);
        List<ReportEntity> reports = (List<ReportEntity>) pu.getList();
        reports.stream().map((menu)->{
            List<WorkEntity> ws = new ArrayList<WorkEntity>();
            Map<String, Object> columnMap = new HashMap<String, Object>();
            columnMap.put("report_id", menu.getReportId());
            List<ReportWorkEntity> roleUsers = reportWorkService.getBaseMapper().selectByMap(columnMap);
            for (ReportWorkEntity rwe:
                    roleUsers) {
                WorkEntity work = workService.getById(rwe.getWorkId());

                columnMap = new HashMap<String, Object>();
                columnMap.put("work_id", work.getWorkId());
                List<WorkUserEntity> workmates = workUserService.getBaseMapper().selectByMap(columnMap);
                for (WorkUserEntity wue:
                     workmates) {
                    if(work.getWorkMate() == null){
                        work.setWorkMate(wue.getUserName());
                    }else {
                        work.setWorkMate(work.getWorkMate() + ", " + wue.getUserName());
                    }
                }
                ws.add(work);
            }
            menu.setWorks(ws);
            return menu;
        }).collect(Collectors.toList());
        pu.setList(reports);

        return pu;
    }

}