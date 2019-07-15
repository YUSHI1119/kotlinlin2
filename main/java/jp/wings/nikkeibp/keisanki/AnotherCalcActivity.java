package jp.wings.nikkeibp.keisanki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AnotherCalcActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {
    private EditText num1;
    private EditText num2;
    private Spinner ope;
    private TextView cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_calc);

        num1 = (EditText)findViewById(R.id.numberInput1);
        num1.addTextChangedListener(this);

        num2 = (EditText)findViewById(R.id.numberInput2);
        num2.addTextChangedListener(this);

        ope = (Spinner)findViewById(R.id.operatorSelector);

        cal = (TextView)findViewById(R.id.calcResult);

        findViewById(R.id.backButton).setOnClickListener(this);

    }

    private boolean checkEditTextInput(){
        String input1 = num1.getText().toString();
        String input2 = num2.getText().toString();

        //2つとも空文字列でなければ、trueを返す
        return !TextUtils.isEmpty(input1) && !TextUtils.isEmpty(input2);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //テキストが変更される直前に呼ばれる

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //テキストが変更されるときに呼ばれる

    }

    @Override
    public void afterTextChanged(Editable editable) {
        //テキストが変更された後に呼ばれる
        refreshResult();

    }

    public void refreshResult(){
        if (checkEditTextInput()){
            try {
                int result = calc();
                String resultText = getString(R.string.calc_result_text, result);

                if (resultText.length() < 11){
                    cal.setText(resultText);
                }else{
                    cal.setText("E");
                }

            }catch (ArithmeticException a){
                cal.setText("ArithmeticException");
            }

        }else{
            cal.setText(R.string.calc_result_default);
        }

    }

    private int calc(){
        String input1 = num1.getText().toString();
        String input2 = num2.getText().toString();

        int number1 = Integer.parseInt(input1);
        int number2 = Integer.parseInt(input2);

        int operator = ope.getSelectedItemPosition();

        switch (operator){
            case 0:
                return number1 + number2;
            case 1:
                return number1 - number2;
            case 2:
                return number1 * number2;
            case 3:
                return number1 / number2;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public void onClick(View v) {
        if(!checkEditTextInput()){
            setResult(RESULT_CANCELED);
        }else{
            try {
                int result = calc();
                if (result < 1000000000) {
                    Intent data = new Intent();
                    data.putExtra("result", result);
                    setResult(RESULT_OK, data);
                } else {
                    setResult(RESULT_CANCELED);
                }
            }catch (ArithmeticException a){
                cal.setText("ArithmeticException");
            }
        }

        finish();

    }
}
