package com.eskaya.task_sanstech.presentation.match_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eskaya.mvvm_application.databinding.ListItemLigBinding
import com.eskaya.task_sanstech.data.remote.models.League


class LigListAdapter(
    private val data: List<League>,
    private val matchListener: MatchAdapterListener
) : RecyclerView.Adapter<LigListHistoryViewHolder>() {
    
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LigListHistoryViewHolder {
        val binding = ListItemLigBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LigListHistoryViewHolder(binding,matchListener, data)
    }

    override fun onBindViewHolder(holder: LigListHistoryViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}

class LigListHistoryViewHolder(
    private val binding: ListItemLigBinding,
    private val matchListener: MatchAdapterListener,
    val data: List<League>,
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var item: League

    init {
        binding.root.setOnClickListener(this)
    }

    fun bind(item: League) {
        val context = binding.root.context
        this.item = item

        Glide.with(context)
            .load(item.flag)
            .centerCrop()
            .into(binding.ivFlag)

        binding.tvLigName.text = item.leagueName
        //create child adapter
        binding.recyclerViewForMatchList.layoutManager = LinearLayoutManager(itemView.context)
        val matchAdapter = MatchListAdapter(item.matches, matchListener)
        binding.recyclerViewForMatchList.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerViewForMatchList.adapter = matchAdapter
    }

    override fun onClick(p0: View?) {
    }
}


