package com.crikee.entertainment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.crikee.entertainment.db.DatabaseHelper;
import com.crikee.entertainment.net.HttpConnection;
import com.crikee.entertainment.object.ItunesRank;
import com.crikee.entertainment.object.RankViewAdapter;






public class MainActivity extends Activity {
    
	private TextView Refresh;
	private ListView listView ;
	private ArrayList<ItunesRank> irlist ;
	private ProgressDialog pd;
	private DatabaseHelper db ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        
        listView = (ListView)findViewById(R.id.lv_itunesranklist);
        Refresh = (TextView)findViewById(R.id.tv_refresh);
        db = new DatabaseHelper(this);
        
        Refresh.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				pd = ProgressDialog.show(MainActivity.this, "请稍等",
							"正在载入榜单...");
				new updateDB().execute(null, null, null);								
			}
		});
       
        pd = ProgressDialog.show(MainActivity.this, "请稍等",
				"正在载入榜单...");
        new showRankList().execute(null, null, null);
        
    }

    public class showRankList extends AsyncTask<Void, Void, Void> {		
    	RankViewAdapter adapter ;
		@Override
        protected Void doInBackground(Void... a) {			
			adapter = getAdapter() ;
			return null;		
        }
        @Override
        protected void onPostExecute(Void tem){  
        	listView.setAdapter(adapter);
        	pd.dismiss();
        }
    }
    
    public class updateDB extends AsyncTask<Void, Void, Void> {		
    	
		@Override
        protected Void doInBackground(Void... a) {			
			UpdateData() ;
			return null;		
        }
        @Override
        protected void onPostExecute(Void tem){  
        	listView.setAdapter(getAdapter());
        	pd.dismiss();
        }
    }
    
    private RankViewAdapter getAdapter()
    {
    	irlist = getData() ;    	
        RankViewAdapter adapter = new RankViewAdapter(this, irlist);        
        return adapter ;
    }
    
    private void UpdateData()
    {
    	HttpConnection hc = new HttpConnection(MainActivity.this);
    	ArrayList<ItunesRank> list = hc.ParseItunes() ;	
    	
    	for(int pos = 0 ; pos<list.size() ; pos++)
    	{
    		ContentValues values = new ContentValues();
    		values.put("rank",list.get(pos).getRank());
    		values.put("songname",list.get(pos).getSongname());
    		values.put("singer",list.get(pos).getSinger());
    		values.put("rate",list.get(pos).getRate());
    		values.put("state",list.get(pos).getState());
    		db.updateRank(values);
    	}
    	
    }
    
    
    private ArrayList<ItunesRank> getData() 
    {   
    	ArrayList<ItunesRank> list = new  ArrayList<ItunesRank> ();
    	Cursor cur = db.queryRank();
    	
    	if (cur.moveToFirst()) {       			
			do
			{
				ItunesRank temp = new ItunesRank();
				temp.setRank(cur.getString(1));
				temp.setSongname(cur.getString(2));
				temp.setSinger(cur.getString(3));
				temp.setRate(cur.getString(4));
				temp.setState(cur.getString(14));
                list.add(temp);
  			}
			while(cur.moveToNext()); 
    	}
        return list;  
    }
            
}