package com.example.hp.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
 private TextView screen;
    private String display="";
    private String currentOperator="";
    private String result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen=(TextView)findViewById(R.id.textView);
        screen.setText(display);

    }
    private void updateScreen() {
        screen.setText(display);
    }
    public void onClickNum(View v){
     if(result!=""){
         clear();
         updateScreen();
     }
        Button b=(Button)v;
        display+=b.getText();
        updateScreen();
    }
private boolean isOperator(char op){
    switch (op) {

        case '+':
        case '-':

        case '*':
        case '/':return true;
        default:return false;

    }
}

    public void onClickOpe(View v){
        if(display=="")return;
        Button b=(Button)v;
        if(result!=""){
            String _display=result;
            clear();
            display=_display;
            currentOperator="";
        }
        if(currentOperator!="") {
            Log.d("calcX",""+display.charAt(display.length() - 1));
            if (isOperator(display.charAt(display.length() - 1))) {

                display=display.replace(display.charAt(display.length() - 1), b.getText().charAt(0));
                updateScreen();
                return;
            }else {
             getResult();
                display=result;
                result="";
            }
            currentOperator = b.getText().toString();
        }

        display+=b.getText();
        currentOperator=b.getText().toString();
        updateScreen();
    }
    public void clear(){
        display="";
        currentOperator="";
        result="";
    }
    public void onClickClear(View v){
        clear();
      updateScreen();
    }
    private double operate(String a,String b,String op){

        switch (op){

            case "+":return Double.valueOf(a)+Double.valueOf(b);
            case "-":return Double.valueOf(a)-Double.valueOf(b);
            case "*":return Double.valueOf(a)*Double.valueOf(b);
            case "/":try {

                return Double.valueOf(a) / Double.valueOf(b);

            }catch (Exception e){
                Log.d("calc",e.getMessage());
            }
                default:return -1;
        }
    }
    private boolean getResult(){
        if(currentOperator=="")return false;
        String[] operation=display.split(Pattern.quote(currentOperator));
        if(operation.length<2)return false;
        result=String.valueOf(operate(operation[0],operation[1],currentOperator));
        return true;
    }
    public void onClickEqual(View v){
        if(display=="")return;

        if(!getResult()) return;
        screen.setText(display+"\n"+String.valueOf(result));
    }

}
