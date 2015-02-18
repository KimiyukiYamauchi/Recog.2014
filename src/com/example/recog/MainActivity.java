package com.example.recog;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity
	implements OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.WHITE);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		Button btn = new Button(this);
		btn.setText("PUSH");
		btn.setTag("btn1");
		btn.setOnClickListener(this);
		layout.addView(btn);
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

	@Override
	public void onClick(View v) {
		String tag = (String)v.getTag();
		if(tag == "btn1"){
			Intent it = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			it.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, 
					RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			it.putExtra(RecognizerIntent.EXTRA_PROMPT, "Sample");
			startActivityForResult(it, 0);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 0 && resultCode == RESULT_OK){
			String s = "";
			ArrayList<String> ss =
					data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			if(ss != null){
				for(int i=0; i<ss.size(); i++){
					s += (ss.get(i) + ",");
				}
			}else{
				s = "???";
			}
			
			AlertDialog.Builder ad = new AlertDialog.Builder(this);
			ad.setMessage(s);
			ad.setPositiveButton("OK", null);
			ad.show();
		}
	}
	
	
}
