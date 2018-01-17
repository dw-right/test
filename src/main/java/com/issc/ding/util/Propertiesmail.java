package com.issc.ding.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class Propertiesmail {
    public static Properties getProperties() throws IOException {
        Properties pro=new Properties();
        InputStream in=new FileInputStream("mail.properties");
        pro.load(in);
        /*String key=pro.getProperty("key");
        char[] c=key.toCharArray();
        for(int i=0;i<c.length;i++){
            c[i]=(char)(c[i]^10);
        }
        key=String.valueOf(c);
        System.out.println(key);
        in.close();*/
        return pro;
    }
}