package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import model.Calculator;

public class MainActivity extends AppCompatActivity {
    private static final String CALC_STRING = "CALC_STRING";
    private TextView CalcText;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalcText = findViewById(R.id.viewResult);
        setNumberButtonListeners();
        if (savedInstanceState == null) {
            //First launch

            calculator = new Calculator();

            logCycle("onCreate First Launch");
        } else {
            logCycle("onCreate Recreate Launch");
            calculator = savedInstanceState.getParcelable(CALC_STRING);

//            CalcText.setText(getString(R.string.calc_res, calculator.getCurString()));
            CalcText.setText(calculator.getCurString());
        }




/*        findViewById(R.id.btn1).setOnClickListener(v -> {
            logCycle("Key <1> pressed");
            calculator.readkey("1");
            //            CalcText.setText(getString(R.string.calc_res, calculator.getCurString()));
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btn2).setOnClickListener(v -> {
            logCycle("Key <2> pressed");
            calculator.readkey("2");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btn3).setOnClickListener(v -> {
            logCycle("Key <3> pressed");
            calculator.readkey("3");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btn4).setOnClickListener(v -> {
            logCycle("Key <4> pressed");
            calculator.readkey("4");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btn5).setOnClickListener(v -> {
            logCycle("Key <5> pressed");
            calculator.readkey("5");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btn6).setOnClickListener(v -> {
            logCycle("Key <6> pressed");
            calculator.readkey("6");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btn7).setOnClickListener(v -> {
            logCycle("Key <7> pressed");
            calculator.readkey("7");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btn8).setOnClickListener(v -> {
            logCycle("Key <8> pressed");
            calculator.readkey("8");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btn9).setOnClickListener(v -> {
            logCycle("Key <9> pressed");
            calculator.readkey("9");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btn0).setOnClickListener(v -> {
            logCycle("Key <0> pressed");
            calculator.readkey("0");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btnplus).setOnClickListener(v -> {
            logCycle("Key <PLUS> pressed");
            calculator.readkey("+");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btnminus).setOnClickListener(v -> {
            logCycle("Key <MINUS> pressed");
            calculator.readkey("-");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btnmult).setOnClickListener(v -> {
            logCycle("Key <MULT> pressed");
            calculator.readkey("*");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btndiv).setOnClickListener(v -> {
            logCycle("Key <DIV> pressed");
            calculator.readkey("/");
            CalcText.setText(calculator.getCurString());
        });
        findViewById(R.id.btngetres).setOnClickListener(v -> {
            logCycle("Key <=> pressed");
            calculator.readkey("=");
            CalcText.setText(calculator.getCurString());
        });*/
    }

    private final int[] numberButtonIds = new int[]{R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

    private void setNumberButtonListeners() {
        for (int i = 0; i < numberButtonIds.length; i++) {
            findViewById(numberButtonIds[i]).setOnClickListener(v -> {
                Button btn = (Button)v;
                String btnStrng = btn.getText().toString();
                logCycle("Key <" + btnStrng + "> pressed");
                calculator.readkey(btnStrng);
                CalcText.setText(calculator.getCurString());
            });
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CALC_STRING, calculator);
        super.onSaveInstanceState(outState);
    }

    private void logCycle(String message) {
        Log.d("MainActivity", message);

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


}