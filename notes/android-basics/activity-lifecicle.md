# O Ciclo de Vida da Activity (Activity Lifecycle) no Android

O **Ciclo de Vida da Activity** é um conjunto de estados pelos quais uma `Activity` (uma tela do seu aplicativo) pode passar desde o momento em que é criada até ser destruída. O sistema operacional Android gerencia esses estados, e entender esse ciclo é crucial para construir aplicativos robustos, eficientes e sem bugs.

## Por que o Ciclo de Vida é Importante?

O Android é um sistema operacional multitarefa. A qualquer momento, o sistema pode decidir pausar, parar ou destruir sua `Activity` para liberar memória para outros aplicativos ou para responder a eventos como uma chamada telefônica ou uma mudança na orientação da tela.

Você **precisa** usar os métodos de callback do ciclo de vida para:

  * **Evitar falhas:** Não travar o app quando o usuário recebe uma ligação ou muda de tela.
  * **Não perder dados:** Salvar o progresso do usuário quando ele sai do seu app e retorna mais tarde.
  * **Gerenciar recursos:** Liberar recursos do sistema (como sensores, câmera ou conexões de rede) quando eles não são necessários, para economizar bateria e memória.
  * **Não consumir recursos em segundo plano:** Pausar operações pesadas quando o app não está visível para o usuário.

## O Diagrama do Ciclo de Vida

O ciclo de vida pode ser visualizado como uma pirâmide ou um fluxograma. Os métodos são chamados em uma sequência específica dependendo do que está acontecendo com a `Activity`.

**Fluxo Básico:**

1.  **Lançamento:** `onCreate()` → `onStart()` → `onResume()` (A `Activity` está visível e em primeiro plano).
2.  **Saindo da tela:** `onPause()` → `onStop()` (A `Activity` não está mais visível).
3.  **Retornando à tela:** `onRestart()` → `onStart()` → `onResume()` (A `Activity` volta a ser visível e em primeiro plano).
4.  **Fechando o app:** `onPause()` → `onStop()` → `onDestroy()` (A `Activity` é destruída).

*(Este é o diagrama oficial do Google, que ilustra perfeitamente o fluxo)*

-----

## Os Métodos de Callback (Callbacks do Ciclo de Vida)

Cada transição de estado é notificada através de um método de callback que você pode sobrescrever na sua classe `Activity`.

### `onCreate()`

  * **Quando é chamado?** Apenas **uma vez**, quando a `Activity` é criada pela primeira vez.
  * **O que fazer aqui?** É aqui que você deve fazer toda a inicialização essencial:
      * Inflar o layout da interface (com `setContentView()`).
      * Inicializar `ViewModels`, `Adapters` e outras classes essenciais.
      * Configurar `Listeners` de cliques.
      * Restaurar o estado salvo (do `Bundle savedInstanceState`).

