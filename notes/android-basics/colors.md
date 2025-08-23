# Cores, Temas e Design System no Jetpack Compose

No desenvolvimento moderno de interfaces, a cor não é apenas um detalhe visual, mas uma parte fundamental de um **sistema de design** coeso. No Jetpack Compose, o tema de cores do seu aplicativo é a base para uma experiência de usuário consistente e acessível.

## 1. A Teoria das Cores: Representação Digital

No Android e em muitas outras plataformas, a cor é representada por um valor **hexadecimal (hex)**.
* Um código hex começa com uma cerquilha (`#`) e é seguido por seis dígitos (letras e/ou números) que representam os componentes **vermelho, verde e azul (RGB)** da cor.
* As duas primeiras letras/números se referem ao vermelho, as duas seguintes ao verde e as duas últimas ao azul.
* Um valor `00` indica a ausência total daquela cor, enquanto `FF` indica a sua intensidade máxima.

**Exemplo:**
* `#FF0000`: Vermelho puro
* `#00FF00`: Verde puro
* `#0000FF`: Azul puro
* `#000000`: Preto (ausência de cor)
* `#FFFFFF`: Branco (todas as cores na intensidade máxima)

### Transparência (Canal Alfa)
Uma cor também pode incluir um valor **alfa** que representa a sua transparência. Quando presente, ele é composto pelos **dois primeiros caracteres** do código hexadecimal.
* `#00` indica 0% de opacidade (totalmente transparente).
* `#FF` indica 100% de opacidade (totalmente opaco).
* Se o valor alfa for omitido, ele é considerado `FF` por padrão.

**Exemplo:**
* `#80FF0000`: Vermelho com 50% de transparência.

## 2. O Sistema de Cores do Material Design 3

O Material Design, o sistema de design do Google, usa um conceito de "slots" de cores. Em vez de atribuir uma cor específica a cada componente, você define as cores para diferentes **funções** (ou "slots") na interface.

### Slots de Cores e suas Funções

* **`primary`**: Cor principal usada para componentes essenciais da interface (botões de ação flutuantes, botões principais).
* **`secondary`**: Cor secundária usada para componentes menos proeminentes ou para dar um contraste sutil.
* **`tertiary`**: Cor terciária usada para dar destaque ou para equilibrar as cores primárias e secundárias.
* **Cores "On"**: Representam a cor do conteúdo **sobre** outra cor.
    * **`onPrimary`**: A cor de texto ou ícones que aparecem sobre a cor `primary`.
    * **`onSurface`**: A cor de texto ou ícones que aparecem sobre a cor de `surface` (cor de fundo dos elementos).
    * **`onBackground`**: A cor de texto ou ícones sobre o fundo do aplicativo.

Esse sistema de design garante que, para cada cor de fundo, você tenha uma cor de "conteúdo" que oferece um contraste adequado para a leitura.

### O Material Theme Builder
Para criar um esquema de cores coeso e acessível, você pode usar o **Material Theme Builder**. Essa ferramenta online gera uma paleta completa (incluindo todas as cores "on") a partir de uma única cor principal que você escolhe.

## 3. Implementando o Tema no Compose

Ao usar os arquivos `Color.kt` e `Theme.kt` gerados pelo Material Theme Builder, o Jetpack Compose faz o **mapeamento automático** dos componentes do Material Design para esses slots de cor.

Você não precisa atribuir explicitamente uma cor para a maioria dos componentes. Por exemplo, um `Card` usará automaticamente a cor de `surface`, e um `Button` usará a cor `primary` do seu tema.

**Exemplo Prático (uso de `Card`):**
Ao envolver um componente `Row` dentro de um `Card`, a cor de fundo da `Row` (e de seus filhos) muda para a cor de superfície do seu tema, criando um contraste visual com o plano de fundo da tela.

```kotlin
// A cor de fundo será a cor de 'surface' do seu tema
Card(modifier = modifier) { 
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(...)
    ) {
        // ... conteúdo da sua linha
    }
}
```

## 4. Temas Escuros (Dark Themes)

Um tema escuro utiliza cores mais escuras e discretas para o fundo e cores claras para o conteúdo. Ele oferece benefícios de acessibilidade (melhora a visibilidade em ambientes escuros) e economia de energia (em telas OLED).

O Material Theme Builder já gera uma paleta de cores para o tema escuro. Para visualizar seu aplicativo em um tema escuro em uma prévia do Compose, basta definir o parâmetro `darkTheme` do seu tema como `true`.

```kotlin
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Tema Escuro")
@Composable
fun WoofDarkThemePreview() {
    WoofTheme(darkTheme = true) {
        WoofApp()
    }
}
```

## 5. Valores de Dimensão e Recursos

Além das cores, outras partes do seu design system, como espaçamento e tamanhos, devem ser centralizadas em um único local. A prática recomendada é usar um arquivo `dimens.xml` para isso.

**Exemplo:**
```xml
<resources>
    <dimen name="padding_small">8dp</dimen>
    <dimen name="padding_medium">16dp</dimen>
</resources>
```
No código Compose, você acessa esses valores usando a função `dimensionResource`, tornando seu layout mais fácil de manter e modificar.

```kotlin
import androidx.compose.ui.res.dimensionResource

// Uso no código:
Modifier.padding(dimensionResource(id = R.dimen.padding_small))
```

## 6. Cores Dinâmicas (Dynamic Colors)

A partir do Android 12, o Material Design 3 introduziu o conceito de **cores dinâmicas**. Esse recurso permite que o sistema crie um tema para o seu aplicativo com base nas cores do plano de fundo do usuário.

* O tema dinâmico está disponível apenas em dispositivos com Android 12 ou versões mais recentes.
* Em dispositivos mais antigos ou em cenários onde você quer uma marca forte, você ainda pode usar um tema personalizado fixo.

**Resumo:** O sistema de theming do Jetpack Compose, baseado nos princípios do Material Design 3, oferece ferramentas poderosas para criar aplicativos visualmente consistentes e acessíveis. Ao definir uma paleta de cores e gerenciar seus recursos (como dimensões), você constrói uma base sólida para um design system robusto e escalável.