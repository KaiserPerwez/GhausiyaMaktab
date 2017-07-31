package com.qtechie.ghausiyamaktab.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.eyalbira.loadingdots.LoadingDots;
import com.qtechie.ghausiyamaktab.R;

public class LoginActivity extends AppCompatActivity {

    EditText txt_passcode;
    TextInputLayout txt_passcode_txtInputLayout;
    LoadingDots dots_progressbar;
    FloatingActionButton fab_login;
    //BookLoading bookLoading_progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_login);

            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                w.setStatusBarColor(Color.parseColor("#86b300"));//colored status bar
            }
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);  //for making it totally transparent
            }
            txt_passcode_txtInputLayout= (TextInputLayout) findViewById(R.id.txt_passcode_txtInputLayout);
            txt_passcode = (EditText) findViewById(R.id.txt_passcode);

//            btn_login = (Button) findViewById(R.id.btn_login);
//            btn_login.requestFocus();
            dots_progressbar = (LoadingDots) findViewById(R.id.dots_progressbar);
            dots_progressbar.setVisibility(View.GONE);
            dots_progressbar.stopAnimation();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


        fab_login = (FloatingActionButton) findViewById(R.id.fab_login);
        fab_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_passcode.getText().toString().length()>0){
                Snackbar.make(view, "Please Wait. Authenticating You...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                fab_login.requestFocus();
                onButtonLogin_Click(view);
                }
                else
                    Snackbar.make(view, "Passcode shouldn't be empty. Right?", Snackbar.LENGTH_LONG).show();
            }
        });

        //bookLoading_progressbar=(BookLoading)findViewById(R.id.bookloading);

//        txt_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow((null==getCurrentFocus())?null:getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
       txt_passcode.clearFocus();
        fab_login.requestFocus();
        return super.onTouchEvent(event);
    }

    public void onButtonLogin_Click(final View view) {
//        bookLoading_progressbar.setVisibility(View.VISIBLE);
////        bookLoading_progressbar.start();
        InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow((null==getCurrentFocus())?null:getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        txt_passcode_txtInputLayout.setVisibility(View.GONE);
       // btn_login.setVisibility(View.GONE);
        dots_progressbar.setVisibility(View.VISIBLE);
        dots_progressbar.startAnimation();

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {

            @Override
            public void run() {

                String txt=txt_passcode.getText().toString();
                if(txt.equals("1111"))
                {   Snackbar.make(view, "Looks like u forgot ur passcode!!", Snackbar.LENGTH_LONG).show();
                    //dots_progressbar.setVisibility(View.GONE);
                    txt_passcode_txtInputLayout.setVisibility(View.VISIBLE);
                }
                else if (txt.equals("k")){
                    Snackbar.make(view, "Verified. Logging u in...", Snackbar.LENGTH_LONG).show();
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        Toast.makeText(LoginActivity.this, "Err:"+e.getMessage(), Toast.LENGTH_SHORT).show();;
//                    }

                    Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    startActivity(intent);
                    finish();
                    //dots_progressbar.setVisibility(View.GONE);
                }
                else
                {Snackbar.make(view, "Authentication Failed. Give it a one more try...", Snackbar.LENGTH_LONG).show();
                 //   dots_progressbar.setVisibility(View.GONE);
                    txt_passcode_txtInputLayout.setVisibility(View.VISIBLE);}



                dots_progressbar.setVisibility(View.GONE);
                //LoginActivity.this.finish();
                //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        }, 3500);




        //dots_progressbar.setVisibility(View.VISIBLE);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
