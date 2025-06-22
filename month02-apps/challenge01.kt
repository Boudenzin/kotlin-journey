interface Ligavel {
    val tipo: String
    
    open fun ligar()
    
    open fun desligar()
    
    open fun status() {
        println("Dispositivo do tipo $tipo está em estado desconhecido")
    }
}

interface Atualizavel

class SmartDevice(name: String, category: String): Ligave {
    val deviceStatus: String = "offline"
    
    constructor(statusCode: Int): this(name, category) {
        deviceStatus = when(statusCode) {
            0 -> "offline"
            1 -> "online"
            else -> unknown
            
        }
    }
}

class SmartTvDevice(name: String, category: String): SmartDevice(name: String, category: String) {
    override fun status () {
        println("A tv está em estado ativo")
    }
    fun abrirYoutube() {
        println("Youtube aberto!")
    }
}

