package com.rn.mathquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class StartActivity extends Activity {

	private Button start;
	private Button leaderboards;
	private Button other;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
        
        start = (Button) findViewById(R.id.start);
        leaderboards = (Button) findViewById(R.id.leaderboards);
        other = (Button) findViewById(R.id.other);
        
        setButtons();
    }
    
    private void setButtons() {
    	start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (StartActivity.this, QuizActivity.class);
				startActivity(intent);
				
			}
		});
    	leaderboards.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (StartActivity.this, QuizActivity.class);
				startActivity(intent);
				
			}
		});
    	other.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (StartActivity.this, QuizActivity.class);
				startActivity(intent);
				
			}
    	});
    }
    
}
