package com.xdclass.xdclass.service;

import com.xdclass.xdclass.exception.XDException;
import com.xdclass.xdclass.mapper.*;
import com.xdclass.xdclass.model.entity.Episode;
import com.xdclass.xdclass.model.entity.PlayRecord;
import com.xdclass.xdclass.model.entity.Video;
import com.xdclass.xdclass.model.entity.VideoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {
    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;


    /**
     * 下单操作
     * 未来版本: 优惠券抵扣, 风险用户检查, 生成订单基础信息, 生成支付信息
     *
     * @param userId
     * @param videoId
     * @return
     */
    @Override
    @Transactional
    public int save(int userId, int videoId) {
        //判断是否已经购买过
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVedioAndState(userId, videoId, 1);
        //购买过
        if(null != videoOrder) {
            return 0;
        }
        //查询video信息
        Video video = videoMapper.findById(videoId);
        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);
        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());
        int rows = videoOrderMapper.saveOrder(newVideoOrder);
        if(rows == 1) {
            Episode episode = episodeMapper.findFirstByVideoId(videoId);
            if(null == episode) {
                throw new XDException(-1,"视频没有集信息,请联系运营人员");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecordMapper.saveRecord(playRecord);
        }
        return rows;
    }

    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {
        return videoOrderMapper.listOrderByUserId(userId);
    }
}
