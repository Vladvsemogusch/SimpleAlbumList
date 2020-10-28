package cc.anisimov.vlad.simplealbumlist.data.source.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface TypicodeApi {

    @GET("albums")
    fun getAlbumsByUserId(@Query("userId") userId: Int)

    @GET("photos")
    fun getPhotosByAlbumId(@Query("albumId") albumId: Int)
}