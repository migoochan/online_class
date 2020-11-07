package com.xdclass.xdclass.mapper;

import com.xdclass.xdclass.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoOrderMapper {
    /**
     * 下单操作
     * @return
     */
    int saveOrder(VideoOrder videoOrder);

    /**
     * 查询用户是否购买过此商品
     * @param userId
     * @param videoId
     * @param state
     * @return
     */
    VideoOrder findByUserIdAndVedioAndState(@Param("userId") int userId, @Param("videoId") int videoId,
                                            @Param("state") int state);

    List<VideoOrder> listOrderByUserId(@Param("userId") Integer userId);
}
