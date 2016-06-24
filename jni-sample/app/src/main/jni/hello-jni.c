#include <string.h>
#include <jni.h>
#include <varnam.h>

JNIEXPORT jstring JNICALL
Java_com_example_hellojni_HelloJni_VarnamInit( JNIEnv *env, jobject thiz )

{

    varnam* handle;
    char *msg;
    int rc = varnam_init_from_id("ml", &handle, &msg);
    if (rc == VARNAM_SUCCESS) {
        return (*env)->NewStringUTF(env, "Init was successful");
    } else {
        return (*env)->NewStringUTF(env, msg);
    }

}

JNIEXPORT void JNICALL
Java_com_example_hellojni_HelloJni_VarnamSetSymbolsDir(JNIEnv *env, jobject thiz, jstring dir)

{
    varnam_set_symbols_dir(dir);
}

//jstring
//Java_com_example_hellojni_HelloJni_transliterate(JNIEnv* env,
//                                                 jobject handle, jstring text )
//{
//    varnam *handle = // extract handle from jobject;
//    const char* input = //convert jstring to const char*
//    jobject output;
//    int rc = varnam_transliterate(handle, input; //take the pointer frp, output)
//    if (rc != VARNAM_SUCCESS) {
//        // handsle the error
//    } else {
//        // retrun the resultr
//    }
//
//}