package jp.wings.nikkeibp.keisanki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener, Spinner.OnItemClickListener {
    private EditText num1;
    private EditText num2;
    private Spinner ope;
    private TextView cal;

    private static final int REQUEST_CODE_ANOTHER_CALC_1 = 1;
    private static final int REQUEST_CODE_ANOTHER_CALC_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText)findViewById(R.id.numberInput1);
        num1.addTextChangedListener(this);

        num2 = (EditText)findViewById(R.id.numberInput2);
        num2.addTextChangedListener(this);

        ope = (Spinner)findViewById(R.id.operatorSelector);
//        ope.setOnItemClickListener(AdapterView.OnItemClickListener);

        cal = (TextView)findViewById(R.id.calcResult);

        findViewById(R.id.calcButton1).setOnClickListener(this);
        findViewById(R.id.calcButton2).setOnClickListener(this);
        findViewById(R.id.nextButton).setOnClickListener(this);
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
        int id = v.getId();

        switch (id){
            case R.id.calcButton1:
                Intent intent1 = new Intent(this, AnotherCalcActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_ANOTHER_CALC_1);
                break;
            case R.id.calcButton2:
                Intent intent2 = new Intent(this, AnotherCalcActivity.class);
                startActivityForResult(intent2, REQUEST_CODE_ANOTHER_CALC_2);
                break;
            case R.id.nextButton:
                if(checkEditTextInput()){
                    try {
                        int result = calc();
                        num1.setText(String.valueOf(result));
                        refreshResult();
                    }catch (ArithmeticException a){
                        cal.setText("ArithmeticException");
                    }
                }
                break;


        }

    }

    @Override
    //startActivityResult()から戻ってきたとき呼ばれる
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){
            return;
        }
        Bundle resultBundle = data.getExtras();

        if(!resultBundle.containsKey("result")){
            return;
        }

        int result = resultBundle.getInt("result");

        if(requestCode == REQUEST_CODE_ANOTHER_CALC_1){
            num1.setText(String.valueOf(result));
        }else if(requestCode == REQUEST_CODE_ANOTHER_CALC_2){
            num2.setText(String.valueOf(result));

        }
        refreshResult();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        refreshResult();
    }
}
