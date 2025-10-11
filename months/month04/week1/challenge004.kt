data class Evento (
    val titulo: String,
    val descricao: String? = null,
    val periodo: Periodo,
    val duracao: Int,
)

enum class Periodo {
    MANHA, TARDE, NOITE
}

val Evento.duracaoDoEvento: String
    get() = if (this.duracao < 60) {
        "Curto"
    } else {
        "Longo"
    }

fun main() {
    val meuEvento = Evento(
        titulo = "Estudar Kotlin",
        descricao = "Comprometer-se a estudar Kotlin pelo menos 15 minutos por dia",
        periodo = Periodo.NOITE,
		duracao = 15,
	)
    println(meuEvento)
    
    val evento1 = Evento(titulo = "Acordar", descricao = "Hora de Levantar", periodo = Periodo.MANHA, duracao = 0)
    val evento2 = Evento(titulo = "Tomar café da manhã", periodo = Periodo.MANHA, duracao = 15)
    val evento3 = Evento(titulo = "Aprender sobre kotlin", periodo = Periodo.TARDE, duracao = 30)
    val evento4 = Evento(titulo = "Praticar Compose", periodo = Periodo.TARDE, duracao = 60)
    val evento5 = Evento(titulo = "Assistir o último vídeo do Boudenzin", periodo = Periodo.TARDE, duracao = 10)
    val evento6 = Evento(titulo = "Checar a última biblioteca lançada do Compose", periodo = Periodo.NOITE, duracao = 45)

    val eventos = mutableListOf<Evento>(evento1, evento2, evento3, evento4, evento5, evento6)
    
    val eventosCurtos = eventos.filter {it.duracao < 60}
    val numeroDeEventosCurtos = eventosCurtos.size
    
    println("Você tem $numeroDeEventosCurtos eventos curtos")
    
    val eventosPeloPeriodo = eventos.groupBy {it.periodo}
    eventosPeloPeriodo.forEach {(periodo, eventos) ->
    	println("$periodo: ${eventos.size} eventos")
		}   
    
    println("Último evento do dia: ${eventos.last().titulo}")
    
   
    
	println("Duração do primeiro evento do dia: ${eventos[0].duracaoDoEvento}")
   
}
