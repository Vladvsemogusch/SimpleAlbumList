package cc.anisimov.vlad.simplealbumlist.data.repository

import cc.anisimov.vlad.simplealbumlist.data.model.RequestResult
import cc.anisimov.vlad.simplealbumlist.data.source.remote.TypicodeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepo @Inject constructor(
    private val remoteApi: TypicodeApi
) {

    suspend fun getAlbumThumbnailData(albumId: Int): RequestResult<List<String>> {
        val result =
            kotlin.runCatching { remoteApi.getPhotosByAlbumId(albumId,4) }
        if (result.isFailure) {
            return RequestResult.Error(result.exceptionOrNull())
        }
        val urlList = result.getOrNull()!!.body()!!.map { it.thumbnailUrl }
        return RequestResult.Success(urlList)
    }
}