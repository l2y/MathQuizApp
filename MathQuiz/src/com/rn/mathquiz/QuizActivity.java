package com.rn.mathquiz;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class QuizActivity extends Activity {

	EditText answer;
	TextView numberOne;
	TextView numberTwo;
	TextView operator;
	
	TextView combo;
	TextView record;
	
	int sendAnswer = 0;
	int correctCount = 0;
	int comboCount = 0;
	int incorrectCount = 0;
	
	int operationType = 0;
	
	public static int ADDITION = 1;
	public static int SUBTRACTION = 2;
	public static int MULTIPLICATION = 3;
	public static int DIVISION = 4;
	public static int REMAINDER = 5;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_quiz);
        
        answer = (EditText) findViewById(R.id.answer);
        numberOne = (TextView) findViewById(R.id.number_1);
        numberTwo = (TextView) findViewById(R.id.number_2);
        operator = (TextView) findViewById(R.id.operation);
        
        combo = (TextView) findViewById(R.id.combo);
        record = (TextView) findViewById(R.id.record);
        
        combo.setText("0");
        record.setText("0-0");
        
        operationType = ADDITION;
        setEquation(operationType);
        setAnswerBox();
    }
    
    private void setAnswerBox() {
    	answer.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					if (Integer.parseInt(answer.getText().toString()) == sendAnswer){
						setEquation(operationType);
						answer.setTextColor(Color.parseColor("#000000"));
						comboCount++;
						correctCount++;
					} else {
						answer.setTextColor(Color.parseColor("#CD181F"));
						comboCount = 0;
						incorrectCount++;
					}
					setStatistics();
				}
				return true;
			}
    		
    	});
    }
    private void setStatistics(){
    	combo.setText(Integer.toString(comboCount));
    	record.setText(Integer.toString(correctCount) + "-" + Integer.toString(incorrectCount));
    }
    private void setEquation(int operation) {
    	Random rand = new Random();
    	
    	numberOne.setText(Integer.toString(rand.nextInt(10)));
    	numberTwo.setText(Integer.toString(rand.nextInt(10)));
    	
    	if (operation == ADDITION) {
    		operator.setText("+");
    		sendAnswer = (Integer.parseInt(numberOne.getText().toString())) + (Integer.parseInt(numberTwo.getText().toString()));
    	} else if (operation == SUBTRACTION) {
    		operator.setText("-");
    		sendAnswer = (Integer.parseInt(numberOne.getText().toString())) - (Integer.parseInt(numberTwo.getText().toString()));
    	} else if (operation == MULTIPLICATION) {
    		operator.setText("x");
    		sendAnswer = (Integer.parseInt(numberOne.getText().toString())) * (Integer.parseInt(numberTwo.getText().toString()));
    	} else if (operation == DIVISION) {
    		operator.setText("/");
    		sendAnswer = (Integer.parseInt(numberOne.getText().toString())) / (Integer.parseInt(numberTwo.getText().toString()));
    	} else if (operation == REMAINDER) {
    		operator.setText("%");
    		sendAnswer = (Integer.parseInt(numberOne.getText().toString())) % (Integer.parseInt(numberTwo.getText().toString()));
    	}
    	answer.setText(Integer.toString(sendAnswer));
    	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
