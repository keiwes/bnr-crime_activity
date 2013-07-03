package com.wesleyreisz.android.criminalintent;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ChoiceFragment  extends DialogFragment {
	private static String EXTRA_DATE = "com.wesleyreisz.android.criminalintent.date";
	private static final int REQUEST_DATE = 0;
	private static final String DIALOG_DATE = "date";
	private static final String TAG = "Choice";
	
	private Date mDate;
	private Button mEditDateButton;
	private Button mEditTimeButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode!=Activity.RESULT_OK) return;
		if(requestCode==REQUEST_DATE){
			Date date = (Date)data
				.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mDate = date;
			Log.d(TAG, "date: " + mDate);
			sendResult(Activity.RESULT_OK);
			getDialog().dismiss();
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
		Log.d("test","Time: " + mDate.toString());
		
		getDialog().setTitle("Edit What?");
		View v = getActivity().getLayoutInflater()
				.inflate(R.layout.dialog_choice, null);
		
		mEditDateButton = (Button)v.findViewById(R.id.choice_date_button);
		mEditDateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newInstance(mDate);
				dialog.setTargetFragment(ChoiceFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
			}
		});
		
		mEditTimeButton = (Button)v.findViewById(R.id.choice_time_button);
		mEditTimeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				TimePickerFragment dialog = TimePickerFragment.newInstance(mDate);
				dialog.setTargetFragment(ChoiceFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
			}
		});
		
		return v;
	}
	
	public static ChoiceFragment newInstance(Date date){
		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_DATE, date);
		
		ChoiceFragment fragment = new ChoiceFragment();
		fragment.setArguments(bundle);
		
		return fragment;
	}
	
	private void sendResult(int resultCode){
		if (getTargetFragment()==null){
			return;
		}
		
		Intent i = new Intent();
		i.putExtra(EXTRA_DATE, mDate);
		
		getTargetFragment()
			.onActivityResult(getTargetRequestCode(), resultCode, i);
	}
}
