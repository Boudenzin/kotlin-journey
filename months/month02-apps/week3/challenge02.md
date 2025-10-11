# Desafio Kotlin: Notificações para Dispositivos Móveis

## Objetivo

Normalmente, o smartphone oferece um resumo das notificações. Sua tarefa é escrever a lógica para exibir essa mensagem de resumo com base no número de notificações recebidas.

## Requisitos

A mensagem de resumo precisa incluir:

* O número **exato** de notificações quando for menor que 100.
* A mensagem **`99+`** como o número de notificações quando houver 100 ou mais.

## Código Inicial

Este é o código que você usará como ponto de partida. Sua tarefa é preencher a função `printNotificationSummary`.

```kotlin
fun main() {
    val morningNotification = 51
    val eveningNotification = 135

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)
}

fun printNotificationSummary(numberOfMessages: Int) {
    // Fill in the code.
}
```

## Saída Esperada

Ao executar o programa com a função `printNotificationSummary` completa, a saída no console deve ser exatamente esta:

```
You have 51 notifications.
Your phone is blowing up! You have 99+ notifications.
```

---

<details>
  <summary>Clique aqui para ver a Solução e a Explicação</summary>
  
  ### Solução Proposta

  Para resolver o desafio, podemos usar uma estrutura condicional `if/else` dentro da função `printNotificationSummary` para verificar se o número de mensagens é menor que 100.

  ```kotlin
  fun printNotificationSummary(numberOfMessages: Int) {
      if (numberOfMessages < 100) {
          // Se for menor que 100, exibe a contagem exata.
          println("You have $numberOfMessages notifications.")
      } else {
          // Se for 100 ou mais, exibe 99+ e uma mensagem diferente.
          println("Your phone is blowing up! You have 99+ notifications.")
      }
  }
  ```

  ### Explicação

  1.  **`if (numberOfMessages < 100)`**: Esta linha verifica a condição.
  2.  **`println("You have $numberOfMessages notifications.")`**: Este bloco é executado se a condição for verdadeira. Ele usa um *template string* para inserir o valor exato da variável `numberOfMessages` na mensagem.
  3.  **`else`**: Se a condição do `if` for falsa (ou seja, `numberOfMessages` é 100, 101, 135, etc.), o bloco de código do `else` é executado.
  4.  **`println("Your phone is blowing up! You have 99+ notifications.")`**: Este bloco imprime a mensagem para um grande volume de notificações, usando o texto estático "99+" como solicitado.

</details>