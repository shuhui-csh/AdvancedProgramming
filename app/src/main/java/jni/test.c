//
// Created by Administrator on 2019/5/27.
//

#include<jni.h>
#include<stdio.h>
#include "com_csh_advancedprogramming_NDKTest.h"

JNIEXPORT jstring JNICALL Java_com_csh_advancedprogramming_NDKTest_getStringFromC
  (JNIEnv *env,  jobject jobject) {

   //返回一个字符串
        return (*env)->NewStringUTF(env,"This is my first NDK Application");
  }