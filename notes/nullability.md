# Nulabilidade em Kotlin: Adeus, NullPointerException!

Um dos principais recursos do Kotlin é o seu sistema de tipos que visa eliminar o famoso `NullPointerException` (NPE) do nosso código. O Kotlin distingue entre tipos que podem e não podem conter `null`.

## Tipos Não-Nuláveis vs. Tipos Nuláveis (`?`)

Por padrão, os tipos em Kotlin são **não-nuláveis**. Isso significa que você não pode atribuir `null` a eles.

```kotlin
var texto: String = "Olá, Mundo!"
// texto = null // Erro de compilação!
```

Para permitir que uma variável contenha `null`, você deve declará-la explicitamente como **nulável**, adicionando um `?` ao final do tipo.

```kotlin
var textoNulavel: String? = "Pode ser nulo"
textoNulavel = null // Válido!
```

## Lidando com Tipos Nuláveis

O compilador do Kotlin nos força a lidar com a possibilidade de `null` antes de acessarmos qualquer propriedade ou método de uma variável nulável.

### 1. Chamada Segura (`?.`)

O operador de chamada segura, `?.`, executa a operação seguinte apenas se o valor da variável não for `null`. Se for `null`, toda a expressão retorna `null`.

```kotlin
val texto: String? = "Kotlin"
val textoNulo: String? = null

println(texto?.length)      // Imprime: 6
println(textoNulo?.length)  // Imprime: null
```

É comum encadear chamadas seguras:

```kotlin
// Suponha que temos classes aninhadas
// class Endereco(val rua: String?)
// class Pessoa(val endereco: Endereco?)

val pessoa: Pessoa? = Pessoa(Endereco("Rua Principal"))
val rua = pessoa?.endereco?.rua
println(rua) // Imprime: Rua Principal
```

### 2. Operador Elvis (`?:`)

O operador Elvis, `?:`, é usado para fornecer um valor padrão quando uma expressão à sua esquerda resulta em `null`.

Se a expressão à esquerda de `?:` não for `null`, ela será retornada. Caso contrário, a expressão à direita será retornada.

```kotlin
val nome: String? = null
val nomeExibicao: String = nome ?: "Convidado"
println(nomeExibicao) // Imprime: Convidado

val comprimentoNome: Int = nome?.length ?: 0
println("O comprimento do nome é: $comprimentoNome") // Imprime: O comprimento do nome é: 0
```

### 3. Operador de Assertiva Não Nula (`!!`)

O operador de assertiva não nula, `!!`, é a forma de dizer ao compilador: "Eu tenho certeza de que esta variável não é nula, e assumo a responsabilidade por isso". Ele converte qualquer valor para um tipo não-nulável e lança um `NullPointerException` se o valor for, de fato, `null`.

**Use com extrema cautela!** Na maioria das vezes, o uso de `!!` indica uma oportunidade de refatorar o código para uma abordagem mais segura com `?.` ou `?:`.

```kotlin
val texto: String? = "Não sou nulo"

// Se você tem 100% de certeza, pode usar !!
val comprimento = texto!!.length
println(comprimento) // Imprime: 11

val textoNulo: String? = null
try {
    // Isso vai lançar um NullPointerException
    val falha = textoNulo!!.length
} catch (e: NullPointerException) {
    println("Capturamos um NullPointerException!")
}
```

### Resumo dos Operadores

| Operador | Nome                 | Descrição                                                                                                  |
| :------- | :------------------- | :--------------------------------------------------------------------------------------------------------- |
| `?`      | Símbolo Nulável      | Anexado a um tipo (`String?`), permite que a variável contenha `null`.                                      |
| `?.`     | Chamada Segura       | Executa a operação se o valor não for `null`; caso contrário, retorna `null`.                              |
| `?:`     | Operador Elvis       | Retorna o valor à esquerda se não for `null`; caso contrário, retorna o valor padrão à direita.             |
| `!!`     | Assertiva Não Nula   | Converte o tipo para não-nulável, mas lança um `NullPointerException` se o valor for `null`. Use com cuidado. |

O sistema de nulabilidade do Kotlin é uma ferramenta poderosa para escrever código mais robusto e seguro, evitando uma das fontes mais comuns de erros em tempo de execução.
