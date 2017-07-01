package com.qtechie.ghausiyamaktab;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eyalbira.loadingdots.LoadingDots;
import com.victor.loading.book.BookLoading;

public class MainActivity extends AppCompatActivity {

    EditText txt_passcode;
    Button btn_login;
    LoadingDots dots_progressbar;
    //BookLoading bookLoading_progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            txt_passcode = (EditText) findViewById(R.id.txt_passcode);
            btn_login = (Button) findViewById(R.id.btn_login);
            dots_progressbar = (LoadingDots) findViewById(R.id.dots_progressbar);
            dots_progressbar.setVisibility(View.GONE);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        //bookLoading_progressbar=(BookLoading)findViewById(R.id.bookloading);

//        txt_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    public void onButtonLogin_Click(View view) {
//        bookLoading_progressbar.setVisibility(View.VISIBLE);
////        bookLoading_progressbar.start();
        InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow((null==getCurrentFocus())?null:getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        txt_passcode.setVisibility(View.GONE);
        btn_login.setVisibility(View.GONE);
        dots_progressbar.setVisibility(View.VISIBLE);

        Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    startActivity(intent);
                    //MainActivity.this.finish();
                    //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
            }, 3000);
        //dots_progressbar.setVisibility(View.VISIBLE);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
