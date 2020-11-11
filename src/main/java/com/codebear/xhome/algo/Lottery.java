package com.codebear.xhome.algo;

import com.codebear.xhome.entity.LotteryAward;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

/**
 * 抽奖
 * 一等奖权重：1
 * 二等奖权重：2
 * 三等奖权重：3
 */
@Slf4j
public class Lottery {
    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        int c = 0;
        int sum = 0;
        List<LotteryAward> list = Arrays.asList(new LotteryAward("一等奖", 1),
                new LotteryAward("二等奖", 2), new LotteryAward("三等奖", 3));
        for (int i = 0; i < 100; i++) {
            LotteryAward lotteryAward = lottery(list);
            if ("一等奖".equals(lotteryAward.getMsg())) {
                a++;
            }
            if ("二等奖".equals(lotteryAward.getMsg())) {
                b++;
            }
            if ("三等奖".equals(lotteryAward.getMsg())) {
                c++;
            }
            sum++;
        }

        log.info("总抽奖次数：{}", sum);
        log.info("一等奖次数：{}", a);
        log.info("二等奖次数：{}", b);
        log.info("三等奖次数：{}", c);
    }

    public static LotteryAward lottery(List<LotteryAward> list) {
        // 总权重
        int total = 0;
        // 以权重区间段的后面的值作为key存当前信息
        TreeMap<Integer, LotteryAward> map = new TreeMap<>();
        for (LotteryAward lotteryAward : list) {
            total += lotteryAward.getWeight();
            map.put(total, lotteryAward);
        }
        int random = new Random().nextInt(total) + 1;
        //log.info("random:{}    奖项:{}",random,map.get(map.ceilingKey(random)));
        Integer key = map.ceilingKey(random);
        return map.get(key);
    }
}
