package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.example.smartlab.utils.ToastUtils;

public class Verification extends AppCompatActivity
{

    private static int counter = 60;
    private static Timer timer;

    private static int successful_code;

    public void sendCode(String email)
    {

        Random random = new Random();
        successful_code = random.nextInt(9000) + 1000;

        System.out.println("Отправляю код " + successful_code + " на email " + email);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        resetCodeTimer();

        EditText verificationEmail1 = findViewById(R.id.verification_emain_1);
        EditText verificationEmail2 = findViewById(R.id.verification_emain_2);
        EditText verificationEmail3 = findViewById(R.id.verification_emain_3);
        EditText verificationEmail4 = findViewById(R.id.verification_emain_4);


        verificationEmail1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (charSequence.length() == 1) {
                    verificationEmail2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
            }
        });

        verificationEmail2.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 1)
                {
                    verificationEmail3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
            }
        });

        verificationEmail3.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (charSequence.length() == 1)
                {
                    verificationEmail4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
            }
        });

        verificationEmail4.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (charSequence.length() == 1)
                {
                    String code = verificationEmail1.getText().toString() +
                            verificationEmail2.getText().toString() +
                            verificationEmail3.getText().toString() +
                            verificationEmail4.getText().toString();


                    System.out.println("Введенный код: " + code);
                    System.out.println("Верный код код: " + successful_code);




                    if (code.equals(Integer.toString(successful_code)))
                    {
                        Intent intent = new Intent(Verification.this, Password.class);
                        startActivity(intent);
                        timer.cancel();
                    }
                    else
                    {
                        ToastUtils.showToastWithDelay(Verification.this, "Вы ввели неверный код из почты", 5);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void resetCodeTimer()
    {
        timer = new Timer();
        final Handler handler = new Handler(Looper.getMainLooper());
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                if (counter > 0)
                {
                    counter--;
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run() {
                            TextView time = findViewById(R.id.verification_reset_code);
                            time.setText("Отправить код повторно можно\nбудет через " + counter + " секунд");
                        }
                    });
                } else {
                    timer.cancel();
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run() {
                            TextView time = findViewById(R.id.verification_reset_code);
                            time.setText("Новый код отправлен");
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    public void goBack(View view)
    {
        Intent intent = new Intent(this, Auth.class);
        startActivity(intent);
        timer.cancel();
    }
}
