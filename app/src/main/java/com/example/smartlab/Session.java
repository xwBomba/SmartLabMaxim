package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Session extends AppCompatActivity
{

    private int stats = 0;
    private Timer timer;

    private static final int SESSION_SCREEN_TIMEOUT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        TextView changeMainText = findViewById(R.id.session_main_text);
        TextView changeLoreText = findViewById(R.id.session_lore_text);

        ImageView changeState = findViewById(R.id.session_state);
        ImageView changeBanner = findViewById(R.id.session_banner);

        timer = new Timer();
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                choiceSession(changeMainText, changeLoreText, changeState, changeBanner);
            }
        };

        timer.schedule(task, 0, SESSION_SCREEN_TIMEOUT * 1000);
    }

    public void choiceSession(TextView mainText, TextView loreText, ImageView state, ImageView banner)
    {
        stats++;
        switch (stats)
        {
            case 1:

                sessionState_1(mainText, loreText, state, banner);

                break;

            case 2:

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        sessionState_2(mainText, loreText, state, banner);
                    }
                });

                break;

            case 3:

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        sessionState_3(mainText, loreText, state, banner);
                    }
                });
                break;

            default:

                timer.cancel();

                Intent intent = new Intent(this, Auth.class);
                startActivity(intent);

                break;
        }
    }

    public void sessionState_1(TextView mainText, TextView loreText, ImageView state, ImageView banner)
    {
        mainText.setText("Анализы");
        loreText.setText("Экспресс сбор и получение проб");
        state.setImageResource(R.drawable.session_state_1);
        banner.setImageResource(R.drawable.session_banner_1);
    }

    public void sessionState_2(TextView mainText, TextView loreText, ImageView state, ImageView banner)
    {
        mainText.setText("Уведомления");
        loreText.setText("Вы быстро узнаете о результатах");
        state.setImageResource(R.drawable.session_state_2);

    }

    public void sessionState_3(TextView mainText, TextView loreText, ImageView state, ImageView banner)
    {
        mainText.setText("Мониторинг");
        loreText.setText("Наши врачи всегда наблюдают\nза вашими показателями здоровья");
        state.setImageResource(R.drawable.session_state_3);

    }

    public void sessionSkip(View view)
    {
        timer.cancel();

        Intent intent = new Intent(this, Auth.class);
        startActivity(intent);

    }
}