# 🧱 UI Básica com Jetpack Compose

Este documento reúne os componentes mais usados em interfaces com Jetpack Compose e como usá-los de forma simples.

---

## 📄 Text

```kotlin
Text(text = "Olá, mundo!")
````

---

## 🖼️ Image

```kotlin
Image(
    painter = painterResource(id = R.drawable.exemplo),
    contentDescription = "Minha imagem"
)
```

---

## 🧭 Column

```kotlin
Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text("Item 1")
    Text("Item 2")
}
```

---

## ↔️ Row

```kotlin
Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Text("Esquerda")
    Text("Direita")
}
```

---

## 🎁 Box

```kotlin
Box(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
) {
    Text("Dentro do Box", modifier = Modifier.align(Alignment.Center))
}
```

---

## ✨ Dicas

* Sempre use `Modifier` para controle de tamanho, espaçamento, cor, etc.
* Use `Preview` para ver rapidamente seus componentes.
* Combine `Column`, `Row` e `Box` para criar layouts complexos.


# Hierarquia da Interface e Layouts em Jetpack Compose

## Entendendo a Hierarquia da Interface

A interface do usuário (UI) no Jetpack Compose é construída com base em uma **hierarquia de contenção**. Isso significa que um componente (Composable) pode conter um ou mais outros componentes. Frequentemente, usamos os termos **pai** e **filho** para descrever essa relação:

* **Elemento Pai:** Um Composable que contém outros Composables.
* **Elemento Filho:** Um Composable que está contido dentro de um Composable pai.

Essa estrutura de árvore (pai contendo filhos, que por sua vez podem conter seus próprios filhos) define como os elementos da UI são organizados e renderizados na tela.

## Layouts Básicos: `Box`, `Column` e `Row`

Para organizar os elementos filhos dentro de um pai, o Compose oferece diversos Composables de layout. Os mais fundamentais são `Box`, `Column` e `Row`. Eles são essenciais para posicionar e agrupar outros Composables.

### `Column` 

O `Column` organiza seus elementos filhos em uma **sequência vertical**, um abaixo do outro.

**Exemplo:**

```kotlin
@Composable
fun ListaVerticalSimples() {
    Column {
        Text("Primeiro item na coluna")
        Text("Segundo item na coluna")
        Image(
            painter = painterResource(id = R.drawable.minha_imagem), // Substitua com seu recurso
            contentDescription = "Uma imagem de exemplo na coluna"
        )
    }
}

@Composable
fun ColunaComEspacamentoEAlinhamento() {
    Column(
        modifier = Modifier
            .padding(16.dp) // Adiciona preenchimento ao redor da coluna
            .fillMaxWidth(), // Faz a coluna ocupar a largura máxima
        verticalArrangement = Arrangement.spacedBy(8.dp), // Adiciona 8.dp de espaço entre cada filho
        horizontalAlignment = Alignment.CenterHorizontally // Centraliza os filhos horizontalmente
    ) {
        Text("Título Centralizado")
        Text("Subtítulo também centralizado")
        Button(onClick = { /* Ação do botão */ }) {
            Text("Botão")
        }
    }
}
```

### `Row`

O `Row` organiza seus elementos filhos em uma **sequência horizontal**, um ao lado do outro.

**Exemplo:**

```kotlin
@Composable
fun LinhaSimples() {
    Row {
        Text("Texto à Esquerda")
        Text("Texto no Centro")
        Text("Texto à Direita")
    }
}

