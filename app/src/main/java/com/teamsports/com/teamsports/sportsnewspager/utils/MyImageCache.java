package com.teamsports.com.teamsports.sportsnewspager.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class MyImageCache implements ImageCache{

	private LruCache<String, Bitmap> lruCache = null;
	private static MyImageCache imageCache = null;

	private MyImageCache() {
		int memoryCount = (int) Runtime.getRuntime().maxMemory();
		lruCache = new LruCache<String, Bitmap>(memoryCount / 8) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}

			@Override
			protected void entryRemoved(boolean evicted, String key,
					Bitmap oldValue, Bitmap newValue) {
				super.entryRemoved(evicted, key, oldValue, newValue);
			}
		};
	}

	public static MyImageCache getInstance() {
		if (imageCache == null) {
			imageCache = new MyImageCache();
		}
		return imageCache;
	}

	@Override
	public Bitmap getBitmap(String url) {

		return lruCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		lruCache.put(url, bitmap);
	}


}
