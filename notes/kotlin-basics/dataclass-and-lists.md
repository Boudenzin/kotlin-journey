# Kotlin: Entendendo `data class` e `listOf`

Em Kotlin, `data class` e `listOf` são duas ferramentas extremamente úteis e frequentemente utilizadas em conjunto para gerenciar coleções de dados de forma estruturada e eficiente.

## 1. `data class`: Classes para Armazenar Dados

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

## 2. `listOf`: Criando Listas Imutáveis

`listOf` é uma função da biblioteca padrão do Kotlin usada para criar uma **lista imutável** (somente leitura). Uma vez que uma lista é criada com `listOf`, você não pode adicionar, remover ou alterar seus elementos.

Essa imutabilidade torna o código mais seguro e previsível, especialmente em aplicações com múltiplas threads.

### Uso Básico

Você pode criar uma lista passando os elementos como argumentos para a função `listOf()`.

```kotlin
// Criando uma lista de Strings
val frutas = listOf("Maçã", "Banana", "Laranja")

// Criando uma lista de Inteiros
val numeros = listOf(1, 10, 42, 100)
```

### Acessando Elementos

Você pode acessar os elementos de uma lista pelo seu índice (começando em 0) ou usando funções como `first()` e `last()`.

```kotlin
println(frutas[0])      // Saída: Maçã
println(frutas.first()) // Saída: Maçã
println(frutas.last())  // Saída: Laranja
```

### Iterando sobre uma Lista

A forma mais comum de percorrer os itens de uma lista é usando um loop `for` ou a função `forEach`.

```kotlin
// Usando um loop for
for (fruta in frutas) {
    println("Fruta da estação: $fruta")
}

// Usando forEach com lambda
numeros.forEach { numero ->
    println("Número: $numero")
}
```

## 3. A Combinação Perfeita: `data class` e `listOf`

A verdadeira magia acontece quando você combina os dois. `data class` é a maneira ideal de modelar os objetos que você quer armazenar, e `listOf` é a maneira ideal de criar uma coleção desses objetos.

### Exemplo Prático

Vamos criar uma lista de usuários usando a `data class` `Usuario` que definimos anteriormente.

```kotlin
fun main() {
    // 1. Definimos a data class
    data class Usuario(val id: Int, val nome: String, val email: String)

    // 2. Criamos uma lista imutável de objetos Usuario
    val listaDeUsuarios = listOf(
        Usuario(1, "Ana", "ana@email.com"),
        Usuario(2, "Carlos", "carlos@email.com"),
        Usuario(3, "Beatriz", "bia@email.com"),
        Usuario(4, "Ana", "ana.rodrigues@email.com") // Outra Ana
    )

    // 3. Agora podemos trabalhar com essa lista de forma poderosa
    
    // Imprimindo a lista inteira (graças ao .toString() da data class)
    println(listaDeUsuarios)
    
    println("-----")
    
    // Encontrando o primeiro usuário chamado "Ana"
    val primeiraAna = listaDeUsuarios.find { it.nome == "Ana" }
    println("Primeira Ana encontrada: $primeiraAna")
    
    println("-----")
    
    // Filtrando todos os usuários chamados "Ana"
    val todasAsAnas = listaDeUsuarios.filter { it.nome == "Ana" }
    println("Todas as Anas: $todasAsAnas")
    
    println("-----")
    
    // Extraindo apenas os e-mails de todos os usuários
    val emails = listaDeUsuarios.map { it.email }
    println("Lista de e-mails: $emails")
}
```

### Resumo

* Use **`data class`** para criar classes que servem primariamente como contêineres de dados. Você ganha `equals()`, `hashCode()`, `toString()`, `copy()` e `componentN()` de graça.
* Use **`listOf`** para criar coleções de dados que não devem ser modificadas após sua criação, promovendo um código mais seguro.
* A combinação dos dois é o padrão idiomático em Kotlin para gerenciar coleções de objetos estruturados.
