#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_testndk_root_testndk_bluetoothchat_BluetoothChatFragment_stringFromJNI(JNIEnv *env,
                                                                                jobject instance) {

    // TODO
    //    std::string hello = "Hello from C++";
    std::string hello = "https://obdtest.azurewebsites.net";
    return env->NewStringUTF(hello.c_str());
}

