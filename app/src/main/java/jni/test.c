//
// Created by Administrator on 2019/5/27.
//

#include<jni.h>
#include<stdio.h>
#include "com_csh_advancedprogramming_NDKTest.h"

JNIEXPORT jstring JNICALL Java_com_csh_advancedprogramming_NDKTest_getStringFromC
  (JNIEnv *env,  jclass jclass) {

   //返回一个字符串
        return (*env)->NewStringUTF(env,"这个是从 c获取而来的文件");
  }