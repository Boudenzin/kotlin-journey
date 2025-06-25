# Funções e Expressões em Kotlin: Do Básico ao Avançado

Kotlin trata funções como cidadãs de primeira classe, o que significa que elas podem ser armazenadas em variáveis, passadas como argumentos e retornadas por outras funções. Isso abre um leque de possibilidades para escrever código conciso, expressivo e funcional.

## 1. A Anatomia de uma Função

Uma função é um bloco de código que realiza uma tarefa específica. A declaração padrão utiliza a palavra-chave `fun`.

```kotlin
// Função que aceita dois inteiros e retorna a soma deles
fun somar(a: Int, b: Int): Int {
    return a + b
}
```
- `fun`: Palavra-chave que declara uma função.
- `somar`: Nome da função.
- `(a: Int, b: Int)`: Parâmetros de entrada com seus tipos.
- `: Int`: Tipo de retorno da função.
- `{ return a + b }`: Corpo da função.

## 2. Funções como Expressões (Expression Body)

Quando uma função possui apenas uma única expressão, podemos torná-la mais concisa. O tipo de retorno é inferido pelo compilador e o `return` se torna implícito.

```kotlin
// A mesma função 'somar' escrita como uma expressão
fun somar(a: Int, b: Int) = a + b

// Outro exemplo:
fun obterSaudacao(nome: String) = "Olá, $nome!"

fun main() {
    println(somar(10, 5)) // Imprime: 15
    println(obterSaudacao("Gemini")) // Imprime: Olá, Gemini!
}
```

## 3. Armazenando Funções em Variáveis (Tipos de Função)

Em Kotlin, você pode atribuir uma função a uma variável. Para isso, a variável precisa ter um **tipo de função**, que define a "assinatura" da função que ela pode armazenar (parâmetros e retorno).

A sintaxe do tipo de função é: `(TipoParam1, TipoParam2) -> TipoRetorno`.

```kotlin
// 'calculo' é uma variável que pode armazenar qualquer função
// que receba dois Ints e retorne um Int.
var calculo: (Int, Int) -> Int

// Atribuindo a função 'somar' à variável 'calculo'
calculo = ::somar // Usamos :: para obter a referência da função

println("Resultado: ${calculo(20, 10)}") // Imprime: Resultado: 30

// Podemos criar uma nova função e atribuí-la à mesma variável
fun subtrair(a: Int, b: Int) = a - b
calculo = ::subtrair

println("Resultado: ${calculo(20, 10)}") // Imprime: Resultado: 10
```

### 3.1. Tipos de Função Anuláveis

Assim como qualquer outro tipo em Kotlin, um tipo de função também pode ser anulável. Isso é útil quando a existência de uma função é opcional. Para declarar um tipo de função anulável, basta envolver o tipo entre parênteses e adicionar `?`.

Sintaxe: `((Param1) -> Retorno)?`

Para invocar uma função que pode ser nula, você deve usar a chamada segura `?.invoke()`.

```kotlin
// 'onFinalizado' é uma ação opcional que pode ser nula
var onFinalizado: (() -> Unit)? = null // Recebe nada, retorna nada (Unit)

// Para chamar, usamos a chamada segura
onFinalizado?.invoke() // Nada acontece, pois é nulo

// Agora, atribuímos uma lambda a ela
onFinalizado = { println("Processo finalizado com sucesso!") }

// A chamada agora será executada
onFinalizado?.invoke() // Imprime: Processo finalizado com sucesso!
```

## 4. Transmitir uma Função para Outra como Argumento (Higher-Order Functions)

Uma função que recebe outra função como argumento ou retorna uma função é chamada de **Higher-Order Function** (Função de Ordem Superior). Este é um conceito central da programação funcional.

```kotlin
// 'executarOperacao' é uma Higher-Order Function.
// Ela recebe dois inteiros e uma função 'operacao'.
fun executarOperacao(a: Int, b: Int, operacao: (Int, Int) -> Int) {
    val resultado = operacao(a, b)
    println("O resultado da operação é $resultado")
}

fun main() {
    // Passando a referência da função 'somar'
    executarOperacao(10, 5, ::somar) // Saída: O resultado da operação é 15
    
    // Passando a referência da função 'subtrair'
    executarOperacao(10, 5, ::subtrair) // Saída: O resultado da operação é 5
    
    // Passando uma lambda diretamente como argumento
    executarOperacao(10, 5, { x, y -> x * y }) // Saída: O resultado da operação é 50
}
```

## 5. Expressões Lambda: A Forma Concisa

Expressões Lambda são "literais de função", ou seja, funções anônimas que podemos passar diretamente como argumentos.

A sintaxe completa de uma lambda é: `{ parâmetros -> corpo }`.

### Sintaxe Abreviada de Lambdas

A verdadeira força das lambdas vem de sua sintaxe concisa. O compilador do Kotlin pode inferir muitas coisas, permitindo-nos abreviar a declaração.

Vamos usar a função `map`, que transforma cada item de uma lista, como exemplo.

```kotlin
val numeros = listOf(1, 2, 3, 4, 5)
```

**Forma 1: *Trailing Lambda***
Se uma lambda é o **último** argumento de uma função, ela pode ser movida para fora dos parênteses.
```kotlin
val quadrados = numeros.map() { numero -> numero * numero }
```

**Forma 2: *Trailing Lambda* com Parênteses Omitidos**
Se a lambda é o **único** argumento (ou os outros têm valores padrão), os parênteses podem ser removidos.
```kotlin
val quadrados = numeros.map { numero -> numero * numero }
```

**Forma 3: O Parâmetro `it`**
Se a lambda tem **apenas um parâmetro**, você pode omitir sua declaração e acessá-lo pela palavra-chave implícita `it`. Esta é a forma mais comum e idiomática.
```kotlin
val quadrados = numeros.map { it * it }
// 'it' aqui se refere a cada número da lista (1, depois 2, depois 3...)
println(quadrados) // Saída: [1, 4, 9, 16, 25]
```

## 6. A Função `repeat()`

A função `repeat()` da biblioteca padrão é um ótimo exemplo de uso de *trailing lambdas*. Ela executa um bloco de código (uma lambda) um determinado número de vezes.

```kotlin
fun main() {
    println("Contagem regressiva:")
    
    // Executa a lambda 3 vezes
    repeat(3) {
        println("Iniciando...")
    }

    println("-----")

    // O parâmetro 'it' (opcional) contém o índice da iteração (0, 1, 2...)
    repeat(4) { indice ->
        println("Esta é a repetição número ${indice + 1}")
    }
}
```

Dominar funções, expressões e lambdas é fundamental para escrever um código Kotlin moderno, limpo e eficiente.
