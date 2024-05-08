package org.example.ipdemo.utils;

import com.maxmind.geoip2.DatabaseReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.ipdemo.model.IPMessage;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class IPLocationUtils {

    private static String geoFile;

    @Value("${geoFile}")
    public void setGeoFile(String geoFile) {
        this.geoFile = geoFile;
    }

    private static File file = null;

    /**
     * @param reader GeoLite2
     * @param ip
     * @return
     * @throws Exception
     */
    public static String getCountry(DatabaseReader reader, String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getCountry().getNames().get("en");
    }

    /**
     * @param reader GeoLite2
     * @param ip
     * @return
     * @throws Exception
     */
    public static String getProvince(DatabaseReader reader, String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getMostSpecificSubdivision().getNames().get("en");
    }

    /**
     * @param reader GeoLite2
     * @param ip
     * @return
     * @throws Exception
     */
    public static String getCity(DatabaseReader reader, String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getCity().getNames().get("en");
    }

    /**
     * @param reader GeoLite2
     * @param ip
     * @return
     * @throws Exception
     */
    public static Double getLongitude(DatabaseReader reader, String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getLocation().getLongitude();
    }

    /**
     * @param reader GeoLite2
     * @param ip
     * @return
     * @throws Exception
     */
    public static Double getLatitude(DatabaseReader reader, String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getLocation().getLatitude();
    }

    public static IPMessage getIPMessage(String ip) {
        log.info("ip {} ,file path {}", ip, geoFile);
        if (StringUtils.isBlank(geoFile)) {
            geoFile = "D:\\Workspace\\workspace\\path\\GeoLite2-City.mmdb";
        }
        if (file == null) {
            log.info("file is null, read file");
            file = new File(geoFile);
        }
        DatabaseReader reader;
        IPMessage ipMessage = new IPMessage();
        try {
            reader = new DatabaseReader.Builder(file).build();
            ipMessage.setCountry(getCountry(reader, ip));
            ipMessage.setCity(getCity(reader, ip));
            ipMessage.setLatitude(getLatitude(reader, ip));
            ipMessage.setLongitude(getLongitude(reader, ip));
            ipMessage.setProvince(getProvince(reader, ip));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ipMessage;
    }

    public static String getIP(HttpServletRequest servletRequest) {
        String realIp = null;
        if (servletRequest.getHeader("X-Forwarded-For") != null) {
                String realIpStr = servletRequest.getHeader("X-Forwarded-For");
            String[] realIpLst = realIpStr.split(",");
            if (realIpLst.length >= 1) {
                realIp = realIpLst[0].trim();
            }
        }
        if (realIp == null) {
            if (servletRequest.getHeader("X-Real-IP") != null) {
                realIp = servletRequest.getHeader("X-Real-IP");
            } else {
                realIp = servletRequest.getRemoteHost();
            }
        }
        return realIp;
    }

    public static void main(String[] args) {
        List<String> ipAddrList = FileReaderUtils.readFile(null);
        System.out.println(ipAddrList);

        List<IPMessage> ipMessageList = new ArrayList<>();
        for (String ipAddr : ipAddrList) {
            try {
                ipMessageList.add(IPLocationUtils.getIPMessage(ipAddr));
            } catch (Exception e) {
                System.out.println("Error processing IP address: " + ipAddr);
                e.printStackTrace();
            }
        }
        System.out.println(ipMessageList);

        FileReaderUtils.writeFile(ipAddrList,ipMessageList);
        System.out.println("Fine");

    }
}
