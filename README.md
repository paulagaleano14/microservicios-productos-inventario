# 🛒 Microservicios: Products Service & Inventory Service  
Arquitectura de microservicios para gestión de productos e inventario, siguiendo buenas prácticas, JSON API, pruebas, y autenticación entre servicios.

---

# 📌 Contenido

1. [Tecnologías Utilizadas](#-tecnologías-utilizadas)  
2. [Arquitectura General](#-arquitectura-general)  
3. [Diagrama General de Servicios](#-diagrama-general-de-servicios)  
4. [Flujo de Compra](#-flujo-de-compra)  
5. [Instalación](#-instalación)  
6. [Ejecución](#-ejecución)  
7. [Documentación de API (Swagger)](#-documentación-de-api-swagger)  
8. [Decisiones Técnicas](#-decisiones-técnicas)  
9. [Testing](#-testing)  
10. [Uso de IA](#-uso-de-ia-en-el-desarrollo)

---

# 🚀 Tecnologías Utilizadas

- Java 17  
- Spring Boot 3  
- Spring Web  
- Spring JPA / Hibernate  
- PostgreSQL  
- Docker & Docker Compose  
- JUnit 5 & Mockito  
- Swagger / OpenAPI  
- Git Flow  

---

# 🏛 Arquitectura General

Sistema compuesto por **dos microservicios independientes**, cada uno con su propia base de datos:

| Servicio | Puerto | Función |
|---------|--------|---------|
| **Products Service** | 3001 | CRUD de productos |
| **Inventory Service** | 3002 | Maneja inventario y compras |
| *(Cliente)* | 3003 | Postman/Swagger |

Servicios comunicándose vía HTTP + API Key.

---

# 📊 Diagrama General de Servicios

<img width="778" height="346" alt="image" src="https://github.com/user-attachments/assets/ced2441e-e480-4e9e-9d38-875c24695656" />

📌 Flujo general:

- El cliente puede consultar productos directamente.  
- Inventory consulta al Products cuando necesita validar un producto.  
- Inventory actualiza su propio inventario.  

(Reemplaza esta línea con la imagen exportada desde draw.io)

---

## 🔄 Flujo de Compra

1. Inventory consulta al microservicio **Products**.
2. Valida la existencia del producto.
3. Revisa inventario y descuenta cantidad si hay stock disponible.
4. Retorna un **JSON API** con la compra realizada.

---

## 🧪 Testing

Incluye:

### ✔ Pruebas unitarias
- Creación de productos.
- Consulta y actualización de inventario.
- Flujo de compra completo.
- Manejo de errores:
  - Producto inexistente.
  - Inventario insuficiente.
  - API Key inválida.
  - Respuestas JSON API.

### ✔ Pruebas de integración
- Un test por cada microservicio:
  - Products Service → CRUD.
  - Inventory Service → flujo de compra + integración con Products.

---

## 🔐 Seguridad

Los servicios se comunican usando API Key:
X-API-KEY: 12345
Configurado dentro de `application.yml`.

---

## 🛠 Instalación

```bash
git clone https://github.com/tuUsuario/microservices-test.git
cd microservices
```

## 🔹 Opción 2: Ejecutar manualmente
🚀 Products Service
cd products-service
mvn spring-boot:run

📦 Inventory Service
cd inventory-service
mvn spring-boot:run

## 📘 Documentación de API (Swagger)

Products Service:
👉 http://localhost:3001/swagger-ui.html

Inventory Service:
👉 http://localhost:3002/swagger-ui.html

## 🧠 Decisiones Técnicas
### 📌 Por qué el endpoint de compra está en Inventory Service:

Es el responsable directo del estado del inventario.

Encapsula la lógica de disponibilidad, evitando duplicar reglas.

Reduce el acoplamiento entre servicios.

Products Service permanece como CRUD simple, siguiendo Single Responsibility.

Inventory puede:

Validar existencia del producto.

Verificar stock.

Descontar inventario.

Devolver JSON API en un flujo transaccional único.

## 🤖 Uso de IA en el Desarrollo

Se utilizaron herramientas como ChatGPT para:

Crear estructuras iniciales de clases y controladores.

Generar pruebas unitarias y de integración.

Producir diagramas arquitectónicos y de interacción.

Optimizar validaciones y manejo de errores.

Redacción y mejora del README.md.

### 📌 Todo el código fue verificado, validado y refactorizado manualmente para garantizar calidad y buenas prácticas.

✔ Estado de la Solución

✓ Microservicios funcionando

✓ Comunicación segura entre servicios

✓ JSON API implementado correctamente

✓ Pruebas unitarias e integración completadas

✓ Documentación lista y entendible

✓ Dockerizado y orquestado

✓ Diagramas incluidos



