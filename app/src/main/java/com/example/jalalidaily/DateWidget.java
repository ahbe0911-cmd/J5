package com.example.jalalidaily;
import android.appwidget.*;import android.content.*;
public class DateWidget extends AppWidgetProvider{@Override public void onUpdate(Context c,AppWidgetManager m,int[] ids){Widgets.refresh(c);}}
