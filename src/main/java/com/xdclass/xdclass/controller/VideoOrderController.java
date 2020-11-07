package com.xdclass.xdclass.controller;

import com.xdclass.xdclass.model.entity.VideoOrder;
import com.xdclass.xdclass.model.request.VideoOrderRequest;
import com.xdclass.xdclass.service.VideoOrderService;
import com.xdclass.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pri/order")
public class VideoOrderController {
    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 下单
     * @param videoOrderRequest
     * @param request
     * @return
     */
    @PostMapping("/save")
    public JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        int videoId = videoOrderRequest.getVideoId();
        int rows = videoOrderService.save(userId, videoId);
        return rows == 0 ? JsonData.buildError("下单失败") : JsonData.buildSuccess("下单成功");
    }

    @GetMapping("/listOrder")
    public JsonData listOrder(HttpServletRequest request) {
        Integer userId = (Integer)request.getAttribute("user_id");
        List<VideoOrder> list = videoOrderService.listOrderByUserId(userId);
        return JsonData.buildSuccess(list);

    }
}
