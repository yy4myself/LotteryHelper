package com.example.mytestapplication.presenter;

import com.example.mytestapplication.activity.MainActivity;
import com.example.mytestapplication.bean.LotteryTicketBean;
import com.example.mytestapplication.model.LotteryTicketModel;

import java.util.List;

public class MainActivityPresenter extends BaseActivityPresenter {

    private MainActivity mActivity;
    private LotteryTicketModel mModel;

    public MainActivityPresenter(MainActivity mActivity) {
        this.mActivity = mActivity;
        mModel = new LotteryTicketModel();
    }

    public void getResult() {
        List<LotteryTicketBean> outputList = mModel.getRandomResult();
        mActivity.showResult(outputList);
    }
}
