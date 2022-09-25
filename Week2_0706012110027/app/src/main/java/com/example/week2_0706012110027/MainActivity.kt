package com.example.week2_0706012110027

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2_0706012110027.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import Interface.CardListener
import Model.Animal
import adapter.RVAdapter
import android.provider.ContactsContract.Data
import com.example.week2_0706012110027.databinding.ActivityAnimalCardBinding

class MainActivity : AppCompatActivity(), CardListener {

    private lateinit var viewBind: ActivityMainBinding
    private val RVAdapter = RVAdapter (Database.Array.ListDataAnimal, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        SetupRecyclerView()
        SetupListener()
    }

    override fun onResume() {
        super.onResume()
        if (Database.Array.ListDataAnimal.size > 0){
            viewBind.MainRecyclerView.visibility = View.VISIBLE
            viewBind.MainNoData.visibility = View.GONE
        }else{
            viewBind.MainRecyclerView.visibility = View.GONE
            viewBind.MainNoData.visibility = View.VISIBLE
        }
        RVAdapter.notifyDataSetChanged()
    }

    private fun SetupRecyclerView(){
        val layoutManager = LinearLayoutManager(baseContext)
        viewBind.MainRecyclerView.layoutManager = layoutManager
        viewBind.MainRecyclerView.adapter = RVAdapter
    }

    private fun SetupListener(){
        viewBind.MainFAB.setOnClickListener{
            val intent = Intent(baseContext, Input::class.java).apply {
                putExtra("status", Database.Array.StatusAdd)
            }
            startActivity(intent)
        }
    }

    override fun OnEditClick(position: Int) {
        val intent = Intent(baseContext, Input::class.java).apply {
            putExtra("status", Database.Array.StatusEdit)
            putExtra("position", position)
        }
        startActivity(intent)
    }

  override fun OnDeleteClick(position: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Hapus Hewan")
            .setMessage("Apakah anda ingin menghapus hewan ini?")
            .setNegativeButton("Batal") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("Setuju") { dialog, which ->
                Database.Array.ListDataAnimal.removeAt(position)
                Toast.makeText(baseContext, "Data berhasil di hapus", Toast.LENGTH_LONG).show()
                onResume()
            }
            .show()
    }
}