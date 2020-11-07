package com.xdclass.xdclass.service;

import com.xdclass.xdclass.model.entity.Video;
import com.xdclass.xdclass.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {
    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);
}
