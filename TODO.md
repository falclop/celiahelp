# 📋 Lista de Tareas del Proyecto CeliaHelp

## ✅ Ya completado
- [x] Definir entidades JPA y sus relaciones (`Incidencia`, `Usuario`, `Rol`, `Log`)
- [x] Crear DTOs y validaciones para **Incidencia** y **Usuario**
- [x] Implementar mappers **IncidenciaMapper** y **UsuarioMapper**
- [x] Desarrollar controllers con validación para **Incidencia** (`IncidenciaController`)
- [x] Escribir servicios e interfaces para **Incidencia** y **Usuario**
- [x] Configurar repositorios Spring Data JPA para **Incidencia** y **Usuario**
- [x] Tests unitarios de servicios (`IncidenciaServiceTest`, `UsuarioServiceTest`)
- [x] Tests de mappers (`IncidenciaMapperTest`, `UsuarioMapperTest`)
- [x] Repository test para **Incidencia** (`IncidenciaRepositoryTest`)
- [x] Controller test para **Incidencia** (`IncidenciaControllerTest`)
- [x] Integration test para **Incidencia** con Testcontainers MySQL (`IncidenciaIntegrationTest`)
- [x] Crear y versionar `README.md` con la documentación técnica actual
- [x] Configurar build y perfil de tests (H2 embebida, Testcontainers)

## ⏳ Pendiente
- [ ] Completar DTOs y validaciones para **Rol** y **Log**
- [ ] Implementar mappers **RolMapper** y **LogMapper**
- [ ] Desarrollar controllers validados para **Rol** y **Log**
- [ ] Escribir servicios e interfaces para **Rol** y **Log**
- [ ] Configurar repositorios JPA y tests de repositorio para **Rol** y **Log**
- [ ] Escribir unit tests de servicios para **Rol** y **Log**
- [ ] Escribir mapper tests para **Rol** y **Log**
- [ ] Escribir controller tests para **Rol** y **Log**
- [ ] Escribir integration tests (Testcontainers) para **Rol** y **Log**
- [ ] Afinar documentación OpenAPI/Swagger
- [ ] Depurar los errores actuales en tests de repositorio (tabla `usuarios` en H2)
- [ ] Resolver el “hang” de Surefire al finalizar los tests (cerrar hilos no-daemon)
- [ ] Revisar y ajustar configuración de `@AutoConfigureTestDatabase` donde sea necesario

---

¡Con esto tenemos una visión clara de lo hecho y lo que queda por desarrollar! Si quieres que añadamos o reasignemos algo, dímelo.
