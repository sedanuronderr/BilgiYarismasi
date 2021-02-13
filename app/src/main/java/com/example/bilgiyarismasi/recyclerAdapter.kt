package com.example.bilgiyarismasi


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview.view.*
import kotlinx.android.synthetic.main.toast_layout.view.*
import org.w3c.dom.Text

class recyclerAdapter(val examplelist:ArrayList<ExampleList>): RecyclerView.Adapter<recyclerAdapter.dogrusayisi>() {

    class dogrusayisi(itemView :View):RecyclerView.ViewHolder(itemView) {


        val itemTitle:TextView=itemView.findViewById(R.id.user)
        val itemDetails :TextView =itemView.findViewById(R.id.puan)
        val itemPicture :TextView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dogrusayisi {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false)

        return dogrusayisi(itemView)
    }

    override fun onBindViewHolder(holder: dogrusayisi, position: Int) {

        val cuurentItem =examplelist[position]
       holder.itemDetails.text =cuurentItem.puann
        holder.itemTitle.text = cuurentItem.userr


        when(position){

            0-> {
                holder.itemPicture.setBackgroundResource(R.drawable.altin)
            }
            1-> {
                holder.itemPicture.setBackgroundResource(R.drawable.gumus)
            }
           2->{ holder.itemPicture.setBackgroundResource(R.drawable.bronz)}
            else->{
                holder.itemPicture.text =cuurentItem.sayii
            }
        }

        /*if (position == 0){
            holder.itemPicture.setBackgroundResource(R.drawable.altin)

        }
        if(position == 1){
            holder.itemPicture.setBackgroundResource(R.drawable.gumus)
        }
        if(position==2){
            holder.itemPicture.setBackgroundResource(R.drawable.bronz)
        }*/
    }

    override fun getItemCount(): Int {
          return examplelist.size
    }
}