package com.wesleyreisz.android.criminalintent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wesleyreisz.android.criminalintent.model.Crime;

import android.content.Context;

public class CrimeLab {
	private static CrimeLab sCrimeLab;
	private Context mAppContext;
	private List<Crime>mCrimes;
	
	private CrimeLab(Context appContext){
		mAppContext = appContext;
		mCrimes=new ArrayList<Crime>();
		for(int i=0;i<100;i++){
			Crime crime = new Crime();
			crime.setTitle("Crime " + i);
			if(i%2==0){
				crime.setSolved(true);
			}
			mCrimes.add(crime);
		}
	}
	public static CrimeLab get(Context c){
		if(sCrimeLab == null){
			sCrimeLab = new CrimeLab(c.getApplicationContext());
		}
		return sCrimeLab;
	}
	public List<Crime>getCrimes(){
		return mCrimes;
	}
	public Crime getCrime(UUID uuid){
		for(Crime crime: mCrimes){
			if (crime.getId().compareTo(uuid)==0){
				return crime;
			}
		}
		return null;
	}
}
