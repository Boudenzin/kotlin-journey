# ViewModel e Estado no Jetpack Compose

No Jetpack Compose, a UI é uma função do estado (`UI = f(state)`). Gerenciar esse estado de forma eficiente e segura é crucial, especialmente em cenários complexos e diante de eventos do Android, como a rotação da tela. O `ViewModel`, parte dos Componentes de Arquitetura do Android, é a peça-chave para essa tarefa.

## 1\. O que é um `ViewModel`?

Um `ViewModel` é uma classe projetada para **armazenar e gerenciar dados relacionados à UI de forma consciente do ciclo de vida**. Suas duas principais responsabilidades são:

1.  **Manter o Estado da UI:** Ele guarda os dados que a sua tela precisa exibir (por exemplo, uma lista de itens, o texto de um campo de busca, o estado de um carregamento).
2.  **Expor a Lógica de Negócios:** Ele contém a lógica para modificar esse estado em resposta a eventos do usuário (por exemplo, o que acontece quando um botão é clicado, um texto é digitado ou dados são buscados da internet).

A principal vantagem do `ViewModel` é que ele **sobrevive a mudanças de configuração**. Quando o usuário gira a tela, a `Activity` é destruída e recriada, mas a instância do `ViewModel` associada a ela permanece intacta, preservando o estado da UI sem a necessidade de salvá-lo e restaurá-lo manualmente.

## 2\. O Fluxo de Dados Unidirecional (UDF)

A arquitetura recomendada ao usar `ViewModel` com Compose segue um padrão chamado **Fluxo de Dados Unidirecional** (Unidirectional Data Flow - UDF).

  * **O estado desce:** O `ViewModel` expõe o estado para a UI. A UI observa esse estado e se redesenha quando ele muda.
  * **Os eventos sobem:** A UI notifica o `ViewModel` sobre eventos do usuário (cliques, digitação, etc.), chamando funções públicas do `ViewModel`.

Este padrão torna o fluxo de dados explícito e previsível, facilitando a depuração e o teste.

## 3\. Expondo o Estado do `ViewModel` com `StateFlow`

A maneira idiomática e moderna de expor o estado de um `ViewModel` para o Compose é usando `StateFlow` do Kotlin Coroutines.

  * **`_uiState` (privado e mutável):** Dentro do `ViewModel`, usamos um `MutableStateFlow` para guardar e modificar o estado. O `_` no início do nome é uma convenção para indicar que é uma propriedade de uso interno.
  * **`uiState` (público e imutável):** O `ViewModel` expõe uma versão somente leitura (`StateFlow`) do estado para a UI, garantindo que apenas o `ViewModel` possa modificá-lo.

<!-- end list -->

```kotlin
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// 1. Defina uma data class para representar o estado da sua tela
data class ContadorUiState(
    val contagem: Int = 0,
    val estaCarregando: Boolean = false
)

// 2. Crie o ViewModel
class ContadorViewModel : ViewModel() {

    // _uiState privado e mutável, acessível apenas dentro do ViewModel
    private val _uiState = MutableStateFlow(ContadorUiState())

    // uiState público e imutável, exposto para a UI
    val uiState: StateFlow<ContadorUiState> = _uiState.asStateFlow()

    // 3. Crie funções públicas para que a UI possa notificar eventos
    fun incrementar() {
        // A função update garante a modificação atômica do estado
        _uiState.update { estadoAtual ->
            estadoAtual.copy(contagem = estadoAtual.contagem + 1)
        }
    }

    fun decrementar() {
        _uiState.update { estadoAtual ->
            estadoAtual.copy(contagem = estadoAtual.contagem - 1)
        }
    }
}
```

## 4\. Coletando o Estado na UI do Compose

A UI do Compose precisa "escutar" as atualizações do `StateFlow` exposto pelo `ViewModel`. Para fazer isso de forma segura em relação ao ciclo de vida, usamos a função `collectAsStateWithLifecycle`.

  * **Dependência:** Para usar `collectAsStateWithLifecycle`, você precisa da dependência `androidx.lifecycle:lifecycle-runtime-compose`.
  * **Como funciona:** Ela coleta os valores do `Flow` e os representa como um `State` do Compose. A coleta é automaticamente iniciada e interrompida de acordo com o ciclo de vida da UI, evitando desperdício de recursos.

<!-- end list -->

```kotlin
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TelaContador(
    // A função viewModel() obtém a instância correta do ViewModel
    contadorViewModel: ContadorViewModel = viewModel()
) {
    // 1. Coleta o estado do ViewModel. 
    // A UI será recomposta sempre que o uiState mudar.
    val uiState by contadorViewModel.uiState.collectAsStateWithLifecycle()

    // 2. A UI é construída com base no estado coletado
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Contagem: ${uiState.contagem}",
            fontSize = 32.sp
        )

        Row {
            // 3. Eventos do usuário chamam as funções do ViewModel
            Button(onClick = { contadorViewModel.incrementar() }) {
                Text(text = "+")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { contadorViewModel.decrementar() }) {
                Text(text = "-")
            }
        }
    }
}
```

## Resumo e Boas Práticas

  * **Separação de Responsabilidades:** Mantenha a lógica de negócios e o gerenciamento de estado no `ViewModel`. Deixe os Composables "burros" (stateless), responsáveis apenas por exibir o estado e emitir eventos.
  * **Sobrevivência a Mudanças de Configuração:** O `ViewModel` resolve nativamente o problema da perda de estado durante a rotação da tela.
  * **Fonte Única da Verdade:** O estado da tela reside em um único lugar (o `ViewModel`), evitando inconsistências.
  * **Testabilidade:** Com a lógica separada da UI, tanto o `ViewModel` quanto os Composables se tornam muito mais fáceis de testar de forma isolada.
  * **Ciclo de Vida:** Use `collectAsStateWithLifecycle` para consumir `Flows` do `ViewModel` de forma segura e eficiente.

A combinação `ViewModel` + `StateFlow` + `collectAsStateWithLifecycle` é a base da arquitetura moderna de apps Android com Jetpack Compose, criando um código escalável, robusto e de fácil manutenção.
