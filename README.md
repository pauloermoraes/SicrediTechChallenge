## Siredi Tech Challenge

### Quais as camadas necessárias?

  - Na estrutura do main, teremos:
    - appstarting
      - a implementação do Application e da Activity launcher
    - global
      - onde estão presentes constantes e objetos de Utils para facilitar a reutilização de métodos e funções
    - features
      - onde está implementada a feature proposta pelo desafio
  - A feature terá esta estrutura
    - DI
      - Extension
    - Domain
      - Mappers
      - Models
      - Repositories
      - Tools
      - UseCases
    - Infrastructure
      - Repositories
      - Mappers
      - External
    - External
      - Network
    - Presentation
      - View
        - activities
        - adapters
        - constants
        - fragments
        - navigation
      - ViewModel
      

### Como será a separação entre elas? ... MVC? MVVM? Clean Code? etc…

  - Clean Architecture + MVVM para controle de estado do Presentation

### Quais as bibliotecas e frameworks a serem usados?
- **androidx.core:core-ktx**: O [Android KTX](https://developer.android.com/kotlin/ktx) é um conjunto de extensões Kotlin que fazem parte do Android Jetpack e de outras bibliotecas do Android. 
- **androidx.appcompat:appcompat**: [Android AppCompat](https://developer.android.com/jetpack/androidx/releases/appcompat) Permite o acesso a novas APIs em versões de API anteriores da plataforma, muitas usando o Material Design.
- **androidx.lifecycle:lifecycle-livedata-ktx**: [LiveData](https://developer.android.com/reference/android/arch/lifecycle/LiveData) é uma classe de holding de dados que pode ser observada dentro de um determinado ciclo de vida
- **androidx.lifecycle:lifecycle-viewmodel-ktx**: [ViewModel](https://developer.android.com/reference/android/arch/lifecycle/ViewModel) é uma classe responsável por preparar e gerenciar os dados de uma Activity ou Fragment
- **androidx.activity:activity-ktx**: A [activity](https://developer.android.com/reference/android/app/Activity) é um tipo de objeto que representa uma tela com ciclo de vida controlado pelo sistema operacional
- **androidx.fragment:fragment-ktx**: O [fragment](https://developer.android.com/guide/fragments) é um tipo de objeto que representa uma porção de tela com ciclo de vida seguido pelo ciclo de vida da activity pai do mesmo
- **androidx.constraintlayout:constraintlayout**: Com o [ConstraintLayout](https://developer.android.com/jetpack/androidx/releases/constraintlayout) é possível definir a posição e o tamanho dos widgets de maneira flexível com posicionamento relativo.
- **androidx.recyclerview:recyclerview**: Com o [RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview) é possível mostrae grandes conjuntos de dados na IU enquanto minimiza o uso de memória.
- **androidx.multidex:multidex**: Com o [MultiDdex](https://developer.android.com/jetpack/androidx/releases/multidex) é possível implantar aplicativos com vários arquivos dex
- **androidx.arch.core:core-testing**: [Arch Core](https://developer.android.com/jetpack/androidx/releases/arch-core) é o assistente para outras dependências de arquivamento, incluindo regras de teste do JUnit que podem ser usadas com o LiveData
- **com.google.android.material:material**: [Material Design](https://material.io/develop/android/docs/getting-started) é a identidade visual do Android, essa library viabiliza o uso dos compoenntes visuais da plataforma
- **org.jetbrains.kotlinx:kotlinx-coroutines-core**: Uma [corrotina](https://developer.android.com/kotlin/coroutines) é um padrão de projeto de simultaneidade que você pode usar no Android para simplificar o código que é executado de forma assíncrona
- **org.jetbrains.kotlinx:kotlinx-coroutines-test**: Essa é a library de testes da [corrotina](https://developer.android.com/kotlin/coroutines) que sinplifica os testes assincronos
- **com.squareup.retrofit2:retrofit**: [Retrofit](https://square.github.io/retrofit/) transforma sua API HTTP em uma interface Java facilitando o trabalho de integração entre app e backend
- **com.squareup.retrofit2:converter-gson**: Biblioteca de conversão de Json usando [Gson](https://github.com/google/gson)
- **com.squareup.okhttp3:okhttp**: [OkHttp](https://square.github.io/okhttp/) é a implementação de um cliente HTTP para Android, utilizado em conjunto com retrofit
- **io.insert-koin:koin-core**: o [Koin](https://insert-koin.io/) é uma biblioteca de injeção de dependência Kotlin inteligente que facilita muito o uso da injeção de dependência e inversão de controle
- **io.insert-koin:koin-android**: Essa é a versão que adiciona algumas extensões para Android da biblioteca padrão, como inicialização de ViewModels
- **io.insert-koin:koin-test**: Essa é a versão que adiciona regraas de teste unitário ao Core
- **io.insert-koin:koin-test-junit4**: Essa é a versão que adiciona regraas de teste unitário ao JUnit
- **io.mockk:mockk**: O [MockK](https://mockk.io/) é uma biblioteca que facilita a impleentação de mocks nos testes unitários
- **com.github.bumptech.glide:glide**: O [Glide](https://github.com/bumptech/glide) é um framework de gerenciamento de mídia e carregamento de imagens de código aberto rápido e eficiente para Android que envolve decodificação de mídia, cache de memória e disco e pool de recursos em uma interface simples e fácil de usar.
- **com.github.bumptech.glide:compiler**: Bibliote de processamento de anotações usada pelo Glide
- **junit:junit**: Biblioteca com códigos para testes unitários em toda a aplicação
  

### Como você irá gerir o estado do seu app?

  - O estado de cada componennte é controlado por ViewModels de Fragments

### E a gestão da qualidade?

  - Serão usados testes unitários em conjunto com a biblioteca MockK

### Instruções
#### Instruções de como instalar as dependências do projeto:
  - Basta buildar o projeto pelo Android Studio

#### Instruções de como rodar a aplicação:
  - Basta executar o projeto pelo Android Studio em algum dispositivo com Android API 19+

#### Instruções de como rodar os testes automatizados:
  - Na pasta de testes unitários (/app/src/test) basta clicar com botão direito do mouse, e clicar em "Run Tests..."

### Conclusão
- O projeto foi criado seguindo as direções citadas no documento presente em https://github.com/WoopSicredi/jobs/issues/1 e utilizou das arquiteturas Clean Architecture e MVVM para seu desenvolvimento
