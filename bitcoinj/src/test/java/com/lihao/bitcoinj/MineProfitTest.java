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
    private static final BigDecimal hashRate = new BigDecimal(Math.pow(10, 12));
    private static final BigDecimal rewordPerBlock = new BigDecimal(12.5);

    @Test
    public void testOneDay() {
        BigDecimal block = calculateBlock("3.17688400354E11", 0, 86400);
        System.out.println(block.multiply(rewordPerBlock)); // 0.00079152000
    }

    @Test
    public void test2016() throws IOException, URISyntaxException {
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
        System.out.println(block.multiply(rewordPerBlock)); // 0.47268783250
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
