package com.example.tttra.calculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.faendir.rhino_android.RhinoAndroidHelper;

import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RhinoAndroidHelper rhinoAndroidHelper;
    private org.mozilla.javascript.Context context;
    private Scriptable scope;

    String processor;

    private TextView tvProcessor, tvResult;
    private Button btnNumber1, btnNumber2, btnNumber3, btnNumber4,
            btnNumber5, btnNumber6, btnNumber7, btnNumber8, btnNumber9, btnNumber0;
    private Button btnClearAll, btnPlus, btnMinus, btnMultiply, btnDivide, btnPoint, btnEqual, btnPercentage;
    private ImageButton imgbtnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        setEventClickViews();

    }

    private void initWidget() {
        tvProcessor = findViewById(R.id.edt_input);
        tvResult = findViewById(R.id.tv_result);

        tvProcessor.setText("");
        tvResult.setText("");

        btnNumber0 = findViewById(R.id.btn_number0);
        btnNumber1 = findViewById(R.id.btn_number1);
        btnNumber2 = findViewById(R.id.btn_number2);
        btnNumber3 = findViewById(R.id.btn_number3);
        btnNumber4 = findViewById(R.id.btn_number4);
        btnNumber5 = findViewById(R.id.btn_number5);
        btnNumber6 = findViewById(R.id.btn_number6);
        btnNumber7 = findViewById(R.id.btn_number7);
        btnNumber8 = findViewById(R.id.btn_number8);
        btnNumber9 = findViewById(R.id.btn_number9);

        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnMultiply = findViewById(R.id.btn_multiply);
        btnDivide = findViewById(R.id.btn_divide);
        btnPercentage = findViewById(R.id.btn_percentage);
        btnPoint = findViewById(R.id.btn_point);
        imgbtnClear = findViewById(R.id.imgbtn_clear);
        btnClearAll = findViewById(R.id.btn_clear_all);
        btnEqual = findViewById(R.id.btn_equal);
    }

    private void setEventClickViews() {
        btnNumber0.setOnClickListener(this);
        btnNumber1.setOnClickListener(this);
        btnNumber2.setOnClickListener(this);
        btnNumber3.setOnClickListener(this);
        btnNumber4.setOnClickListener(this);
        btnNumber5.setOnClickListener(this);
        btnNumber6.setOnClickListener(this);
        btnNumber7.setOnClickListener(this);
        btnNumber8.setOnClickListener(this);
        btnNumber9.setOnClickListener(this);


        btnClearAll.setOnClickListener(this);
        imgbtnClear.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnPercentage.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
    }

    // set event for button
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_number0:
                tvProcessor.append("0");
                break;
            case R.id.btn_number1:
                tvProcessor.append("1");
                break;
            case R.id.btn_number2:
                tvProcessor.append("2");
                break;
            case R.id.btn_number3:
                tvProcessor.append("3");
                break;
            case R.id.btn_number4:
                tvProcessor.append("4");
                break;
            case R.id.btn_number5:
                tvProcessor.append("5");
                break;
            case R.id.btn_number6:
                tvProcessor.append("6");
                break;
            case R.id.btn_number7:
                tvProcessor.append("7");
                break;
            case R.id.btn_number8:
                tvProcessor.append("8");
                break;
            case R.id.btn_number9:
                tvProcessor.append("9");
                break;
            case R.id.btn_plus:
                tvProcessor.append("+");
                break;
            case R.id.btn_minus:
                tvProcessor.append("-");
                break;
            case R.id.btn_multiply:
                tvProcessor.append("*");
                break;
            case R.id.btn_divide:
                tvProcessor.append("/");
                break;
            case R.id.btn_point:
                tvProcessor.append(".");
                break;
            case R.id.btn_percentage:
                tvProcessor.append("%");
                break;
            case R.id.imgbtn_clear:
                deleteNumber();
                break;
            case R.id.btn_clear_all:
                tvProcessor.setText("");
                tvResult.setText("");
                break;
            case R.id.btn_equal:
                    getResult();
                break;

        }
    }
        // set delete button
    public void deleteNumber() {
        processor = tvProcessor.getText().toString();
        if(processor.length() > 0){
            processor = processor.substring(0, processor.length()-1);
            tvProcessor.setText(processor);
        }else{
            tvResult.setText("");
        }
    }

    //user library rhino to calculate
    public void getResult() {
        processor = tvProcessor.getText().toString();
        if(processor == ""){
            tvResult.setText("0");
        }else{
            processor = processor.replaceAll("%", "/100");

            rhinoAndroidHelper = new RhinoAndroidHelper(this);
            context = rhinoAndroidHelper.enterContext();
            context.setOptimizationLevel(1);
            scope = new ImporterTopLevel(context);
            String result = "";
            try {
                result =context.evaluateString(scope, processor, "JavaScript", 1, null).toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            tvResult.setText(result);
        }
    }
}



