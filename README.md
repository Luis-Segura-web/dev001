# IPTV Player

Un reproductor IPTV moderno desarrollado en Kotlin con Jetpack Compose, que utiliza Xtream Codes API para obtener contenido y TMDB API para enriquecer la información de películas y series.

## Características

### 🎥 Reproducción de Contenido
- **Canales en Vivo**: Reproducción de canales de televisión en tiempo real
- **Películas**: Catálogo completo de películas con información detallada
- **Series de TV**: Series con temporadas y episodios organizados
- **EPG (Guía Electrónica de Programas)**: Información de programación para canales en vivo
- **Catch-up TV**: Reproducción de contenido pasado (si está disponible)

### 🎨 Interfaz de Usuario
- **Material Design 3**: Interfaz moderna y atractiva
- **Tema Oscuro/Claro**: Soporte para ambos temas con detección automática del sistema
- **Navegación Intuitiva**: Bottom Navigation con secciones organizadas
- **Búsqueda Avanzada**: Búsqueda rápida en todos los tipos de contenido
- **Categorías**: Organización por categorías para fácil navegación

### 🎮 Reproductor de Video
- **ExoPlayer**: Reproductor de video robusto y optimizado
- **Controles Personalizados**: Interfaz de controles adaptada para IPTV
- **Múltiples Calidades**: Selección automática y manual de calidad
- **Subtítulos**: Soporte para múltiples pistas de subtítulos
- **Audio Multi-idioma**: Selección de pistas de audio
- **Pantalla Completa**: Experiencia inmersiva de visualización

### 📱 Funcionalidades Adicionales
- **Favoritos**: Marca y organiza tu contenido favorito
- **Historial de Reproducción**: Continúa viendo desde donde lo dejaste
- **Control Parental**: Bloqueo de contenido por categorías y calificación
- **Configuración Personalizable**: Ajustes de reproducción, tema y más
- **Offline**: Almacenamiento local para acceso rápido

### 🔧 Tecnologías Utilizadas
- **Kotlin**: Lenguaje de programación principal
- **Jetpack Compose**: UI toolkit moderno para Android
- **Hilt**: Inyección de dependencias
- **Room**: Base de datos local
- **Retrofit**: Cliente HTTP para APIs
- **ExoPlayer**: Reproductor multimedia
- **Coroutines**: Programación asíncrona
- **Flow**: Manejo reactivo de datos
- **Navigation Compose**: Navegación entre pantallas

## APIs Integradas

### Xtream Codes API
- Autenticación de usuarios
- Obtención de canales en vivo
- Catálogo de películas y series
- Información detallada de contenido
- EPG (Electronic Program Guide)
- URLs de streaming

### TMDB (The Movie Database) API
- Información enriquecida de películas
- Detalles de series de TV
- Imágenes de alta calidad (posters, backdrops)
- Calificaciones y reseñas
- Trailers y videos promocionales
- Información de reparto y crew

## Arquitectura

El proyecto sigue los principios de **Clean Architecture** y **MVVM**:

```
├── data/
│   ├── local/          # Room database, DAOs, entities
│   ├── remote/         # APIs, network models
│   ├── mappers/        # Data transformation
│   └── repositories/   # Repository implementations
├── domain/
│   ├── models/         # Domain models
│   ├── repositories/   # Repository interfaces
│   └── usecases/       # Business logic
├── presentation/
│   ├── ui/            # Compose screens
│   ├── viewmodels/    # ViewModels
│   └── navigation/    # Navigation logic
└── di/                # Dependency injection modules
```

## Configuración

### Requisitos Previos
- Android Studio Flamingo o superior
- Java JDK 17 o 20 (compatible con Gradle 8.5)
- Kotlin 1.9.22
- Android SDK 24+
- Gradle 8.5 (incluido en el proyecto)

### Configuración de APIs

1. **TMDB API Key**:
   - Regístrate en [TMDB](https://www.themoviedb.org/settings/api)
   - Obtén tu API key
   - Configúrala en la aplicación desde Configuración

2. **Xtream Codes**:
   - Obtén las credenciales de tu proveedor IPTV
   - URL del servidor, usuario y contraseña
   - Configúralas en la pantalla de login

### Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/tu-usuario/iptv-player.git
```

2. Abre el proyecto en Android Studio

3. Sincroniza las dependencias de Gradle

4. Ejecuta la aplicación

## Uso

### Primer Uso
1. **Login**: Ingresa las credenciales de tu proveedor Xtream Codes
2. **TMDB API**: Configura tu API key de TMDB (opcional pero recomendado)
3. **Sincronización**: La app descargará el catálogo de contenido
4. **¡Disfruta!**: Navega y reproduce tu contenido favorito

### Navegación
- **Inicio**: Contenido destacado y recientes
- **Canales**: Televisión en vivo con EPG
- **Películas**: Catálogo de películas
- **Series**: Series de TV con temporadas
- **Favoritos**: Tu contenido marcado como favorito
- **Configuración**: Ajustes de la aplicación

## Características Técnicas

### Base de Datos Local
- **Room**: Almacenamiento eficiente de contenido
- **Caché Inteligente**: Reduce llamadas a la API
- **Sincronización**: Actualización automática del contenido

### Reproductor
- **ExoPlayer**: Soporte para múltiples formatos
- **Adaptive Streaming**: Ajuste automático de calidad
- **Buffer Inteligente**: Optimización de la reproducción
- **Controles Personalizados**: Interfaz adaptada para TV

### Rendimiento
- **Lazy Loading**: Carga eficiente de listas
- **Image Caching**: Caché de imágenes con Coil
- **Background Sync**: Sincronización en segundo plano
- **Memory Management**: Gestión optimizada de memoria

## Contribución

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## Soporte

Si encuentras algún problema o tienes sugerencias:

1. Abre un issue en GitHub
2. Proporciona información detallada del problema
3. Incluye logs si es posible

## Roadmap

### Próximas Características
- [ ] Soporte para múltiples perfiles de usuario
- [ ] Integración con servicios de streaming adicionales
- [ ] Modo offline para contenido descargado
- [ ] Chromecast support
- [ ] Android TV optimization
- [ ] Widgets para pantalla de inicio
- [ ] Notificaciones de programas favoritos

### Mejoras Técnicas
- [ ] Migración a Compose Multiplatform
- [ ] Implementación de tests unitarios
- [ ] CI/CD pipeline
- [ ] Análisis de rendimiento
- [ ] Optimización de batería

## Agradecimientos

- [ExoPlayer](https://github.com/google/ExoPlayer) - Reproductor multimedia
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - UI toolkit
- [TMDB](https://www.themoviedb.org/) - Base de datos de películas
- [Material Design](https://material.io/) - Sistema de diseño

---

**Nota**: Este proyecto es para uso educativo y personal. Asegúrate de cumplir con los términos de servicio de tu proveedor IPTV y las APIs utilizadas.
