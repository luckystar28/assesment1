package org.d3if3067.assesment1fix.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Laundry(
    val id: String,
    val nama: String,
    val berat: String,
    val harga: String,
    val image: String,
    val auth: String
)