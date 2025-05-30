# Gerenciamento de Estado em Jetpack Compose

O gerenciamento de estado em Compose é fundamental para construir UIs dinâmicas e reativas. Basicamente, **estado** é qualquer valor que pode mudar ao longo do tempo e, consequentemente, afetar a UI. Compose observa essas mudanças e redesenha automaticamente as partes da tela que dependem desse estado.

## Conceitos Chave 🔑

Existem alguns conceitos centrais para entender o gerenciamento de estado em Compose:

### 1. `State` e `MutableState`

* `State<T>`: É um tipo que encapsula um valor e notifica o Compose quando esse valor muda. Ele é **imutável** (somente leitura) para quem o recebe.
* `MutableState<T>`: É uma interface que estende `State<T>` e adiciona uma propriedade `value` que pode ser **modificada**. É através dela que você altera o estado.

Você geralmente cria um `MutableState` usando a função `mutableStateOf()`:

```kotlin
val count: MutableState<Int> = mutableStateOf(0)
// ou de forma mais idiomática:
var count by mutableStateOf(0) // Usando delegação de propriedade do Kotlin
```

Quando `count.value` (ou `count` se estiver usando `by`) é alterado, qualquer Composable que esteja lendo esse estado será **recomposto** (redesenhado).

### 2. `remember`

Composable functions podem ser chamadas várias vezes durante a recomposição. Para **manter o estado** entre essas recomposições (e não perdê-lo toda vez que a função é chamada novamente), você usa a função `remember`.

```kotlin
var count by remember { mutableStateOf(0) }
```

O `remember` "lembra" do valor produzido pelo bloco de código `{ mutableStateOf(0) }` na composição inicial e o reutiliza nas recomposições subsequentes. Se a composição for descartada (por exemplo, ao sair da tela), o estado lembrado também é esquecido.

### 3. Recomposição Inteligente 🧠

Compose é inteligente sobre a recomposição. Ele só redesenha os Composables que **realmente mudaram** ou que dependem de um estado que mudou. Isso torna a UI eficiente, pois evita redesenhar a tela inteira desnecessariamente.

### 4. Elevação de Estado (State Hoisting) 🚀

Este é um padrão muito importante em Compose. A ideia é **mover o estado para cima na árvore de Composables**, para um ancestral comum dos Composables que precisam ler ou modificar esse estado.

**Benefícios:**

* **Fonte Única de Verdade (Single Source of Truth):** Evita inconsistências, pois o estado reside em um único lugar.
* **Encapsulamento:** Os Composables filhos se tornam "burros" (stateless), recebendo o estado e callbacks para modificá-lo via parâmetros. Isso os torna mais reutilizáveis e testáveis.
* **Compartilhamento de Estado:** Permite que múltiplos Composables observem e modifiquem o mesmo estado.

**Como funciona:**

1.  O Composable que possui o estado (o ancestral) passa o valor do estado para os filhos.
2.  Ele também passa lambdas (funções) que os filhos podem chamar para solicitar uma mudança no estado.

**Exemplo:**

```kotlin
@Composable
fun MyScreen() {
    var name by remember { mutableStateOf("") }

    // NameInput é "burro" (stateless)
    NameInput(
        name = name, // Passa o estado atual
        onNameChange = { newName -> name = newName } // Passa um callback para mudar o estado
    )
    DisplayName(name = name) // Passa o estado atual
}

@Composable
fun NameInput(name: String, onNameChange: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = onNameChange, // Chama o callback quando o valor do TextField muda
        label = { Text("Nome") }
    )
}

@Composable
fun DisplayName(name: String) {
    Text(text = "Olá, $name!")
}
```

No exemplo acima, `MyScreen` "eleva" o estado `name` e o controla. `NameInput` e `DisplayName` são Composables sem estado próprio, apenas recebem dados e emitem eventos.

## Tipos de Estado e Onde Armazená-los

* **Estado da UI (UI State):** O que está na tela. Geralmente gerenciado com `remember` e elevação de estado dentro dos Composables ou, para lógicas mais complexas, em `ViewModel`s.
* **Lógica de UI (UI Logic) / Lógica de Negócios (Business Logic):** Como o estado da UI muda em resposta a eventos. Pode estar em lambdas de eventos nos Composables (para lógica simples) ou, mais comumente, em `ViewModel`s (para lógica mais complexa, acesso a repositórios, etc.).

## ViewModel para Estado Complexo e Lógica de Negócios

Quando o estado é mais complexo, precisa sobreviver a mudanças de configuração (como rotação da tela), ou envolve lógica de negócios, o ideal é usar um `ViewModel` do Android Architecture Components.

* O `ViewModel` mantém o estado (geralmente usando `StateFlow` ou `LiveData` que podem ser convertidos para `State` em Compose com `collectAsState()`).
* Os Composables observam o estado do `ViewModel`.
* Eventos da UI são enviados para o `ViewModel` para processar e atualizar o estado.

**Exemplo com ViewModel:**

```kotlin
// No ViewModel
class MyViewModel : ViewModel() {
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    fun onNameChange(newName: String) {
        _name.value = newName
    }
}

// No Composable
@Composable
fun MyScreenWithViewModel(myViewModel: MyViewModel = viewModel()) { // viewModel() é um helper do Compose
    val name by myViewModel.name.collectAsState() // Coleta o StateFlow como State

    NameInput(
        name = name,
        onNameChange = { myViewModel.onNameChange(it) }
    )
    DisplayName(name = name)
}
```

## Em resumo, o gerenciamento de estado em Compose se baseia em:

* Usar `mutableStateOf` para criar estado observável.
* Usar `remember` para manter o estado durante recomposições.
* Elevar o estado para ancestrais comuns (State Hoisting) para melhor organização e reutilização.
* Utilizar `ViewModel`s para gerenciar estados mais complexos, lógicas de negócios e para que o estado sobreviva a mudanças de configuração.

Essa abordagem torna o desenvolvimento de UIs em Android mais declarativo, intuitivo e menos propenso a erros relacionados ao estado.
