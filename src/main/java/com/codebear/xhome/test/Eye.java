package com.codebear.xhome.test;

public class Eye implements Zb {
    private Zb zb;

    public Eye(Zb zb) {
        this.zb = zb;
    }

    @Override
    public void showName() {
        System.out.println("八倍镜");

    }
}
