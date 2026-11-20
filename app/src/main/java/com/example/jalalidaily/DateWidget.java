package com.example.jalalidaily;
import android.appwidget.*;import android.content.*;
public class DateWidget extends AppWidgetProvider {
 @Override public void onUpdate(Context c,AppWidgetManager m,int[] ids){try{Widgets.refresh(c);}catch(Exception e){android.util.Log.e("JalaliWidget","Update",e);}}
 @Override public void onReceive(Context c,Intent intent){try{super.onReceive(c,intent);String action=intent.getAction();if(Intent.ACTION_DATE_CHANGED.equals(action)||Intent.ACTION_TIMEZONE_CHANGED.equals(action)||Intent.ACTION_TIME_CHANGED.equals(action)||Intent.ACTION_BOOT_COMPLETED.equals(action)){Widgets.refresh(c);}}catch(Exception e){android.util.Log.e("JalaliWidget","Broadcast",e);}}
}