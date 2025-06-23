# Programação Orientada a Objetos em Kotlin: Classes, Herança e Interfaces

A Programação Orientada a Objetos (POO) é um paradigma de programação baseado no conceito de "objetos", que podem conter dados na forma de campos (propriedades) e código, na forma de procedimentos (métodos). Kotlin, como uma linguagem moderna, oferece um suporte robusto e flexível à POO.

## Classes: A Base da POO

Uma classe é um "molde" ou uma "planta" para criar objetos. Ela define as propriedades (dados) e os métodos (comportamentos) que seus objetos terão.

### Declaração de Classe Simples

A forma mais simples de declarar uma classe em Kotlin é com a palavra-chave `class`:

```kotlin
class Pessoa {
    // Propriedades (dados)
    var nome: String = "Anônimo"
    var idade: Int = 0

    // Método (comportamento)
    fun apresentar() {
        println("Olá, meu nome é $nome e eu tenho $idade anos.")
    }
}

fun main() {
    // Criando uma instância (objeto) da classe Pessoa
    val pessoa1 = Pessoa()
    pessoa1.apresentar() // Olá, meu nome é Anônimo e eu tenho 0 anos.

    // Acessando e modificando as propriedades
    pessoa1.nome = "Carlos"
    pessoa1.idade = 30
    pessoa1.apresentar() // Olá, meu nome é Carlos e eu tenho 30 anos.
}
```

### Construtores

Um construtor é um bloco de código especial para criar e inicializar um objeto. Kotlin tem um **construtor primário** e **construtores secundários**.

#### Construtor Primário

O construtor primário faz parte do cabeçalho da classe. É a forma mais concisa e comum de declarar um construtor.

```kotlin
// As propriedades são declaradas diretamente no construtor primário
class Animal(val nome: String, val especie: String) {

    // Bloco init é executado quando o objeto é criado pelo construtor primário
    init {
        println("Um novo animal da espécie '$especie' chamado '$nome' foi criado!")
    }

    fun emitirSom() {
        when (especie.lowercase()) {
            "cachorro" -> println("$nome diz: Au au!")
            "gato" -> println("$nome diz: Miau!")
            else -> println("$nome faz um som desconhecido.")
        }
    }
}
```
* `val` no construtor cria uma propriedade imutável (somente leitura).
* `var` no construtor cria uma propriedade mutável (leitura e escrita).

## Herança: Reutilizando Código

Herança é um mecanismo que permite que uma classe (chamada de subclasse ou classe filha) **herde propriedades e métodos de outra classe** (superclasse ou classe pai). Uma classe filha só pode herdar de **uma** superclasse (herança simples).

### A Palavra-chave `open`

Por padrão, as classes e os métodos em Kotlin são `final`, ou seja, não podem ser herdados ou sobrescritos. Para permitir que uma classe seja herdada, você deve marcá-la com a palavra-chave `open`.

```kotlin
// Superclasse (Pai) - precisa ser 'open' para ser herdada
open class Funcionario(val nome: String, val salario: Double) {

    // Este método também precisa ser 'open' para ser sobrescrito
    open fun calcularBonusAnual(): Double {
        return salario * 0.10
    }
}
```

### Criando a Subclasse e Sobrescrevendo (`override`)

Para herdar de uma classe, use o operador `:` e chame o construtor da superclasse. Para fornecer uma implementação diferente a um método herdado, use `override`.

```kotlin
// Subclasse (Filha) - herda de Funcionario
class Gerente(nome: String, salario: Double, val area: String) : Funcionario(nome, salario) {
    
    // Sobrescrevendo o método da classe pai para dar um bônus maior
    override fun calcularBonusAnual(): Double {
        // A palavra 'super' permite acessar a implementação da classe pai
        val bonusBase = super.calcularBonusAnual()
        return bonusBase * 1.5 // Bônus 50% maior que o de um funcionário comum
    }
}
```

## Interfaces: Definindo Contratos

Uma interface define um "contrato" que uma classe pode se comprometer a seguir. Ela pode conter declarações de métodos abstratos e também métodos com implementação padrão. Diferente da herança de classes, uma classe pode **implementar múltiplas interfaces**.

Interfaces são ideais para definir capacidades ou comportamentos que podem ser compartilhados por classes não relacionadas. Ex: "Voável", "Serializável", "Clicável".

### Declarando e Implementando uma Interface

```kotlin
// Interface que define um contrato para seres que podem voar
interface Voavel {
    val velocidadeMaximaVoo: Int // Propriedade abstrata (deve ser implementada)

    fun voar() // Método abstrato (deve ser implementado)

    fun pousar() {
        // Método com implementação padrão
        println("Pousando suavemente.")
    }
}
```

Agora, uma classe pode implementar esta interface:

```kotlin
class Passaro(
    val nome: String,
    override val velocidadeMaximaVoo: Int // Implementando a propriedade da interface
) : Voavel {

    // Implementando o método abstrato obrigatório
    override fun voar() {
        println("O pássaro $nome está batendo as asas e voando a ${velocidadeMaximaVoo}km/h.")
    }
    
    // O método pousar() já tem uma implementação padrão, não é obrigatório sobrescrevê-lo,
    // mas podemos, se quisermos.
}
```

### Implementando Múltiplas Interfaces

A grande vantagem das interfaces é a possibilidade de uma classe adquirir múltiplos comportamentos.

```kotlin
interface Nadador {
    fun nadar()
}

// A classe Pato herda de Animal e implementa Voavel e Nadador
class Pato(
    nome: String,
    override val velocidadeMaximaVoo: Int
) : Animal(nome, "Pato"), Voavel, Nadador { // Herança primeiro, depois interfaces

    override fun voar() {
        println("O pato $nome está voando baixo sobre a água.")
    }

    override fun nadar() {
        println("O pato $nome está nadando no lago.")
    }
}

fun main() {
    val pardal = Passaro("Jack", 20)
    pardal.voar()
    pardal.pousar()

    println("-----")

    val patoDonald = Pato("Donald", 15)
    patoDonald.emitirSom() // Método da classe Animal
    patoDonald.voar()      // Método implementado da interface Voavel
    patoDonald.nadar()     // Método implementado da interface Nadador
}
```

### Herança de Classes vs. Implementação de Interfaces

| Característica        | Herança de Classe (`class`)                               | Implementação de Interface (`interface`)                |
| --------------------- | --------------------------------------------------------- | ------------------------------------------------------- |
| **Relação** | "É um" (um `Gerente` **é um** `Funcionario`)              | "É capaz de" (um `Pato` **é capaz de** `Voar`)          |
| **Cardinalidade** | Só pode herdar de **uma** classe.                         | Pode implementar **múltiplas** interfaces.              |
| **Estado** | Pode ter estado (propriedades com valores).               | Não pode ter estado (propriedades devem ser abstratas ou ter apenas getters). |
| **Construtores** | Possui construtores para inicializar o objeto.            | Não possui construtores.                                |
| **Uso Principal** | Reutilização de código e criação de hierarquias de tipos. | Definição de contratos e comportamentos comuns.         |

Classes, herança e interfaces são os pilares da POO em Kotlin, permitindo a criação de código organizado, flexível, reutilizável e escalável.
