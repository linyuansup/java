package zy.e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTextGet {
    public static void main(String[] args) {
        BufferedReader br = null; // 数据读取流
        try {
            URL url = new URL("http://www.baidu.com");
            br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")); // 指定字符串防止乱码
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line); // 不断读取数据并输出到控制台
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close(); // 关闭数据读取流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
