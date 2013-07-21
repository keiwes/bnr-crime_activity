package com.wesleyreisz.android.criminalintent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONException;

import android.content.Context;
import android.util.Log;

import com.wesleyreisz.android.criminalintent.model.Crime;

public class CrimeLab {
	private final static String TAG = "CrimeLab"; 
	private final static String FILENAME = "crimes.json";
	CriminalIntentJSONSerializer mSerializer;
	private static CrimeLab sCrimeLab;
	private Context mAppContext;
	private List<Crime>mCrimes;
	
	private CrimeLab(Context appContext){
		Log.d(TAG,"Creating Instance");
		mSerializer = new CriminalIntentJSONSerializer(appContext, FILENAME);
		mAppContext = appContext;
		
		try {
			mCrimes = mSerializer.loadCrimes();
			Log.d(TAG, "Loaded Crimes from sandbox");
		} catch (Exception e) {
			mCrimes=new ArrayList<Crime>();
			Log.d(TAG, "Error loading crimes: " + e.getMessage());
		}
	}
	public static CrimeLab get(Context c){
		if(sCrimeLab == null){
			sCrimeLab = new CrimeLab(c.getApplicationContext());
		}
		return sCrimeLab;
	}
	public List<Crime>getCrimes(){
		Log.d(TAG,"Returning Crimes");
		return mCrimes;
	}
	public Crime getCrime(UUID uuid){
		Log.d(TAG,"Returning Crime (" + uuid + ")");
		for(Crime crime: mCrimes){
			if (crime.getId().compareTo(uuid)==0){
				return crime;
			}
		}
		return null;
	}
	public void addCrime(Crime c){
		Log.d(TAG,"Adding Crimes");
		mCrimes.add(c);
	}
	public boolean saveCrimes(){
		try {
			mSerializer.saveCrimes(mCrimes);
			Log.d(TAG, "Saving crimes");
			return true;
		} catch (Exception e) {
			Log.d(TAG, "Failed saving crimes: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}
