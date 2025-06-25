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

## 4. Expressões Lambda: Funções Anônimas

Expressões Lambda são "literais de função", ou seja, funções que não são declaradas com `fun`, mas podem ser tratadas como expressões e passadas diretamente como argumentos.

A sintaxe completa de uma lambda é: `{ parâmetros -> corpo }`.

```kotlin
// Atribuindo uma expressão lambda a uma variável
val multiplicar: (Int, Int) -> Int = { a: Int, b: Int -> a * b }

// A lambda é o bloco dentro das chaves {}
val saudar: (String) -> String = { nome: String -> "Bem-vindo, $nome!" }

fun main() {
    println(multiplicar(5, 4)) // Imprime: 20
    println(saudar("Kotlin"))  // Imprime: Bem-vindo, Kotlin!
}
```

### Sintaxe Abreviada de Lambdas

A verdadeira força das lambdas vem de sua sintaxe concisa. O compilador do Kotlin pode inferir muitas coisas, permitindo-nos abreviar a declaração.

Vamos pegar uma função que recebe uma lambda, como a `map`, que transforma cada item de uma lista.

```kotlin
val numeros = listOf(1, 2, 3, 4, 5)
```

**Forma 1: Sintaxe Completa**
```kotlin
val quadrados = numeros.map({ numero: Int -> numero * numero })
// Resultado: [1, 4, 9, 16, 25]
```

**Forma 2: Inferência de Tipo**
O compilador já sabe que `numeros` é uma lista de `Int`, então o tipo do parâmetro da lambda pode ser omitido.
```kotlin
val quadrados = numeros.map({ numero -> numero * numero })
```

**Forma 3: *Trailing Lambda***
Se uma lambda é o **último** argumento de uma função, ela pode ser movida para fora dos parênteses.
```kotlin
val quadrados = numeros.map() { numero -> numero * numero }
```

**Forma 4: *Trailing Lambda* com Parênteses Vazios Omitidos**
Se a lambda é o **único** argumento, os parênteses podem ser removidos completamente.
```kotlin
val quadrados = numeros.map { numero -> numero * numero }
```

**Forma 5: O Parâmetro `it`**
Se a lambda tem **apenas um parâmetro**, você pode omitir sua declaração e acessá-lo pela palavra-chave implícita `it`. Esta é a forma mais comum e idiomática.
```kotlin
val quadrados = numeros.map { it * it }
// 'it' aqui se refere a cada número da lista (1, depois 2, depois 3...)
```

## 5. A Função `repeat()`

A função `repeat()` da biblioteca padrão é um excelente exemplo de uso de *trailing lambdas*. Ela executa um bloco de código (uma lambda) um determinado número de vezes.

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

### Saída do Código Acima:
```
Contagem regressiva:
Iniciando...
Iniciando...
Iniciando...
-----
Esta é a repetição número 1
Esta é a repetição número 2
Esta é a repetição número 3
Esta é a repetição número 4
```

Dominar funções, expressões e lambdas é fundamental para escrever um código Kotlin moderno, limpo e eficiente.
