# Sistema de Gerenciamento de Tarefas

## Cadastro de usuário

```
http://localhost:9090/users

{
    "name" :"Marcelo",
    "email": "marceloformentini@gmail.com",
    "password":"123456"
}
```

## Cadastro Task
```
http://localhost:9090/task

{
    "title" :"Cadastrar de usuário",
    "description": "Fazer o cadastro de novos usuários para ter controle de entrada/saída na portaria",
    "priority":"L",
    "status": "P",
    "user_id": 1
}
```

## Banco de dados
**Coluna status (Status da Tarefa)** - Esta coluna indicará o andamento da tarefa. Podemos usar as seguintes opções:

| Valor | Significado                |
|-------|----------------------------|
|   P   | Pendente (Pending)         |
|   I   | Em andamento (In Progress) |
|   C   | Concluído (Completed)      |
|   A   | Cancelado (Aborted)        |


**Coluna priority (Prioridade da Tarefa)** - Esta coluna indicará a importância da tarefa. Podemos definir os seguintes valores:

| Valor | Significado      |
|-------|------------------|
|   L   | Baixa (Low)      |
|   M   | Média (Medium)   |
|   H   | Alta (High)      |
|   U   | Urgente (Urgent) |