package com.codebear.xhome.test;

public class Voice implements Zb {
    private Zb zb;
    public Voice(Zb zb) {
        this.zb = zb;
    }

    @Override
    public void showName() {
        System.out.println("消音器");
        zb.showName();
    }
}
