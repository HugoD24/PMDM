package com.dam.reloj

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/*ViewModel es una clase diseñada para guardar y gestionar la logica y los datos de la UI
       * sin depender del ciclo de vida de la activity o fragment. Su funcion es
       * sobrevivir a los cambios de configuracion como rotacion de pantalla y mantener un estado
       * consistente.*/

/*LiveData es un contenedor de datos observable y consciente del ciclo de vida.
* Cuando el valor cambia, notifica automaticamente a los observadores (la UI), pero solo
* si estan activos. Es un canal seguro para enviar datos desde el ViewModel hasta la UI.*/

/*El ViewModel contiene y gestiona el LiveData
* La UI observa ese LiveData
* Cuando el ViewModel actualiza el LiveData, la UI se actualiza sola.*/

/*Resumen: El ViewModel mantiene los datos; LiveData avisa a la pantalla cuando esos datos cambian.*/
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Pide al sistema un viewModel asociado a esta activity*/
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.iniciarReloj() //le dice al ViewModel que empiece a actualizar la hora

        //busca el TextView del layout para mostrar la hora
        val textView: TextView = findViewById(R.id.textView)

        /*Lanza una corrutina ligada al ciclo de vida de la activity*/
        lifecycleScope.launch {
            /*Solo ejecuta el nlocque interno cuando la activity esta en estado STARTED
            * si pasa a STOPPED o DESTROYED, cancela la coleccion
            * cuando vuelve a STARTED, vuelve a lanzar el bloque*/
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.hora.collect { hora -> //collect es la funcion que escucha los valores emitidos por un FLOW y ejecuta un cloque de codigo cada vez que llega un valor nuevo
                    textView.text = hora //actualiza
                }
            }
        }


        textView.setOnClickListener {
            viewModel.startStopReloj() //toggle reloj
        }
    }
}