<!-- end list -->

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    // Inicialize seus componentes aqui
}
```

### `onStart()`

  * **Quando é chamado?** Quando a `Activity` está prestes a se tornar visível para o usuário.
  * **O que fazer aqui?** Tarefas que preparam a UI para ser interativa. Geralmente, o código aqui é mínimo, pois `onResume` é chamado logo em seguida.

### `onResume()`

  * **Quando é chamado?** Quando a `Activity` está em primeiro plano e o usuário pode interagir com ela. É o estado "ativo".
  * **O que fazer aqui?** Iniciar recursos que precisam estar ativos apenas quando a tela está em foco.

### `onPause()`

  * **Quando é chamado?** Quando a `Activity` está prestes a sair de primeiro plano. Isso pode acontecer porque outra `Activity` está sendo aberta por cima, ou o usuário pressionou o botão "Home".
  * **O que fazer aqui?** Pausar operações que não devem continuar enquanto a `Activity` não está em foco. O código aqui deve ser **muito rápido**.

### `onStop()`

  * **Quando é chamado?** Quando a `Activity` não está mais visível para o usuário.
  * **O que fazer aqui?** Liberar recursos que não são necessários quando o app não está visível.

### `onRestart()`

  * **Quando é chamado?** Quando uma `Activity` que estava parada (`onStop`) está prestes a ser iniciada novamente.
  * **O que fazer aqui?** Restaurar o estado da `Activity`, se necessário. Este método é sempre seguido por `onStart()`.

### `onDestroy()`

  * **Quando é chamado?** Apenas **uma vez**, antes da `Activity` ser destruída. Isso acontece quando o usuário fecha a `Activity` (pressionando "Voltar") ou quando o sistema a destrói para liberar memória.
  * **O que fazer aqui?** Limpeza final de todos os recursos.

-----

## Visualizando o Ciclo de Vida com Logs

A melhor maneira de entender e depurar o ciclo de vida é adicionar logs a cada um dos callbacks. Isso permite que você veja no **Logcat** do Android Studio exatamente quando cada método é chamado.

A classe `Log` do Android é usada para isso, com o método `d()` (debug) sendo o mais comum. É uma boa prática definir uma `TAG` constante para filtrar as mensagens facilmente.

**Exemplo Prático de Logging:**

```kotlin
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Define uma constante para a TAG do Log
    private val TAG = "MainActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate chamado")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart chamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume chamado")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause chamado")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop chamado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart chamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy chamado")
    }
}
```

> **Teste você mesmo:** Adicione este código à sua `MainActivity`, execute o app e observe o Logcat. Gire a tela, pressione o botão "Home" e volte para o app. Você verá a sequência exata de chamadas do ciclo de vida\!

-----

## Lidando com Mudanças de Configuração e Estado (Views vs. Compose)

Um caso especial e muito comum é a **mudança de configuração**, como a rotação da tela. Quando isso acontece, o Android, por padrão, **destrói e recria** a `Activity`. Se você não tratar isso, os dados na tela serão perdidos.

### A Solução no Sistema de Views: `onSaveInstanceState`

  * **`onSaveInstanceState(outState: Bundle)`**: Este método é chamado antes de a `Activity` ser destruída. Use-o para salvar pequenos dados de estado em um `Bundle`.
  * **`onCreate(savedInstanceState: Bundle?)`**: O `Bundle` que você salvou é passado para o `onCreate` da nova `Activity`. Você pode verificar se `savedInstanceState` não é nulo e restaurar seus dados.

### A Solução no Jetpack Compose: `rememberSaveable`

No Jetpack Compose, o conceito é muito mais simples. Em vez de gerenciar manualmente os bundles, usamos o composable **`rememberSaveable`**.

`rememberSaveable` funciona como o `remember`, mas com um superpoder: ele **salva automaticamente o estado** em um `Bundle` para sobreviver a mudanças de configuração e até mesmo à morte do processo pelo sistema.

```kotlin
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun ContadorPersistente() {
    // Use 'rememberSaveable' em vez de 'remember'
    // O estado 'contador' agora sobreviverá à rotação da tela
    var contador by rememberSaveable { mutableStateOf(0) }

    Button(onClick = { contador++ }) {
        Text(text = "Contador: $contador")
    }
}
```

Para a maioria dos tipos de dados primitivos e `data classes` anotadas com `@Parcelize`, o `rememberSaveable` funciona sem configuração adicional, tornando o gerenciamento de estado em Compose muito mais direto.

## A Abordagem Moderna com Jetpack Lifecycle

Os componentes de Arquitetura do Android Jetpack (como `ViewModel` e `LifecycleObserver`) simplificam ainda mais o gerenciamento do ciclo de vida.

  * **`ViewModel`**: Sobrevive automaticamente a mudanças de configuração. É o local ideal para armazenar e gerenciar os dados da UI, eliminando a necessidade de usar `onSaveInstanceState` ou `rememberSaveable` para dados complexos e lógica de negócios.
  * **`LifecycleObserver`**: Permite que classes separadas observem os eventos do ciclo de vida de uma `Activity` ou `Fragment`, movendo a lógica de gerenciamento de recursos para fora da `Activity` e tornando o código mais limpo e testável.
