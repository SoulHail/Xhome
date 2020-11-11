package com.codebear.xhome.test;

public class TestA {
    public static void main(String[] args) {
        // 98K
        Zb kar98 = new Gun();
        // 八倍98K
        Zb kar988 = new Eye(kar98);
        // 八倍消音98K
        Zb kar998voice = new Voice(kar988);
        kar998voice.showName();
    }
}
