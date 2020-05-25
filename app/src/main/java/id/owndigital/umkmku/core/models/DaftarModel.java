package id.owndigital.umkmku.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DaftarModel {
    @SerializedName("success")
    @Expose
    private int success;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
