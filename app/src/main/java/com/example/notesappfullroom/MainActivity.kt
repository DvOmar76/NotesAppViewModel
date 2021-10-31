package com.example.notesappfullroom
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notesappfullroom.databinding.MainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainBinding
    lateinit var contect:Context
    val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    var rvAdapter= RVAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contect = applicationContext

        mainViewModel.getNotes().observe(this,{
            notes->rvAdapter.update(notes)
        })
        binding.btnAddNote.setOnClickListener {
            val note = binding.edNote.text.toString()
            mainViewModel.addNote(note)
            binding.edNote.text.clear()
        }
        }



    fun alertDialog(id:Int,note:String){

        val dialogBuild= AlertDialog.Builder(this)
        val lLayout= LinearLayout(this)
        val tvAlert= TextView(this)
        val edAlert= EditText(this)
        tvAlert.text=" Update Note  "
        edAlert.setSingleLine()
        edAlert.setText(note)
        lLayout.addView(tvAlert)
        lLayout.addView(edAlert)
        lLayout.setPadding(50,40,50,10)

        dialogBuild.setNegativeButton("cancel", DialogInterface.OnClickListener { _, _ ->

        })
        dialogBuild.setPositiveButton("save",DialogInterface.OnClickListener { _, _ ->
           val noteUpdete=edAlert.text.toString()
            if (noteUpdete.isNotEmpty()){
                mainViewModel.updateNote(id, noteUpdete)
            }

        })
        val aler=dialogBuild.create()
        aler.setView(lLayout)
        aler.show()
    }


}