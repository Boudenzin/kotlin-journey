# Tópicos Avançados e Idiomáticos em Kotlin

Este guia explora várias características poderosas do Kotlin que permitem escrever código mais limpo, seguro e reutilizável. Abordaremos genéricos, enums, singletons, funções de extensão e de escopo.

## 1. Criar uma Classe Reutilizável com Genéricos

Genéricos (`<T>`) permitem criar classes, interfaces e funções que podem operar com qualquer tipo de dado, evitando a duplicação de código e garantindo a segurança de tipo em tempo de compilação.

A letra `T` (de *Type*) é uma convenção para um parâmetro de tipo genérico.

**Exemplo: Uma classe `Caixa` que pode guardar qualquer tipo de item.**

```kotlin
// A classe Caixa é genérica e pode conter um item do tipo T.
class Caixa<T>(item: T) {
    private var conteudo: T = item

    fun obterConteudo(): T {
        return conteudo
    }

    fun inspecionar() {
        println("A caixa contém: $conteudo")
    }
}

fun main() {
    // Criando uma Caixa para guardar um Inteiro
    val caixaDeNumero = Caixa<Int>(42)
    caixaDeNumero.inspecionar() // Saída: A caixa contém: 42
    val numero = caixaDeNumero.obterConteudo() // numero é do tipo Int

    // Criando uma Caixa para guardar uma String
    // O tipo pode ser inferido, não precisa de <String>
    val caixaDeTexto = Caixa("Olá, Kotlin!") 
    caixaDeTexto.inspecionar() // Saída: A caixa contém: Olá, Kotlin!
    val texto = caixaDeTexto.obterConteudo() // texto é do tipo String
}
```

## 2. `data class`: Classes para Armazenar Dados

Uma `data class` (classe de dados) é um tipo especial de classe em Kotlin, otimizada para um único propósito: **armazenar dados**. Quando você declara uma classe como `data`, o compilador do Kotlin gera automaticamente várias funções úteis que, de outra forma, você teria que escrever manualmente.

### Declaração Básica

Para criar uma `data class`, basta adicionar a palavra-chave `data` antes de `class`.

```kotlin
data class Usuario(val id: Int, val nome: String, val email: String)
```

### O que o Compilador Gera para Você?

Ao declarar a `data class` `Usuario` acima, o compilador gera automaticamente:

1.  **`.equals()` e `.hashCode()`**:
    * Verifica a igualdade estrutural, não a de referência. Dois objetos de uma `data class` são considerados iguais se todas as suas propriedades forem iguais.
    * O `hashCode` é calculado com base nas propriedades, garantindo consistência com o `equals`.

    ```kotlin
    val usuario1 = Usuario(1, "Ana", "ana@email.com")
    val usuario2 = Usuario(1, "Ana", "ana@email.com")
    val usuario3 = Usuario(2, "Carlos", "carlos@email.com")

    println(usuario1 == usuario2) // true
    println(usuario1 == usuario3) // false
    ```

2.  **`.toString()`**:
    * Fornece uma representação em string legível e informativa do objeto, mostrando o nome da classe e suas propriedades.

    ```kotlin
    println(usuario1) // Saída: Usuario(id=1, nome=Ana, email=ana@email.com)
    ```

3.  **`.copy()`**:
    * Permite criar uma cópia de um objeto, modificando opcionalmente algumas de suas propriedades. Isso é muito útil para trabalhar com imutabilidade.

    ```kotlin
    val usuarioAnaAtualizado = usuario1.copy(email = "ana.silva@email.com")
    println(usuarioAnaAtualizado) // Saída: Usuario(id=1, nome=Ana, email=ana.silva@email.com)
    ```

4.  **`.componentN()`**:
    * Funções que permitem a **desestruturação** da classe, ou seja, extrair seus valores para variáveis separadas.

    ```kotlin
    val (id, nome, email) = usuario1
    println("ID: $id, Nome: $nome, Email: $email") // Saída: ID: 1, Nome: Ana, Email: ana@email.com
    ```


## 3. Usar uma Classe de Enumeração (`enum class`)

Classes de enumeração (`enum class`) são usadas para representar um conjunto fixo de constantes. São ideais para modelar conceitos que têm um número limitado de valores possíveis, como dias da semana, status de uma requisição ou naipes de um baralho.

**Exemplo: Status de uma requisição de rede.**

```kotlin
// Cada constante é um objeto e pode ter suas próprias propriedades.
enum class Status(val mensagem: String) {
    CARREGANDO("Carregando dados..."),
    SUCESSO("Dados carregados com sucesso!"),
    ERRO("Ocorreu um erro na requisição.")
}

fun processarStatus(statusAtual: Status) {
    // Enums são muito poderosos com a expressão 'when'.
    when (statusAtual) {
        Status.CARREGANDO -> println(statusAtual.mensagem)
        Status.SUCESSO -> {
            println(statusAtual.mensagem)
            println("Exibindo dados na tela.")
        }
        Status.ERRO -> {
            println(statusAtual.mensagem)
            println("Mostrando tela de erro.")
        }
    }
}

fun main() {
    processarStatus(Status.SUCESSO)
}
// Saída:
// Dados carregados com sucesso!
// Exibindo dados na tela.
```

## 3. Usar um Objeto Singleton (`object`)

A palavra-chave `object` em Kotlin declara uma classe e cria uma única instância dela ao mesmo tempo. Isso é conhecido como o padrão **Singleton**, que garante que exista apenas uma instância de uma classe em toda a aplicação. É útil para configurações, gerenciadores de conexão ou logging.

**Exemplo: Um objeto para guardar configurações da aplicação.**

