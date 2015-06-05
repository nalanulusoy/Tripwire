package com.example.tripware;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by ulusoyy on 19.05.2015.
 */
public class About extends Activity{

    Button btn_geri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        btn_geri = (Button) findViewById(R.id.button_geri);


        btn_geri.setOnClickListener(new Button.OnClickListener()

                                    {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplicationContext(),
                                                    MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }

        );



        TextView t1 = (TextView) findViewById(R.id.textView1);
        TextView t2 = (TextView) findViewById(R.id.textView2);
        TextView t3 = (TextView) findViewById(R.id.textView3);
        TextView t4 = (TextView) findViewById(R.id.textView4);
        TextView t5 = (TextView) findViewById(R.id.textView5);
        TextView t6 = (TextView) findViewById(R.id.textView6);
        TextView t7 = (TextView) findViewById(R.id.textView7);
        TextView t8 = (TextView) findViewById(R.id.textView8);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
        }

        return false;
        // Disable back button..............
    }
    }
