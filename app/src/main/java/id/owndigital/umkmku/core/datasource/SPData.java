package id.owndigital.umkmku.core.datasource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SPData {
    @SuppressLint("StaticFieldLeak")
    private static SPData mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private static final String spName = "UMKM";
    private static final String kUid = "uid";
    private static final String kNama = "nama";
    private static final String kEmail = "email";
    private static final String kHp = "hp";
    private static final String kFoto = "foto";
    private static final String kUser = "user";

    private SPData(Context context) {
        mContext = context;
    }

    public static synchronized SPData getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SPData(context);
        }
        return mInstance;
    }

    public void userLogin(String uid, String nama, String email, String hp, String foto) {

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(kUid, uid);
        editor.putString(kNama, nama);
        editor.putString(kEmail, email);
        editor.putString(kHp, hp);
        editor.putString(kFoto, foto);

        editor.apply();

    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(kUid, null) != null;
    }

    public void logout() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        editor.commit();
    }

    public String getUid() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(kUid, null);
    }

    public void setUid(String uid) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(kUid, uid);
        editor.apply();
    }

    public String getNama() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(kNama, null);
    }

    public void setNama(String nama) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(kNama, nama);
        editor.apply();
    }

    public String getEmail() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(kEmail, null);
    }

    public void setEmail(String email) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(kNama, email);
        editor.apply();
    }

    public String getHp() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(kHp, null);
    }

    public void setHp(String hp) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(kHp, hp);
        editor.apply();
    }

    public String getFoto() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(kFoto, null);
    }

    public void setFoto(String foto) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(kFoto, foto);
        editor.apply();
    }

    public String getUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(kUser, null);
    }

    public void setUser(String user) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(kUser, user);
        editor.apply();
    }
}
