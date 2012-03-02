#include <jni.h>

JNIEXPORT void JNICALL Java_com_looprecur_restart_RestartModule_tomestoneCrash(JNIEnv *env, jobject obj) {
	int *p=0;
	*p=1;    //will seg fault
}
