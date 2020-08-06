package com.guai.common.utils;

/**
 * @author gqw
 * @date 2020-08-05
 */
public class IdUtil {
    private static long sequence;
    private static String compareTime;

    /**
     * 生成唯一序列号
     * 根据当前时间加五位序号，一共20位
     *
     * @return 序列号
     */
    public static synchronized String getUNID() {
        // System.out.println(sequence);
        String currentTime = DateUtil.getCurrentDateString("yyMMddHHmmssSSS");
        if (compareTime == null || compareTime.compareTo(currentTime) != 0) {
            compareTime = currentTime;
            sequence = 1;
        } else {
            sequence++;
        }
        int i = (int) (Math.random() * 9000 + 1000);
        return currentTime + i + sequence;
    }
}
