package org.example.utils;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class excelUtil {

//    public static void main(String[] args) {
//        String excelInputPath = "C:\\Users\\Jaeger_Eren\\Desktop\\车型名称映射.xlsx";
//        String excelOutPath = "C:\\Users\\Jaeger_Eren\\Desktop\\车型名称映射结果.xlsx";
//
//        //提取A公司 和 B公司的数据
//        List<String> listA = readExcel(excelInputPath, 0);
//        List<String> listB = readExcel(excelInputPath, 1);
//
//        //将B公司的数据和A公司的数据进行映射
//        List<String> listB_match = matchString(listA, listB);
//
//        //导出excel
//        writeExcel(excelOutPath, listA, listB_match);
//    }

    // 暴力循环 遍历映射字符串
    public static List<String> matchString(List<String> listA, List<String> listB) {
        List<String> listB_match = new ArrayList<>();
        for (String carA : listA) {
            double similarity_init = 0;
            String num = null;
            for (String carB : listB) {
                int distance = calculateDistance(carA, carB);
                double similarity = 1 - (double) distance / carB.length();
                if (similarity > similarity_init) {
                    similarity_init = similarity;
                    num = carB;
                }
            }
            listB_match.add(num);
        }
        return listB_match;
    }

    // 用来计算两个字符串之间的编辑距离
    private static int calculateDistance(String s1, String s2) {
        int[][] d = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= s2.length(); j++) {
            d[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + cost);
            }
        }
        return d[s1.length()][s2.length()];
    }

    //导出为excel表
    public static void writeExcel(String excelOutPath, List<String> listA, List<String> listB) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("结果");
        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("a_name");
        row0.createCell(1).setCellValue("b_name");
        for (int rowindex = 1; rowindex <= listA.size(); rowindex++) {
            XSSFRow row = sheet.createRow(rowindex);

            XSSFCell cell1 = row.createCell(0);
            cell1.setCellValue(listA.get(rowindex - 1));

            XSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(listB.get(rowindex - 1));
        }

        // 设置列的自动宽度
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        try {
            FileOutputStream fos = new FileOutputStream(excelOutPath);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 读取excel表中的数据
    public static List<String> readExcel(String excelInputPath, int index) {
        List<String> stringList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(excelInputPath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(index);
            // 开始行索引为1，将标题排除
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                for (int colIndex = 0; colIndex < row.getLastCellNum(); colIndex++) {
                    XSSFCell cell = row.getCell(colIndex);
                    String value = cell.getStringCellValue();
                    stringList.add(value);
                }
            }
            workbook.close();
            fis.close();
            return stringList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringList;
    }

}