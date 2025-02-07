package com.wyn.videoparser.service;
/*
 *  @Author: wangyinuo
 *  @Date:   2024/6/28
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VideoParserService {
    private static final String DY_VIDEO_PREFIX = "https://aweme.snssdk.com";

    public String parseUrl(String url) {
        return getDyVideo(url);
    }

    public String getDyVideo(String url) {
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", "Galaxy S5");

        System.setProperty("webdriver.driver.chrome", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--auto-open-devtools-for-tabs");
        options.addArguments("--headless");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(capabilities);
        // 打开网页
        driver.get(url);

        // 可以通过 Chrome 调试得到下一页 a 标签 的 CSS 选择器
        Document document = Jsoup.parse(driver.getPageSource());
        if (document == null) {
            return null;
        }
        Element elementVideo_player = document.getElementById("video-player");
        if (elementVideo_player == null) {
            return null;
        }
        String src = elementVideo_player.attr("src");
        if (src == null) {
            return null;
        }
        System.out.println("src:" + src);
        driver.close();
        String dyVideoUrl = DY_VIDEO_PREFIX + src;
        return dyVideoUrl;
    }
}
