package org.d3if3067.assesment1fix.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "laundry")
data class Laundry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nama: String,
    val berat: String,
    val hari: String,
    var selectedOption:String=""
)