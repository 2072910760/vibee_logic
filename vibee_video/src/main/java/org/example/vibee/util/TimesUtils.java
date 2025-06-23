package org.example.vibee.util;

public class TimesUtils {
    /**
     * 获取微秒
     * @return
     */
    public static long getMicTime() {
        long currenTime = System.currentTimeMillis() * 1000; // 微秒
        long nanoTime = System.nanoTime(); // 纳秒
        return currenTime + (nanoTime - nanoTime / 1000000 * 1000000) / 1000;
    }
}
