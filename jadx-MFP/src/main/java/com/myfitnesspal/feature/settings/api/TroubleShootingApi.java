package com.myfitnesspal.feature.settings.api;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface TroubleShootingApi {
    @GET("/iphone_api/check_diagnostic_status")
    Observable<String> checkDiagnosticsStatus(@Query("code") String str);

    @POST("/iphone_api/diagnostic_upload")
    @Multipart
    Observable<String> uploadDiagnostics(@Part("code") RequestBody requestBody, @Part MultipartBody.Part part);
}
