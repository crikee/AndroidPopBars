package com.crikee.entertainment.object;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crikee.entertainment.R;



public class RankViewAdapter extends BaseAdapter {
	private ArrayList<ItunesRank> mRankList;
	private LayoutInflater mInflater;
	private Context mContext;
	
	
	public RankViewAdapter(Context context, ArrayList<ItunesRank> rankList) {
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
		mRankList = rankList ;
	}

	public int getCount() {
		return mRankList.size();
	}

	public Object getItem(int position) {
		return mRankList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public int getItemViewType(int position) {
		return position;
	}
	
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		final ViewHolder holder ;
		
		if(convertView==null)
		{
			 convertView = mInflater.inflate(R.layout.listview_itunes, null);
			 holder = new ViewHolder();
			 holder.tv_rank     = (TextView)convertView.findViewById(R.id.tv_itunesRank);
			 holder.tv_songname = (TextView)convertView.findViewById(R.id.tv_itunesSongname);
			 holder.tv_singer   = (TextView)convertView.findViewById(R.id.tv_itunesSinger);
			 holder.tv_rate     = (TextView)convertView.findViewById(R.id.tv_itunesRate);
			 holder.item_bg     = (RelativeLayout)convertView.findViewById(R.id.item_bg);
			 //绑定ViewHolder对象
			 convertView.setTag(holder);
		}
		else
		{
			//取出ViewHolder对象
			 holder = (ViewHolder)convertView.getTag();			 
		}
		ItunesRank rankDate = mRankList.get(position);
		holder.tv_rank.setText(rankDate.getRank());
		holder.tv_songname.setText(rankDate.getSongname());
		holder.tv_singer.setText(rankDate.getSinger());
		holder.tv_rate.setText(rankDate.getRate());
		
		if(rankDate.getState().equals("RED"))
			holder.item_bg.setBackgroundResource(R.color.RED);
			
		else if(rankDate.getState().equals("DARKRED"))
			holder.item_bg.setBackgroundResource(R.color.DARKRED);
			
		else if(rankDate.getState().equals("GREEN"))
			holder.item_bg.setBackgroundResource(R.color.GREEN);
			
		else if(rankDate.getState().equals("DARKGREEN"))
			holder.item_bg.setBackgroundResource(R.color.DARKGREEN);			
			
		else if(rankDate.getState().equals("WHITE"))				
			holder.item_bg.setBackgroundResource(R.color.WHITE);
		
		
		return convertView ;
	}
	
	private final class ViewHolder
	{
		TextView tv_rank ;
		TextView tv_songname ;
		TextView tv_singer ;
		TextView tv_rate ;
		RelativeLayout item_bg ;
	}
}


