package cc.anisimov.vlad.simplealbumlist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.anisimov.vlad.simplealbumlist.R
import cc.anisimov.vlad.simplealbumlist.domain.model.AlbumUI
import cc.anisimov.vlad.simplealbumlist.domain.viewmodel.AlbumListViewModel
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import kotlinx.android.synthetic.main.fragment_album_list.*

@AndroidEntryPoint
class AlbumListFragment : Fragment() {
    private val viewModel: AlbumListViewModel by viewModels()
    private lateinit var listAdapter: FlexibleAdapter<AlbumAdapterItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setTitle(R.string.albums)
        setupList()
    }

    private fun setupList() {
        rv.layoutManager = GridLayoutManager(requireContext(), 2)
        listAdapter = FlexibleAdapter<AlbumAdapterItem>(ArrayList())
        rv.adapter = listAdapter
        viewModel.oAlbumList.observe(viewLifecycleOwner) { albumList ->
            listAdapter.clear()
            val adapterItems = albumList.map { AlbumAdapterItem(it) }
            listAdapter.addItems(0, adapterItems)
        }
    }

    class AlbumAdapterItem(val album: AlbumUI) :
        AbstractFlexibleItem<AlbumAdapterItem.AlbumViewHolder>() {

        override fun equals(other: Any?): Boolean {
            if (other == null) {
                return false
            }
            if (other is AlbumAdapterItem) {
                return this.album.id == other.album.id
            }
            return false
        }

        override fun getLayoutRes(): Int {
            return R.layout.item_album
        }

        override fun createViewHolder(
            view: View,
            adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
        ): AlbumViewHolder {
            return AlbumViewHolder(view, adapter)
        }

        override fun bindViewHolder(
            adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
            holder: AlbumViewHolder,
            position: Int,
            payloads: MutableList<Any>?
        ) {
            holder.ivThumbnail1.load(album.thumbnailURLList[0])
            holder.ivThumbnail2.load(album.thumbnailURLList[1])
            holder.ivThumbnail3.load(album.thumbnailURLList[2])
            holder.ivThumbnail4.load(album.thumbnailURLList[3])
            holder.tvAlbumTitle.text = album.title
        }

        override fun hashCode(): Int {
            return album.id.hashCode()
        }

        class AlbumViewHolder(view: View, adapter: FlexibleAdapter<*>) :
            FlexibleViewHolder(view, adapter) {
            val ivThumbnail1: ImageView = view.findViewById(R.id.ivThumbnail1)
            val ivThumbnail2: ImageView = view.findViewById(R.id.ivThumbnail2)
            val ivThumbnail3: ImageView = view.findViewById(R.id.ivThumbnail3)
            val ivThumbnail4: ImageView = view.findViewById(R.id.ivThumbnail4)
            val tvAlbumTitle: TextView = view.findViewById(R.id.tvAlbumTitle)
        }
    }
}