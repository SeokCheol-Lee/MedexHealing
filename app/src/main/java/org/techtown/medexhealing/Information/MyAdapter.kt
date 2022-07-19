package org.techtown.medexhealing.Information

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techtown.medexhealing.R


class MyAdapter(val context: Context, val contentList: List<MyDataItem>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var content: TextView

        init {
            content = itemView.findViewById<TextView>(R.id.contentTxt)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.content.text = contentList[position].content.toString()
    }

    override fun getItemCount(): Int {
        return contentList.size
    }
}