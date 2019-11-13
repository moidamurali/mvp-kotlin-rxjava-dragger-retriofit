package com.es.android.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "person")
class Person {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String
    var email: String
    var number: String
    var pincode: String
    var city: String

    @Ignore
    constructor(name: String, email: String, number: String, pincode: String, city: String) {
        this.name = name
        this.email = email
        this.number = number
        this.pincode = pincode
        this.city = city
    }

    constructor(id: Int, name: String, email: String, number: String, pincode: String, city: String) {
        this.id = id
        this.name = name
        this.email = email
        this.number = number
        this.pincode = pincode
        this.city = city
    }
}
