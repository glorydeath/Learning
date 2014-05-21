package com.example.helloandroid;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

/*
 * Issues to handle:
 * 1. How to deal with real number? Now the program can only handle integers.
 */


public class MainActivity extends ActionBarActivity {
	
	protected int status = 0;
	
	protected int numStatus = 0;
	// numStatus == 1: continue to add number character
	// numSstatus == 0: add number from the beginning
	
	protected int num1;
	
	protected char operator = ' ';
	// Here, you have to consider the illegal stuff

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//setButtonListener();
		TextView resultView = (TextView)findViewById(R.id.result_textView);
		resultView.setText(null);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	protected void setButtonListener() {
		findViewById(R.id.btn_num_1).setOnClickListener(listener);
		findViewById(R.id.btn_num_2).setOnClickListener(listener);
		findViewById(R.id.btn_num_3).setOnClickListener(listener);
		findViewById(R.id.btn_num_4).setOnClickListener(listener);
		findViewById(R.id.btn_num_5).setOnClickListener(listener);
		findViewById(R.id.btn_num_6).setOnClickListener(listener);
		findViewById(R.id.btn_num_7).setOnClickListener(listener);
		findViewById(R.id.btn_num_8).setOnClickListener(listener);
		findViewById(R.id.btn_num_9).setOnClickListener(listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			//View rootView = inflater.inflate(R.layout.fragment_main, container,
			//		false);
			//return rootView;
			return null;
		}
	}
	
	/*
	 * Click to continue, go to another activity
	 */
	public void clickToContinueHandler(View v) {
		//Toast.makeText(MainActivity.this, "Click handled", Toast.LENGTH_SHORT).show();
		
	}
	
	public void numberHandler(View v) {
		Button btn = (Button) v;
		String num_str = (String)btn.getText();
		TextView resultView = (TextView)findViewById(R.id.result_textView);
		if (1 == status) {
			// continue to add the number
			resultView.setText(resultView.getText() + num_str);
		} else if (0 == status) {
			// start from the beginning
			resultView.setText(num_str);
			status = 1;
		}
		//resultView.setText(resultView.getText() + num_str);
	}
	
	public void operatorHandler(View v) {
		Button btn = (Button) v;
		String op = (String)btn.getText();
		TextView resultView = (TextView)findViewById(R.id.result_textView);
		
		/*
		 * Possible bugs here:
		 * parseInt should return the integer in the textView
		 * the integer in the textView should be in num1
		 * 
		 * check how to do with status
		 */
		int tmpNum = Integer.parseInt(resultView.getText().toString());
		if (operator == ' ') {
			num1 = tmpNum;
			operator = op.charAt(0);
		} else {
			switch (operator) {
			case '+':
				num1 = num1 + tmpNum;
				break;
			case '-':
				num1 = num1 - tmpNum;
				break;
			case '*':
				num1 = num1 * tmpNum;
				break;
			case '/':
				num1 = num1 / tmpNum;
				break;
			default:
				break;
			}
			operator = op.charAt(0);
		}
		status = 0;
		//int curNum = Integer.parseInt(resultView.getText().toString());
		resultView.setText(String.valueOf(num1));
		
	}

	private OnClickListener listener = new OnClickListener(){ 
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Button btn=(Button)v;
			String num_str = (String)btn.getText();
			TextView resultView = (TextView)findViewById(R.id.result_textView);
			if (0 == status){
				resultView.setText(resultView.getText() + num_str);
			} else if (1 == status){
				resultView.setText(num_str);
			}
			/*
			switch (btn.getId()){
				case R.id.btn_num_1:
					if(0 == status){
						resultView.setText(resultView.getText()+num_str);
					} else {
						resultView.setText(num_str);
					}
					break;
				case R.id.btn_num_2:
					resultView.setText(btn.getText());
					break;
				case R.id.btn_num_3:
					resultView.setText("3");
					break;
				case R.id.btn_num_4:
					resultView.setText("4");
				default:
					break;
			}
			*/
		 }
	 };

}
