package com.example.jalalidaily;
import android.app.*;import android.content.*;import java.time.*;
public final class RolloverReceiver extends BroadcastReceiver {
 @Override public void onReceive(Context c,Intent i){try{DayBundles.rolloverUnfinishedTasks(c);Widgets.refresh(c);}catch(Exception e){android.util.Log.e("JalaliRollover","Midnight rollover",e);}schedule(c);}
 static void schedule(Context c){try{ZoneId zone=ZoneId.of("Asia/Tehran");long at=LocalDate.now(zone).plusDays(1).atStartOfDay(zone).toInstant().toEpochMilli();Intent intent=new Intent(c,RolloverReceiver.class);PendingIntent pi=PendingIntent.getBroadcast(c,931,intent,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);AlarmManager a=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE);if(a!=null)a.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,at,pi);}catch(Exception e){android.util.Log.e("JalaliRollover","Schedule failed",e);}}
}
