package com.feinland.tipsytipcalc;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TipsyTipCalc extends Activity {
	
	private static final String BILL = "BILL";
	private static final String CURRENT_TIP_PERCENT = "CURRENT_TIP_PERCENT";
	private static final String TIP_AMOUNT = "TIP_AMOUNT";
	private static final String TOTAL = "TOTAL";
	
	private double bill;
	private double tipPercent;
	private double tipAmount;
	private double total;
	
	EditText billET;
	EditText tipPercentET;
	EditText tipAmountET;
	EditText totalET;
	
	Button increaseTipButton;
	Button decreaseTipButton;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipsy_tip_calc);
        setTitleColor(Color.BLUE);
        
        if(savedInstanceState == null) {
        	bill = 0.0;
        	tipPercent = 0.15;
        	tipAmount = 0.0;
        	total = 0.0;
        }
        else {
        	bill = savedInstanceState.getDouble(BILL);
        	tipPercent = savedInstanceState.getDouble(CURRENT_TIP_PERCENT);
        	tipAmount = savedInstanceState.getDouble(TIP_AMOUNT);
        	total = savedInstanceState.getDouble(TOTAL);
        }
        
        billET = (EditText) findViewById(R.id.billEditText);
    	tipPercentET = (EditText) findViewById(R.id.tipPercentEditText);
    	tipAmountET = (EditText) findViewById(R.id.tipAmountEditText);
    	totalET = (EditText) findViewById(R.id.totalEditText);
        
    	billET.addTextChangedListener(billListener);
    	tipPercentET.addTextChangedListener(tipPercentListener);
    	
    	increaseTipButton = (Button) findViewById(R.id.increaseTipButton);
    	decreaseTipButton = (Button) findViewById(R.id.decreaseTipButton);
    	
    	setButtonOnClickListeners();
    	        
    }

    
    private TextWatcher billListener = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			try {
				bill = Double.parseDouble(arg0.toString());
			}
			catch(NumberFormatException e) {
				bill = 0.0;				
			}
			
			updateTipAndFinalBill();	
		}
    };
    
    
    private TextWatcher tipPercentListener = new TextWatcher() {
    	@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			try {
				tipPercent = Double.parseDouble(arg0.toString());
			}
			catch(NumberFormatException e) {
				tipPercent = 0.0;				
			}
			
			updateTipAndFinalBill();	
		}
    	
    };
    
    
    private void updateTipAndFinalBill() {
    	double tipPercent = Double.parseDouble(tipPercentET.getText().toString());
    	double tipAmount = bill * tipPercent;
    	double total = bill + tipAmount;
    	
    	tipAmountET.setText(String.format("%.02f", tipAmount));
    	totalET.setText(String.format("%.02f", total));   	
    }
    
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	
    	outState.putDouble(TOTAL, total);
    	outState.putDouble(CURRENT_TIP_PERCENT, tipPercent);
    	outState.putDouble(TIP_AMOUNT, tipAmount);
    	outState.putDouble(BILL, bill);
    }
    
    
    private void setButtonOnClickListeners() {
    	increaseTipButton.setOnClickListener(new OnClickListener(){
    		
    		public void onClick(View arg0) {
    			double oldTipPercent = Double.parseDouble(tipPercentET.getText().toString());
				double newTipPercent = oldTipPercent + 0.01;
				
				tipPercentET.setText(String.format("%.02f", newTipPercent));
				updateTipAndFinalBill();
    		}
    	});
    	
    	
    	
    	
    	decreaseTipButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				double oldTipPercent = Double.parseDouble(tipPercentET.getText().toString());
				double newTipPercent = oldTipPercent - 0.01;
				
				tipPercentET.setText(String.format("%.02f", newTipPercent));
				updateTipAndFinalBill();
			}
    	});
    }
    
    
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tipsy_tip_calc, menu);
        return true;
    }
    
}
