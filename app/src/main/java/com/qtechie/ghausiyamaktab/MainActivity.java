package com.qtechie.ghausiyamaktab;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
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
    TextInputLayout txt_passcode_txtInputLayout;
    LoadingDots dots_progressbar;
    //BookLoading bookLoading_progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            txt_passcode_txtInputLayout= (TextInputLayout) findViewById(R.id.txt_passcode_txtInputLayout);
            txt_passcode = (EditText) findViewById(R.id.txt_passcode);
           // btn_login = (Button) findViewById(R.id.btn_login);
          //  btn_login.requestFocus();
            dots_progressbar = (LoadingDots) findViewById(R.id.dots_progressbar);
            dots_progressbar.setVisibility(View.GONE);
            dots_progressbar.stopAnimation();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


        final FloatingActionButton fab_login = (FloatingActionButton) findViewById(R.id.fab_login);
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
                    Snackbar.make(view, "Looks like u forgot ur passcode!!", Snackbar.LENGTH_LONG).show();
                else if (txt.equals("kaiser")){
                    Snackbar.make(view, "Verified. Logging u in...", Snackbar.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Toast.makeText(MainActivity.this, "Err:"+e.getMessage(), Toast.LENGTH_SHORT).show();;
                    }
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    startActivity(intent);
                }
                else
                    Snackbar.make(view, "Authentication Failed. Give it a one more try...", Snackbar.LENGTH_LONG).show();

                dots_progressbar.setVisibility(View.GONE);
                txt_passcode_txtInputLayout.setVisibility(View.VISIBLE);


                //MainActivity.this.finish();
                //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        }, 4000);




        //dots_progressbar.setVisibility(View.VISIBLE);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
