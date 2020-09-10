package com.hero.heroa.dreport.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hero.heroa.dreport.entity.WorkcontentEntity;
import com.hero.heroa.dreport.service.WorkcontentService;
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
@RequestMapping("dreport/workcontent")
public class WorkcontentController {
    @Autowired
    private WorkcontentService workcontentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("dreport:workcontent:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = workcontentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{contentId}")
    //@RequiresPermissions("dreport:workcontent:info")
    public R info(@PathVariable("contentId") Long contentId){
		WorkcontentEntity workcontent = workcontentService.getById(contentId);

        return R.ok().put("workcontent", workcontent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("dreport:workcontent:save")
    public R save(@RequestBody WorkcontentEntity workcontent){
		workcontentService.save(workcontent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("dreport:workcontent:update")
    public R update(@RequestBody WorkcontentEntity workcontent){
		workcontentService.updateById(workcontent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("dreport:workcontent:delete")
    public R delete(@RequestBody Long[] contentIds){
		workcontentService.removeByIds(Arrays.asList(contentIds));

        return R.ok();
    }

}
