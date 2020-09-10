package com.hero.heroa.dreport.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hero.common.utils.PageUtils;
import com.hero.common.utils.R;
import com.hero.heroa.dreport.entity.ReportEntity;
import com.hero.heroa.dreport.entity.ReportWorkEntity;
import com.hero.heroa.dreport.entity.WorkEntity;
import com.hero.heroa.dreport.entity.WorkUserEntity;
import com.hero.heroa.dreport.service.ReportService;
import com.hero.heroa.dreport.service.ReportWorkService;
import com.hero.heroa.dreport.service.WorkService;
import com.hero.heroa.dreport.service.WorkUserService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("dreport/api")
public class ApiController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private WorkService workService;
    @Autowired
    private ReportWorkService reportWorkService;
    @Autowired
    private WorkUserService workUserService;

    //响应前端发来日报储存请求，获取用户填写的日报数据
    @RequestMapping("/save")
    //@RequiresPermissions("dreport:report:save")
    public R save(@RequestBody Map<String, Object> inputData) throws ParseException {
        //拿到前端传过来的JSON数据
        JSONObject jsonObj = new JSONObject(inputData);

        //构建ReportEntity 并插入数据库
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setReportTime(new Date());
        reportEntity.setReportUserName(jsonObj.getString("reporter"));
        reportEntity.setReportNote(jsonObj.getString("otherNote"));
        reportEntity.setReportRate(jsonObj.getInteger("rate"));
        reportService.save(reportEntity);
        //拿到插入的report_id
        Long report_id = reportEntity.getReportId();

        //构建WorkEntity 并插入数据库
        JSONArray works = jsonObj.getJSONArray("inputData");
        for (int i = 0; i < works.size(); i++) {
            JSONObject object = new JSONObject((Map<String, Object>) works.get(i));
            WorkEntity workEntity = new WorkEntity();
            workEntity.setDurationTime(object.getString("workDurcation"));
            workEntity.setWorkContent(object.getString("workContent"));
            workEntity.setWorkDetail(object.getString("workDetail"));

            SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format

            workEntity.setWorkStart(new java.sql.Time(format.parse(object.getString("startTime")).getTime()));
            workEntity.setWorkEnd(new java.sql.Time(format.parse(object.getString("endTime")).getTime()));
            workEntity.setWorkLocation(object.getString("workLocation"));
            workEntity.setWorkProgress(object.getInteger("workProgress"));
            workEntity.setWorkNote(object.getString("workNote"));
            workService.save(workEntity);
            //拿到插入的work_id
            Long work_id = workEntity.getWorkId();

            //构建ReportWorkEntity 并插入数据库
            ReportWorkEntity reportWorkEntity = new ReportWorkEntity();
            reportWorkEntity.setReportId(report_id);
            reportWorkEntity.setWorkId(work_id);
            reportWorkService.save(reportWorkEntity);

            JSONArray users = object.getJSONArray("otherWorker");
            for (int a = 0; a < users.size(); a++) {
                WorkUserEntity workUserEntity = new WorkUserEntity();
                workUserEntity.setUserName(users.get(a).toString());
                workUserEntity.setWorkId(work_id);
                workUserService.save(workUserEntity);
            }
        }
        return R.ok();
    }

    //响应前端发来的检查请求，检查当前用户当天是否提交过日报
    @RequestMapping("/check/{userName}")
    public R check(@PathVariable("userName") String userName){
        QueryWrapper<ReportEntity> queryWrapper = new QueryWrapper<ReportEntity>();
        queryWrapper.in("report_user_name",userName);
        queryWrapper.like("report_time",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        List<ReportEntity> reports =  reportService.getBaseMapper().selectList(queryWrapper);

        return R.ok().put("data", reports.size());
    }

    @RequestMapping("/list")
    //@RequiresPermissions("dreport:report:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = reportService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/doExport")
    public void doExport(@RequestBody Long[] reportIds, HttpServletResponse response){
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("日报表");

            // 设置要导出的文件的名字
            String fileName = "日报导出"  + new Date() + ".xls";

            // headers表示excel表中第一行的表头 在excel表中添加表头
            String[] headers = { "日报提交时间","日报提交者","日报备注","自我评价","工作开始","工作结束","工作时间","同组人员","工作地点","工作内容","工作细节","工作进度","工作备注"};
            HSSFRow row = sheet.createRow(0);
            for(int i=0;i<headers.length;i++){
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }

            // 新增数据行，并且设置单元格数据
            int rowNum = 1;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            //操作数据库查询数据
            for (Long reportId:
                 reportIds) {
                ReportEntity re = reportService.getBaseMapper().selectById(reportId);
                //在表中存放查询到的数据放入对应的列
                HSSFRow row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(dateFormat.format(re.getReportTime()));
                row1.createCell(1).setCellValue(re.getReportUserName());
                row1.createCell(2).setCellValue(re.getReportNote());
                row1.createCell(3).setCellValue(re.getReportRate());
                QueryWrapper<ReportWorkEntity> rwqw = new QueryWrapper<ReportWorkEntity>();
                rwqw.in("report_id", reportId);
                List<ReportWorkEntity> rwList = reportWorkService.getBaseMapper().selectList(rwqw);
                for (ReportWorkEntity rwe:
                        rwList) {
                    WorkEntity we = workService.getBaseMapper().selectById(rwe.getWorkId());
                    QueryWrapper<WorkUserEntity> queryWrapper = new QueryWrapper<WorkUserEntity>();
                    queryWrapper.in("work_id",rwe.getWorkId());
                    List<WorkUserEntity> list = workUserService.getBaseMapper().selectList(queryWrapper);

                    for (WorkUserEntity wue:
                            list) {
                        if(we.getWorkMate()==null){
                            we.setWorkMate(wue.getUserName());
                        }else{
                            we.setWorkMate(we.getWorkMate()+", "+wue.getUserName());
                        }
                    }
                    row1.createCell(4).setCellValue(we.getWorkStart().toString().substring(0, 5));
                    row1.createCell(5).setCellValue(we.getWorkEnd().toString().substring(0, 5));
                    row1.createCell(6).setCellValue(we.getDurationTime());
                    row1.createCell(7).setCellValue(we.getWorkMate());
                    row1.createCell(8).setCellValue(we.getWorkLocation());
                    row1.createCell(9).setCellValue(we.getWorkContent());
                    row1.createCell(10).setCellValue(we.getWorkDetail());
                    row1.createCell(11).setCellValue(we.getWorkProgress());
                    row1.createCell(12).setCellValue(we.getWorkNote());
                    rowNum++;
                    row1 = sheet.createRow(rowNum);
                }
            }

            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
