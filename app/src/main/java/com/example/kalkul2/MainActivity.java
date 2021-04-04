package com.example.kalkul2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    Button mb0;
    Button mb1;
    Button mb2;
    Button mb3;
    Button mb4;
    Button mb5;
    Button mb6;
    Button mb7;
    Button mb8;
    Button mb9;

    TextView mtablo;

    Button mAC;
    Button msmen;
    Button mdel;
    Button mymn;
    Button mmin;
    Button mplus;
    Button mrav;
    Button mzap;
    Button mbask;

    float mValue = 0;
    String mOperator = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Подписки
        mb0 = findViewById(R.id.b0);
        mb1 = findViewById(R.id.b1);
        mb2 = findViewById(R.id.b2);
        mb3 = findViewById(R.id.b3);
        mb4 = findViewById(R.id.b4);
        mb5 = findViewById(R.id.b5);
        mb6 = findViewById(R.id.b6);
        mb7 = findViewById(R.id.b7);
        mb8 = findViewById(R.id.b8);
        mb9 = findViewById(R.id.b9);

        mtablo = findViewById(R.id.tablo);

        mAC = findViewById(R.id.AC);
        msmen = findViewById(R.id.smen);
        mdel = findViewById(R.id.del);
        mymn = findViewById(R.id.ymn);
        mmin = findViewById(R.id.min);
        mplus = findViewById(R.id.plus);
        mrav = findViewById(R.id.rav);
        mzap = findViewById(R.id.zap);
        mbask = findViewById(R.id.bask);


        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }

            public void onNumberClick(View button) {
                String number = ((Button) button).getText().toString();
                String field = mtablo.getText().toString();

                if (field.equals("0"))
                    field = number;
                else
                    field += number;

                mtablo.setText(field);
            }
        };

        mb0.setOnClickListener(numberListener);
        mb1.setOnClickListener(numberListener);
        mb2.setOnClickListener(numberListener);
        mb3.setOnClickListener(numberListener);
        mb4.setOnClickListener(numberListener);
        mb5.setOnClickListener(numberListener);
        mb6.setOnClickListener(numberListener);
        mb7.setOnClickListener(numberListener);
        mb8.setOnClickListener(numberListener);
        mb9.setOnClickListener(numberListener);


        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorListener(v);
            }
            public void onOperatorListener(View button) {
                String operator = ((Button) button).getText().toString();
                mOperator = operator;

                String field = mtablo.getText().toString();
                mValue = Float.parseFloat(field);

                mtablo.setText("0");
            }
        };

        //Подписки
        mplus.setOnClickListener(operatorListener);
        mmin.setOnClickListener(operatorListener);
        mdel.setOnClickListener(operatorListener);
        mymn.setOnClickListener(operatorListener);

        /**
         Обработка кнопок с операторами и кнопки равно
         * */
        View.OnClickListener resultListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResultListener(v);
            }
            public void onResultListener(View button) {
                String field = mtablo.getText().toString();
                float value = Float.parseFloat(field);
                boolean i = true;
                float result = value;

                switch (mOperator) {
                    case "+": {
                        result = value + mValue;
                        break;
                    }
                    case "-": {
                        result = mValue - value;
                        break;
                    }
                    case "*": {
                        result = mValue * value;
                        break;
                    }
                    case "/": {
                        if (value == 0) {
                            i = true;
                        } else {
                            i = false;
                            result = mValue / value;
                        }
                        break;
                    }
                }

                DecimalFormat format = new DecimalFormat("0.######");
                format.setRoundingMode(RoundingMode.DOWN);
                String resultText = format.format(result);

                mtablo.setText(resultText);
                mValue = result;
                mOperator = "";
            }
        };
        //Подписки
        mrav.setOnClickListener(resultListener);

        /**
         Обработка кнопки с "+/-"
         * */
        View.OnClickListener plusMinusListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlusMinusListener(v);
            }
            public void onPlusMinusListener(View button) {
                String display = mtablo.getText().toString();
                float value = Float.parseFloat(display);
                value = value*-1;

                DecimalFormat format = new DecimalFormat("0.######");
                format.setRoundingMode(RoundingMode.DOWN);
                String resultText = format.format(value);

                mtablo.setText(String.valueOf(resultText));
            }
        };
        //Подписки
        msmen.setOnClickListener(plusMinusListener);

        /**
         Обработка кнопки очистки поля
         * */
        View.OnClickListener clearListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearListener(v);
            }
            public void onClearListener(View button) {
                mValue = 0;
                mOperator = "";
                mtablo.setText("0");
            }
        };
        //Подписки
        mAC.setOnClickListener(clearListener);

        /**
         Обработка кнопки удаления последнего символа
         * */
        View.OnClickListener backListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackListener(v);
            }
            public void onBackListener(View button) {
                String field = mtablo.getText().toString();
                if (field.length() >1 ) {
                    field = field.substring(0, field.length() - 1);
                    mtablo.setText(field);
                }
                else if (field.length() <=1 ) {
                    mtablo.setText("0");
                }
            }
        };
        //Подписки
        mbask.setOnClickListener(backListener);

        /**
         Обработка кнопки с точкой
         * */
        View.OnClickListener commaListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommaListener(v);
            }
            public void onCommaListener(View button) {
                String field = mtablo.getText().toString();
                String display = field + ".";
                mtablo.setText(display);
            }
        };
        //Подписки
        mzap.setOnClickListener(commaListener);
    }
}