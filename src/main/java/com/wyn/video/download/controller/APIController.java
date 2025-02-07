package com.wyn.video.download.controller;

import com.wyn.video.download.common.domain.BaseResponse;
import com.wyn.video.download.common.utils.StringPattern;
import com.wyn.video.download.service.VideoParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private VideoParserService videoParserService;


    @RequestMapping("/analysis")
    public BaseResponse get(@RequestParam(name = "url", required = true) String url) {
        System.out.println("url:" + url);
        if (!StringUtils.isEmpty(url)) {
            if (StringPattern.isUrlString(url)) {
                //https://www.douyin.com/video/7384785741508807975
                String mobileUrl = url;
                if (mobileUrl.contains("www.")) {
                    mobileUrl = mobileUrl.replace("www.", "m.");
                    mobileUrl = mobileUrl.replace("/video/", "/share/video/");
                }
                System.out.println(mobileUrl);
                String videoUrl = videoParserService.parseUrl(mobileUrl);
                if (!StringUtils.isEmpty(videoUrl)) {
                    return BaseResponse.success(videoUrl);
                }
            }
        }
        return BaseResponse.fail(url + ":解析失败");
    }
}
