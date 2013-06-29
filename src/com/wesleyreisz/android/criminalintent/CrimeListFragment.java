package com.wesleyreisz.android.criminalintent;

import java.util.List;

import com.wesleyreisz.android.criminalintent.model.Crime;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

public class CrimeListFragment extends ListFragment {
	public List<Crime>crimes;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.crimes_title);
		crimes = CrimeLab.get(getActivity()).getCrimes();
		
	}
}
