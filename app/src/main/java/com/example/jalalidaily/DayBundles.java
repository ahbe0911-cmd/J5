package com.example.jalalidaily;
import android.content.*;import org.json.*;
public final class DayBundles{
 static void migrate(Context c){android.content.SharedPreferences prefs=c.getSharedPreferences("bundles",0);if(prefs.getBoolean("_legacy_imported",false))return;JSONArray old=Store.all(c);for(int i=0;i<old.length();i++){JSONObject o=old.optJSONObject(i);if(o==null)continue;String date=o.optString("date");if(date.isEmpty())continue;JSONArray list=raw(c,date);try{String text=o.optString("title");String body=o.optString("body");if(!body.isEmpty())text+=" — "+body;list.put(new JSONObject().put("emoji",o.optString("sticker","📝")).put("text",text).put("done",o.optBoolean("done",false)));save(c,date,list);}catch(Exception ignored){}}prefs.edit().putBoolean("_legacy_imported",true).apply();}
 static JSONArray raw(Context c,String date){try{return new JSONArray(c.getSharedPreferences("bundles",0).getString(date,"[]"));}catch(Exception e){return new JSONArray();}}
 static JSONArray get(Context c,String date){migrate(c);return raw(c,date);}
 /* legacy reader retained */
 static JSONArray unused(Context c,String date){try{return new JSONArray(c.getSharedPreferences("bundles",0).getString(date,"[]"));}catch(Exception e){return new JSONArray();}}
 static void save(Context c,String date,JSONArray tasks){c.getSharedPreferences("bundles",0).edit().putString(date,tasks.toString()).apply();}
 static void add(Context c,String date,String emoji,String text){JSONArray a=get(c,date);try{a.put(new JSONObject().put("emoji",emoji).put("text",text).put("done",false));save(c,date,a);}catch(Exception ignored){}}
 static void done(Context c,String date,int index,boolean value){JSONArray a=get(c,date);JSONObject o=a.optJSONObject(index);if(o!=null){try{o.put("done",value);}catch(Exception ignored){}save(c,date,a);}}
 static void remove(Context c,String date,int index){JSONArray a=get(c,date),b=new JSONArray();for(int i=0;i<a.length();i++)if(i!=index)b.put(a.opt(i));save(c,date,b);}
}