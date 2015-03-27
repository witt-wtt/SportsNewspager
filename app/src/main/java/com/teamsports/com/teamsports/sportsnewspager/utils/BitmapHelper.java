package com.teamsports.com.teamsports.sportsnewspager.utils;

import android.content.Context;
import android.os.Environment;

import com.lidroid.xutils.BitmapUtils;
import com.teamsports.sportsnewspager.sportsnewspager.R;

import java.io.File;

/**
 * Created by Administrator on 2015/3/24.
 * 图片缓存类要用单例，防止内存溢出
 */
public class BitmapHelper {
    private static BitmapUtils utils;
    public static void initUtils(Context context){
        //创建目录存储项目图片
        utils =new BitmapUtils(context,
                new File(Environment.getExternalStorageDirectory(),"sinaImage").getAbsolutePath(),0.25f);
        //下载显示图片
        utils.configDefaultLoadingImage(R.mipmap.ic_launcher);
        utils.configDefaultLoadFailedImage(R.mipmap.ic_launcher);
        utils.configDefaultBitmapMaxSize(100,100);
        //磁盘缓存过期时间
        utils.configDefaultCacheExpiry(1000*60*60*24);
        //连接超时
        utils.configDefaultConnectTimeout(5000);
        //读取超时
        utils.configDefaultReadTimeout(10000);
    }
    public static BitmapUtils getUtils(){
        return utils;
    }
}
