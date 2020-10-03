package com.pof.articles.ui;

import android.content.Context;
import android.widget.Toast;

public class ErrorHandling {

    public static void showToast(Context context, String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

}
