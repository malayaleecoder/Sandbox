package com.example.hellojni;

import android.app.Activity;
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

        File f = new File(getCacheDir() + "/ml");
        if (!f.exists()) try {

            System.out.println("Scheme exists");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("******************" + f.getPath());
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
        System.loadLibrary("hello-jni");
    }
}
