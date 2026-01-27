package com.dam.corutinas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /*Un dispatcher = un gestor que asigna tus coroutines
         al tipo de hilo adecuado según la tarea.*/
        //default es para calculos
        //main actualizar la ui, manejar eventos, liveData, viewModel
        //IO Lectura/Escritura de archivos, BD, red

        suma()
        iniciarSumaParalela()
    }

    fun suma() {
        val numeros = 100000000


        println("Iniciando Hilo")
        lifecycleScope.launch(Dispatchers.Default) {
            var sum = 0L
            val startTime = System.currentTimeMillis()


            for (i in 0 until numeros) {
                sum += i
            }


            val endTime = System.currentTimeMillis()


            // Cambia al hilo principal (UI thread) para actualizar la interfaz o imprimir
            withContext(Dispatchers.Main) {
                println("Suma total: $sum")
                println("Tiempo total: ${endTime - startTime} ms")
            }
        }
        println("Fin metodo del Hilo")

    }
    private fun iniciarSumaParalela() {
        lifecycleScope.launch { //lanza una coroutine ligada al ciclo de vida, o sea esta activity


            val numeros = 100000000 //establece con cuantos numeros vamos a trabajar
            val numPartes = 4 //numero de hilos
            val rangoPorParte = numeros / numPartes //calcula cuantos numeros le tocan a cada parte


            println("Iniciando Coroutines (4 Hilos Paralelos)")
            val startTime = System.currentTimeMillis() //marca de tiempo inicial

            /*Nuevo Scope, entra en un coroutineScope que asegura que todas las coroutines hijas
              terminen antes de salir. Devuelve un resultado cuando todo termina*/
            val totalSum = coroutineScope {

                /*Crea una lista de tareas asincronas para cada indice i entre 0 y numPartes - 1,
                 crea una coroutine asincrona cuya referencia se almacena en una lista de Deferred<long>
                 (cada una devolvera una suma parcial tipo long)
                * */
                val deferredSumas: List<Deferred<Long>> = (0 until numPartes).map { i ->

                    /* Crea una coroutine con async usando Dispatchers.Default,
                    que utiliza un pool de hilos optimizado para tareas intensivas de CPU.*/
                    async(Dispatchers.Default) {
                        var sum = 0L //aqui se acumula la suma parcial de este segmento
                        val start = i * rangoPorParte //calcula el numero inicial del rango para esta parte
                        val end = start + rangoPorParte //calcula el limite del rango(exclusivo) para esta parte
                        for (j in start until end) {//cada coroutine tiene un tramo distinto, el bucle recorre cada tramo
                            sum += j //suma de todos los numeros del tramo asignado
                        }
                        // Devolvemos la suma parcial
                        return@async sum
                    }
                }


                // Esperar todos los resultados y sumarlos
                deferredSumas.awaitAll().sum()
            }


            val endTime = System.currentTimeMillis()


            // Cambiamos al Main Dispatcher para imprimir/actualizar la UI
            withContext(Dispatchers.Main) {
                println("Suma total: $totalSum")
                println("Tiempo total: ${endTime - startTime} ms")
            }
        }
    }
}