package com.issc.ding.action;

import com.alibaba.fastjson.JSON;
import com.issc.ding.util.QueryResultInfo;
import com.issc.ding.util.Propertiesmail;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class OkHttpMail {
    public static String okHttpMail(String url) throws IOException {
        Properties pro = Propertiesmail.getProperties();    //读取配置文件
        int rows = Integer.valueOf(pro.getProperty("rows"));

        OkHttpClient okHttpClient = new OkHttpClient();     //
        Request request = new Request.Builder().url(url).build();
        okhttp3.Response response = null;

        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonstr = null;

        if (response != null && response.isSuccessful()) {
            try {
                if (response.body() != null) {
                    jsonstr = Objects.requireNonNull(response.body()).string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        QueryResultInfo queryResultInfo = JSON.parseObject(jsonstr, QueryResultInfo.class);
        for (int i = 0; i < rows; i++) {
            if (queryResultInfo != null && queryResultInfo.getResult().get(i).getContent().length() < 100) {
                jsonstr = queryResultInfo.getResult().get(i).getContent();
                break;
            }
            // System.out.println(queryResultInfo.getResult().get(i));
        }
        return jsonstr;
    }
}
