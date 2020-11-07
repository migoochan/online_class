package com.xdclass.xdclass.mapper;

import com.xdclass.xdclass.model.entity.Episode;
import com.xdclass.xdclass.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface EpisodeMapper {
    Episode findFirstByVideoId(@Param("videoId") int videoId);
}
