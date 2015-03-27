package com.teamsports.com.teamsports.sportsnewspager.utils;

/**
 * Created by Administrator on 2015/3/24.
 */
public class AppConstants {
    /**
     * 专栏首页数据，POST请求
     */
    public final static String COLUMN_MAIN="http://platform.sina.com.cn/sports_client/z_list";
    /**默认无订阅状态下，
     * 专栏首页POST请求携带数据
     * app_key=2586208540&list_type=recmd
     */
    public final static String APP_KEY="2586208540";
    public final static String LIST_TYPE="recmd";


    /**
     * 专栏item详细页json地址模板
     * 冠军详细页json地址不同
     */
    public final static String GUANJUN="http://platform.sina.com.cn/sports_client/ad_list?app_key=2586208540&partner=clear&pos=sports";
    //模板地址头部
    public final static String MODEADDRESSHEAD="http://platform.sina.com.cn/sports_client/z_feed_list?app_key=2586208540&id=";
    //模板地址尾部
    public final static String MODEADDRESSEND="&pdps_params=%7B%22app%22%3A%7B%22timestamp%22%3A%221427356205441%22%2C%22os%22%3A%22Android%22%2C%22model%22%3A%22Lenovo+A630t%22%2C%22device_type%22%3A%224%22%2C%22osv%22%3A%224.0.4%22%2C%22name%22%3A%22cn.com.sina.sports%22%2C%22carrier%22%3A%22%E4%B8%AD%E5%9B%BD%E7%A7%BB%E5%8A%A8%22%2C%22device_id%22%3A%22862328022633608%22%2C%22make%22%3A%22Lenovo+A630t%22%2C%22channel%22%3A%22%22%2C%22connection_type%22%3A%222%22%2C%22version%22%3A30000012%2C%22ip%22%3A%22fe80%3A%3Acadd%3Ac9ff%3Afe70%3A4c0%25wlan0%22%7D%7D";
}
