package id.owndigital.umkmku.model;

public class DetailUmkmModel {
    private String countPopuler, uid, namaUmkm, hpUmkm, emailUmkm, namaPemilik, hpPemilik,
            emailPemilik, longitude, latitude, foto, createdAt, updatedAt;
    private double jarak;

    public DetailUmkmModel(String countPopuler, String uid, String namaUmkm, String hpUmkm,
                           String emailUmkm, String namaPemilik, String hpPemilik, String emailPemilik,
                           String longitude, String latitude, String foto, String createdAt,
                           String updatedAt, double jarak) {
        this.countPopuler = countPopuler;
        this.uid = uid;
        this.namaUmkm = namaUmkm;
        this.hpUmkm = hpUmkm;
        this.emailUmkm = emailUmkm;
        this.namaPemilik = namaPemilik;
        this.hpPemilik = hpPemilik;
        this.emailPemilik = emailPemilik;
        this.longitude = longitude;
        this.latitude = latitude;
        this.foto = foto;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.jarak = jarak;
    }

    public String getCountPopuler() {
        return countPopuler;
    }

    public void setCountPopuler(String countPopuler) {
        this.countPopuler = countPopuler;
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

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getHpPemilik() {
        return hpPemilik;
    }

    public void setHpPemilik(String hpPemilik) {
        this.hpPemilik = hpPemilik;
    }

    public String getEmailPemilik() {
        return emailPemilik;
    }

    public void setEmailPemilik(String emailPemilik) {
        this.emailPemilik = emailPemilik;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getJarak() {
        return jarak;
    }

    public void setJarak(double jarak) {
        this.jarak = jarak;
    }
}
