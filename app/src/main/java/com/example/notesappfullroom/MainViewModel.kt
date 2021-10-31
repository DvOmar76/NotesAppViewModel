package com.example.notesappfullroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesappfullroom.DBRoom.Note
import com.example.notesappfullroom.DBRoom.NoteDatabase
import com.example.notesappfullroom.DBRoom.NoteDoa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application):AndroidViewModel(application) {
    private val notes: LiveData<List<Note>>
     var dbRoom:NoteDoa
    init {
        dbRoom=NoteDatabase.getInstance(application).NoteDoa()
        notes =dbRoom.getAllNote()
    }
    fun getNotes():LiveData<List<Note>>{
        return notes
    }
    fun addNote(note:String){
        CoroutineScope(Dispatchers.IO).launch {
            dbRoom.insertNote(Note(0,note))
        }
    }
    fun updateNote(id:Int,note:String){
        CoroutineScope(Dispatchers.IO).launch {
            dbRoom.updateNote(Note(id,note))
        }
    }
    fun deleteNote(id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            dbRoom.deleteNote(Note(id))
        }
    }

}