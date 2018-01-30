#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_testndk_root_testndk_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
//    std::string hello = "Hello from C++";
    std::string hello = "https://obdtest.azurewebsites.net";
    return env->NewStringUTF(hello.c_str());
}
