package com.xdclass.xdclass.service;

import com.xdclass.xdclass.config.CacheKeyManager;
import com.xdclass.xdclass.model.entity.Video;
import com.xdclass.xdclass.model.entity.VideoBanner;
import com.xdclass.xdclass.mapper.VideoMapper;
import com.xdclass.xdclass.model.entity.VideoOrder;
import com.xdclass.xdclass.util.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    @Override
    public List<Video> listVideo() {
        try {
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_KEY, () -> {
                List<Video> videos = videoMapper.listVideo();
                return videos;
            });
            if(cacheObj instanceof List) {
                List<Video> list =  (List<Video>) cacheObj;
                return list;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<VideoBanner> listBanner() {
        try {
            Object cacheObj= baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY, () -> {
                List<VideoBanner> videoBanners = videoMapper.listVideoBanner();
                System.out.println("从数据库里找轮播图列表");
                return videoBanners;
            });
            if(cacheObj instanceof List) {
                List<VideoBanner> videoBanners = (List<VideoBanner>)cacheObj;
                return videoBanners;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public Video findDetailById(int videoId) {
        //单独构建一个缓存,每个视频的key都不一样的
        String videoCacheKey = String.format(CacheKeyManager.VIDEO_DETAIL, videoId);
        try {
            Object objectCache = baseCache.getOneHourCache().get(videoCacheKey, () -> {
                return videoMapper.findDetailById(videoId);
            });
            if(objectCache instanceof Video) {
                Video video = (Video)objectCache;
                return video;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
