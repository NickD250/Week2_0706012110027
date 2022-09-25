package com.example.week2_0706012110027

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.week2_0706012110027.databinding.ActivityInputBinding
import java.lang.NumberFormatException
import Database.Array
import Model.Animal
import Model.Ayam
import Model.Sapi

class Input : AppCompatActivity() {

    private lateinit var viewBinding: ActivityInputBinding
    private lateinit var imageArray: ByteArray
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        GetIntent()
        SetupListener()
    }

    private fun GetIntent(){
        if (intent.getIntExtra("status", 0) == Array.StatusAdd){
            viewBinding.TittleEdit.visibility = View.INVISIBLE
        }else if (intent.getIntExtra("status", 0) == Array.StatusEdit){
            position = intent.getIntExtra("position", -1)
            viewBinding.TittleTambah.visibility = View.INVISIBLE
            viewBinding.InputNama.editText?.setText(Array.ListDataAnimal[position].NamaHewan)
            viewBinding.InputUmur.editText?.setText(Array.ListDataAnimal[position].UmurHewan.toString())
        }
    }

    private fun SetupListener(){
        viewBinding.Photo.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_PICK)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBinding.BackButton.setOnClickListener {
            finish()
        }
        viewBinding.InputButton.setOnClickListener{
            try{

                val animal = Animal(
                    viewBinding.InputNama.editText?.text.toString().trim(),
                    viewBinding.InputUmur.editText?.text.toString().trim().toInt(),
                    viewBinding.Photo.toString().trim(),

                )

                if (FormChecker(animal)){
                    if (intent.getIntExtra("status", 0) == Database.Array.StatusAdd){
                        Database.Array.ListDataAnimal.add(animal)
                    } else if (intent.getIntExtra("status", 0) == Database.Array.StatusEdit){
                        Database.Array.ListDataAnimal[position] = animal
                    }
                    Toast.makeText(baseContext, "Data berhasil di simpan", Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(baseContext, "Data gagal di simpan", Toast.LENGTH_LONG).show()
                }
            }catch (e: NumberFormatException){
                viewBinding.InputUmur.error = "Umur hewan belum terisi"
            }catch (e: UninitializedPropertyAccessException){
                if (intent.getIntExtra("status", 0) == Array.StatusEdit){

                    val animal = Animal(
                        viewBinding.InputNama.editText?.text.toString().trim(),
                        viewBinding.InputUmur.editText?.text.toString().trim().toInt(),
                        viewBinding.Photo.toString().trim(),
                    )

                    if (FormChecker(animal)){
                        Array.ListDataAnimal[position] = animal
                        Toast.makeText(baseContext, "Data berhasil di simpan", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(baseContext, "Data gagal di simpan", Toast.LENGTH_LONG).show()
                    }
                }
                Toast.makeText(baseContext, "Foto Hewan belum di pilih", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun FormChecker(animal: Animal): Boolean {

        var isCompleted = true

        if(animal.NamaHewan.isEmpty()){
            viewBinding.InputNama.error = "Nama hewan belum terisi"
            isCompleted = false
        }else{
            viewBinding.InputNama.error = ""
        }

        if(animal.UmurHewan == 0){
            viewBinding.InputUmur.error = "Umur hewan belum terisi"
            isCompleted = false
        }else{
            viewBinding.InputUmur.error = ""
        }

        return isCompleted
    }

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data                 // GET PATH TO IMAGE FROM GALLEY
            viewBinding.Photo.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
            if (uri != null) {
                val inputStream = contentResolver.openInputStream(uri)
                inputStream?.buffered()?.use {
                    imageArray = it.readBytes()
                }
            }
        }
    }
    }
