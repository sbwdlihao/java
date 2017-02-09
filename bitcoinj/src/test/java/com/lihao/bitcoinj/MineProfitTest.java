package com.lihao.bitcoinj;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by sbwdlihao on 05/01/2017.
 */
public class MineProfitTest {

    private static final BigDecimal twoPow32 = new BigDecimal(Math.pow(2, 32));
    private static final BigDecimal hashRate = new BigDecimal(Math.pow(10, 12)); // 1T算力
    private static final BigDecimal rewordPerBlock = new BigDecimal(12.5);

    @Test
    public void testOneDay() {
        BigDecimal block = calculateBlock("4.22170566883E11", 0, 86400);
        System.out.println(block.multiply(rewordPerBlock)); // 0.00059562875
    }

    @Test
    public void test2016() throws IOException, URISyntaxException {
        System.out.println(calculateReward()); // 0.42131951500
    }

    // 计算盈亏平衡点
    @Test
    public void testZero() throws IOException {
        BigDecimal fixCost = new BigDecimal(888); // 一台矿机的固定成本
        double electricBill = 1.428; // 每天的电费
        int mineDay = 365 - 12; // 挖矿的天数，每月停1次维护
        BigDecimal electricCost = new BigDecimal(electricBill * mineDay); // 一年的电费
        BigDecimal reward = calculateReward(); // 1T算力一年挖到的btc
        System.out.println(fixCost.add(electricCost).divide(reward, BigDecimal.ROUND_UP)); // 3304
    }

    private BigDecimal calculateReward() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Difficulty difficulty = objectMapper.readValue(this.getClass().getClassLoader().getResource("difficulty2016.json"), Difficulty.class);
        List<Point> points = difficulty.getValues();
        BigDecimal block = new BigDecimal(0);
        for (int i = 0; i < points.size() - 2; i++) {
            Point start = points.get(i);
            Point end = points.get(i + 1);
            String diff = start.getY();
            int startTime = start.getX();
            int endTime = end.getX();
            block = block.add(calculateBlock(diff, startTime, endTime));
        }
        return block.multiply(rewordPerBlock);
    }

    private BigDecimal calculateBlock(String diff, int startTime, int endTime) {
        if (endTime < startTime) {
            throw new IllegalArgumentException("endTime must greater than startTime");
        }
        // 当前难度下挖到1个区块的时间，秒为单位
        BigDecimal blockTime = new BigDecimal(diff).multiply(twoPow32).divide(hashRate, BigDecimal.ROUND_UP);
        // 当前难度窗口内能挖到的区块
        return new BigDecimal(endTime - startTime).divide(blockTime, 10, BigDecimal.ROUND_DOWN);
    }
}
