package com.xdclass.xdclass.mapper;

import com.xdclass.xdclass.model.entity.Video;
import com.xdclass.xdclass.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {
    /**
     * 查詢video列表
     * @return
     */
    List<Video> listVideo();

    List<VideoBanner> listVideoBanner();

    Video findDetailById(@Param("videoId") int videoId);

    Video findById(@Param("videoId") int videoId);
}
