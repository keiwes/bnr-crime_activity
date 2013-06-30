package com.wesleyreisz.android.criminalintent;

import java.util.List;

import com.wesleyreisz.android.criminalintent.model.Crime;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CrimeListFragment extends ListFragment {
	private static final String TAG = "CrimeListFragment";
	public List<Crime>mCrimes;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.crimes_title);
		mCrimes = CrimeLab.get(getActivity()).getCrimes();
		
		ArrayAdapter<Crime> adapter =
				new ArrayAdapter<Crime>(getActivity(),
						android.R.layout.simple_list_item_1,
						mCrimes);
		
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Crime c = (Crime)(getListAdapter().getItem(position));
		Log.d(TAG,c.getTitle() + " was Clicked");
		
		Toast toast = Toast.makeText(getActivity(),c.getTitle() + " was Clicked",Toast.LENGTH_SHORT);
		toast.show();
	}
}
