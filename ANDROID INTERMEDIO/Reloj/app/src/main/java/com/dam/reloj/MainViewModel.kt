package com.dam.reloj

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel: ViewModel() { //esta clase vivira separada de la activity y no se destruira al rotar la pantalla
    private val _hora = MutableStateFlow("")
    private var job: Job? = null //representa la ejecucion de una corrutina, para saber si esta activa o cancelarla

/*StateFlow es un flujo de datos observable que siempre tiene un estado actual
* y emite ese estado cada vez que cambia. O sea la UI lo observa y se actualiza. Es la version moderna de LiveData*/
    val hora: StateFlow<String> = _hora


    fun iniciarReloj() {
        job?.cancel() //si ya habia una corrutina de reloj corriendo, la cancela antes de iniciar una nueva


        job = viewModelScope.launch { //lanza una corrutina ligada al ciclo de vida del viewmodel
            val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            while (isActive) { //bucle infinito mientras la corrutina no haya sido cancelada
                val millis = System.currentTimeMillis() //obtiene el tiempo actual en milisegundos
                val date = Date(millis) //lo convierte en un objeto Date
                _hora.value = sdf.format(date) //pasa de date a string y actualiza el valor del StateFlow.
                                                // al cambiar .value, StateFlow notifica a todos los observadores activos


                delay(1000) // esperar 1 segundo
            }
        }
    }


    fun detenerReloj() {
        job?.cancel()
    }
    fun startStopReloj() {
        if(job?.isActive == true){
            detenerReloj()
        }else
        {
            iniciarReloj()
        }


    }
}
