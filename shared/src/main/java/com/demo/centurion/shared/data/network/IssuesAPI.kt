package com.demo.centurion.shared.data.network

import com.demo.centurion.shared.data.models.IssueResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Url

interface IssuesAPI {
    @GET
    @Headers(
        "Authorization:Bearer eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiZGlyIn0..KnvFON96fIgXpRgFhyyvfw.DIhGr5GitUJWG2YOzI8v8wXXYByRrPGYo_3-skLWHSWC3WctYV_P9hi2qhaD2VeIwUrpA38Kq7ElDP_LeyuV6sypeKZzrQBPu8JrPghb3pWtYA2syh-HFWffFn8AS2OG5DF5hxxu0U5vD0fd-sZYnNo63u8A2hE0KTH1OFAJVAp4CjMsZoVtanoZHl5bpyG3-EVmjD0-M_sOg_hpahD95Y9VeJPTg5L7Da15uAPNgKJLpKKoXwdON4aRjofq_4eBXu3bStrbXaxkX_2i9ELWH5zU2PioBuiOg95vgS93yjhX3TB1rfAfq3MjJ1-mPwVWn3fKtFAZjeif5yW32Ux_eQ.-ISar_Xmfwhk0DUCax_4Aw",
        "x-application-key:38567940-b045-4b37-9999-d6c3b960af9e",
        "application-type:ANDROID PHONE",
        "Accept-Language:en_US",
        "key-state:VALID",
        "x-type-mdm:ENABLE",
        "client-version: 22.6.0",
        "Content-Type:application/json"
    )
    suspend fun getIssues(@Url url: String): Response<IssueResponse>
}