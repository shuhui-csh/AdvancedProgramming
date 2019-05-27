package com.csh.advancedprogramming;

public class NDKTest {

    // 动态导入 so 库
    static {
        System.loadLibrary("JNITest");
    }

    public native String getStringFromC();
}
