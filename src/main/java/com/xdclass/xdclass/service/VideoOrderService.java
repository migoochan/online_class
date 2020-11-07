package com.xdclass.xdclass.service;

import com.xdclass.xdclass.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {
    int save(int userId, int VideoId);

    List<VideoOrder> listOrderByUserId(Integer userId);
}
