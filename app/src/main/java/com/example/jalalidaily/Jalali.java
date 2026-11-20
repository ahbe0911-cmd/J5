package com.example.jalalidaily;
import java.time.*;import java.time.chrono.*;import java.time.temporal.*;import java.util.*;
public final class Jalali {
 static final String[] MONTHS={"فروردین","اردیبهشت","خرداد","تیر","مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند"};
 static int[] fromGregorian(LocalDate d){PersianDate p=PersianDate.from(d);return new int[]{p.year,p.month,p.day};}
 static LocalDate toGregorian(int y,int m,int d){return LocalDate.ofEpochDay(PersianDate.toEpoch(y,m,d));}
 static int daysInMonth(int y,int m){return (int)(toGregorian(y,m==12?1:m+1,1).toEpochDay()-toGregorian(y,m,1).toEpochDay());}
 static String key(int[] a){return String.format(Locale.US,"%04d-%02d-%02d",a[0],a[1],a[2]);}
 static String label(int[] a){return a[2]+" "+MONTHS[a[1]-1]+" "+a[0];}
 static final class PersianDate{
  final int year,month,day;PersianDate(int y,int m,int d){year=y;month=m;day=d;}
  static PersianDate from(LocalDate date){int gy=date.getYear(),gm=date.getMonthValue(),gd=date.getDayOfMonth();int[] gdm={0,31,59,90,120,151,181,212,243,273,304,334};int jy=gy<=1600?0:979;gy-=gy<=1600?621:1600;int gy2=gm>2?gy+1:gy;int days=365*gy+(gy2+3)/4-(gy2+99)/100+(gy2+399)/400-80+gd+gdm[gm-1];jy+=33*(days/12053);days%=12053;jy+=4*(days/1461);days%=1461;if(days>365){jy+=(days-1)/365;days=(days-1)%365;}int jm=days<186?1+days/31:7+(days-186)/30;int jd=1+(days<186?days%31:(days-186)%30);return new PersianDate(jy,jm,jd);}
  static long toEpoch(int jy,int jm,int jd){LocalDate start=LocalDate.of(jy+621,3,20);for(int delta=-3;delta<=3;delta++){LocalDate x=start.plusDays(delta);PersianDate p=from(x);if(p.year==jy&&p.month==1&&p.day==1){int off=(jm<=6?(jm-1)*31:186+(jm-7)*30)+jd-1;return x.plusDays(off).toEpochDay();}}throw new IllegalArgumentException("Invalid Jalali year");}
 }
}