```kotlin
object ConfiguracaoApp {
    const val API_URL = "[https://api.example.com](https://api.example.com)"
    var modoNoturno = false

    fun imprimirConfiguracoes() {
        println("URL da API: $API_URL")
        println("Modo Noturno Ativado: $modoNoturno")
    }
}

fun main() {
    // Você acessa membros do singleton diretamente pelo nome do objeto.
    ConfiguracaoApp.modoNoturno = true
    ConfiguracaoApp.imprimirConfiguracoes()
}
// Saída:
// URL da API: [https://api.example.com](https://api.example.com)
// Modo Noturno Ativado: true
```

## 4. Ampliar Classes com Novas Propriedades e Métodos

Kotlin permite "adicionar" novas funcionalidades a classes existentes sem precisar herdá-las, usando **funções e propriedades de extensão**. Isso é extremamente útil para ampliar classes de bibliotecas de terceiros ou as classes padrão do Kotlin.

**Exemplo: Adicionando um método `capitalizarPrimeiraLetra` e uma propriedade `tamanhoSemEspacos` à classe `String`.**

```kotlin
// Função de extensão para a classe String
fun String.capitalizarPrimeiraLetra(): String {
    if (this.isEmpty()) return this
    return this.substring(0, 1).uppercase() + this.substring(1)
}

// Propriedade de extensão para a classe String
val String.tamanhoSemEspacos: Int
    get() = this.replace(" ", "").length

fun main() {
    val texto = "olá mundo"
    println(texto.capitalizarPrimeiraLetra()) // Saída: Olá mundo

    val frase = "Kotlin é poderoso"
    println("Tamanho real: ${frase.length}") // Saída: Tamanho real: 17
    println("Tamanho sem espaços: ${frase.tamanhoSemEspacos}") // Saída: Tamanho sem espaços: 15
}
```

## 5. Polimorfismo com Funções de Extensão e Interfaces

Uma técnica avançada e poderosa é criar funções de extensão em **interfaces**. Isso permite adicionar um comportamento padrão a um grupo de classes que, de outra forma, não são relacionadas, mas que implementam a mesma interface.

**Exemplo: Criar um comportamento de log para diferentes classes.**

```kotlin
// 1. Crie uma interface que define um contrato
interface Logavel {
    fun obterDadosParaLog(): String
}

// 2. Crie uma função de extensão NA INTERFACE
fun Logavel.gerarLogCompleto() {
    val timestamp = java.time.LocalDateTime.now()
    println("LOG [$timestamp]: ${this.obterDadosParaLog()}")
}

// 3. Crie classes diferentes que implementam a interface
data class Produto(val id: Int, val nome: String) : Logavel {
    override fun obterDadosParaLog(): String {
        return "Produto(id=$id, nome='$nome')"
    }
}

class EventoUsuario(val tipo: String, val usuarioId: Int) : Logavel {
    override fun obterDadosParaLog(): String {
        return "Evento(tipo='$tipo', usuarioId=$usuarioId)"
    }
}

fun main() {
    val produto = Produto(101, "Notebook")
    val evento = EventoUsuario("LOGIN", 58)

    // Ambos podem usar a função de extensão, pois implementam Logavel
    produto.gerarLogCompleto()
    evento.gerarLogCompleto()
}
```

## 6. Usar Funções de Escopo (Scope Functions)

Kotlin possui cinco funções de escopo na sua biblioteca padrão: `let`, `run`, `with`, `apply`, e `also`. O propósito delas é executar um bloco de código no contexto de um objeto, tornando o código mais conciso e legível.

Elas se diferenciam principalmente por duas coisas:
1.  **Como se referem ao objeto de contexto:** como `this` ou como `it`.
2.  **Qual o valor de retorno:** o próprio objeto de contexto ou o resultado da expressão lambda.

| Função | Objeto de Contexto | Valor de Retorno     | Caso de Uso Comum                               |
| :----- | :----------------- | :------------------- | :---------------------------------------------- |
| `let`  | `it`               | Resultado da lambda  | Executar código em objetos não-nulos.           |
| `run`  | `this`             | Resultado da lambda  | Configuração de objeto e cálculo de resultado.  |
| `with` | `this`             | Resultado da lambda  | Agrupar chamadas de função em um objeto.        |
| `apply`| `this`             | Objeto de contexto   | Configuração de objeto (ex: Builder pattern).   |
| `also` | `it`               | Objeto de contexto   | Ações adicionais que não alteram o objeto (log).|

**Exemplo Prático:**

```kotlin
data class Pessoa(var nome: String, var idade: Int, var cidade: String? = null)

fun main() {
    // apply: Ideal para configurar um objeto. Retorna o próprio objeto.
    val pessoa = Pessoa("Ana", 25).apply {
        this.idade = 26
        this.cidade = "São Paulo"
    }
    println("Apply: $pessoa")

    // let: Ideal para trabalhar com objetos que podem ser nulos.
    val cidadeNaoNula: String? = pessoa.cidade
    cidadeNaoNula?.let {
        println("Let: A cidade é $it e tem ${it.length} letras.")
    }

    // run: Similar ao 'apply', mas retorna o resultado da lambda.
    val anosParaAposentar = pessoa.run {
        println("Run: Verificando ${this.nome}...")
        65 - this.idade
    }
    println("Run: Faltam $anosParaAposentar anos para $pessoa.nome se aposentar.")
    
    // also: Ideal para ações secundárias, como logging. Retorna o objeto.
    val pessoaOriginal = pessoa.also {
        println("Also: Logando o objeto criado -> ${it.nome}")
    }
}
```
