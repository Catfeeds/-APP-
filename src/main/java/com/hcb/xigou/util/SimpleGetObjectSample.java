package com.hcb.xigou.util;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;



public class SimpleGetObjectSample {
    
    public static InputStream getUrlStream(String url) throws IOException {
        URL net = new URL(url);
        URLConnection urlConn = net.openConnection();
        InputStream ism = urlConn.getInputStream();
        return ism;
    }
}
