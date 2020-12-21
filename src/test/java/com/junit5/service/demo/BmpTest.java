package com.junit5.service.demo;

import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BmpTest {
    @Test
    public void bmpProxyTest(){
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);
        int port = proxy.getPort(); // get the JVM-assigned port
        // Selenium or HTTP client configuration goes here

        proxy.addResponseFilter(new ResponseFilter() {
            @Override
            public void filterResponse(HttpResponse response, HttpMessageContents contents, HttpMessageInfo messageInfo) {
                if (messageInfo.getOriginalUrl().contains(".json")) {
                    //获取数据如是json可转为hashmap，然后使用递归改成新的hashmap，然后再转为json塞回文本里即可替换，以下是简单实现
                    String contentsNew = contents.getTextContents().replaceAll("\"[^\"]*\"","null");
                    contents.setTextContents(contentsNew);
                }
            }
        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
