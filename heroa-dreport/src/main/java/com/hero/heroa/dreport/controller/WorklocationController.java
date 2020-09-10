package com.hero.heroa.dreport.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hero.heroa.dreport.entity.WorklocationEntity;
import com.hero.heroa.dreport.service.WorklocationService;
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
@RequestMapping("dreport/worklocation")
public class WorklocationController {
    @Autowired
    private WorklocationService worklocationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("dreport:worklocation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = worklocationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{locationId}")
    //@RequiresPermissions("dreport:worklocation:info")
    public R info(@PathVariable("locationId") Long locationId){
		WorklocationEntity worklocation = worklocationService.getById(locationId);

        return R.ok().put("worklocation", worklocation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("dreport:worklocation:save")
    public R save(@RequestBody WorklocationEntity worklocation){
		worklocationService.save(worklocation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("dreport:worklocation:update")
    public R update(@RequestBody WorklocationEntity worklocation){
		worklocationService.updateById(worklocation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("dreport:worklocation:delete")
    public R delete(@RequestBody Long[] locationIds){
		worklocationService.removeByIds(Arrays.asList(locationIds));

        return R.ok();
    }

}
