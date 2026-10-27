package com.example.big_bro
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import kotlin.math.round

data class DadosBateria(
    val porcentagem: Int,
    val correnteMa: Int,
    val tensaoV: Double,
    val temperaturaC: Float,
    val watts: Float,
    val plugado: Boolean
)
fun lerBateria(context: Context): DadosBateria{
    val gerenciador = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    val porcentagem = gerenciador.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    val correnteMa = gerenciador.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)
    val intent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    val tensaoMv = intent?.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0) ?: 0
    val temperaturaDecimos = intent?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) ?: 0
    val plugadoCodigo = intent?.getIntExtra(BatteryManager.EXTRA_PLUGGED,0) ?: 0
    val watts = (correnteMa / 1000f) * (tensaoMv / 1000f)
    val tensaoV = tensaoMv /1000.0
    return DadosBateria(
        porcentagem = porcentagem,
        correnteMa = correnteMa,
        tensaoV = tensaoV,
        temperaturaC = temperaturaDecimos / 10f,
        watts = watts,
        plugado = plugadoCodigo != 0
    )
}