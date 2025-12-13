package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvInput;
    String input = "", operator = "";
    Double num1, num2, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInput = findViewById(R.id.tvInput);

        int[] numbers = {R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9};
        int[] operators = {R.id.btnAdd,R.id.btnSub,R.id.btnMul,R.id.btnDiv,R.id.btnPercent};

        // Number buttons
        View.OnClickListener numClick = v -> {
            Button b = (Button) v;
            input += b.getText();
            tvInput.setText(input);
        };
        for(int id:numbers) findViewById(id).setOnClickListener(numClick);
        findViewById(R.id.btn00).setOnClickListener(v -> {
            input += "00";           // Adds double zero
            tvInput.setText(input);
        });

        // Operator Buttons (+ - * / %)
        View.OnClickListener opClick = v -> {
            Button b = (Button) v;
            if(!input.isEmpty()){
                num1 = Double.parseDouble(input);
                operator = b.getText().toString();
                input += operator;
                tvInput.setText(input);
            }
        };
        for(int id:operators) findViewById(id).setOnClickListener(opClick);

        // AC
        findViewById(R.id.btnAC).setOnClickListener(v -> {
            input=""; operator=""; num1=num2=result=null;
            tvInput.setText("");
        });

        // C
        findViewById(R.id.btnC).setOnClickListener(v -> {
            if(!input.isEmpty()){
                input = input.substring(0,input.length()-1);
                tvInput.setText(input);
            }
        });

        // Decimal
        findViewById(R.id.btnDot).setOnClickListener(v -> {
            input += ".";
            tvInput.setText(input);
        });

        // Equal ======================= UPDATED WITH % ===========================
        findViewById(R.id.btnEqual).setOnClickListener(this::onClick);
    }

    private void onClick(View v) {
        if (!operator.isEmpty()) {
            String[] split = input.split("\\" + operator);
            if (split.length == 2) {
                num2 = Double.parseDouble(split[1]);

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    case "%":
                        result = (num1 * num2) / 100; // Percentage logic
                        break;
                }

                tvInput.setText(String.valueOf(result));
                input = result + "";
                operator = "";
            }
        }
    }
}
