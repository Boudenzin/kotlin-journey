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

```
WIP - EM CONSTRUÇÃO
```
