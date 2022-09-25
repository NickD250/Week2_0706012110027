package adapter

import Interface.CardListener
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week2_0706012110027.R
import com.example.week2_0706012110027.databinding.ActivityAnimalCardBinding
import Model.Animal
import Database.Array
import Model.Ayam
import Model.Kambing
import Model.Sapi
import android.net.Uri
import android.widget.Toast


class RVAdapter (val ListDataAnimal: ArrayList<Animal>, val cardlistener: CardListener):
    RecyclerView.Adapter<RVAdapter.viewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_animal_card, parent, false)
        return viewHolder(view, cardlistener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(ListDataAnimal[position])
    }

    override fun getItemCount(): Int {
        return ListDataAnimal.size
    }

    class viewHolder(val itemView: View, val cardlistener: CardListener): RecyclerView.ViewHolder(itemView) {

        val viewBinding = ActivityAnimalCardBinding.bind(itemView)

        fun setData(data: Animal){
            viewBinding.CardNamaHewan.text = data.NamaHewan

            if (data is Sapi) {
                viewBinding.AnimalType.text = "Sapi"
            } else if (data is Ayam) {
                viewBinding.AnimalType.text = "Pitik"
            } else {
                viewBinding.AnimalType.text = "Wedhus"
            }

            viewBinding.CardUmurHewan.text = data.UmurHewan.toString() + " Tahun"

            if (data.imageUri.isNotEmpty()) {
                viewBinding.CardPicture.setImageURI(Uri.parse(data.imageUri))
            }

            viewBinding.CardButtonEdit.setOnClickListener {
                cardlistener.OnEditClick(adapterPosition)
            }

            viewBinding.CardButtonDelete.setOnClickListener {
                cardlistener.OnDeleteClick(adapterPosition)
            }

            viewBinding.Sound.setOnClickListener {
                Toast.makeText(itemView.context, data.interaction(), Toast.LENGTH_SHORT).show()
            }

            viewBinding.Food.setOnClickListener {
                Toast.makeText(itemView.context, data.feed(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}