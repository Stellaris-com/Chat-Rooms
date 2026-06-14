# Chat Rooms

Sistema de salas de conversa em tempo real desenvolvido com Spring Boot, Spring Security, JWT, WebSocket (STOMP) e JPA.

## Funcionalidades

* Cadastro de usuários
* Autenticação via JWT
* Criação de salas
* Entrada e saída de salas
* Envio de mensagens
* Recebimento de mensagens em tempo real via WebSocket
* Histórico de mensagens
* Listagem de notificações
* Perfil do usuário autenticado

---

# Autenticação

A autenticação é realizada através de JWT.

Após realizar login ou cadastro, a API retorna um token JWT que deve ser enviado em todas as rotas protegidas.

## Header esperado

```http
Authorization: Bearer <TOKEN>
```

---

# Rotas Públicas

## Health Check

Verifica se a aplicação está online.

### Request

```http
GET /health-check
```

### Response

```json
"Chat-Rooms is running"
```

---

## Registrar Usuário

Cria uma nova conta.

### Request

```http
POST /auth/register
```

### Body

```json
{
  "username": "gabriel",
  "password": "123456"
}
```

### Response

```json
{
  "accessToken": "jwt-token"
}
```

### Status

```http
201 Created
```

---

## Login

Autentica um usuário existente.

### Request

```http
GET /auth/login
```

### Body

```json
{
  "username": "gabriel",
  "password": "123456"
}
```

### Response

```json
{
  "accessToken": "jwt-token"
}
```

### Status

```http
200 OK
```

---

## WebSocket Handshake

Endpoint utilizado para estabelecer conexão WebSocket.

### Endpoint

```http
/ws
```

A conexão não exige autenticação.

---

# Rotas Autenticadas

Todas as rotas abaixo exigem:

```http
Authorization: Bearer <TOKEN>
```

---

# Usuário

## Visualizar Perfil

### Request

```http
GET /users/me
```

### Response

```json
{
  "id": "6c5afbc8-df3f-4c8f-8d34-8f08b5d77b1c",
  "username": "gabriel",
  "messages": 25,
  "roomsCreated": 3,
  "role": "MEMBER"
}
```

---

## Logout

Invalida o token atual.

### Request

```http
DELETE /auth/logout
```

### Response

```http
200 OK
```

---

# Salas

## Criar Sala

### Request

```http
POST /rooms
```

### Body

```json
{
  "name": "Java Developers"
}
```

### Response

```json
{
  "id": "uuid",
  "name": "Java Developers",
  "simpleDescription": null,
  "membersOfRoom": [
    {
      "id": "user-id",
      "username": "gabriel",
      "typeOfMember": "OWNER"
    }
  ]
}
```

---

## Listar Todas as Salas

### Request

```http
GET /rooms
```

### Response

```json
[
  {
    "id": "uuid",
    "name": "Java Developers",
    "simpleDescription": null,
    "membersOfRoom": []
  }
]
```

---

## Buscar Sala

### Request

```http
GET /rooms/{roomId}
```

### Response

```json
{
  "id": "uuid",
  "name": "Java Developers",
  "simpleDescription": null,
  "membersOfRoom": [
    {
      "id": "user-id",
      "username": "gabriel",
      "typeOfMember": "OWNER"
    }
  ]
}
```

---

## Listar Minhas Salas

Retorna apenas as salas das quais o usuário participa.

### Request

```http
GET /rooms/my
```

### Response

```json
[
  {
    "id": "uuid",
    "name": "Java Developers"
  }
]
```

---

## Entrar em uma Sala

### Request

```http
POST /rooms/{roomId}/enter
```

### Response

```json
{
  "id": "uuid",
  "name": "Java Developers",
  "simpleDescription": null,
  "membersOfRoom": []
}
```

---

## Atualizar Sala

### Request

```http
PUT /rooms/{roomId}
```

### Body

```json
{
  "name": "Backend Developers"
}
```

### Response

```json
{
  "id": "uuid",
  "name": "Backend Developers",
  "simpleDescription": null,
  "membersOfRoom": []
}
```

---

## Sair da Sala

### Request

```http
DELETE /rooms/{roomId}/exit
```

### Response

```http
200 OK
```

---

# Mensagens

## Enviar Mensagem

Além de persistir a mensagem no banco, a aplicação envia automaticamente o evento para todos os clientes inscritos via WebSocket.

### Request

```http
POST /messages/{roomId}
```

### Body

```json
{
  "message": "Olá pessoal!"
}
```

### Response

```json
{
  "id": "message-id",
  "message": "Olá pessoal!",
  "user": {
    "id": "user-id",
    "username": "gabriel"
  },
  "createdAt": "2026-06-14T18:30:00"
}
```

---

## Histórico de Mensagens

### Request

```http
GET /messages/{roomId}
```

### Response

```json
[
  {
    "id": "message-id",
    "message": "Olá pessoal!",
    "user": {
      "id": "user-id",
      "username": "gabriel"
    },
    "createdAt": "2026-06-14T18:30:00"
  }
]
```

---

# Notificações

## Listar Notificações da Sala

### Request

```http
GET /notifications/{roomId}
```

### Response

```json
[
  {
    "id": "notification-id",
    "message": "Gabriel entrou na sala",
    "createdAt": "2026-06-14T18:20:00"
  }
]
```

---

# WebSocket

O sistema utiliza STOMP sobre WebSocket para entrega de mensagens em tempo real.

## Configuração

### Endpoint de conexão

```text
ws://localhost:8080/ws
```

### Prefixo de aplicação

```text
/app
```

### Broker

```text
/topic
```

---

## Assinar Mensagens de uma Sala

Para receber mensagens em tempo real de uma sala específica:

### Subscribe

```text
/topic/rooms/{roomId}
```

Exemplo:

```text
/topic/rooms/5f14d5df-b19e-42f4-a55e-fd2cce84fca4
```

---

## Evento Recebido

Quando alguém envia uma mensagem utilizando:

```http
POST /messages/{roomId}
```

Todos os clientes inscritos recebem:

```json
{
  "id": "message-id",
  "message": "Olá pessoal!",
  "user": {
    "id": "user-id",
    "username": "gabriel"
  },
  "createdAt": "2026-06-14T18:30:00"
}
```

---

# Modelo de Permissões

## Rotas Públicas

```text
POST /auth/register
GET  /auth/login
GET  /health-check
/ws/**
```

## Rotas Protegidas

```text
/users/**
/rooms/**
/messages/**
/notifications/**
DELETE /auth/logout
```

---

# Tecnologias

* Java 21
* Spring Boot
* Spring Security
* JWT
* Spring WebSocket
* STOMP
* Spring Data JPA
* Hibernate
* PostgreSQL
* Lombok
* Maven
* Rabbimq

---

# Fluxo Básico de Utilização

1. Registrar usuário
2. Fazer login
3. Receber JWT
4. Criar ou entrar em uma sala
5. Conectar ao WebSocket `/ws`
6. Assinar `/topic/rooms/{roomId}`
7. Enviar mensagens usando `/messages/{roomId}`
8. Receber mensagens em tempo real pelo WebSocket
