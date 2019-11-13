package com.es.android.database


import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.es.android.data.Person

@Dao
interface PersonDao {

    @Query("SELECT * FROM PERSON ORDER BY ID")
    fun loadAllPersons(): List<Person>

    @Insert
    fun insertPerson(person: Person)

    @Update
    fun updatePerson(person: Person)

    @Delete
    fun delete(person: Person)

    @Query("SELECT * FROM PERSON WHERE id = :id")
    fun loadPersonById(id: Int): Person
}
