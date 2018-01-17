package com.issc.ding.model;

import com.issc.ding.action.OkHttpMail;
import com.issc.ding.commom.SendMail;
import com.issc.ding.util.Propertiesmail;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TimingSendMail {
    public static void main(String[] args) throws IOException {
        TimingSendMail.timingSend();

    }

    private static void timingSend() throws IOException{
        Properties pro = Propertiesmail.getProperties();
        int rows = Integer.valueOf(pro.getProperty("rows"));
        int page=(int) (Math.random() * 1001);
        String key=pro.getProperty("key");
        char[] c=key.toCharArray();
        for(int i=0;i<c.length;i++){
            c[i]=(char)(c[i]^10);
        }
        key=String.valueOf(c);
        String finalKey = key;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // task to run goes hereString key = "9e76e8444cf04de8b85f1d3841b46cc0";
                String url = "http://api.avatardata.cn/Joke/QueryJokeByTime?key="+ finalKey +"&page=" + page + "&rows=" + rows + "rows+&sort=asc&time=1418745237";
                try {
                    String jsonstr= OkHttpMail.okHttpMail(url);
                    SendMail.sendMail(jsonstr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        long intevalPeriod = 24*60*60 * 1000;             //
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15);     //设置定时的时
        calendar.set(Calendar.MINUTE, 30);          //设置定时的分
        calendar.set(Calendar.SECOND, 0);           //秒
        Date date=calendar.getTime(); //第一次执行定时任务的时间
        //如果第一次执行定时任务的时间 小于当前的时间
        //此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
       /* if (date.before(new Date())) {
            date =addDay(date, 1);
        }*/
        // schedules the task to be run in an interval
        //pool.scheduleAtFixedRate();
        timer.scheduleAtFixedRate(task, date, intevalPeriod);
    }
}
