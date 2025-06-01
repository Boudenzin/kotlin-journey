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
