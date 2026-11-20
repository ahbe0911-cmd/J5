package com.example.jalalidaily;
import android.appwidget.*;import android.content.*;
public class NotesWidget extends AppWidgetProvider {
 @Override public void onUpdate(Context c,AppWidgetManager m,int[] ids){try{Widgets.refresh(c);}catch(Exception e){android.util.Log.e("JalaliWidget","Update",e);}}
 @Override public void onReceive(Context c,Intent intent){try{super.onReceive(c,intent);String action=intent.getAction();if(Widgets.ACTION_TOGGLE_TASK.equals(action)){String id=intent.getStringExtra("taskId");if(id!=null&&!id.isEmpty()){DayBundles.toggleById(c,id);Widgets.refresh(c);}return;}if(Intent.ACTION_DATE_CHANGED.equals(action)||Intent.ACTION_TIMEZONE_CHANGED.equals(action)||Intent.ACTION_TIME_CHANGED.equals(action)||Intent.ACTION_BOOT_COMPLETED.equals(action)){Widgets.refresh(c);}}catch(Exception e){android.util.Log.e("JalaliWidget","Broadcast",e);}}
}