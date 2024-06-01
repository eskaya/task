package com.eskaya.task_sanstech.presentation.match_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eskaya.mvvm_application.R
import com.eskaya.mvvm_application.databinding.ListItemMatchRowBinding
import com.eskaya.task_sanstech.data.AppPreferences
import com.eskaya.task_sanstech.domain.model.Match


class MatchListAdapter(
    val data: List<Match>,
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
    val data: List<Match>,
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var item: Match
    private val context = binding.root.context

    init {
        binding.root.setOnClickListener(this)
        binding.ivStar.setOnClickListener {
            item.id?.let { it1 -> listener.onStarClick(it1) }
            //listener.onStarClick(1)
        }

    }

    fun bind(item: Match) {
        this.item = item
       binding.tvScore.text = item.scorInfos?.abbr
        //ht --> ev sahibi, n --> adı
        binding.tvHomeTeam.text = item.homeTeam?.n

        //at --> deplasman, n --> adı
        binding.tvAwayTeam.text = item.awayTeam?.n
        binding.tvScoreCount.text =
            item.scorInfos?.ht?.r.toString() + " " + "-" + " " + item.scorInfos?.at?.r.toString()
        setFavoriteIcon()
    }

    override fun onClick(v: View?) {
        item.homeTeam?.let { listener.onClickedItem(it.n) } //i --> ma.ın benzersiz kimliği
    }

    private fun setFavoriteIcon() {
        if (item.id?.let { AppPreferences.getInstance(context).isFavorite(it) } == true) {
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
