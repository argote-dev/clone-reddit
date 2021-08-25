# Clone reddit - App
---
#### Arquitectura (MVVM - Clean Arquitecture)
```
-src
--main
---java
    --application
      -App
    --core
      -BaseViewHolder
      -Constants
      -InternetCheck
      -Resource
      -TimeUtlis
    --di
      -ApplicationModule
    --domain
      --local
        -PostEntity
      --remote
        -Child
        -Data
        -Media
        -Post
        -RedditVideo
        -TopResponse
    --data
      -local
        --dao
          -PostDao
        -ApplicationDatabase
      -Remote
        -Service
    --repository
      --post_repository
        -PostRepository
        -PostRepositoryInterface
    --ui
      --home
        --adapter
          -PostAdapter
        -HomeFragment
        -HomeViewModel
      --post_detail
        -PostDetailFragment
      -MainActivity
--test
```
---
### Pricipos SOLID
  #### Principio de responsabilidad única:
  - El principio de responsabilidad única nos dice que una clase debe tener una sola responsabilidad,
  esto debido a que si una clase tenemos más de una responsabilidad se hace difícil de leer, testear y mantener.
  #### Clases y sus responsabilidades:
```
      -src
      --main
      ---java
          --application
            -App // Instala módulo de dependencias
          --core
            -BaseViewHolder // Clase abstracta para definir la base de los view holder
            -Constants // Contiene constantes de la aplicación
            -InternetCheck // Objeto que contiene función para chequear la conexión a internet por medio  de socket
            -Resource // Clase sellada para tipar los procesos del flow
            -TimeUtlis // Clase encargada de manejar utilidades de Time
          --di
            -ApplicationModule // Módulo de dependencias
          --domain
            --local
              -PostEntity // Modelo para base de datos Room
            --remote
              -Child // Modelo para consumo de servicio
              -Data // Modelo para consumo de servicio
              -Media // Modelo para consumo de servicio
              -Post // Modelo para consumo de servicio, aloja funciones de extensión para convertir
              -RedditVideo // Modelo para consumo de servicio
              -TopResponse // Modelo para consumo de servicio
          --data
            -local
              --dao
                -PostDao // Interfase que maneja la tabla Post
              -ApplicationDatabase // Clase que crea y configura la base de datos Room
            -Remote
              -Service // Maneja las peticiones hacia el servicio
          --repository
            --post_repository
              -PostRepository // Encargado de actualizar y comunicar la información de posts
              -PostRepositoryInterface // Contrato de funciones para Postrepository
          --ui
            --home
              --adapter
                -PostAdapter // Adaptar el contenido para el recicler view de posts
              -HomeFragment // Manejar los datos que le retorna el viewModel
              -HomeViewModel // Manejar los datos que le retorna el repository
            --post_detail
              -PostDetailFragment // Recibir y pintar los datos que se envían por medio de safeArgs
            -MainActivity // Punto de partida de la app, gestiona el navigation
      --test
```
  