@Composable
fun LinhaComIconesEAlinhamento() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround, // Distribui espaço ao redor dos filhos
        verticalAlignment = Alignment.CenterVertically // Centraliza os filhos verticalmente na linha
    ) {
        Icon(Icons.Filled.Favorite, contentDescription = "Ícone de Favorito")
        Text("Curtir")
        Icon(Icons.Filled.Share, contentDescription = "Ícone de Compartilhar")
        Text("Compartilhar")
    }
}
```

### `Box`

O `Box` é um Composable que permite **empilhar seus filhos um sobre o outro**. O último filho declarado será desenhado por cima dos anteriores. Ele também é útil para posicionar elementos de forma mais livre dentro de seus limites usando `Modifier.align()`.

**Exemplo:**

```kotlin
@Composable
fun CaixaComSobreposicao() {
    Box(
        modifier = Modifier
            .size(200.dp) // Define um tamanho para o Box
            .background(Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fundo_cartao), // Imagem de fundo
            contentDescription = "Imagem de fundo",
            modifier = Modifier.fillMaxSize(), // Ocupa todo o Box
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Texto Sobreposto",
            modifier = Modifier
                .align(Alignment.Center) // Alinha o texto ao centro do Box
                .background(Color(0xAA000000)) // Fundo semi-transparente para o texto
                .padding(8.dp),
            color = Color.White,
            fontSize = 20.sp
        )
        Text(
            text = "Rodapé",
            modifier = Modifier
                .align(Alignment.BottomCenter) // Alinha ao centro inferior
                .padding(8.dp),
            color = Color.Yellow
        )
    }
}
```

## Sintaxe de Lambdas Finais (Trailing Lambdas)

No Kotlin, quando o **último parâmetro de uma função é outra função (uma lambda)**, você pode usar uma sintaxe especial chamada "trailing lambda" (lambda final).

Em vez de passar a expressão lambda dentro dos parênteses da chamada da função, você pode colocá-la **fora dos parênteses, diretamente entre chaves `{}`**. Essa prática é muito comum e recomendada no Jetpack Compose, tornando o código mais legível, especialmente para definir o conteúdo (`content`) de Composables de layout.

**Por que usar?**

Muitos Composables, como `Row`, `Column`, e `Box`, têm um último parâmetro chamado `content`. Este parâmetro é uma função lambda `@Composable () -> Unit` que descreve os elementos filhos que o layout irá conter.

**Exemplo com `Row`:**

Suponha que você queira criar uma linha com três elementos de texto.

**1. Sem Trailing Lambda (menos comum para `content` no Compose):**

```kotlin
@Composable
fun MinhaLinhaSemTrailingLambda() {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        content = { // Lambda passada como parâmetro nomeado 'content'
            Text("Item 1")
            Text("Item 2")
            Text("Item 3")
        }
    )
}
```
Este código funcionaria, mas pode parecer um pouco verboso.

**2. Com Trailing Lambda (idiomático e preferido no Compose):**

Como `content` é o último parâmetro e seu valor é uma expressão lambda, podemos remover `content =` e mover a lambda para fora dos parênteses:

```kotlin
@Composable
fun MinhaLinhaComTrailingLambda() {
    Row( // Os parênteses podem até ser omitidos se não houver outros parâmetros antes da lambda
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) { // Lambda final aqui
        Text("Item 1")
        Text("Item 2")
        Text("Item 3")
    }
}
```

Se não houvesse outros parâmetros como `modifier` ou `horizontalArrangement`, poderíamos até omitir os parênteses completamente, mas isso é menos comum para `Row`, `Column` e `Box` que frequentemente usam modifiers:

```kotlin
// Exemplo hipotético de um Composable simples apenas com content lambda
AlgumComposableSimples {
    Text("Conteúdo direto")
}
```

**Por enquanto, não se preocupe se você não entender completamente o que é uma lambda. Apenas familiarize-se com a sintaxe de colocar as chaves `{}` após os parênteses (ou no lugar deles, se não houver outros parâmetros) para definir os filhos de um layout Composable.**

## Ferramentas do Android Studio para Layouts

O Android Studio oferece algumas ferramentas para agilizar o desenvolvimento de layouts no Compose.

### "Surround with widget/composable" (Envolver com widget/composable)

Se você já tem alguns Composables escritos e deseja envolvê-los rapidamente com um layout como `Row`, `Column` ou `Box`:

1.  **Selecione** os Composables que você quer agrupar no editor de código.
2.  Clique no **ícone de lâmpada** 💡 que aparece (ou pressione `Alt + Enter` no Windows/Linux ou `Option + Enter` no macOS).
3.  No menu de contexto, escolha a opção **"Surround with widget"** ou **"Surround with composable"**.
4.  Depois, selecione o tipo de layout desejado, como **"Surround with Row"**, **"Surround with Column"**, ou **"Surround with Box"**.

O Android Studio automaticamente envolverá os elementos selecionados com o Composable de layout escolhido.

### Importações Automáticas

Ao usar Composables como `Row`, `Column`, `Box`, `Text`, `Image`, etc., o Android Studio geralmente lida com as **importações necessárias automaticamente**.

Se uma importação não for adicionada, você pode passar o mouse sobre o nome do Composable sublinhado em vermelho e usar a sugestão do IDE (`Alt + Enter` ou `Option + Enter`) para importar a classe correta.

Por exemplo, ao usar `Row`, o IDE deve adicionar:
`import androidx.compose.foundation.layout.Row`

Verifique sempre a seção de importações no topo do seu arquivo Kotlin se encontrar algum problema de referência não resolvida.
