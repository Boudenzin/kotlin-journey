## 🎯 **Desafio: Sistema de Dispositivos Inteligentes**

Você vai criar um mini sistema que gerencia **dispositivos inteligentes** conectados a uma **central de controle**.

---

### 🧩 1. Interface `Ligavel`

Crie uma interface com:

* Uma propriedade `val tipo: String`
* Um método `fun ligar()`
* Um método `fun desligar()`
* Um método `fun status()` com implementação padrão que imprime:
  `"Dispositivo do tipo $tipo está em estado desconhecido."`

---

### 🧱 2. Classe `SmartDevice`

Crie uma classe base `SmartDevice` com:

* Construtor primário com `name: String`, `category: String`
* Um atributo `deviceStatus` inicializado como `"offline"`
* Um **construtor secundário** que aceita `statusCode: Int` e transforma:

  * `0 -> "offline"`
  * `1 -> "online"`
  * Outro -> `"unknown"`

Implemente a interface `Ligavel`, e sobrescreva os métodos para atualizar o `deviceStatus`.

---

### 📺 3. Subclasse `SmartTvDevice`

Crie uma classe que herda de `SmartDevice` chamada `SmartTvDevice`.

* Deve sobrescrever o método `status()` da interface, mas ainda chamar o `status()` da interface original.
* Adicione uma função `abrirYoutube()`.

---

### 🧠 4. Interface `Atualizavel`

Crie uma interface `Atualizavel` com:

* `fun atualizarFirmware()`, com implementação padrão que imprime `"Firmware atualizado com sucesso."`
* Outro método `fun status()` que imprime `"Status de atualização desconhecido."`

---

### 🧩 5. Resolver conflito de métodos

Faça `SmartTvDevice` implementar **também** a interface `Atualizavel`
→ Agora existem **dois métodos `status()`**, e você deve sobrescrever e combinar ambos:

```kotlin
override fun status() {
    super<Ligavel>.status()
    super<Atualizavel>.status()
    println("Status completo da Smart TV.")
}
```

---

### 📦 6. Composição

Crie uma classe `CentralDeControle`, que **possui (has-a)** uma lista de dispositivos do tipo `Ligavel`, e métodos para:

* Adicionar dispositivo
* Ligar todos
* Desligar todos
* Mostrar status de todos

---

### ✅ Bônus: `main()`

No `fun main()`:

* Crie uma `SmartTvDevice`
* Crie outra classe que implemente `Ligavel` (ex: `LampadaInteligente`)
* Adicione os dois na central
* Ligue todos, mostre status, desligue todos
