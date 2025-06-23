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

# Adicionar Imagens em Jetpack Compose 🖼️

Exibir imagens é uma parte fundamental da maioria das interfaces de usuário. No Jetpack Compose, isso é feito de forma declarativa e integrada com o sistema de recursos do Android.

## 1. Preparando e Importando Imagens para o Projeto

Antes de exibir uma imagem no seu app Compose, você precisa adicioná-la aos recursos do seu projeto.

### A. Usando o Resource Manager no Android Studio

O Android Studio oferece uma ferramenta visual para facilitar a importação de imagens (drawables):

1.  No Android Studio, acesse o **Resource Manager**:
    * Clique em **Tools > Resource Manager** no menu superior.
    * Ou, se já estiver visível, selecione a guia **Resource Manager** (geralmente ao lado da janela do projeto).
2.  Clique no ícone **`+`** (Adicionar recursos ao módulo) dentro da aba "Drawable" do Resource Manager.
3.  Selecione **"Import Drawables"**.
    ![Exemplo do Resource Manager](month02-apps/week1/LearnTogether/app/src/main/res/drawable-mdpi/bg_compose_background.png) 
4.  No navegador de arquivos do seu sistema, localize e selecione o(s) arquivo(s) de imagem que você deseja importar (ex: `.png`, `.jpg`, `.xml` para vetores).
5.  Clique em **"Open"** (Abrir) e depois em **"Next"** (Avançar) e **"Import"** (Importar) nas caixas de diálogo seguintes.

A imagem será copiada para a pasta `res/drawable` do seu projeto.

### B. Considerações sobre Densidade de Pixels (`dpi`)

O Android roda em uma vasta gama de dispositivos com diferentes densidades de tela (pixels por polegada - dpi). Para garantir que suas imagens apareçam com boa qualidade em todas as telas, você pode fornecer versões diferentes da mesma imagem para densidades específicas (ex: `mdpi`, `hdpi`, `xhdpi`, `xxhdpi`, `xxxhdpi`).

* Se você importar uma imagem através do Resource Manager sem especificar uma densidade, ela geralmente vai para a pasta `res/drawable` (que funciona como um fallback ou para imagens de densidade única).
* Para vetores drawables (arquivos XML), eles são independentes de densidade e vão para `res/drawable`.
* O sistema Android seleciona automaticamente o recurso de imagem mais apropriado com base na densidade da tela do dispositivo.

### C. Estrutura de Pastas de Recursos (`res/`)

É uma prática fundamental no Android separar recursos (como imagens, layouts, strings, etc.) do código da aplicação. Isso facilita a manutenção e permite que o Android escolha os recursos corretos para diferentes configurações de dispositivo.

A pasta `res/` (resources) no seu projeto contém subdiretórios para diferentes tipos de recursos:

* **`res/drawable/`**: Para arquivos de imagem (bitmaps como PNG, JPG) e vetores drawables (XML).
* **`res/mipmap/`**: Principalmente para ícones de lançador do aplicativo (ícones da tela de início), que podem precisar de densidades diferentes para uma ótima aparência.
* **`res/values/`**: Para arquivos XML que definem valores simples, como strings (`strings.xml`), cores (`colors.xml`), dimensões (`dimens.xml`) e estilos (`themes.xml`).
* Outros diretórios como `layout/` (para layouts XML tradicionais), `anim/`, `font/`, etc.

## 2. A Classe `R` e IDs de Recurso

Quando você adiciona recursos ao seu projeto e o compila, o Android Studio gera automaticamente uma classe chamada `R`. Esta classe contém **IDs estáticos (identificadores únicos)** para todos os recursos do seu projeto.

* Você acessa seus recursos no código usando esses IDs.
* Por exemplo, se você tem uma imagem `minha_imagem.png` na pasta `res/drawable/`, o ID gerado será `R.drawable.minha_imagem`.
* Da mesma forma, uma string `app_name` em `strings.xml` é acessada via `R.string.app_name`.

## 3. Tutorial: Exibindo Imagens com o Composable `Image`

Para exibir uma imagem na sua UI do Compose, você utiliza o Composable `Image`.

### A. Componentes Essenciais do `Image`

O Composable `Image` requer principalmente dois parâmetros:

* `painter`: Define *o que* será desenhado (a imagem em si).
* `contentDescription`: Uma descrição textual da imagem para fins de acessibilidade.

### B. Carregando Imagens Locais com `painterResource()`

A forma mais comum de carregar uma imagem que está nos seus recursos `drawable` é usando a função `painterResource()`.

1.  **Importe as funções necessárias:**
    ```kotlin
    import androidx.compose.foundation.Image
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.res.painterResource
    // Importe a classe R do seu projeto (geralmente auto-importada ou use o nome completo do pacote)
    // Ex: import com.example.meuapp.R
    ```

