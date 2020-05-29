package id.owndigital.umkmku.core;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

//import com.android.volley.toolbox.ImageLoader;


public class AppController extends Application {

    private static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
//    private ImageLoader mImageLoader;
//    LruBitmapCache mLruBitmapCache;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

//    public void clearApplicationData() {
//        File cacheDirectory = getCacheDir();
//        File applicationDirectory = new File(cacheDirectory.getParent());
//        if (applicationDirectory.exists()) {
//            String[] fileNames = applicationDirectory.list();
//            for (String fileName : fileNames) {
//                if (!fileName.equals("lib")) {
//                    deleteFile(new File(applicationDirectory, fileName));
//                }
//            }
//        }
//    }

//    public static boolean deleteFile(File file) {
//        boolean deletedAll = true;
//        if (file != null) {
//            if (file.isDirectory()) {
//                String[] children = file.list();
//                for (int i = 0; i < children.length; i++) {
//                    deletedAll = deleteFile(new File(file, children[i])) && deletedAll;
//                }
//            } else {
//                deletedAll = file.delete();
//            }
//        }
//
//        return deletedAll;
//    }


    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

//    public ImageLoader getImageLoader() {
//        getRequestQueue();
//        if (mImageLoader == null) {
//            getLruBitmapCache();
//            mImageLoader = new ImageLoader(this.mRequestQueue, mLruBitmapCache);
//        }
//
//        return this.mImageLoader;
//    }

//    public LruBitmapCache getLruBitmapCache() {
//        if (mLruBitmapCache == null)
//            mLruBitmapCache = new LruBitmapCache();
//        return this.mLruBitmapCache;
//    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

//    public <T> void addToRequestQueue(Request<T> req) {
//        req.setTag(TAG);
//        getRequestQueue().add(req);
//    }
//
//    public void cancelPendingRequests(Object tag) {
//        if (mRequestQueue != null) {
//            mRequestQueue.cancelAll(tag);
//        }
//    }

}
