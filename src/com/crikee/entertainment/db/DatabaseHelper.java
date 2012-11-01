package com.crikee.entertainment.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * RankTable
 */

public class DatabaseHelper extends SQLiteOpenHelper{
	Cursor cursor;
	private SQLiteDatabase db;
	private final static String DATABASE_NAME = "popbars_v_1";
	private final static int DATABASE_VERSION = 1;
	
 
	private final String TBL_NAME_Rank = "RankTbl" ;	
	private final String COLUMNS_Rank = 
			"rank text," +
			"songname text," +
			"singer text," +
			"rate text," +
			"rate2 text," +
			"rate3 text," +
			"rate4 text," +
			"rate5 text," +
			"rate6 text," +
			"rate7 text," +
			"rate8 text," +
			"rate9 text," +
			"rate10 text," +
			"state text" ;
	private final String CREATE_TBL_Rank = " create table "
			+ " RankTbl(_id integer primary key autoincrement, "   + COLUMNS_Rank + ")";
	

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public DatabaseHelper() {
		super(null, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		db.execSQL(CREATE_TBL_Rank) ;
	
	}
	
	public void insertRank(ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TBL_NAME_Rank, null , values);
		db.close();
	}

	public Cursor queryRank() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TBL_NAME_Rank, null , null , null , null, null, null);
		return c;
	}
	
	public int updateRank(ContentValues values)
    {
        SQLiteDatabase db= getWritableDatabase();
        String where = "rank=?" ;
		String[] whereArgs = new String[] { String.valueOf(values.get("rank"))} ;
        return db.update(TBL_NAME_Rank,values,where,whereArgs);
    }
	
	public void close() {
		if (db != null)
			db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	
}