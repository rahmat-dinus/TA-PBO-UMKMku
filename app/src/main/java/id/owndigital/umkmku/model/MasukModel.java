package id.owndigital.umkmku.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasukModel {
    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("hp")
    @Expose
    private String hp;
    @SerializedName("foto")
    @Expose
    private String foto;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
