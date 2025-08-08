# O Guia Completo de Coleções em Kotlin

As coleções são estruturas de dados usadas para armazenar e organizar múltiplos itens. Kotlin possui um sistema de coleções rico e bem definido, com uma distinção clara entre coleções **imutáveis** (somente leitura) e **mutáveis** (leitura e escrita).

## Princípio Fundamental: Imutabilidade vs. Mutabilidade

  * **Coleções Imutáveis (`List`, `Set`, `Map`):** Uma vez criadas, seus elementos não podem ser adicionados, removidos ou alterados. São criadas com funções como `listOf()`, `setOf()`, `mapOf()`. Isso torna o código mais seguro e previsível.
  * **Coleções Mutáveis (`MutableList`, `MutableSet`, `MutableMap`):** Permitem a modificação de seus elementos após a criação. São criadas com funções como `mutableListOf()`, `mutableSetOf()`, `mutableMapOf()`.

-----

## 1\. Listas (Lists)

Uma `List` é uma coleção **ordenada** de itens, onde cada item tem uma posição (índice) específica. As listas **permitem elementos duplicados**.

### Lista Imutável (`List`)

Use `listOf()` para criar uma lista somente leitura.

```kotlin
val cores = listOf("Vermelho", "Verde", "Azul", "Verde")
println("Primeira cor: ${cores[0]}")
```

### Lista Mutável (`MutableList`)

Use `mutableListOf()` para criar uma lista que pode ser modificada.

```kotlin
val planetas = mutableListOf("Mercúrio", "Vênus", "Terra")
planetas.add("Marte")    // Adiciona um elemento
planetas.remove("Vênus") // Remove um elemento
planetas[0] = "Meu Planeta" // Altera um elemento
```

**Quando usar Listas?** Quando a ordem dos elementos é importante e você precisa acessá-los por sua posição.

-----

## 2\. Conjuntos (Sets)

Um `Set` é uma coleção de itens **únicos**. A ordem dos elementos geralmente não é garantida. Conjuntos **não permitem elementos duplicados**.

### Conjunto Imutável (`Set`)

Use `setOf()` para criar um conjunto somente leitura. Duplicatas são automaticamente ignoradas.

```kotlin
val numeros = setOf(1, 5, 2, 5, 3, 1) // Resultado: [1, 5, 2, 3]
println("O conjunto contém o número 3? ${3 in numeros}") // true
```

### Conjunto Mutável (`MutableSet`)

Use `mutableSetOf()` para criar um conjunto que pode ser modificado.

```kotlin
val vogais = mutableSetOf('a', 'e', 'i')
vogais.add('o')     // Adiciona 'o'
vogais.add('a')     // Não faz nada, 'a' já existe
vogais.remove('i')  // Remove 'i'
```

**Quando usar Conjuntos?** Quando você precisa garantir a unicidade dos elementos e a principal operação é verificar a existência de um item.

-----

## 3\. Mapas (Maps)

Um `Map` é uma coleção de pares **chave-valor**. As chaves são **únicas**, e cada chave é mapeada para exatamente um valor. É como um dicionário.

### Mapa Imutável (`Map`)

Use `mapOf()` para criar um mapa somente leitura.

```kotlin
val capitais = mapOf("Brasil" to "Brasília", "França" to "Paris")
println("A capital do Brasil é: ${capitais["Brasil"]}")
```

### Mapa Mutável (`MutableMap`)

Use `mutableMapOf()` para criar um mapa que pode ser modificado.

```kotlin
val ddi = mutableMapOf(55 to "Brasil", 1 to "EUA")
ddi[351] = "Portugal" // Adiciona ou atualiza
ddi.remove(55)      // Remove pela chave
```

**Quando usar Mapas?** Quando você precisa associar valores a identificadores únicos para uma busca rápida.

-----

## 4\. Matrizes (Arrays)

Um `Array` em Kotlin é uma estrutura de **tamanho fixo** e **mutável** em seus elementos. Diferente de `List`, seu tamanho não pode ser alterado após a criação.

```kotlin
val nomes = arrayOf("João", "Maria", "Pedro")
nomes[0] = "José" // OK, os elementos são mutáveis
// nomes.add("Ana") // Erro de compilação! O tamanho é fixo.
```

