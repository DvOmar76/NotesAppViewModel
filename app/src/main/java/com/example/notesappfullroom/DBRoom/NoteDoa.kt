package com.example.notesappfullroom.DBRoom

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDoa {
    @Query("select * from Notes  order by id ASC")
    fun getAllNote():LiveData<List<Note>>
    @Insert
    fun insertNote(note:Note)
    @Update
    fun updateNote(note: Note)
    @Delete
    fun deleteNote(note: Note)
}