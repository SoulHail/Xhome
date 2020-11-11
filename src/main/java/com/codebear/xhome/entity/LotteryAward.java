package com.codebear.xhome.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 抽奖信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotteryAward {
    private String msg; // 奖项信息
    private Integer weight; // 权重
}
