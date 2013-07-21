package com.wesleyreisz.android.criminalintent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import com.wesleyreisz.android.criminalintent.model.Crime;

import android.content.Context;
import android.widget.AbsoluteLayout;

public class CriminalIntentJSONSerializer {
	private Context mContext;
	private String mFileName;
	
	public CriminalIntentJSONSerializer(Context context, String fileName){
		this.mContext = context;
		this.mFileName = fileName;
	}
	public List<Crime> loadCrimes() throws IOException, JSONException{
		ArrayList<Crime> crimes = new ArrayList<Crime>();
		BufferedReader reader = null;
		try {
			InputStream in = mContext.openFileInput(mFileName);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			while((line=reader.readLine())!=null){
				jsonString.append(line);
			}
			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
			for(int i = 0;i<array.length(); i++){
				crimes.add(new Crime(array.getJSONObject(i)));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if (reader!=null){
				reader.close();
			}
		}	
		
		return crimes;
	}
	
	public void saveCrimes(List<Crime>crimes) throws IOException, JSONException{
		JSONArray array = new JSONArray();
		for(Crime crime:crimes){
			array.put(crime.toJSON());
		}
		Writer writer = null;
		try {
			OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			writer.close();
		}
	}
}
