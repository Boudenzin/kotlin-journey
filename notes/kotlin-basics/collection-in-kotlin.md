# O Guia Completo de Coleções em Kotlin

As coleções são estruturas de dados usadas para armazenar e organizar múltiplos itens. Kotlin possui um sistema de coleções rico e bem definido, com uma distinção clara entre coleções **imutáveis** (somente leitura) e **mutáveis** (leitura e escrita).

## Princípio Fundamental: Imutabilidade vs. Mutabilidade

* **Coleções Imutáveis (`List`, `Set`, `Map`):** Uma vez criadas, seus elementos não podem ser adicionados, removidos ou alterados. São criadas com funções como `listOf()`, `setOf()`, `mapOf()`. Isso torna o código mais seguro e previsível.
* **Coleções Mutáveis (`MutableList`, `MutableSet`, `MutableMap`):** Permitem a modificação de seus elementos após a criação. São criadas com funções como `mutableListOf()`, `mutableSetOf()`, `mutableMapOf()`.

---

## 1. Listas (Lists)

Uma `List` é uma coleção **ordenada** de itens, onde cada item tem uma posição (índice) específica. As listas **permitem elementos duplicados**.

### Lista Imutável (`List`)

Use `listOf()` para criar uma lista somente leitura.

```kotlin
val cores = listOf("Vermelho", "Verde", "Azul", "Verde")

// Acessando por índice (começa em 0)
println("Primeira cor: ${cores[0]}") // Saída: Primeira cor: Vermelho

// Verificando o tamanho
println("Tamanho da lista: ${cores.size}") // Saída: Tamanho da lista: 4

// Iterando sobre a lista
for (cor in cores) {
    println(cor)
}

// cores.add("Amarelo") // Erro de compilação! A lista é imutável.
```

### Lista Mutável (`MutableList`)

Use `mutableListOf()` para criar uma lista que pode ser modificada.

```kotlin
val planetas = mutableListOf("Mercúrio", "Vênus", "Terra")
println("Planetas iniciais: $planetas")

// Adicionando um elemento
planetas.add("Marte")
println("Adicionando Marte: $planetas")

// Removendo um elemento
planetas.remove("Vênus")
println("Removendo Vênus: $planetas")

// Alterando um elemento pelo índice
planetas[0] = "Meu Planeta"
println("Alterando Mercúrio: $planetas")
```

**Quando usar Listas?** Quando a ordem dos elementos é importante e você precisa acessá-los por sua posição.

---

## 2. Conjuntos (Sets)

Um `Set` é uma coleção de itens **únicos**. A ordem dos elementos geralmente não é garantida (embora a implementação padrão em Kotlin, `LinkedHashSet`, mantenha a ordem de inserção). Conjuntos **não permitem elementos duplicados**.

### Conjunto Imutável (`Set`)

Use `setOf()` para criar um conjunto somente leitura. Duplicatas são automaticamente ignoradas.

```kotlin
val numeros = setOf(1, 5, 2, 5, 3, 1) // O '5' e o '1' duplicados são ignorados
println("Conjunto de números: $numeros") // Saída: Conjunto de números: [1, 5, 2, 3]

// Verificando se um elemento existe (operação muito eficiente em Sets)
println("O conjunto contém o número 3? ${3 in numeros}") // Saída: true
```

### Conjunto Mutável (`MutableSet`)

Use `mutableSetOf()` para criar um conjunto que pode ser modificado.

```kotlin
val vogais = mutableSetOf('a', 'e', 'i')
println("Vogais iniciais: $vogais")

// Adicionando um elemento
vogais.add('o')
println("Adicionando 'o': $vogais")

// Tentar adicionar um elemento que já existe não faz nada
vogais.add('a')
println("Tentando adicionar 'a' de novo: $vogais")

// Removendo um elemento
vogais.remove('i')
println("Removendo 'i': $vogais")
```

**Quando usar Conjuntos?** Quando você precisa garantir a unicidade dos elementos e a principal operação é verificar a existência de um item na coleção.

---

## 3. Mapas (Maps)

Um `Map` é uma coleção de pares **chave-valor**. As chaves são **únicas**, e cada chave é mapeada para exatamente um valor. É como um dicionário.

