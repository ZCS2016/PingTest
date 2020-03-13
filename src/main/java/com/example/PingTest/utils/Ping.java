package com.example.PingTest.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ping {

    public static String ping(String ipAddress, int pingTimes, int timeOut) {
        BufferedReader in = null;
        Runtime r = Runtime.getRuntime();
        // 将要执行的ping命令,此命令是windows格式的命令
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes    + " -w " + timeOut;
        // Linux命令如下
        // String pingCommand = "ping" -c " + pingTimes + " -w " + timeOut + ipAddress;
        try {
            System.out.println("pingCommand" + pingCommand);
            // 执行命令并获取输出
            Process p = r.exec(pingCommand);
            if (p == null) {
                return "";
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int connectedCount = 0;
            String line;
            StringBuilder sb = new StringBuilder();
            // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
                sb.append("<br/>");
            }
            // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真
            return sb.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
