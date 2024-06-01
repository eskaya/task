package com.eskaya.task_sanstech.presentation.match_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eskaya.mvvm_application.R
import com.eskaya.mvvm_application.databinding.ListItemMatchRowBinding
import com.eskaya.task_sanstech.data.AppPreferences
import com.eskaya.task_sanstech.data.remote.models.response.Data


class MatchListAdapter(
    val data: List<Data>,
    private val listener: MatchAdapterListener
) : RecyclerView.Adapter<MatchListHistoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchListHistoryViewHolder {
        val binding = ListItemMatchRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MatchListHistoryViewHolder(binding, listener, data)
    }

    override fun onBindViewHolder(holder: MatchListHistoryViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}

class MatchListHistoryViewHolder(
    private val binding: ListItemMatchRowBinding,
    private val listener: MatchAdapterListener,
    val data: List<Data>,
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var item: Data
    private val context = binding.root.context

    init {
        binding.root.setOnClickListener(this)
        binding.ivStar.setOnClickListener {
            listener.onStarClick(item.i)
        }

    }

    fun bind(item: Data) {
        this.item = item
        binding.tvScore.text = item.sc.abbr
        //ht --> ev sahibi, n --> adı
        binding.tvHomeTeam.text = item.ht.n

        //at --> deplasman, n --> adı
        binding.tvAwayTeam.text = item.at.n
        binding.tvScoreCount.text =
            item.sc.ht.r.toString() + " " + "-" + " " + item.sc.at.r.toString()
        setFavoriteIcon()
    }

    override fun onClick(v: View?) {
        listener.onClickedItem(item.ht.n) //i --> ma.ın benzersiz kimliği
    }

    private fun setFavoriteIcon() {
        if (AppPreferences.getInstance(context).isFavorite(item.i)) {
            Glide.with(context)
                .load(R.drawable.ic_star)
                .centerCrop()
                .into(binding.ivStar)
        } else {
            Glide.with(context)
                .load(R.drawable.ic_star_empty)
                .centerCrop()
                .into(binding.ivStar)
        }
    }

}

interface MatchAdapterListener {
    fun onClickedItem(homeName: String)
    fun onStarClick(match: Int)
}
