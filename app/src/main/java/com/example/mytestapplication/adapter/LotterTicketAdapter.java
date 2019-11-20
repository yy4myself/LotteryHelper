package com.example.mytestapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mytestapplication.R;
import com.example.mytestapplication.bean.LotteryTicketBean;

import java.util.ArrayList;
import java.util.List;


public class LotterTicketAdapter extends RecyclerView.Adapter<LotterTicketAdapter.ViewHolder> {

    private Context context;
    private List<List<LotteryTicketBean>> list;
    private ItemClickListener listener;

    public LotterTicketAdapter(Context context, List<List<LotteryTicketBean>> list, ItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lotter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final List<LotteryTicketBean> beanList = list.get(position);
        List<LotteryTicketBean> red = new ArrayList<>();
        List<LotteryTicketBean> blue = new ArrayList<>();
        LotteryTicketBean bean;
        for (int i = 0; i < beanList.size(); i++) {
            bean = beanList.get(i);
            if (bean.getType() == LotteryTicketBean.TYPE_RED) red.add(bean);
            if (bean.getType() == LotteryTicketBean.TYPE_BLUE) blue.add(bean);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("红色球序号为：");
        for (int i = 0; i < red.size(); i++) {
            stringBuilder.append(red.get(i).getNumber());
            if (i != red.size() - 1) {
                stringBuilder.append("，");
            }
        }
        stringBuilder.append("；");
        stringBuilder.append("蓝色球序号为：");
        for (int i = 0; i < blue.size(); i++) {
            stringBuilder.append(blue.get(i).getNumber());
            if (i != blue.size() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("。");
        viewHolder.textViewResult.setText(stringBuilder.toString());

        if (listener != null) {
            viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClickListener(position);
                }
            });
            viewHolder.mLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.OnItemLongClickListener(position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLayout;
        TextView textViewResult;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLayout = itemView.findViewById(R.id.linearLayout);
            textViewResult = itemView.findViewById(R.id.textView_result);
        }
    }

    public interface ItemClickListener {
        void OnItemClickListener(int position);

        void OnItemLongClickListener(int position);
    }

    public void sortPositionData(int position) {
        List<LotteryTicketBean> tempList = list.get(position);
        List<LotteryTicketBean> red = new ArrayList<>();
        List<LotteryTicketBean> blue = new ArrayList<>();
        LotteryTicketBean bean;
        for (int i = 0; i < tempList.size(); i++) {
            bean = tempList.get(i);
            if (bean.getType() == LotteryTicketBean.TYPE_RED) red.add(bean);
            if (bean.getType() == LotteryTicketBean.TYPE_BLUE) blue.add(bean);
        }
        List<LotteryTicketBean> targetList = new ArrayList<>();
        //直接选择排序
        red = sort(red);
        blue = sort(blue);
        targetList.addAll(red);
        targetList.addAll(blue);
        list.set(position, targetList);
        notifyDataSetChanged();
    }

    private List<LotteryTicketBean> sort(List<LotteryTicketBean> input) {
        for (int i = 0; i < input.size(); i++) {
            int min = input.get(i).getNumber();
            int index = i;
            for (int j = i + 1; j < input.size(); j++) {
                if (input.get(j).getNumber() < min) {
                    min = input.get(j).getNumber();
                    index = j;
                }
            }
            if (index != i) {
                LotteryTicketBean temp = input.get(index);
                input.set(index, input.get(i));
                input.set(i, temp);
            }
        }
        return input;
    }
}
