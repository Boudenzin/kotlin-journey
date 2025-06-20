# O `when` em Kotlin: Dominando o Controle de Fluxo

O `when` em Kotlin é uma estrutura de controle de fluxo poderosa e flexível, que pode ser vista como uma evolução do `switch` de outras linguagens. Ele permite avaliar uma expressão e executar um bloco de código correspondente a um determinado valor, tipo ou condição.

## Sintaxe Básica

A sintaxe do `when` pode ser usada como uma declaração ou como uma expressão (retornando um valor).

```kotlin
fun main() {
    val diaDaSemana = 3

    // Usando 'when' como uma declaração
    when (diaDaSemana) {
        1 -> println("Domingo")
        2 -> println("Segunda-feira")
        3 -> println("Terça-feira")
        4 -> println("Quarta-feira")
        5 -> println("Quinta-feira")
        6 -> println("Sexta-feira")
        7 -> println("Sábado")
        else -> println("Dia inválido")
    }

    // Usando 'when' como uma expressão
    val nomeDoDia = when (diaDaSemana) {
        1 -> "Domingo"
        2 -> "Segunda-feira"
        3 -> "Terça-feira"
        4 -> "Quarta-feira"
        5 -> "Quinta-feira"
        6 -> "Sexta-feira"
        7 -> "Sábado"
        else -> "Dia inválido"
    }
    println("Hoje é $nomeDoDia")
}
```

## Verificando Tipos com `is`

O operador `is` permite verificar o tipo de uma variável. Isso é extremamente útil para trabalhar com tipos genéricos ou polimorfismo. Uma grande vantagem é que o compilador do Kotlin realiza um "smart cast" (conversão inteligente), permitindo que você acesse membros daquele tipo específico sem uma conversão explícita.

```kotlin
fun descrever(obj: Any): String {
    return when (obj) {
        is String -> "É uma String com tamanho ${obj.length}" // obj é um smart cast para String
        is Int -> "É um número Inteiro com valor $obj"
        is Double -> "É um número Double"
        is Long -> "É um número Long"
        else -> "Tipo desconhecido"
    }
}

fun main() {
    println(descrever("Olá, Kotlin!"))
    println(descrever(42))
    println(descrever(3.14))
    println(descrever(100L))
    println(descrever(true))
}
```

## Verificando Intervalos com `in`

O operador `in` é utilizado para checar se um valor está dentro de um intervalo (`range`) ou de uma coleção.

```kotlin
fun verificarNumero(num: Int) {
    when (num) {
        in 1..10 -> println("$num está entre 1 e 10")
        in 11..20 -> println("$num está entre 11 e 20")
        !in 21..30 -> println("$num não está entre 21 e 30") // Usando a negação !in
        else -> println("$num está fora dos intervalos conhecidos")
    }
}

fun main() {
    verificarNumero(5)
    verificarNumero(15)
    verificarNumero(35)
}
```

## A Cláusula `else`

A cláusula `else` no `when` funciona como o `default` no `switch`. Ela é executada se nenhuma das outras condições for satisfeita.

**Importante:** Quando o `when` é usado como uma **expressão** (ou seja, seu resultado é atribuído a uma variável ou retornado de uma função), a cláusula `else` é **obrigatória**, a menos que o compilador possa provar que todos os casos possíveis já foram cobertos (como em `enum` ou classes seladas).

```kotlin
fun getEstacao(mes: Int): String {
    return when (mes) {
        12, 1, 2 -> "Verão"
        3, 4, 5 -> "Outono"
        6, 7, 8 -> "Inverno"
        9, 10, 11 -> "Primavera"
        else -> "Mês inválido" // Obrigatório, pois 'when' é uma expressão
    }
}

fun main() {
    println("A estação para o mês 7 é: ${getEstacao(7)}")
    println("A estação para o mês 13 é: ${getEstacao(13)}")
}
```

O `when` é uma ferramenta versátil que torna o código Kotlin mais legível, seguro e conciso.
