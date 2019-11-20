package com.example.mytestapplication.bean;

public class LotteryTicketBean {

    public final static int TYPE_RED = 1;
    public final static int TYPE_BLUE = 2;
    private int number;
    private int type;

    public LotteryTicketBean(int number, int type) {
        this.number = number;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