**Quando usar Matrizes?** Quando você precisa de uma coleção de tamanho fixo, em cenários de performance crítica ou ao interoperar com APIs Java. Para a maioria dos outros casos, `List` e `MutableList` são mais flexíveis.

-----

## Principais Funções de Alta Ordem para Coleções

A verdadeira força das coleções em Kotlin vem de suas funções de extensão, que permitem manipular dados de forma declarativa e poderosa. Vamos usar a seguinte lista como base para os exemplos:

```kotlin
data class Produto(val nome: String, val preco: Double, val categoria: String)

val produtos = listOf(
    Produto("Notebook", 4500.0, "Eletrônicos"),
    Produto("Mouse", 150.0, "Eletrônicos"),
    Produto("Camiseta", 80.0, "Vestuário"),
    Produto("Calça Jeans", 220.0, "Vestuário")
)
```

### `forEach()`

  * **O que faz:** Executa uma ação para cada elemento da coleção, na ordem em que aparecem.
  * **O que retorna:** `Unit` (nada). É usada para causar "efeitos colaterais", como imprimir na tela.

<!-- end list -->

```kotlin
println("--- Lista de Produtos ---")
produtos.forEach { produto ->
    println("${produto.nome} - R$ ${produto.preco}")
}
```

### `map()`

  * **O que faz:** Transforma cada elemento da coleção em um novo elemento, criando uma **nova lista** com os resultados.
  * **O que retorna:** Uma `List` contendo os elementos transformados. A lista resultante sempre terá o mesmo tamanho da original.

<!-- end list -->

```kotlin
val nomesDosProdutos = produtos.map { it.nome }
// Resultado: ["Notebook", "Mouse", "Camiseta", "Calça Jeans"]
println(nomesDosProdutos)
```

### `filter()`

  * **O que faz:** Cria uma **nova lista** contendo apenas os elementos da coleção original que satisfazem uma determinada condição (predicado).
  * **O que retorna:** Uma `List` com os elementos que passaram no filtro. O tamanho pode ser menor ou igual ao da lista original.

<!-- end list -->

```kotlin
val eletronicos = produtos.filter { it.categoria == "Eletrônicos" }
// Resultado: [Produto(nome=Notebook, ...), Produto(nome=Mouse, ...)]
println(eletronicos)
```

### `groupBy()`

  * **O que faz:** Agrupa os elementos da coleção em um `Map`. As chaves do mapa são o resultado da função de agrupamento, e os valores são listas dos elementos correspondentes.
  * **O que retorna:** Um `Map<K, List<T>>`, onde `K` é o tipo da chave e `T` é o tipo dos elementos.

<!-- end list -->

```kotlin
val produtosPorCategoria = produtos.groupBy { it.categoria }
// Resultado: {Eletrônicos=[...], Vestuário=[...]}
println(produtosPorCategoria)
```

### `sortedBy()`

  * **O que faz:** Cria uma **nova lista** com os elementos da coleção original, ordenados de forma crescente com base em um seletor numérico ou alfabético.
  * **O que retorna:** Uma `List` ordenada.

<!-- end list -->

```kotlin
val ordenadoPorPreco = produtos.sortedBy { it.preco }
// Resultado: [Produto(nome=Camiseta, preco=80.0, ...), ...]
println(ordenadoPorPreco)
```

### `fold()`

  * **O que faz:** Acumula um valor a partir da coleção. Ele começa com um **valor inicial** e aplica uma operação a esse acumulador e a cada elemento da coleção, um por um.
  * **O que retorna:** O valor final do acumulador.

<!-- end list -->

```kotlin
// Vamos calcular o valor total do estoque de produtos
val valorInicial = 0.0
val valorTotalEstoque = produtos.fold(valorInicial) { acumulador, produto ->
    // A cada iteração, o novo valor do acumulador é o valor antigo + o preço do produto atual
    acumulador + produto.preco
}
// Resultado: 4500.0 + 150.0 + 80.0 + 220.0 = 4950.0
println("Valor total do estoque: R$ $valorTotalEstoque")
```
