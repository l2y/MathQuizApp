package com.rn.mathquiz;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class QuizActivity extends Activity {

	EditText answer;
	TextView numberOne;
	TextView numberTwo;
	TextView operator;
	
	TextView combo;
	TextView record;
	TextView time;
	
	int sendAnswer = 0;
	int correctCount = 0;
	int comboCount = 0;
	int incorrectCount = 0;
	int maxCombo = 0;
	
	int operationType = 0;
	
	Timer runTime;
	int TimeCounter = 0;
	
	int digit = 1;
	
	public static int ADDITION = 1;
	public static int SUBTRACTION = 2;
	public static int MULTIPLICATION = 3;
	public static int DIVISION = 4;
	
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
        time = (TextView) findViewById(R.id.time);
        
        setStatistics();
        
        operationType = ADDITION;
        setEquation(operationType);
        setAnswerBox();
        setRunTimer();
    }
    private void setRunTimer() {
    	runTime = new Timer();
    	runTime.scheduleAtFixedRate(new TimerTask() {
    		@Override
    		public void run() {
    			runOnUiThread(new Runnable() {
    				public void run() {
    					TimeCounter++;
    					time.setText(TimeCounter/6000+":"+TimeCounter/100 % 60+":"+TimeCounter % 100);
    				}
    			});
    		}
    	},0,10);//start running on first second, repeat every 10ms after
    }
    private void setAnswerBox() {
    	answer.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					if (Integer.parseInt(answer.getText().toString()) == sendAnswer){
						addToHistory();
						answer.setTextColor(Color.parseColor("#000000"));
						comboCount++;
						maxCombo = Math.max(maxCombo, comboCount);
						correctCount++;
						setEquation(operationType);
					} else {
						answer.setTextColor(Color.parseColor("#CD181F"));
						comboCount = 0;
						incorrectCount++;
					}
					setStatistics();
					//Quiz Completed
					if(correctCount == 10){
						runTime.cancel();
						Intent intent = new Intent(QuizActivity.this, ResultsActivity.class);
						intent.putExtra("combo", maxCombo);
						intent.putExtra("correct", correctCount);
						intent.putExtra("incorrect", incorrectCount);
						intent.putExtra("timer", TimeCounter);
						startActivity(intent);
						finish();
					}
						
				}
				return true;
			}
    		
    	});
    }
    private void addToHistory(){
    	TextView historyCell = new TextView(this);
    	historyCell.setText(numberOne.getText().toString() + " + " + numberTwo.getText().toString() + " = " + sendAnswer);
    	View history = findViewById(R.id.history);
    	historyCell.setGravity(Gravity.CENTER);
    	((LinearLayout)history).addView(historyCell, 0);
    }
    private void setStatistics(){
    	combo.setText(Integer.toString(comboCount));
    	record.setText(Integer.toString(correctCount) + "-" + Integer.toString(incorrectCount));
    }
    private void setEquation(int operation) {
    	Random rand = new Random();
    	
    	numberOne.setText(Integer.toString(rand.nextInt((int)Math.pow(10,digit))));
    	numberTwo.setText(Integer.toString(rand.nextInt((int)Math.pow(10,digit))));
    	
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
    	} 
    	answer.setText(Integer.toString(sendAnswer));
    }
}
