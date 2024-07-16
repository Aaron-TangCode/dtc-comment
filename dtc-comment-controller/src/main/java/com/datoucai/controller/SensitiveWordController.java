package com.datoucai.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.datoucai.dto.CommentInfoDto;
import com.datoucai.entity.TbSensitiveWordsExample;
import com.datoucai.param.AddCommentRequestParam;
import com.datoucai.param.AddSensitiveWordParam;
import com.datoucai.param.BaseResult;
import com.datoucai.service.ISensitiveService;
import com.datoucai.service.dto.SensitiveWordDto;
import com.datoucai.utils.BaseResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 敏感词controller
 */
@RestController
@RequestMapping("/sensitivewords")
@Slf4j
public class SensitiveWordController {

    @Autowired
    private ISensitiveService sensitiveService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResult<Boolean> addWord(@RequestBody AddSensitiveWordParam param){
        try {
            log.info("增加敏感词-controller层-addWord-入参:{}", JSON.toJSONString(param));
            SensitiveWordDto sensitiveWordDto = new SensitiveWordDto();
            sensitiveWordDto.setWord(param.getWord());
            sensitiveWordDto.setCategory(param.getCategory());
            int insert = sensitiveService.insert(sensitiveWordDto);
            log.info("增加敏感词-controller层-addWord-出参:{}", insert);
            return BaseResultUtils.generateSuccess(insert>0);
        }catch (Exception e){
            log.error("增加敏感词-controller层-addWord-异常:", e);
            return BaseResultUtils.generateFail("增加敏感词异常:"+e.getMessage());
        }
    }

    /**
     * 批量导入
     * @param file
     * @return
     */
    @RequestMapping(value = "/import",method = RequestMethod.POST)
    public BaseResult<Boolean> importWords(MultipartFile file){
        try {

            InputStream inputStream = file.getInputStream();
            ExcelReader reader = ExcelUtil.getReader(inputStream);

            List<List<Object>> list = reader.read(1);
            int total = 0;
            for (int i = 0; i < list.size(); i++) {
                List<Object> objects = list.get(i);

                SensitiveWordDto sensitiveWordDto = new SensitiveWordDto();
                sensitiveWordDto.setWord(objects.get(0).toString());
                sensitiveWordDto.setCategory(objects.get(1).toString());

                int insert = sensitiveService.insert(sensitiveWordDto);
                total += insert;
            }
            log.info("批量导入敏感词-controller层-importWords-插入敏感词数量:{}", total);

            return BaseResultUtils.generateSuccess(total>0);
        }catch (Exception e){
            log.error("批量导入敏感词-controller层-importWords-异常:", e);
            return BaseResultUtils.generateFail("importWords-异常:"+e.getMessage());
        }
    }
    /**
     * 导出敏感词
     * @param response
     * @return
     */
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public void exportWords(HttpServletResponse response){
        try {


            TbSensitiveWordsExample tbSensitiveWordsExample = new TbSensitiveWordsExample();
            tbSensitiveWordsExample.setLimit(1000);
            List<SensitiveWordDto> dataList = sensitiveService.queryByParam(tbSensitiveWordsExample);

            // 通过工具类创建writer，默认创建xls格式
            ExcelWriter writer = ExcelUtil.getWriter();
            // 一次性写出内容，使用默认样式，强制输出标题
            writer.write(dataList, true);
            //out为OutputStream，需要写出到的目标流

            //response为HttpServletResponse对象
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
            response.setHeader("Content-Disposition","attachment;filename=test.xlsx");
            ServletOutputStream out= null;
            try {
                out = response.getOutputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            writer.flush(out, true);
            // 关闭writer，释放内存
            writer.close();
            //此处记得关闭输出Servlet流
            IoUtil.close(out);
//            return BaseResultUtils.generateSuccess(true);
        }catch (Exception e){
            log.error("批量导出敏感词-controller层-exportWords-异常:", e);
//            return BaseResultUtils.generateFail("exportWords-异常:"+e.getMessage());
        }
    }
}
