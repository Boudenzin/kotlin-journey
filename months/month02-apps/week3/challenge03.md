# Desafio Kotlin: Preço do Ingresso do Cinema

## Objetivo

Geralmente, o preço dos ingressos de cinema é diferente dependendo da idade dos usuários. Sua tarefa é escrever um programa que calcule e retorne o preço do ingresso com base na idade do espectador e se é ou não segunda-feira.

## Regras de Preço

O cálculo do preço do ingresso deve seguir estas regras:

* O preço do ingresso é **US$ 15** para pessoas com **até 12 anos**.
* O preço padrão do ingresso é **US$ 30** para pessoas com idade **entre 13 e 60 anos**.
* Às **segundas-feiras**, o ingresso padrão (idade entre 13 e 60) tem um desconto e custa **US$ 25**.
* O preço de **US$ 20** para idosos é válido para pessoas com **61 anos ou mais**. (Suponha uma idade máxima de 100 anos).
* Um valor de **-1** deve ser retornado para indicar um preço inválido se um usuário inserir uma idade fora da faixa especificada (por exemplo, menor que 0 ou maior que 100).

## Código Inicial

Este é o código que você usará como ponto de partida. Sua tarefa é preencher a função `ticketPrice`.

```kotlin
fun main() {
    val child = 5
    val adult = 28
    val senior = 87
    
    val isMonday = true
    
    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    // Fill in the code.
}
```

## Saída Esperada

Ao executar o programa com a função `ticketPrice` completa, a saída no console deve ser exatamente esta:

```
The movie ticket price for a person aged 5 is $15.
The movie ticket price for a person aged 28 is $25.
The movie ticket price for a person aged 87 is $20.
```

---

<details>
  <summary>Clique aqui para ver a Solução e a Explicação</summary>
  
  ### Solução Proposta

  A estrutura `when` do Kotlin é perfeita para lidar com múltiplas condições e intervalos, tornando o código limpo e legível. Podemos usá-la como uma expressão para retornar o valor diretamente.

  ```kotlin
  fun ticketPrice(age: Int, isMonday: Boolean): Int {
      return when(age) {
          // Verifica se a idade está no intervalo de 0 a 12
          in 0..12 -> 15
          
          // Verifica se a idade está no intervalo de 13 a 60
          in 13..60 -> {
              // Se for, verifica se é segunda-feira
              if (isMonday) {
                  25
              } else {
                  30
              }
          }
          
          // Verifica se a idade está no intervalo de 61 a 100
          in 61..100 -> 20
          
          // Para qualquer outro valor de idade (ex: negativo ou > 100)
          else -> -1
      }
  }
  ```

  ### Explicação

  1.  **`return when(age)`**: Usamos o `when` como uma expressão, o que significa que o valor do bloco correspondente será retornado pela função.
  2.  **`in 0..12 -> 15`**: A palavra-chave `in` verifica se a `age` está dentro do intervalo (`range`) de 0 a 12. Se for verdade, a expressão retorna `15`.
  3.  **`in 13..60 -> { ... }`**: Se a idade estiver entre 13 e 60, entramos em um bloco de código. Dentro dele, uma nova condição `if (isMonday)` verifica se o dia é segunda-feira para decidir entre o preço com desconto (`25`) e o preço padrão (`30`).
  4.  **`in 61..100 -> 20`**: Verifica o intervalo para idosos e, se for verdade, retorna `20`.
  5.  **`else -> -1`**: Esta é a ramificação padrão. Se a idade não se encaixar em nenhum dos intervalos anteriores (por ser negativa ou acima de 100), a expressão retorna `-1`, indicando um valor inválido.

</details>