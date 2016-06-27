package com.example.hellojni;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.os.Bundle;
import java.io.File;


public class HelloJni extends Activity
{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        File f = new File(getAssets() + "/ml");
        if (f.exists()) try {

            System.out.println("Scheme exists");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String dir = f.getPath();

        VarnamSetSymbolsDir(dir);

        TextView tv = new TextView(this);
        tv.setText(VarnamInit());
        setContentView(tv);
    }

    /*** Native Methods ***/
    public native String VarnamInit();
    public native void VarnamSetSymbolsDir(String dir);

    static {
        try {
            System.loadLibrary(("varnam"));
        } catch(UnsatisfiedLinkError e) {
            Log.e("JNI", "Warning:Could not use varnam library");
        }
        try {
            System.loadLibrary("hello-jni");
        } catch(UnsatisfiedLinkError e) {
            Log.e("JNI", "Warning:Could not use hello-jni library");
        }
        try {
            System.loadLibrary("libs/libpthead.so");
        } catch(UnsatisfiedLinkError e) {
            Log.e("JNI", "Warning:Could not use lipthread library");
        }

    }
}
