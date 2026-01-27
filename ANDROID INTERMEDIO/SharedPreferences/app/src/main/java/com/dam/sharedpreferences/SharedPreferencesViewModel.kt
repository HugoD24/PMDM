package com.dam.sharedpreferences

import android.R
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
/*Persistencia de datos, se almacenan como pares clave-valor*/
class SharedPreferencesViewModel : ViewModel() { //El ViewModel será el que lea/escriba datos persistentes.
    val Context.dataStore by preferencesDataStore(name = "settings")//crea un DataStore llamado settings

    companion object { //declaramos claves
        val NOMBRE_KEY = stringPreferencesKey("nombre")
        val EDAD_KEY = intPreferencesKey("edad")
        val HORA_KEY = stringPreferencesKey("hora")
    }

    fun getNombre(context: Context): Flow<String> {


        return context.dataStore.data.map { preferences ->
            preferences[NOMBRE_KEY] ?: "Sin nombre" //devuelve un flow que la UI recoge con collect
        }
    }

    fun setNombre(context: Context, nombre: String) {
        /*Edit abre la transaccion, luego escribe y todo se hace dentro de una corrutina para evitar bloqueos*/
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[NOMBRE_KEY] = nombre
            }
        }
    }


}