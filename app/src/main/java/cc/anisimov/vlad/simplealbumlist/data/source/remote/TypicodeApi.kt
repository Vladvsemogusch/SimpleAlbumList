package cc.anisimov.vlad.simplealbumlist.data.source.remote

import cc.anisimov.vlad.simplealbumlist.data.model.Album
import cc.anisimov.vlad.simplealbumlist.data.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TypicodeApi {

    @GET("albums")
    fun getAlbumsByUserId(@Query("userId") userId: Int): Response<List<Album>>

    @GET("photos")
    fun getPhotosByAlbumId(@Query("albumId") albumId: Int): Response<List<Photo>>
}