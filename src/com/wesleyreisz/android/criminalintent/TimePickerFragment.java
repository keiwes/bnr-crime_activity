package com.wesleyreisz.android.criminalintent;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment {
	public static final String EXTRA_DATE = 
			"com.wesleyreisz.android.criminalintent.date";
	
	private Date mDate;
	
	public static TimePickerFragment newInstance(Date date){
		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_DATE, date);
		
		TimePickerFragment fragment = new TimePickerFragment();
		fragment.setArguments(bundle);
		
		return fragment;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(mDate);
		
		int hours = cal.get(Calendar.HOUR);
		int minutes = cal.get(Calendar.MINUTE);
		
		View v = getActivity().getLayoutInflater()
				.inflate(R.layout.dialog_time, null);
		
		TimePicker timePicker = (TimePicker) v.findViewById(R.id.dialog_date_timePicker);
		timePicker.setIs24HourView(false);
		timePicker.setCurrentHour(hours);
		timePicker.setCurrentMinute(minutes);
		timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(mDate);
				cal.set(Calendar.HOUR, hourOfDay);
				cal.set(Calendar.MINUTE, minute);
				mDate = cal.getTime();
				
				//depreciated date
				//mDate.setHours(hourOfDay);
				//mDate.setMinutes(minute);
				getArguments().putSerializable(EXTRA_DATE, mDate);
			}
		});
		
		
		return new AlertDialog.Builder(getActivity())
			.setView(v)
			.setTitle(R.string.time_picker_title)
			.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							sendResult(Activity.RESULT_OK);
						}
					})
			.create();
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
