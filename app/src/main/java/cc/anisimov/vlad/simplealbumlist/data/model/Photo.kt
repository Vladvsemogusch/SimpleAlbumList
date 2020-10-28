package cc.anisimov.vlad.simplealbumlist.data.model

data class Photo(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)