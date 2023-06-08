package com.example.smartlab;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class CreateAccount extends AppCompatActivity
{

    private EditText accountNameEditText;
    private EditText accountSurname1EditText;
    private EditText accountSurname2EditText;
    private EditText accountSexEditText;
    private Button authButton;

    private TextView accountBirthdayTextView;
    private Calendar selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_accoint);

        // Инициализация полей EditText и кнопки
        accountNameEditText = findViewById(R.id.account_name);
        accountSurname1EditText = findViewById(R.id.account_surname_1);
        accountSurname2EditText = findViewById(R.id.account_surname_2);
        accountSexEditText = findViewById(R.id.account_sex);
        authButton = findViewById(R.id.account_button);
        accountBirthdayTextView = findViewById(R.id.account_birthday);

        // Добавление слушателя TextWatcher для всех полей EditText
        accountNameEditText.addTextChangedListener(textWatcher);
        accountSurname1EditText.addTextChangedListener(textWatcher);
        accountSurname2EditText.addTextChangedListener(textWatcher);
        accountSexEditText.addTextChangedListener(textWatcher);


        accountBirthdayTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog()
    {
        Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(CreateAccount.this,
                new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth)
                    {
                        selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        updateBirthdayTextView();
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    private void updateBirthdayTextView()
    {
        if (selectedDate != null)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru", "RU"));
            String formattedDate = dateFormat.format(selectedDate.getTime());
            accountBirthdayTextView.setText(formattedDate);
        }
    }

    // TextWatcher для отслеживания изменений в полях EditText
    private final TextWatcher textWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
            // Не требуется
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            // Не требуется
        }

        @Override
        public void afterTextChanged(Editable s)
        {
            // Проверяем, пустое ли хотя бы одно из полей EditText
            boolean isAnyFieldEmpty = isEditTextEmpty(accountNameEditText)
                    || isEditTextEmpty(accountSurname1EditText)
                    || isEditTextEmpty(accountSurname2EditText)
                    || isEditTextEmpty(accountSexEditText);

            // Изменяем цвет фона кнопки в зависимости от статуса полей
            if (!isAnyFieldEmpty)
            {
                authButton.setBackgroundResource(R.drawable.button_email_enable_bg);
            }
        }
    };

    // Вспомогательный метод для проверки, пустое ли поле EditText
    private boolean isEditTextEmpty(EditText editText)
    {
        return editText.getText().toString().trim().isEmpty();
    }


}
