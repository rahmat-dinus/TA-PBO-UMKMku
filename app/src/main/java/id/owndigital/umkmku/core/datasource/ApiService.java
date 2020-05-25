package id.owndigital.umkmku.core.datasource;

import id.owndigital.umkmku.core.models.DaftarModel;
import id.owndigital.umkmku.core.models.MasukModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST(BaseApi.masuk)
    Call<MasukModel> masuk(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST(BaseApi.daftar)
    Call<DaftarModel> daftar(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("hp") String hp,
            @Field("password") String password
    );
}
