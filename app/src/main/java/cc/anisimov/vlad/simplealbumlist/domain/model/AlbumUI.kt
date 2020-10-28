package cc.anisimov.vlad.simplealbumlist.domain.model

data class AlbumUI(
    val id: Int,
    val title: String,
    val thumbnailURLList: List<String>
)