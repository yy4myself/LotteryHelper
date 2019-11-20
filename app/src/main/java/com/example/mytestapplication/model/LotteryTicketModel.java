package com.example.mytestapplication.model;

import com.example.mytestapplication.bean.LotteryTicketBean;

import java.util.ArrayList;
import java.util.List;

public class LotteryTicketModel extends BaseModel {

    private List<LotteryTicketBean> redBallList;
    private List<LotteryTicketBean> blueBallList;
    private List<LotteryTicketBean> resultList;

    public List<LotteryTicketBean> getRandomResult() {
        LotteryTicketBean lotteryTicketBean;
        //初始化红色球列表
        redBallList = new ArrayList<>();
        for (int i = 0; i < 33; i++) {
            lotteryTicketBean = new LotteryTicketBean(i + 1, LotteryTicketBean.TYPE_RED);
            redBallList.add(lotteryTicketBean);
        }
        //初始化蓝色球列表
        blueBallList = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            lotteryTicketBean = new LotteryTicketBean(i + 1, LotteryTicketBean.TYPE_BLUE);
            blueBallList.add(lotteryTicketBean);
        }

        resultList = new ArrayList<>();
        //选出红色球
        for (int i = 0; i < 5; i++) {
            int index = (int) (redBallList.size() * Math.random());
            resultList.add(redBallList.get(index));
            redBallList.remove(index);
        }
        //选出蓝色球
        for (int i = 0; i < 1; i++) {
            int index = (int) (blueBallList.size() * Math.random());
            resultList.add(blueBallList.get(index));
            blueBallList.remove(index);
        }
        return resultList;
    }
}
