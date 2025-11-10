# 🎨 UI State no Jetpack Compose: Guia Completo

O **UI State** (Estado da UI) é uma descrição imutável de "o que" a tela precisa exibir em um determinado momento. Em Jetpack Compose, o estado flui para baixo e os eventos (como cliques) fluem para cima, seguindo o padrão de **Fluxo de Dados Unidirecional (UDF)**.

## 1\. 🔍 Conceitos Fundamentais

### 1.1. State (Estado)

O estado é qualquer valor que pode mudar ao longo do tempo. Quando o estado muda, a Composable (função que desenha a UI) que lê esse estado é automaticamente reexecutada (Recomposition).

### 1.2. Recomposition (Recomposição)

É o processo de reexecutar as funções Composable afetadas quando o estado de que elas dependem muda. O Compose se encarrega de atualizar apenas as partes da UI que precisam ser alteradas, tornando-o eficiente.

### 1.3. UI State (Estado da UI)

É a classe que contém **todos os dados** necessários para renderizar a UI. Deve ser imutável (`data class` no Kotlin) para garantir uma fonte única de verdade e rastreabilidade das mudanças.

  * **Exemplo:**
    ```kotlin
    data class HomeScreenUiState(
        val isLoading: Boolean = false,
        val newsArticles: List<Article> = emptyList(),
        val errorMessage: String? = null
    )
    ```

### 1.4. Hoisting de Estado (State Hoisting)

É o padrão de mover o estado de um Composable para o seu chamador, tornando o Composable chamado **Stateless (Sem Estado)** e mais reutilizável. O Composable sem estado recebe o estado via parâmetros e expõe eventos (como `onClick`) via funções.

## 2\. 🗂️ Tipos de Estado

Existem dois tipos principais de estado na UI:

| Tipo de Estado | Descrição | Exemplo | Onde armazenar |
| :--- | :--- | :--- | :--- |
| **Estado da Tela (Screen UI State)** | Dados que o aplicativo busca de outras camadas (Domínio/Dados) para exibir na tela. Contém a lógica de negócio. | Lista de artigos, detalhes de um produto, perfil do usuário. | **ViewModel** (usando `StateFlow` ou `LiveData`) |
| **Estado do Elemento da UI (UI Element State)** | Propriedades intrínsecas a um elemento da UI que afetam como ele é renderizado. | O estado de *expansão* de um card, se um `TextField` está *focado*. | Dentro do Composable (usando `remember` ou um **State Holder** simples). |

## 3\. 💾 Gerenciamento de Estado Local (`remember`)

Para o **Estado do Elemento da UI**, você usa as APIs do Compose para manter o estado vivo durante as recomposições.

### 3.1. `remember`

Armazena um objeto na Composição para que ele persista durante as recomposições.

```kotlin
@Composable
fun Counter() {
    // Estado persiste através de recomposições
    var count by remember { mutableStateOf(0) }

    Button(onClick = { count++ }) {
        Text("Count: $count")
    }
}
```

### 3.2. `mutableStateOf`

Cria um objeto observável (`MutableState<T>`). Quando o valor (`.value`) muda, as Composables que leem esse estado são recompostas.

### 3.3. `rememberSaveable`

Funciona como `remember`, mas o estado sobrevive a **mudanças de configuração** (como rotação de tela) e à **morte do processo** do sistema operacional. Use para estados essenciais que precisam ser mantidos.

```kotlin
var name by rememberSaveable { mutableStateOf("") }
```

## 4\. 🧠 Gerenciamento de Estado da Tela (`ViewModel`)

Para o **Estado da Tela** (que envolve lógica de negócio), o **ViewModel** é o *State Holder* recomendado.

### 4.1. `ViewModel`

Armazena o estado e processa a lógica de negócio, sobrevivendo a mudanças de configuração.

### 4.2. `StateFlow` ou `LiveData`

O ViewModel expõe o **UI State Imutável** usando um desses *State Holders* observáveis, garantindo que o Composable receba apenas dados para leitura.

  * **No ViewModel:**

    ```kotlin
    class HomeViewModel : ViewModel() {
        private val _uiState = MutableStateFlow(HomeScreenUiState())
        val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

        fun loadData() {
            viewModelScope.launch {
                _uiState.update { it.copy(isLoading = true) }
                // ... lógica de carregamento
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        newsArticles = loadedArticles 
                    ) 
                }
            }
        }
    }
    ```

  * **No Composable:**

    ```kotlin
    @Composable
    fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
        // Observa o StateFlow e coleta o estado de forma segura.
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            NewsList(articles = state.newsArticles)
        }
    }
    ```

## 5\. 🧱 Modelando o UI State com `Sealed Class` ou `Sealed Interface`

Para estados mais complexos que representam diferentes "telas" (Carregando, Sucesso, Erro, Vazio), o uso de `sealed class` ou `sealed interface` é altamente recomendado.

```kotlin
sealed interface ArticleUiState {
    object Loading : ArticleUiState
    data class Success(val articles: List<Article>) : ArticleUiState
    data class Error(val message: String) : ArticleUiState
    object Empty : ArticleUiState
}

// No Composable:
@Composable
fun ArticleScreen(state: ArticleUiState) {
    when (state) {
        ArticleUiState.Loading -> LoadingIndicator()
        is ArticleUiState.Success -> ArticlesList(state.articles)
        is ArticleUiState.Error -> ErrorMessage(state.message)
        ArticleUiState.Empty -> EmptyStateView()
    }
}
```

-----

## 6\. 🔄 Resumo e Melhores Práticas

1.  **Imutabilidade:** O `UI State` deve ser sempre uma **classe de dados imutável** (`data class`), forçando o uso do método `copy()` para todas as atualizações.
2.  **UDF:** O estado flui **para baixo** (do ViewModel para o Composable), e os eventos fluem **para cima** (do Composable para o ViewModel).
3.  **Single Source of Truth (SSOT):** Mantenha o estado em um único lugar, geralmente no ViewModel para a lógica de tela.
4.  **Separar Responsabilidades:**
      * **ViewModel:** Responsável por manter o `UI State` da tela e executar a lógica de negócio.
      * **Composable:** Responsável apenas por **renderizar** a UI com base no `UI State` recebido e expor **Eventos** do usuário.
5.  **Use `remember`:** Para o estado local da UI que não precisa sobreviver à morte do processo (ex: um toggle de expansão).

Este guia cobre o essencial para implementar o gerenciamento de `UI State` de forma robusta no Jetpack Compose\!

-----

