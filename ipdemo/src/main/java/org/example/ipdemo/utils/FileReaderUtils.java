package org.example.ipdemo.utils;

import org.apache.commons.lang3.StringUtils;
import org.example.ipdemo.model.IPMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: erenjaeger
 * @Date: 2024/5/7 17:36
 */
public class FileReaderUtils {

    public static List<String> readFile(String path) {
        if (StringUtils.isBlank(path)) {
            path = "D:\\Workspace\\workspace\\path\\txt";
        }
        List<String> lines = new ArrayList<>();
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.replace("\"",""));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void writeFile(List<String> ipAddrList, List<IPMessage> ipMessageList) {

        String path = "D:\\Workspace\\workspace\\path\\ipAddr.txt";

        FileWriter fw = null;
        try {

            fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);

            Iterator<String> iterator1 = ipAddrList.iterator();
            Iterator<IPMessage> iterator2 = ipMessageList.iterator();
            while (iterator1.hasNext() && iterator2.hasNext()) {
                String ipAddr = iterator1.next();
                IPMessage ipMessage = iterator2.next();
                bw.write(String.format("%s\t%s\t%s\t%s\n",ipAddr,ipMessage.getCountry(),ipMessage.getProvince(),ipMessage.getCity()));
            }

            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}