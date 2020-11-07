package com.xdclass.xdclass.controller;

import com.xdclass.xdclass.model.entity.VideoBanner;
import com.xdclass.xdclass.service.VideoService;
import com.xdclass.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pri/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("list_banner")
    public JsonData indexBanner() {

        List<VideoBanner> banners = videoService.listBanner();
        return JsonData.buildSuccess(banners);
    }

    @RequestMapping("/list")
    public JsonData listVideo() {
        return JsonData.buildSuccess(videoService.listVideo());
    }

    @GetMapping("/findDetailById")
    public JsonData findDetailById(@RequestParam(value = "video_id", required = true) int videoId) {
        return JsonData.buildSuccess(videoService.findDetailById(videoId));
    }

}
