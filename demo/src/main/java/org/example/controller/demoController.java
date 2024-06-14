package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.utils.excelUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class demoController {

    @PostMapping("/demo")
    public void demo() {
        log.info("开启");
        String excelInputPath = "/opt/test/车型名称映射.xlsx";
        String excelOutPath = "/opt/test/车型名称映射结果.xlsx";

        //提取A公司 和 B公司的数据
        List<String> listA =  excelUtil.readExcel(excelInputPath, 0);
        List<String> listB = excelUtil.readExcel(excelInputPath, 1);

        //将B公司的数据和A公司的数据进行映射
        List<String> listB_match = excelUtil.matchString(listA, listB);

        //导出excel
        excelUtil.writeExcel(excelOutPath, listA, listB_match);
        log.info("结束");
    }
}
