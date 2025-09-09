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
  * **O que fazer aqui?** Iniciar recursos que precisam estar ativos apenas quando a tela está em foco:
      * Começar a tocar animações ou vídeos.
      * Registrar `BroadcastReceivers`.
      * Inicializar a câmera ou sensores.

### `onPause()`

  * **Quando é chamado?** Quando a `Activity` está prestes a sair de primeiro plano. Isso pode acontecer porque outra `Activity` (até mesmo uma caixa de diálogo) está sendo aberta por cima, ou o usuário pressionou o botão "Home".
  * **O que fazer aqui?** Pausar operações que não devem continuar enquanto a `Activity` não está em foco. O código aqui deve ser **muito rápido**.
      * Salvar dados não salvos (como um rascunho de e-mail).
      * Pausar animações ou vídeos.
      * Liberar recursos exclusivos, como a câmera.

### `onStop()`

  * **Quando é chamado?** Quando a `Activity` não está mais visível para o usuário.
  * **O que fazer aqui?** Liberar recursos que não são necessários quando o app não está visível.
      * Cancelar requisições de rede.
      * Desregistrar `BroadcastReceivers`.
      * Realizar operações de "desligamento" mais pesadas que não puderam ser feitas em `onPause()`.

### `onRestart()`

  * **Quando é chamado?** Quando uma `Activity` que estava parada (`onStop`) está prestes a ser iniciada novamente.
  * **O que fazer aqui?** Restaurar o estado da `Activity`, se necessário. Este método é sempre seguido por `onStart()`.

### `onDestroy()`

  * **Quando é chamado?** Apenas **uma vez**, antes da `Activity` ser destruída. Isso acontece quando o usuário fecha a `Activity` (pressionando "Voltar") ou quando o sistema a destrói para liberar memória.
  * **O que fazer aqui?** Limpeza final de todos os recursos.
      * Liberar `ViewModels`.
      * Cancelar todas as operações em segundo plano.

## Lidando com Mudanças de Configuração e Estado

Um caso especial e muito comum é a **mudança de configuração**, como a rotação da tela. Quando isso acontece, o Android, por padrão, **destrói e recria** a `Activity` (`onDestroy()` → `onCreate()`).

Se você não tratar isso, todos os dados na tela (como o texto em um `EditText` ou a posição de uma `ScrollView`) serão perdidos.

  * **`onSaveInstanceState(outState: Bundle)`**: Este método é chamado antes de a `Activity` ser destruída (geralmente após `onPause()`). Use-o para salvar pequenos dados de estado em um `Bundle`.
  * **`onCreate(savedInstanceState: Bundle?)`**: O `Bundle` que você salvou em `onSaveInstanceState` é passado para o `onCreate` da nova `Activity`. Você pode verificar se `savedInstanceState` não é nulo e restaurar seus dados.

## A Abordagem Moderna com Jetpack Lifecycle

Os componentes de Arquitetura do Android Jetpack (como `ViewModel` e `LifecycleObserver`) simplificam o gerenciamento do ciclo de vida.

  * **`ViewModel`**: Sobrevive automaticamente a mudanças de configuração. É o local ideal para armazenar e gerenciar os dados da UI, eliminando a necessidade de usar `onSaveInstanceState` para dados complexos.
  * **`LifecycleObserver`**: Permite que classes separadas observem os eventos do ciclo de vida de uma `Activity` ou `Fragment`, movendo a lógica de gerenciamento de recursos para fora da `Activity` e tornando o código mais limpo e testável.
