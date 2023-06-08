package com.example.smartlab;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Password extends AppCompatActivity implements View.OnClickListener {
    private ImageView passwordState1, passwordState2, passwordState3, passwordState4;
    private int clickCount = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        // Находим ImageView для состояний пароля
        passwordState1 = findViewById(R.id.password_state_1);
        passwordState2 = findViewById(R.id.password_state_2);
        passwordState3 = findViewById(R.id.password_state_3);
        passwordState4 = findViewById(R.id.password_state_4);

        // Находим кнопки для цифр
        Button button0 = findViewById(R.id.password_button_0);
        Button button1 = findViewById(R.id.password_button_1);
        Button button2 = findViewById(R.id.password_button_2);
        Button button3 = findViewById(R.id.password_button_3);
        Button button4 = findViewById(R.id.password_button_4);
        Button button5 = findViewById(R.id.password_button_5);
        Button button6 = findViewById(R.id.password_button_6);
        Button button7 = findViewById(R.id.password_button_7);
        Button button8 = findViewById(R.id.password_button_8);
        Button button9 = findViewById(R.id.password_button_9);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (view instanceof Button)
        {
            Button button = (Button) view;
            int buttonId = button.getId();

            if (buttonId >= R.id.password_button_0 && buttonId <= R.id.password_button_9)
            {
                // Проверяем, достигнут ли максимальный счетчик
                if (clickCount >= 3)
                {
                    // Если достигнут максимальный счетчик, сбрасываем его и сбрасываем фоны ImageView
                    Intent intent = new Intent(Password.this, CreateAccount.class);
                    startActivity(intent);
                    clickCount = 0;
                    // resetPasswordStateBackground();
                }

                // Получаем индекс текущего ImageView на основе значения clickCount
                int imageViewIndex = clickCount + 1;

                // Получаем ресурс идентификатор фона для текущего ImageView
                int backgroundResource = getBackgroundResource(imageViewIndex);

                // Устанавливаем фон для текущего ImageView
                setImageViewBackground(imageViewIndex, backgroundResource);

                // Устанавливаем фон для текущей кнопки
                button.setBackgroundResource(R.drawable.button_password_enable_bg);

                // Увеличиваем счетчик нажатий
                clickCount++;

                // Задержка изменения фона кнопки обратно на исходный
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        button.setBackgroundResource(R.drawable.button_password_disable_bg);
                    }
                }, 200);
            }
        }
    }


    private void resetPasswordStateBackground()
    {
        passwordState1.setBackgroundResource(R.drawable.state_disable_bg);
        passwordState2.setBackgroundResource(R.drawable.state_disable_bg);
        passwordState3.setBackgroundResource(R.drawable.state_disable_bg);
        passwordState4.setBackgroundResource(R.drawable.state_disable_bg);
    }

    private int getBackgroundResource(int imageViewIndex)
    {
        switch (imageViewIndex) {
            case 1:
                return R.drawable.state_enable_bg;
            case 2:
                return R.drawable.state_enable_bg;
            case 3:
                return R.drawable.state_enable_bg;
            case 4:
                return R.drawable.state_enable_bg;
            default:
                return 0;
        }
    }

    private void setImageViewBackground(int imageViewIndex, int backgroundResource)
    {
        switch (imageViewIndex)
        {
            case 1:
                passwordState1.setBackgroundResource(backgroundResource);
                break;
            case 2:
                passwordState2.setBackgroundResource(backgroundResource);
                break;
            case 3:
                passwordState3.setBackgroundResource(backgroundResource);
                break;
            case 4:
                passwordState4.setBackgroundResource(backgroundResource);
                break;
        }
    }
}
