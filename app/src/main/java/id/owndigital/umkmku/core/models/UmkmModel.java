package id.owndigital.umkmku.core.models;

public class UmkmModel {
    private String uid, namaUmkm, hpUmkm, emailUmkm, longitude, latitude, countPopuler, foto, createdAt;

    public UmkmModel(String uid, String namaUmkm, String hpUmkm, String emailUmkm, String longitude, String latitude, String countPopuler, String foto, String createdAt) {
        this.uid = uid;
        this.namaUmkm = namaUmkm;
        this.hpUmkm = hpUmkm;
        this.emailUmkm = emailUmkm;
        this.longitude = longitude;
        this.latitude = latitude;
        this.countPopuler = countPopuler;
        this.foto = foto;
        this.createdAt = createdAt;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNamaUmkm() {
        return namaUmkm;
    }

    public void setNamaUmkm(String namaUmkm) {
        this.namaUmkm = namaUmkm;
    }

    public String getHpUmkm() {
        return hpUmkm;
    }

    public void setHpUmkm(String hpUmkm) {
        this.hpUmkm = hpUmkm;
    }

    public String getEmailUmkm() {
        return emailUmkm;
    }

    public void setEmailUmkm(String emailUmkm) {
        this.emailUmkm = emailUmkm;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCountPopuler() {
        return countPopuler;
    }

    public void setCountPopuler(String countPopuler) {
        this.countPopuler = countPopuler;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
