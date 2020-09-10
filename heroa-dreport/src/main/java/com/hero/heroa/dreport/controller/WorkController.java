package com.hero.heroa.dreport.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hero.heroa.dreport.entity.ReportEntity;
import com.hero.heroa.dreport.entity.ReportWorkEntity;
import com.hero.heroa.dreport.entity.WorkUserEntity;
import com.hero.heroa.dreport.service.ReportWorkService;
import com.hero.heroa.dreport.service.WorkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hero.heroa.dreport.entity.WorkEntity;
import com.hero.heroa.dreport.service.WorkService;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.R;



/**
 * 
 *
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-07-15 13:02:26
 */
@RestController
@RequestMapping("dreport/work")
public class WorkController {
    @Autowired
    private WorkService workService;
    @Autowired
    private ReportWorkService reportWorkService;
    @Autowired
    private WorkUserService workUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("dreport:work:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = workService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{workId}")
    //@RequiresPermissions("dreport:work:info")
    public R info(@PathVariable("workId") Long workId){
		WorkEntity work = workService.getById(workId);
        QueryWrapper<WorkUserEntity> queryWrapper = new QueryWrapper<WorkUserEntity>();
        queryWrapper.in("work_id",workId);
		List<WorkUserEntity> list = workUserService.getBaseMapper().selectList(queryWrapper);

        List<String> names = new ArrayList<>();
		for (WorkUserEntity wue:
             list) {
            names.add(wue.getUserName());
        }
		work.setWorkMates(names);

        return R.ok().put("work", work);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("dreport:work:save")
    public R save(@RequestBody WorkEntity work){
		workService.save(work);
        ReportWorkEntity reportWorkEntity = new ReportWorkEntity();
        reportWorkEntity.setWorkId(work.getWorkId());
        reportWorkEntity.setReportId(work.getReportId());
        reportWorkService.save(reportWorkEntity);
        for (String name:
             work.getWorkMates()) {
            WorkUserEntity workUserEntity = new WorkUserEntity();
            workUserEntity.setWorkId(work.getWorkId());
            workUserEntity.setUserName(name);
            workUserService.save(workUserEntity);
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("dreport:work:update")
    public R update(@RequestBody WorkEntity work){
		workService.updateById(work);

        QueryWrapper<WorkUserEntity> queryWrapper = new QueryWrapper<WorkUserEntity>();
        queryWrapper.in("work_id",work.getWorkId());
        List<WorkUserEntity> list = workUserService.getBaseMapper().selectList(queryWrapper);
        for (WorkUserEntity wue:
                list) {
            workUserService.removeById(wue.getId());
        }

        for (String name:
                work.getWorkMates()) {
            WorkUserEntity workUserEntity = new WorkUserEntity();
            workUserEntity.setWorkId(work.getWorkId());
            workUserEntity.setUserName(name);
            workUserService.save(workUserEntity);
        }

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("dreport:work:delete")
    public R delete(@RequestBody Long[] workIds){
		workService.removeByIds(Arrays.asList(workIds));
		QueryWrapper<ReportWorkEntity> queryWrapper = new QueryWrapper<ReportWorkEntity>();
		queryWrapper.in("work_id",workIds[0]);
		ReportWorkEntity reportWorkEntity = reportWorkService.getBaseMapper().selectOne(queryWrapper);
		reportWorkService.removeById(reportWorkEntity.getId());

        QueryWrapper<WorkUserEntity> qw = new QueryWrapper<WorkUserEntity>();
        qw.in("work_id",workIds[0]);
        List<WorkUserEntity> list = workUserService.getBaseMapper().selectList(qw);
        for (WorkUserEntity wue:
                list) {
            workUserService.removeById(wue.getId());
        }


        return R.ok();
    }

}
