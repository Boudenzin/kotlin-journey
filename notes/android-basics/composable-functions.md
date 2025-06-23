# Funções Composable em Jetpack Compose

As funções Composable são o **elemento básico** de uma interface de usuário no Jetpack Compose. Elas possuem as seguintes características:

* Descrevem uma parte da sua interface (UI).
* Não retornam nenhum valor (são funções `Unit`).
* Recebem dados como entrada e geram o que será exibido na tela.

## A Anotação `@Composable`

Toda função Composable **obrigatoriamente** precisa ser marcada com a anotação `@Composable`. Essa anotação informa ao compilador do Compose que a função tem como objetivo transformar dados em interface gráfica.

### Exemplo Básico

O snippet de código abaixo demonstra uma função Composable simples que recebe um parâmetro `name` (String) e o utiliza para renderizar um elemento de texto na tela:

```kotlin
@Composable
fun Greeting(name: String) {
    Text(text = "Olá $name!")
}
```

## Boas Práticas de Nomenclatura

É uma prática recomendada nomear as funções Composable de forma que descrevam claramente a funcionalidade ou o elemento de UI que representam.

### Padrão PascalCase

Funções Composable que não retornam valor (a grande maioria) e carregam a anotação `@Composable` **DEVEM** ser nomeadas usando o padrão **PascalCase**. Esta é uma convenção de nomenclatura onde a primeira letra de cada palavra em um nome composto é maiúscula.

### Diretrizes para Nomes de Funções Composable:

* **PRECISA ser um substantivo ou frase nominal descritiva:**
    * Exemplo: `DoneButton()`
    * Exemplo: `UserProfilePhoto()`
    * Substantivos **PODEM** ter adjetivos descritivos como prefixos: `RoundIcon()`, `DetailedUserCard()`

* **NÃO PODE ser um verbo ou frase verbal:**
    * Evitar: `DrawTextField()` (Prefira: `TextField` ou `CustomTextField`)

* **NÃO PODE ser uma preposição nominal (evitar nomes muito genéricos ou que soem como modificadores):**
    * Evitar: `TextFieldWithLink()` (Prefira: `LinkedTextField` ou algo mais específico sobre o propósito)

* **NÃO PODE ser um adjetivo isolado:**
    * Evitar: `Bright()` (Prefira: `BrightBackground` ou `BrightText`)

* **NÃO PODE ser um advérbio isolado:**
    * Evitar: `Outside()` (Prefira: `ExternalContentArea` ou similar, dependendo do contexto)

## Unidades de Medida em Compose

Elementos da interface em apps Android usam principalmente duas unidades de medida diferentes no Compose:

### Pixels Escaláveis (`sp`)

* **Definição:** `sp` (Scalable Pixels ou Pixels Escalonáveis) é a unidade de medida recomendada para **tamanhos de fonte**.
* **Comportamento:** Por padrão, a unidade `sp` tem o mesmo tamanho que a `dp`. No entanto, ela é redimensionada com base na preferência de tamanho de texto definida pelo usuário nas configurações do sistema Android. Isso garante acessibilidade, permitindo que os usuários ajustem o tamanho do texto conforme sua necessidade visual.
* **Propriedade de Extensão:** Em Kotlin para Compose, `.sp` é uma propriedade de extensão para tipos numéricos como `Int`, `Float`, e `Double`, facilitando a conversão direta para esta unidade.
    * Exemplo: `fontSize = 16.sp`
* **Importação:** O Android Studio pode destacar o código `.sp` se a importação necessária (`androidx.compose.ui.unit.sp`) não estiver presente. Certifique-se de que ela foi adicionada ao seu arquivo.

#### `lineHeight` e `sp`

A propriedade `lineHeight` (altura da linha) em um `Text` Composable também utiliza `sp`. Especificar uma `lineHeight` adequada pode ser crucial para evitar a sobreposição de texto, especialmente com fontes maiores ou textos multilinhas.

* Exemplo de atualização para incluir altura da linha:
    ```kotlin
    Text(
        text = "Algum texto longo que pode precisar de mais espaço entre as linhas.",
        fontSize = 20.sp,
        lineHeight = 30.sp // Exemplo de lineHeight
    )
    ```
    No seu exemplo anterior, você mencionou `lineHeight = 116.sp`. Este seria um valor específico para um `fontSize` provavelmente grande, como o `70.sp` no exemplo original do `GreetingText`.

### Pixels de Densidade Independente (`dp`)

* **Definição:** `dp` ou `dip` (Density-Independent Pixels ou Pixels de Densidade Independente) é a unidade de medida para **dimensões de layout** (largura, altura, padding, margens, etc.).
* **Comportamento:** `dp` é uma unidade abstrata baseada na densidade física da tela. Um `dp` é equivalente a um pixel físico em uma tela de 160 dpi (dots per inch). O sistema Android escala os valores em `dp` apropriadamente para diferentes densidades de tela, garantindo que os elementos da UI tenham um tamanho físico consistente em diferentes dispositivos.
