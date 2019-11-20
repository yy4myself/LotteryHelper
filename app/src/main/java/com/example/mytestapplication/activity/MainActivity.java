package com.example.mytestapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.myannotationlibrary.BindView;
import com.example.myannotationlibrary.BindViewProcess;
import com.example.mytestapplication.R;
import com.example.mytestapplication.adapter.LotterTicketAdapter;
import com.example.mytestapplication.bean.LotteryTicketBean;
import com.example.mytestapplication.fragment.LeftFragment;
import com.example.mytestapplication.presenter.MainActivityPresenter;
import com.example.mytestapplication.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, LotterTicketAdapter.ItemClickListener {

    private FrameLayout leftFrameLayout;
    private LeftFragment leftFragment;
    private MainActivityPresenter mPresenter;

    @BindView(R.id.button_start)
    private Button buttonStart;
    @BindView(R.id.recyclerView_result)
    private RecyclerView mRecyclerView;
    private LotterTicketAdapter mAdapter;
    private List<List<LotteryTicketBean>> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        //自定义的注解类
        BindViewProcess.injectViews(this);
        //初始化侧边栏的Fragment
        initFragment();
        //初始化点击事件
        buttonStart.setOnClickListener(this);
        //初始化显示
        mList = new ArrayList<>();
        mAdapter = new LotterTicketAdapter(this, mList, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //初始化中介者
        mPresenter = new MainActivityPresenter(this);
    }

    private void initFragment() {
        leftFrameLayout = findViewById(R.id.left_frame_layout);
        leftFragment = LeftFragment.newInstance();

        //设置侧边栏的宽度
        ViewGroup.LayoutParams layoutParam = leftFrameLayout.getLayoutParams();
        layoutParam.width = CommonUtil.getScreenWidth() * 3 / 5;
        leftFrameLayout.setLayoutParams(layoutParam);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.left_frame_layout, leftFragment);
        transaction.commit();
    }

    public void showResult(List<LotteryTicketBean> list) {
        mList.add(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_start:
                mPresenter.getResult();
                break;
        }
    }

    @Override
    public void OnItemClickListener(int position) {
        mAdapter.sortPositionData(position);
    }

    @Override
    public void OnItemLongClickListener(int position) {

    }

    /**
     * 跳出循环的3种方式
     */
    private void jump() {
        System.out.println("---------java中跳出多重循环的三种方式：---------");
        System.out.println("---------第一种，使用带有标号的的break语句---------");
        String a1 = "";
        String b1 = "";
        here:
        for (int i = 1; i <= 4; i++) {
            a1 = "外层循环第" + i + "层";
            for (int j = 1; j <= 4; j++) {
                b1 = "内层循环第" + j + "层";
                if (2 == j & 2 == i) {
                    break here;
                }
            }
        }
        System.out.println(a1 + b1);
        System.out.println("---------第二种，外层的循环条件收到内层的代码控制限制---------");
        String a2 = "";
        String b2 = "";
        Boolean state = true;
        for (int i = 1; i <= 4 && state; i++) {
            a2 = "外层循环第" + i + "层";
            for (int j = 1; j <= 4 && state; j++) {
                b2 = "内层循环第" + j + "层";
                if (2 == j & 2 == i) {
                    state = false;
                }
            }
        }
        System.out.println(a2 + b2);
        System.out.println("---------第三种，使用try/catch强制跳出循环---------");
        String a3 = "";
        String b3 = "";
        try {
            for (int i = 1; i <= 3; i++) {
                a3 = "外层循环第" + i + "层";
                for (int j = 1; j <= 3; j++) {
                    b3 = "内层循环第" + j + "层";
                    if (2 == j & 2 == i) {
                        throw new Exception();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(a3 + b3);
        }
        System.out.println("---------java中跳出多重循环的两种方式---------");
    }

    /**
     * 返回桌面的两种方式
     */
    private void returnHome() {
        moveTaskToBack(true);
        //=============================
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}
