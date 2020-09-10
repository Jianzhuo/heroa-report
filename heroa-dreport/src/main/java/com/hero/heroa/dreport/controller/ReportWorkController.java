package com.hero.heroa.dreport.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hero.heroa.dreport.entity.ReportWorkEntity;
import com.hero.heroa.dreport.service.ReportWorkService;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.R;



/**
 * 
 *
 * @author Jianzhuo Shen
 * @email shenjianzhuo@gmail.com
 * @date 2020-07-15 10:30:42
 */
@RestController
@RequestMapping("dreport/reportwork")
public class ReportWorkController {
    @Autowired
    private ReportWorkService reportWorkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("dreport:reportwork:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = reportWorkService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("dreport:reportwork:info")
    public R info(@PathVariable("id") Long id){
		ReportWorkEntity reportWork = reportWorkService.getById(id);

        return R.ok().put("reportWork", reportWork);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("dreport:reportwork:save")
    public R save(@RequestBody ReportWorkEntity reportWork){
		reportWorkService.save(reportWork);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("dreport:reportwork:update")
    public R update(@RequestBody ReportWorkEntity reportWork){
		reportWorkService.updateById(reportWork);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("dreport:reportwork:delete")
    public R delete(@RequestBody Long[] ids){
		reportWorkService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
