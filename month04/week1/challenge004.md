# Desafios de Programação em Kotlin: Gerenciando Eventos

Esta é uma série de desafios práticos para aplicar conceitos importantes de Kotlin, como classes de dados, enums, coleções e funções de extensão, no contexto de um aplicativo de gerenciamento de eventos.

-----

## Desafio 1: Criando a Classe de Dados `Event`

### Objetivo

Outro engenheiro de software já concluiu algumas tarefas de alto nível no app e você é responsável por implementar os detalhes. É necessário implementar a classe `Event` para armazenar os detalhes de um evento inserido pelo usuário.

> **Dica:** Essa classe não precisa definir métodos nem executar ações, seu principal objetivo é armazenar dados.

### Requisitos

Crie uma `data class` chamada `Event` que armazene as seguintes informações:

  * **Título** do evento como uma `String`.
  * **Descrição** do evento como uma `String` (pode ser nula).
  * **Período do dia** como uma `String`.
  * **Duração** do evento em minutos como um `Int`.

### Tarefa

Usando sua classe, crie uma instância com as seguintes informações:

  * **Título:** "Study Kotlin"
  * **Descrição:** "Commit to studying Kotlin at least 15 minutes per day."
  * **Período do dia:** "Evening"
  * **Duração:** 15

Imprima o objeto criado para confirmar se a saída corresponde à esperada.

### Saída Esperada

```
Event(title=Study Kotlin, description=Commit to studying Kotlin at least 15 minutes per day., daypart=Evening, durationInMinutes=15)
```

\<details\>
\<summary\>Clique aqui para ver a Solução\</summary\>

```kotlin
data class Event(
    val title: String,
    val description: String?,
    val daypart: String,
    val durationInMinutes: Int
)

fun main() {
    val meuEvento = Event(
        title = "Study Kotlin",
        description = "Commit to studying Kotlin at least 15 minutes per day.",
        daypart = "Evening",
        durationInMinutes = 15
    )
    println(meuEvento)
}
```

\</details\>

-----

## Desafio 2: Refatorando com `enum class`

### Contexto

Depois da classe `Event` ser usada por algum tempo, percebeu-se que usar uma `String` para o período do dia não é o ideal. Alguns desenvolvedores armazenaram "Morning", outros "morning" e outros "MORNING", causando muitos problemas.

### Objetivo

Sua tarefa é corrigir o problema com uma **refatoração** (o processo de melhorar o código sem mudar sua função). Que tipo de classe pode ser usada para modelar um conjunto limitado de valores distintos para ajudar a corrigir esse problema?

### Requisitos

Mude o código do período do dia para usar uma `enum class`.

  * O nome da classe de enumeração deve ser `Daypart`.
  * Ela precisa ter três valores: `MORNING`, `AFTERNOON`, `EVENING`.

### Tarefa

1.  Como você criaria essa classe de enumeração?
2.  Como você refatoraria a classe `Event` para usá-la?

\<details\>
\<summary\>Clique aqui para ver a Solução\</summary\>

```kotlin
// 1. Criar a enum class
enum class Daypart {
    MORNING, AFTERNOON, EVENING
}

// 2. Refatorar a data class Event
data class Event(
    val title: String,
    val description: String?,
    val daypart: Daypart, // O tipo agora é Daypart
    val durationInMinutes: Int
)

fun main() {
    // Exemplo de uso
    val eventoManha = Event(
        title = "Acordar",
        description = "Hora de levantar",
        daypart = Daypart.MORNING, // Agora usamos o valor do enum
        durationInMinutes = 0
    )
    println(eventoManha)
}
```

\</details\>

-----

## Desafio 3: Organizando Eventos em uma Coleção

### Contexto

A equipe criou muitos eventos, e cada um deles exige a própria variável, o que torna difícil acompanhar todos eles.

```kotlin
val event1 = Event(title = "Wake up", ..., daypart = Daypart.MORNING, durationInMinutes = 0)
val event2 = Event(title = "Eat breakfast", ..., daypart = Daypart.MORNING, durationInMinutes = 15)
// ... e assim por diante
```

### Objetivo

Você consegue pensar em uma forma melhor de organizar o armazenamento desses eventos?

### Requisitos

Como armazenar todos os eventos em uma única variável?

  * Ela precisa ser flexível, pois mais eventos podem ser adicionados.
  * Ela precisa retornar com eficiência a contagem do número de eventos.

### Tarefa

Qual classe ou tipo de dados você usaria? Como você adicionaria os eventos a ela?

\<details\>
\<summary\>Clique aqui para ver a Solução\</summary\>

> A melhor opção é usar uma `MutableList`, que permite adicionar mais eventos depois de criada.

```kotlin
// Supondo que as classes Daypart e Event já existem

fun main() {
    val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
    val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
    val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
    val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
    val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
    val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)

    // Criando uma lista mutável e adicionando os eventos
    val events = mutableListOf<Event>(event1, event2, event3, event4, event5, event6)
    
    println("Número de eventos: ${events.size}")
}
```

