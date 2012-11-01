package com.crikee.entertainment.net;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;

import com.crikee.entertainment.object.ItunesRank;

public class HttpConnection {
	
	private Context context ;
	public HttpConnection(Context context)
	{	
		this.context = context ;
	}
	
	final static String ITUNES_URL = "http://kworb.net/pop/" ;
	
	
	public ArrayList<ItunesRank> ParseItunes()
	{
		String source = null ;
		try {
			source = gethtml(ITUNES_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int splitindex = source.indexOf("<tr><td");
		source = source.substring(splitindex);
		System.out.println(source);
		
		int ranklistsize = 1 ;
		boolean yes = false ;
		
		ArrayList<ItunesRank> irlist = new ArrayList<ItunesRank>();
		
		ItunesRank ir = new ItunesRank(); 
		int part = 1 ;
		String temp = "" ;
		for(int i=0 ; i<source.length() ; i++)
		{
			if(ranklistsize>20) break ;
			if(source.charAt(i)=='<') 
			{
				yes = false ;
			}
			else if(source.charAt(i)=='>')
			{
				yes = true ;
			}
			else
			{
				if(yes) 
				{	
					temp += source.charAt(i) ;				
				}
				else
				{
					
					if(temp.length()>0)
					{
						
						if(part==1)
						{
							if(source.charAt(i-temp.length()-4)=='1')
							{
								if(source.charAt(i-temp.length()-5)=='p')
								{
									ir.setState("GREEN") ;
								}
								else
									ir.setState("RED");
							}
							else if(source.charAt(i-temp.length()-4)=='2')
							{
								if(source.charAt(i-temp.length()-5)=='p')
								{
									ir.setState("DARKGREEN") ;
								}
								else
									ir.setState("DARKRED");
							}
							else
							{
								ir.setState("WHITE") ;
							}
							
							ir.setRank(temp);
							part ++ ;
							temp = "" ;
						}
						else if(part==2)
						{
							String song[] = temp.split(" - ");	
							ir.setSinger(song[0]);
							ir.setSongname(song[1]);
							part ++ ;
							temp = "" ;
						}
						else if(part==3) 
						{				
							ir.setRate(temp);
							irlist.add(ir);
							ir = new ItunesRank();
							part ++ ;
							temp = "" ;
						}
						else if(part>=4)
						{
							part ++ ;
							temp = "" ;
							if(part==23) 
							{
								part = 1 ;
								ranklistsize ++ ;
							}
						}
					}
				}			
			}
		}
		return irlist ;
	}
	
	public String gethtml(String urlpath) throws Exception
    {
		String htmlcode = "NULL" ;
    	URL url = new URL(urlpath);
    	HttpURLConnection con = (HttpURLConnection)url.openConnection();
    	con.setConnectTimeout(5 * 1000);
    	con.setRequestMethod("GET");
    	InputStream inputStream = con.getInputStream();
    	byte[] 	data = readStream(inputStream);
    	htmlcode = new String(data);
    	return htmlcode ;   	
    	
    }
    
    public byte[] readStream(InputStream inputStream) throws Exception
    {
    	byte[] buffer=new byte[1024];
    	int len=-1;
	    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
	    
        while((len=inputStream.read(buffer))!=-1)
	    {
		    byteArrayOutputStream.write(buffer,0,len);
	    }
    
        inputStream.close();
	    byteArrayOutputStream.close();
	    return byteArrayOutputStream.toByteArray();
    }
	
}