package org.d3if3067.assesment1fix.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3067.assesment1fix.database.LaundryDao
import org.d3if3067.assesment1fix.model.Laundry

class DetailViewModel(private val dao: LaundryDao) : ViewModel() {

    fun insert(nama: String, berat: String, hari: String) {
        val laundry = Laundry(
            nama = nama,
            berat = berat,
            hari = hari,
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(laundry)
        }
    }
    suspend fun getLaundry(id: Long): Laundry? {
        return dao.getLaundryById(id)
    }
    fun update(id: Long, nama: String, berat: String, hari: String) {
        val laundry = Laundry(
            id = id,
            nama = nama,
            berat = berat,
            hari = hari
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(laundry)
        }
    }
    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}