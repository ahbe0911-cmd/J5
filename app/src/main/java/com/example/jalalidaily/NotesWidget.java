package com.example.jalalidaily;
import android.appwidget.*;import android.content.*;
public class NotesWidget extends AppWidgetProvider{
 @Override public void onUpdate(Context context,AppWidgetManager manager,int[] ids){Widgets.refresh(context);}
 @Override public void onReceive(Context context,Intent intent){super.onReceive(context,intent);String action=intent.getAction();if(Intent.ACTION_DATE_CHANGED.equals(action)||Intent.ACTION_TIMEZONE_CHANGED.equals(action)||Intent.ACTION_TIME_CHANGED.equals(action)||Intent.ACTION_BOOT_COMPLETED.equals(action))Widgets.refresh(context);}
}