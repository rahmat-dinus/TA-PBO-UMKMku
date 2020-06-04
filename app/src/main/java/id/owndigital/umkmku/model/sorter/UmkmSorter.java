package id.owndigital.umkmku.model.sorter;

import java.util.ArrayList;
import java.util.Collections;

import id.owndigital.umkmku.model.UmkmModel;

public class UmkmSorter {
    private ArrayList<UmkmModel> umkmModels;

    public UmkmSorter(ArrayList<UmkmModel> umkmModels) {
        this.umkmModels = umkmModels;
    }

    public void getSortedByLokasiAsc() {
        Collections.sort(umkmModels, UmkmModel.sortLokasiAsc);
    }

    public void getSortedByLokasiDesc() {
        Collections.sort(umkmModels, UmkmModel.sortLokasiDesc);
    }

    public void getSortedByPopulerAsc() {
        Collections.sort(umkmModels, UmkmModel.sortPopulerAsc);
    }

    public void getSortedByPopulerDesc() {
        Collections.sort(umkmModels, UmkmModel.sortPopulerDesc);
    }

    public void getSortedByTerbaruAsc() {
        Collections.sort(umkmModels, UmkmModel.sortTerbaruAsc);
    }

    public void getSortedByTerbaruDesc() {
        Collections.sort(umkmModels, UmkmModel.sortTerbaruDesc);
    }
}
