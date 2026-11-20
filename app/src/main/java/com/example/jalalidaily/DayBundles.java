package com.example.jalalidaily;
import android.content.*;import org.json.*;
public final class DayBundles{
 static JSONArray get(Context c,String date){try{return new JSONArray(c.getSharedPreferences("bundles",0).getString(date,"[]"));}catch(Exception e){return new JSONArray();}}
 static void save(Context c,String date,JSONArray tasks){c.getSharedPreferences("bundles",0).edit().putString(date,tasks.toString()).apply();}
 static void add(Context c,String date,String emoji,String text){JSONArray a=get(c,date);try{a.put(new JSONObject().put("emoji",emoji).put("text",text).put("done",false));save(c,date,a);}catch(Exception ignored){}}
 static void done(Context c,String date,int index,boolean value){JSONArray a=get(c,date);JSONObject o=a.optJSONObject(index);if(o!=null){try{o.put("done",value);}catch(Exception ignored){}save(c,date,a);}}
 static void remove(Context c,String date,int index){JSONArray a=get(c,date),b=new JSONArray();for(int i=0;i<a.length();i++)if(i!=index)b.put(a.opt(i));save(c,date,b);}
}