\</details\>

-----

## Desafio 4: Filtrando Eventos Curtos

### Objetivo

Seu gerente decide que o usuário precisa acessar um resumo dos eventos curtos dele, com base na duração. Por exemplo: "Você tem 5 eventos curtos".

### Requisitos

  * Um evento "curto" tem **menos de 60 minutos**.

### Tarefa

Usando a lista de eventos da tarefa anterior, como você conseguiria esse resultado?

> **Dica:** Pode ser útil resolver esse problema em várias etapas. Primeiro, como você filtraria os eventos com base na duração? Depois de filtrar, como determinaria a quantidade?

\<details\>
\<summary\>Clique aqui para ver a Solução\</summary\>

> A função `filter()` é ideal para selecionar os itens que atendem a uma condição, e a propriedade `.size` ou a função `.count()` pode ser usada para obter a quantidade.

```kotlin
// Dentro da função main(), após criar a lista 'events'

// Filtra os eventos com duração menor que 60 minutos
val shortEvents = events.filter { it.durationInMinutes < 60 }
val numberOfShortEvents = shortEvents.size

println("Você tem $numberOfShortEvents eventos curtos.")

// Alternativa em uma linha:
// val numberOfShortEvents = events.count { it.durationInMinutes < 60 }
```

\</details\>

-----

## Desafio 5: Agrupando Eventos por Período do Dia

### Objetivo

Seus colegas de equipe querem que os usuários consigam acessar um resumo de todos os eventos agrupados pelo período do dia.

### Saída Esperada

A resposta precisa ser semelhante a esta (os números podem variar):

```
Morning: 2 events
Afternoon: 3 events
Evening: 1 events
```

### Tarefa

Usando a lista de eventos, como você pode atingir esse resultado?

> **Dica:** É parecido com a tarefa anterior, mas em vez de dividir em dois grupos (curtos/longos), divida em vários. Como você agruparia os eventos pelo período do dia? Depois de agrupá-los, como contaria os eventos em cada grupo?

\<details\>
\<summary\>Clique aqui para ver a Solução\</summary\>

> A função `groupBy()` é perfeita para isso. Ela cria um `Map` onde as chaves são o resultado da função de agrupamento e os valores são listas dos itens correspondentes.

```kotlin
// Dentro da função main(), após criar a lista 'events'

// Agrupa os eventos pela propriedade 'daypart'
val eventsByDaypart = events.groupBy { it.daypart }

// Itera sobre o mapa resultante para imprimir o resumo
eventsByDaypart.forEach { (daypart, events) ->
    println("$daypart: ${events.size} events")
}
```

\</details\>

-----

## Desafio 6: Simplificando o Acesso ao Último Item

### Contexto

Atualmente, seu colega encontra e imprime o último item usando o índice dele: `println("Last event of the day: ${events[events.size - 1].title}")`. O gerente sugere consultar a documentação do Kotlin para encontrar uma função que possa simplificar esse código.

### Tarefa

Qual função idiomática do Kotlin você encontrou para obter o último item de uma lista? Use-a para confirmar que você recebe os mesmos resultados.

\<details\>
\<summary\>Clique aqui para ver a Solução\</summary\>

> A função `last()` retorna o último elemento de uma lista.

```kotlin
// Dentro da função main(), após criar a lista 'events'

val lastEvent = events.last()
println("Last event of the day: ${lastEvent.title}")
```

\</details\>

-----

## Desafio 7: Adicionando Comportamento com Propriedades de Extensão

### Contexto

Sua equipe acha repetitivo programar o seguinte código sempre que precisa da duração de um evento como uma string:

```kotlin
val durationOfEvent = if (events[0].durationInMinutes < 60) {
    "short"
} else {
    "long"
}
println("Duration of first event of the day: $durationOfEvent")
```

Mudar a classe `Event` diretamente não é uma opção, pois outras equipes já a utilizam.

### Tarefa

Sem mudar a `data class` original, como você pode escrever uma **propriedade de extensão** que retorne os mesmos valores ("short" ou "long")?

### Uso Esperado

Quando implementado corretamente, você poderá usar o seguinte código:

```kotlin
println("Duration of first event of the day: ${events[0].durationOfEvent}")
```

\<details\>
\<summary\>Clique aqui para ver a Solução\</summary\>

> Criamos uma propriedade de extensão na classe `Event`.

```kotlin
// Esta propriedade pode ser declarada no nível superior do arquivo
val Event.durationOfEvent: String
    get() = if (this.durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }

// Dentro da função main()
fun main() {
    // ... (criação da lista de eventos)
    
    // Agora podemos usar a nova propriedade diretamente
    println("Duration of first event of the day: ${events[0].durationOfEvent}")
}
```

\</details\>