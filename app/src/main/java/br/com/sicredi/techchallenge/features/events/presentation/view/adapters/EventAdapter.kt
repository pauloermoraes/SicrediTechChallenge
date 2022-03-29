package br.com.sicredi.techchallenge.features.events.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.sicredi.techchallenge.R
import br.com.sicredi.techchallenge.databinding.AdapterEventsItemBinding
import br.com.sicredi.techchallenge.features.events.domain.mappers.DoublePriceToStringFormattedPriceMapper
import br.com.sicredi.techchallenge.features.events.domain.mappers.LongDateToStringFormattedDateMapper
import br.com.sicredi.techchallenge.features.events.domain.models.Events
import com.bumptech.glide.Glide

class EventAdapter(
    private val events: Events,
    private val onClickInfo: (eventId: String) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        return EventHolder(
            AdapterEventsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        val event = events[position]
        with(holder.adapterEventsItemBinding) {
            tvEventTitle.text = event.title
            tvEventDate.setText(LongDateToStringFormattedDateMapper().map(event.date))
            tvEventPrice.text = DoublePriceToStringFormattedPriceMapper().map(event.price)
            Glide.with(holder.itemView.context)
                .load(event.image)
                .centerCrop()
                .placeholder(R.drawable.ic_image_placeholder)
                .into(ivEventImageBanner)
        }
        holder.adapterEventsItemBinding.root.setOnClickListener { onClickInfo(event.id) }
        holder.adapterEventsItemBinding.btnEventInfo.setOnClickListener { onClickInfo(event.id) }
    }

    override fun getItemCount(): Int = events.size

    class EventHolder(
        val adapterEventsItemBinding: AdapterEventsItemBinding
    ) : RecyclerView.ViewHolder(adapterEventsItemBinding.root)
}