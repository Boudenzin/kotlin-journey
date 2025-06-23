interface Ligavel {
    var tipo: String
    
    fun ligar()
    
    fun desligar()
    
    fun status() {
        println("Dispositivo do tipo $tipo está em estado desconhecido")
    }
}

interface Atualizavel {
    
    fun atualizarFirmware() {
        println("Firmware atualizado com sucesso.")
    }
    
    fun status() {
        println("Status de atualização desconhecido.")
    }
}

open class SmartDevice(name: String, category: String): Ligavel {
    
    var name = name
    
    override var tipo: String = category
    
    override fun ligar() {
    	deviceStatus = "online"
    }

    override fun desligar() {
        deviceStatus = "offline"
    }
    
    var deviceStatus: String = "offline"
    
    constructor(name: String, category: String, statusCode: Int): this(name, category) {
        deviceStatus = when(statusCode) {
            0 -> "offline"
            1 -> "online"
            else -> "unknown"
            
        }
    }
    
    override fun status() {
        println("Dispositivo: '$name' ($tipo). Status: $deviceStatus")
    }
    
}

class CentralDeControle() {
    
    private val dispositivos = mutableListOf<Ligavel>()
    
    fun adicionarDispositivo(d: Ligavel) {
        println("Dispositivo adicionado")
        dispositivos.add(d)
    }
    fun ligarTodos() {
        println("Todos os dispositivos foram ligados")
        for (dispositivo in dispositivos) {
            dispositivo.ligar()
        }
    }
    fun desligarTodos() {
        println("Todos os dispositivos foram desligados")
        for (dispositivo in dispositivos) {
            dispositivo.desligar()
        }
    }
    fun mostrarStatus() {
        for (dispositivo in dispositivos) {
            dispositivo.status()
        }
    }
}

class SmartTvDevice(name: String, category: String): SmartDevice(name, category), Atualizavel {
    override fun status () {
        super<SmartDevice>.status()
        super<Atualizavel>.status()
        println("Status completo da Smart TV.")
    }
    fun abrirYoutube() {
        println("Youtube aberto!")
    }
}

fun main() {
    val smartTv = SmartTvDevice("TCL Q655", "Android TV")
    val central = CentralDeControle()
    
    central.adicionarDispositivo(smartTv)
    central.ligarTodos()
    central.mostrarStatus()
    central.desligarTodos()
}
