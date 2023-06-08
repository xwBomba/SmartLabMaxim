package com.example.smartlab.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ToastUtils
{

    public static void showToast(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToastWithDelay(final Context context, final String message, int delay)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Toast.makeText(context, "", Toast.LENGTH_SHORT).cancel();
            }
        }, delay * 1000L) ;
    }
}