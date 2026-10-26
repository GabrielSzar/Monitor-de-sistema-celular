package com.example.big_bro
import android.R
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager

data class DadosBateria(
    val porcentagem: Int,
    val correnteMa: Int,
    val tensaoMv: Int,
    val temperaturaC: Float,
    val watts: Float,
    val plugado: Boolean
)
fun lerBateria(context: Context): DadosBateria{
    val gerenciador = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    val porcentagem = gerenciador.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    val correnteUa = gerenciador.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)
    val intent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    val tensaoMv = intent?.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0) ?: 0
    val temperaturaDecimos = intent?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) ?: 0
    val plugadoCodigo = intent?.getIntExtra(BatteryManager.EXTRA_PLUGGED,0) ?: 0
    val correnteMa = correnteUa / 1000
    val watts = (correnteUa / 1_000_000f) * (tensaoMv / 1000f)
    return DadosBateria(
        porcentagem = porcentagem,
        correnteMa = correnteMa,
        tensaoMv = tensaoMv,
        temperaturaC = temperaturaDecimos / 10f,
        watts = watts,
        plugado = plugadoCodigo != 0
    )
}