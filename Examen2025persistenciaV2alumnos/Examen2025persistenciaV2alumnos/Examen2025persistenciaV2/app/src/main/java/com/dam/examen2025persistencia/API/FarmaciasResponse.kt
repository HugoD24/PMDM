package com.dam.examen2025persistencia.API

import androidx.room.Entity
import androidx.room.PrimaryKey
class FarmaciasResponse (
    val success: Boolean,
    val result: ResultData
)
data class ResultData(
    val include_total: Boolean,
    val records: List<Farmacia>,

    )