2.  **Use `painterResource()` no Composable `Image`:**
    A função `painterResource()` recebe o ID do recurso drawable como argumento.

    ```kotlin
    @Composable
    fun MinhaImagemDeExemplo() {
        Image(
            painter = painterResource(id = R.drawable.androidparty), // Substitua 'androidparty' pelo nome do seu arquivo de imagem
            contentDescription = "Logo da festa do Android" // Descrição para acessibilidade
        )
    }
    ```
    * **Nota:** O Android Studio pode destacar `painterResource` se a importação não tiver sido adicionada. Use `Alt + Enter` (ou `Option + Enter` no Mac) para importar.

### C. A Importância do `contentDescription` (Acessibilidade)

O parâmetro `contentDescription` é **crucial para a acessibilidade**. Ele fornece uma descrição textual da imagem que será lida por leitores de tela para usuários com deficiência visual.

* **Sempre forneça uma descrição significativa.**
* Se a imagem for puramente decorativa e não transmitir nenhuma informação importante, você pode definir `contentDescription = null`.

    ```kotlin
    @Composable
    fun ImagemDecorativa() {
        Image(
            painter = painterResource(id = R.drawable.fundo_decorativo),
            contentDescription = null // Imagem puramente decorativa, não transmite informação
        )
    }
    ```

### D. Personalizando com `Modifier`

Como a maioria dos Composables, `Image` aceita um `Modifier` para personalizar sua aparência e layout (tamanho, preenchimento, bordas, etc.).

```kotlin
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun AvatarDeUsuario() {
    Image(
        painter = painterResource(id = R.drawable.avatar_usuario),
        contentDescription = "Avatar do perfil do usuário",
        modifier = Modifier
            .size(120.dp) // Define o tamanho da imagem
            .clip(CircleShape) // Corta a imagem em formato circular
            .padding(4.dp) // Adiciona um preenchimento ao redor
    )
}
```

### E. Controlando o Dimensionamento com `contentScale`

Se as dimensões da sua imagem original não corresponderem exatamente às dimensões do Composable `Image` na tela, o `contentScale` define como a imagem deve ser dimensionada para caber.

Valores comuns para `ContentScale`:

* `ContentScale.Fit`: Redimensiona a imagem para caber dentro dos limites, mantendo a proporção. Pode deixar espaços vazios.
* `ContentScale.Crop`: Redimensiona a imagem para preencher os limites, mantendo a proporção, mas pode cortar partes da imagem.
* `ContentScale.FillBounds`: Preenche os limites esticando a imagem, sem manter a proporção.
* `ContentScale.Inside`: Redimensiona a imagem para caber dentro dos limites se a imagem for maior que eles; caso contrário, mantém o tamanho original.
* `ContentScale.None`: Não aplica dimensionamento, exibe a imagem no seu tamanho original, cortando se for maior que os limites.
* `ContentScale.FillWidth`: Dimensiona a imagem para preencher a largura dos limites, ajustando a altura para manter a proporção.
* `ContentScale.FillHeight`: Dimensiona a imagem para preencher a altura dos limites, ajustando a largura para manter a proporção.

```kotlin
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.layout.ContentScale

@Composable
fun ImagemBanner() {
    Image(
        painter = painterResource(id = R.drawable.banner_promocional),
        contentDescription = "Banner da promoção",
        modifier = Modifier
            .fillMaxWidth() // Ocupa toda a largura
            .height(200.dp), // Define uma altura fixa
        contentScale = ContentScale.Crop // Corta a imagem para preencher o espaço, mantendo a proporção
    )
}
```

### F. Outros Tipos de `Painter` (Avançado/Externo)

Embora `painterResource()` seja para drawables locais, existem outros tipos de `Painter` que você pode usar, especialmente ao carregar imagens da internet com bibliotecas como Coil, Glide ou Picasso. Essas bibliotecas geralmente fornecem suas próprias funções Composable que retornam um `Painter`.

Exemplo conceitual com Coil (requer a biblioteca Coil):
```kotlin
// import coil.compose.rememberAsyncImagePainter // Exemplo de importação do Coil

// @Composable
// fun ImagemDaInternet(url: String) {
//     Image(
//         painter = rememberAsyncImagePainter(model = url),
//         contentDescription = "Imagem carregada da internet"
//     )
// }
```

## Resumo

1.  **Importe** sua imagem para a pasta `res/drawable` (preferencialmente usando o Resource Manager).
2.  No seu Composable, use a função `Image()`.
3.  Defina o parâmetro `painter` usando `painterResource(id = R.drawable.nome_da_sua_imagem)`.
4.  **Sempre** forneça um `contentDescription` significativo para acessibilidade (ou `null` para imagens decorativas).
5.  Use `Modifier` e `contentScale` para personalizar a aparência e o dimensionamento.
