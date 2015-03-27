package com.teamsports.com.teamsports.sportsnewspager.utils;

import android.util.Log;

/**
 * Created by HTao on 2015/3/25.
 */
public class urlUtils {
    public static final String home ="http://platform.sina.com.cn/sports_client/feed?";
    private static  final String nba_url1 = "http://platform.sina.com.cn/sports_client/feed?app_key=";
    private static  final String nba_url2="&feed_id=";
    private static  final String nba_url3="&f=title%2Curl%2Ccategoryid%2Cimg%2Ccomment_show%2Cctime%2Cvid%2Cvideo_info&num=20&pdps_params=%7B%22app%22%3A%7B%22timestamp%22%3A%221427095808647%22%2C%22os%22%3A%22Android%22%2C%22model%22%3A%22Lenovo+A630t%22%2C%22device_type%22%3A%224%22%2C%22osv%22%3A%224.0.4%22%2C%22name%22%3A%22cn.com.sina.sports%22%2C%22carrier%22%3A%22%E4%B8%AD%E5%9B%BD%E7%A7%BB%E5%8A%A8%22%2C%22device_id%22%3A%22862328022633608%22%2C%22make%22%3A%22Lenovo+A630t%22%2C%22channel%22%3A%22%22%2C%22connection_type%22%3A%222%22%2C%22version%22%3A30000012%2C%22ip%22%3A%22fe80%3A%3Acadd%3Ac9ff%3Afe70%3A4c0%25wlan0%22%7D%7D";
    private static  final String hot_url1="&pid=29&len=20&news_type=0%2C2%2C3%2C101%2C102%2C103%2C106%2C107%2C108";

    private static  final String hot_url4="&pdps_params=%7B%22app%22%3A%7B%22timestamp%22%3A%221427094780551%22%2C%22os%22%3A%22Android%22%2C%22model%22%3A%22Lenovo+A630t%22%2C%22device_type%22%3A%224%22%2C%22osv%22%3A%224.0.4%22%2C%22name%22%3A%22cn.com.sina.sports%22%2C%22carrier%22%3A%22%E4%B8%AD%E5%9B%BD%E7%A7%BB%E5%8A%A8%22%2C%22device_id%22%3A%22862328022633608%22%2C%22make%22%3A%22Lenovo+A630t%22%2C%22channel%22%3A%22%22%2C%22connection_type%22%3A%222%22%2C%22version%22%3A30000012%2C%22ip%22%3A%22fe80%3A%3Acadd%3Ac9ff%3Afe70%3A4c0%25wlan0%22%7D%7D";

    public static String getChapter(String app_key ,int feed_id) {
         if(feed_id == 1009) {
            return nba_url1 + app_key + hot_url1 + nba_url2 + feed_id + hot_url4;
        }else{
            return nba_url1 + app_key + nba_url2 + feed_id + nba_url3;
        }
    }


}