### Mapa Imutável (`Map`)

Use `mapOf()` para criar um mapa somente leitura. A sintaxe `chave to valor` é uma forma idiomática de criar os pares.

```kotlin
val capitais = mapOf(
    "Brasil" to "Brasília",
    "França" to "Paris",
    "Japão" to "Tóquio"
)

// Acessando um valor pela chave
println("A capital do Brasil é: ${capitais["Brasil"]}") // Saída: Brasília

// Acessando todas as chaves
println("Países: ${capitais.keys}") // Saída: [Brasil, França, Japão]

// Acessando todos os valores
println("Capitais: ${capitais.values}") // Saída: [Brasília, Paris, Tóquio]
```

### Mapa Mutável (`MutableMap`)

Use `mutableMapOf()` para criar um mapa que pode ser modificado.

```kotlin
val ddi = mutableMapOf(55 to "Brasil", 1 to "EUA")
println("DDIs iniciais: $ddi")

// Adicionando ou atualizando um par chave-valor
ddi[351] = "Portugal"
println("Adicionando Portugal: $ddi")

// Atualizando um valor existente
ddi[1] = "Estados Unidos"
println("Atualizando EUA: $ddi")

// Removendo um par pela chave
ddi.remove(55)
println("Removendo Brasil: $ddi")
```

**Quando usar Mapas?** Quando você precisa associar valores a identificadores únicos para uma busca rápida.

---

## 4. Matrizes (Arrays)

Um `Array` em Kotlin é uma estrutura de **tamanho fixo** e **mutável** em seus elementos. Diferente de `List`, seu tamanho não pode ser alterado após a criação. Existem arrays para tipos genéricos (`Array<T>`) e para tipos primitivos (`IntArray`, `DoubleArray`, etc.), que oferecem melhor performance.

```kotlin
// Array de objetos (String)
val nomes = arrayOf("João", "Maria", "Pedro")
nomes[0] = "José" // OK, os elementos são mutáveis
// nomes.add("Ana") // Erro de compilação! O tamanho é fixo.

println("Primeiro nome: ${nomes[0]}") // Saída: José

// Array de tipo primitivo (Int) - mais eficiente
val idades = intArrayOf(25, 30, 22)
println("Idade do meio: ${idades[1]}") // Saída: 30

// Criando um array de tamanho 5, inicializado com zeros
val placares = IntArray(5)
println(placares.joinToString()) // Saída: 0, 0, 0, 0, 0
```

**Quando usar Matrizes?** Quando você precisa de uma coleção de tamanho fixo, em cenários de performance crítica ou ao interoperar com APIs Java que esperam arrays. Para a maioria dos outros casos, `List` e `MutableList` são mais flexíveis e preferíveis.

## Funções de Coleção Poderosas

A verdadeira força das coleções em Kotlin vem de suas funções de extensão, que permitem manipular dados de forma declarativa.

```kotlin
data class Produto(val nome: String, val preco: Double, val categoria: String)

val produtos = listOf(
    Produto("Notebook", 4500.0, "Eletrônicos"),
    Produto("Mouse", 150.0, "Eletrônicos"),
    Produto("Camiseta", 80.0, "Vestuário"),
    Produto("Calça Jeans", 220.0, "Vestuário")
)

// filter: seleciona itens que atendem a uma condição
val eletronicos = produtos.filter { it.categoria == "Eletrônicos" }
println("Eletrônicos: $eletronicos")

// map: transforma cada item da coleção em algo novo
val nomesDosProdutos = produtos.map { it.nome }
println("Nomes: $nomesDosProdutos")

// find: encontra o primeiro item que atende a uma condição
val produtoCaro = produtos.find { it.preco > 2000.0 }
println("Produto caro: $produtoCaro")

// sortedBy: ordena a coleção com base em uma propriedade
val ordenadoPorPreco = produtos.sortedBy { it.preco }
println("Ordenado: $ordenadoPorPreco")

// groupBy: agrupa os itens em um mapa com base em uma chave
val porCategoria = produtos.groupBy { it.categoria }
println("Agrupado: $porCategoria")
```
