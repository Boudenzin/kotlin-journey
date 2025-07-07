# Jetpack Compose: Entendendo Button e remember

Jetpack Compose é um kit de ferramentas moderno e declarativo para criar interfaces de usuário nativas no Android. Dois dos conceitos mais fundamentais que você encontrará ao começar são o composable `Button` e a função `remember`. Vamos explorar cada um deles e, mais importante, como eles trabalham juntos.

## 1. O Composable `Button`

O `Button` é um dos componentes mais básicos de qualquer UI. É um elemento clicável que o usuário utiliza para acionar uma ação.

### Uso Básico

A forma mais simples de criar um botão é fornecer duas coisas:
1.  A ação a ser executada no clique (`onClick`).
2.  O conteúdo do botão, que é o que o usuário vê (geralmente um `Text`).

```kotlin
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BotaoSimples() {
    Button(onClick = { 
        // Esta ação é executada quando o botão é clicado
        println("Botão clicado!") 
    }) {
        // Este é o conteúdo visual do botão
        Text("Clique aqui")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBotaoSimples() {
    BotaoSimples()
}
```
Neste exemplo, sempre que o botão for pressionado, a mensagem "Botão clicado!" aparecerá no Logcat do Android Studio. Simples, certo?

## 2. O Problema: Por que o Botão Sozinho não Muda a Tela?

Agora, vamos tentar fazer algo mais interativo: um contador. Queremos que um número na tela aumente cada vez que o botão for clicado. Um iniciante poderia tentar fazer assim:

```kotlin
@Composable
fun BotaoContador_Quebrado() {
    var contador = 0 // Variável local padrão

    Button(onClick = { 
        contador++ 
        println("Contador agora é: $contador") // O log vai funcionar
    }) {
        Text("Cliques: $contador") // O texto na tela NÃO vai mudar
    }
}
```
Se você executar este código, verá algo interessante:
* No Logcat, o valor de `contador` aumenta a cada clique (`1`, `2`, `3`...).
* Na tela, o texto do botão **sempre permanecerá "Cliques: 0"**.

**Por que isso acontece?** Por causa da forma como o Compose funciona. Compose executa suas funções `@Composable` para "desenhar" a UI. Quando o estado de uma aplicação muda, ele "recompõe" (executa novamente) as funções afetadas para refletir a mudança.

O problema é que uma variável local comum como `var contador = 0` não é um "estado" que o Compose observa. Alterá-la não informa ao Compose que ele precisa redesenhar a tela. A função não é chamada novamente, e a UI não é atualizada.

## 3. A Solução: `remember` para Memorizar o Estado

É aqui que `remember` entra em cena. A função `remember` diz ao Compose: "Guarde este valor na memória e não o perca se esta função for recomposta".

Mas `remember` sozinho não basta. Precisamos combinar com `mutableStateOf` para criar um estado que o Compose possa observar.

* `mutableStateOf(valorInicial)`: Cria um "estado observável". Sempre que o valor dele mudar, o Compose agenda uma recomposição de todos os composables que leem esse estado.
* `remember { mutableStateOf(...) }`: Combina os dois. Cria um estado observável e garante que ele sobreviva às recomposições.

### A Combinação Perfeita: `Button` + `remember`

Vamos corrigir nosso contador usando o padrão correto:

```kotlin
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BotaoContadorCorreto() {
    // 1. Declaramos uma variável de estado usando remember e mutableStateOf
    // O 'by' é um "delegate" que nos poupa de escrever .value toda vez.
    var contador by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 2. O Text lê o estado. Ele será recomposto quando 'contador' mudar.
        Text(text = "Cliques: $contador")
        
        // 3. O Button atualiza o estado. Isso aciona a recomposição.
        Button(onClick = { 
            contador++ 
        }) {
            Text("Incrementar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBotaoContador() {
    BotaoContadorCorreto()
}

```

### Como Funciona (Passo a Passo):

1.  **Inicialização:** `BotaoContadorCorreto` é executado. A linha `var contador by remember { mutableStateOf(0) }` cria um estado que armazena o valor `0`. O `Text` lê esse valor e exibe "Cliques: 0".
2.  **Clique do Usuário:** Você clica no `Button`.
3.  **Ação `onClick`:** O código `contador++` é executado. Como `contador` é um `MutableState`, essa atribuição notifica o Compose: "Ei, um estado que você está observando mudou!".
4.  **Recomposição:** O Compose identifica todos os composables que leem `contador` (neste caso, o `Column` e seu conteúdo) e os agenda para recomposição.
5.  **Redesenho:** A função `BotaoContadorCorreto` é executada novamente. Desta vez, `remember` vê que já tem um valor guardado para `contador` (que agora é `1`) e o retorna.
6.  **Atualização da UI:** O `Text` lê o novo valor de `contador` (`1`) e exibe na tela "Cliques: 1". A UI foi atualizada!

## Resumo

| Conceito | Para que serve? | Exemplo |
| :--- | :--- | :--- |
| **`Button`** | Para capturar a **ação** do usuário através de um clique. | `Button(onClick = { /* faça algo */ })` |
| **`remember`** | Para **manter** um valor ou objeto na memória através das recomposições. | `remember { ... }` |
| **`mutableStateOf`** | Para criar um **estado observável** que, ao ser alterado, **aciona** a recomposição. | `mutableStateOf(0)` |

A combinação **`Button` (para modificar o estado) + `remember { mutableStateOf(...) }` (para guardar o estado)** é o padrão fundamental para criar qualquer UI interativa e dinâmica em Jetpack Compose.
