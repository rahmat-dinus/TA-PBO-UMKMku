package id.owndigital.umkmku.core.models;

import java.util.Comparator;

public class UmkmModel {
    private String uid, namaUmkm, hpUmkm, emailUmkm, longitude, latitude, countPopuler, foto, createdAt;
    private double jarak;

    public UmkmModel(String uid, String namaUmkm, String hpUmkm, String emailUmkm, String longitude, String latitude, String countPopuler, String foto, String createdAt, double jarak) {
        this.uid = uid;
        this.namaUmkm = namaUmkm;
        this.hpUmkm = hpUmkm;
        this.emailUmkm = emailUmkm;
        this.longitude = longitude;
        this.latitude = latitude;
        this.countPopuler = countPopuler;
        this.foto = foto;
        this.createdAt = createdAt;
        this.jarak = jarak;
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

    public double getJarak() {
        return jarak;
    }

    public void setJarak(double jarak) {
        this.jarak = jarak;
    }

    public static Comparator<UmkmModel> sortLokasiAsc = new Comparator<UmkmModel>() {
        @Override
        public int compare(UmkmModel o1, UmkmModel o2) {
            return (Double.compare(o1.getJarak(), o2.getJarak()));
        }
    };

    public static Comparator<UmkmModel> sortLokasiDesc = new Comparator<UmkmModel>() {
        @Override
        public int compare(UmkmModel o1, UmkmModel o2) {
            return (Double.compare(o2.getJarak(), o1.getJarak()));
        }
    };

    public static Comparator<UmkmModel> sortPopulerAsc = new Comparator<UmkmModel>() {
        @Override
        public int compare(UmkmModel o1, UmkmModel o2) {
            int c1 = Integer.parseInt(o1.getCountPopuler());
            int c2 = Integer.parseInt(o2.getCountPopuler());
            return (Integer.compare(c2, c1));
        }
    };

    public static Comparator<UmkmModel> sortPopulerDesc = new Comparator<UmkmModel>() {
        @Override
        public int compare(UmkmModel o1, UmkmModel o2) {
            int c1 = Integer.parseInt(o1.getCountPopuler());
            int c2 = Integer.parseInt(o2.getCountPopuler());
            return (Integer.compare(c1, c2));
        }
    };

    public static Comparator<UmkmModel> sortTerbaruAsc = new Comparator<UmkmModel>() {
        @Override
        public int compare(UmkmModel o1, UmkmModel o2) {
            return o2.getCreatedAt().compareTo(o1.getCreatedAt());
        }
    };

    public static Comparator<UmkmModel> sortTerbaruDesc = new Comparator<UmkmModel>() {
        @Override
        public int compare(UmkmModel o1, UmkmModel o2) {
            return o1.getCreatedAt().compareTo(o2.getCreatedAt());
        }
    };
}
