package com.hero.heroa.dreport.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hero.heroa.dreport.entity.ReportWorkEntity;
import com.hero.heroa.dreport.entity.WorkEntity;
import com.hero.heroa.dreport.entity.WorkUserEntity;
import com.hero.heroa.dreport.service.ReportWorkService;
import com.hero.heroa.dreport.service.WorkService;
import com.hero.heroa.dreport.service.WorkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hero.heroa.dreport.entity.ReportEntity;
import com.hero.heroa.dreport.service.ReportService;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.R;



/**
 * 日报表
 *
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-07-15 13:02:27
 */
@RestController
@RequestMapping("dreport/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private ReportWorkService reportWorkService;
    @Autowired
    private WorkService workService;
    @Autowired
    private WorkUserService workUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("dreport:report:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = reportService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{reportId}")
    //@RequiresPermissions("dreport:report:info")
    public R info(@PathVariable("reportId") Long reportId){
		ReportEntity report = reportService.getById(reportId);

        return R.ok().put("report", report);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("dreport:report:save")
    public R save(@RequestBody ReportEntity report){
		reportService.save(report);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("dreport:report:update")
    public R update(@RequestBody ReportEntity report){
		reportService.updateById(report);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("dreport:report:delete")
    public R delete(@RequestBody Long[] reportIds){
		reportService.removeByIds(Arrays.asList(reportIds));
        for (Long reportId:
             reportIds) {
            QueryWrapper<ReportWorkEntity> qw = new QueryWrapper<ReportWorkEntity>();
            qw.in("report_id",reportId);
            List<ReportWorkEntity> list = reportWorkService.getBaseMapper().selectList(qw);
            for (ReportWorkEntity rwe:
                    list) {
                reportWorkService.removeById(rwe.getId());
                QueryWrapper<WorkEntity> wqw = new QueryWrapper<WorkEntity>();
                wqw.in("work_id",rwe.getWorkId());
                List<WorkEntity> wlist = workService.getBaseMapper().selectList(wqw);
                for (WorkEntity we:
                        wlist) {
                    workService.removeById(we.getWorkId());
                    QueryWrapper<WorkUserEntity> wuqw = new QueryWrapper<WorkUserEntity>();
                    wuqw.in("work_id",we.getWorkId());
                    List<WorkUserEntity> wulist = workUserService.getBaseMapper().selectList(wuqw);
                    for (WorkUserEntity wue:
                            wulist) {
                        workUserService.removeById(wue.getId());
                    }

                }
            }
        }

        return R.ok();
    }

}
