package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class myadapter(val context: Context, val array: List<Article>) : RecyclerView.Adapter<myadapter.myviewholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
      val it = LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        return myviewholder(it)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val cur = array[position]
         holder.Hheadin.text = cur.title
         holder.Hdescrip.text = cur.description
         holder.Hname.text = cur.source.name
         holder.Hdate.text = cur.publishedAt
         holder.Hcontent.text = cur.content
        Picasso.get().load(cur.urlToImage).into(holder.Hphoto)
        holder.webbtn.setOnClickListener {
            var weblink = cur.url
            if(weblink.isNotEmpty()){
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(weblink)
                holder.itemview.context.startActivity(intent)
            }
        }
    }
    class myviewholder(val itemview: View):RecyclerView.ViewHolder(itemview) {
        var Hheadin = itemview.findViewById<TextView>(R.id.heading)
        var Hdescrip = itemview.findViewById<TextView>(R.id.description)
        var Hname = itemview.findViewById<TextView>(R.id.personname)
        var Hdate = itemview.findViewById<TextView>(R.id.release_date)
        var Hphoto = itemview.findViewById<ImageView>(R.id.The_picture)
        var Hcontent = itemview.findViewById<TextView>(R.id.content_news)
        var webbtn = itemview.findViewById<TextView>(R.id.check)
    }

}