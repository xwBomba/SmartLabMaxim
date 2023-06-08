package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

import com.example.smartlab.utils.ToastUtils;

public class Auth extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // Находим кнопку и поле ввода email
        Button getButton = findViewById(R.id.auth_button);
        EditText getEmail = findViewById(R.id.auth_email);

        // Добавляем слушателя TextWatcher для поля ввода email
        getEmail.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // Не требуется
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String userInput = s.toString();

                if (userInput.isEmpty())
                {
                    getButton.setBackgroundResource(R.drawable.button_email_disable_bg);
                }
                else
                {
                    //System.out.println("Пользователь ввёл email: " + userInput);
                    getButton.setBackgroundResource(R.drawable.button_email_enable_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                // Не требуется
            }
        });
    }

    // Метод, вызываемый при нажатии на кнопку "Проверить аутентификацию"
    public void checkAuth(View view)
    {
        EditText getEmail = findViewById(R.id.auth_email);
        String email = getEmail.getText().toString().trim();

        if (!TextUtils.isEmpty(email))
        {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                // Показываем всплывающее сообщение с задержкой
                ToastUtils.showToastWithDelay(this, "Введите настоящий email", 5);
            }
            else
            {
                // Если email введен корректно, переходим на экран верификации

                Verification  verification = new Verification();
                verification.sendCode(email);

                Intent intent = new Intent(this, Verification.class);
                startActivity(intent);
            }
        }
    }

}