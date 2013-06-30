package com.wesleyreisz.android.criminalintent.model;

import java.util.Date;
import java.util.UUID;

public class Crime {
	private UUID mId;
	private String mTitle;
	private Date mDate;
	private boolean mSolved;
	
	public Crime(){
		mId=UUID.randomUUID();
		mDate= new Date();
	}
	
	public UUID getId() {
		return mId;
	}
	
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String title) {
		this.mTitle = title;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date mDate) {
		this.mDate = mDate;
	}

	public boolean isSolved() {
		return mSolved;
	}

	public void setSolved(boolean mSolved) {
		this.mSolved = mSolved;
	}
	
	@Override
	public String toString() {
		return mTitle + " (" + mId + "): " + mSolved;
	}
	
}
