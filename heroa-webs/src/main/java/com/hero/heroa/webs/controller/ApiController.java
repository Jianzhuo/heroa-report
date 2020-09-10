package com.hero.heroa.webs.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.R;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("webs/api")
public class ApiController {
    @RequestMapping("/check/{userName}")
    public R check(@PathVariable("userName") String userName){

        return R.ok().put("data", "aa");
    }
}

