data class Event (
    val titulo: String,
    val descricao: String? = null,
    val periodo: Periodo,
    val duracao: Int,
)

enum class Periodo {
    MANHA, TARDE, NOITE
}

fun main() {
    val meuEvento = Event(
        titulo = "Estudar Kotlin",
        descricao = "Comprometer-se a estudar Kotlin pelo menos 15 minutos por dia",
        periodo = Periodo.NOITE,
		duracao = 15,
	)
    println(meuEvento)
}
