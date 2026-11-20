package com.example.jalalidaily;
import android.content.*;import org.json.*;import java.util.*;
public final class Store{
 static JSONArray all(Context c){try{return new JSONArray(c.getSharedPreferences("notes",0).getString("items","[]"));}catch(Exception e){return new JSONArray();}}
 static void save(Context c,JSONArray a){c.getSharedPreferences("notes",0).edit().putString("items",a.toString()).apply();}
 static ArrayList<JSONObject> forDay(Context c,String key){ArrayList<JSONObject> out=new ArrayList<>();JSONArray a=all(c);for(int i=0;i<a.length();i++){JSONObject o=a.optJSONObject(i);if(o!=null&&key.equals(o.optString("date")))out.add(o);}out.sort((a,b)->a.optString("time").compareTo(b.optString("time")));return out;}
 static long add(Context c,String date,String title,String body,String time,long at){long id=System.currentTimeMillis();try{JSONObject o=new JSONObject();o.put("id",id);o.put("date",date);o.put("title",title);o.put("body",body);o.put("time",time);o.put("at",at);JSONArray a=all(c);a.put(o);save(c,a);}catch(Exception ignored){}return id;}
 static void delete(Context c,long id){JSONArray a=all(c),b=new JSONArray();for(int i=0;i<a.length();i++){JSONObject o=a.optJSONObject(i);if(o!=null&&o.optLong("id")!=id)b.put(o);}save(c,b);}
}