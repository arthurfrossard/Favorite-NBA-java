**Descrição do Projeto: Sistema de Gerenciamento de Times de Basquete com Spring Boot e Consumo da API Ball Don't Lie**

O projeto consiste em um sistema desenvolvido em Java com Spring Boot para gerenciar uma lista de times de basquete favoritos. A aplicação realiza a integração com a API Ball Don't Lie (https://www.balldontlie.io/api/v1/) para obter informações sobre os times.

**Funcionalidades:**

1. **Listagem de Times Favoritos:**
   - Ao iniciar a aplicação, o usuário pode visualizar a lista de times de basquete favoritos previamente cadastrados.

2. **Cadastro de Times:**
   - A aplicação permite adicionar novos times à lista de favoritos, utilizando as informações disponíveis na API Ball Don't Lie, como nome, abreviação, cidade, conferência, e divisão.

3. **Atualização de Times:**
   - É possível editar as informações de um time já cadastrado, permitindo ao usuário manter os dados atualizados.

4. **Remoção de Times:**
   - O usuário pode remover times da lista de favoritos, caso não deseje mais acompanhá-los.

5. **Busca por ID:**
   - A aplicação possibilita buscar um time específico por meio do seu ID, exibindo suas informações detalhadas.

6. **Filtragem por Conference e Nome:**
   - O usuário pode filtrar a lista de times por conference e por nome, facilitando a visualização de times específicos.

7. **Testes Unitários:**
   - Foram implementados testes unitários para os principais métodos do sistema, garantindo a integridade e confiabilidade das funcionalidades.

**Tecnologias Utilizadas:**

- **Java:** Linguagem de programação principal.
- **Spring Boot:** Framework utilizado para o desenvolvimento de aplicativos Java.
- **API Ball Don't Lie:** Fonte de dados para informações sobre times de basquete.
- **JUnit:** Framework de teste para testes unitários.

**Instruções de Uso:**

1. Clone o repositório do projeto.
2. Configure as propriedades da API Ball Don't Lie no arquivo de configuração.
3. Execute a aplicação.
4. Utilize as operações CRUD para gerenciar sua lista de times de basquete favoritos.

**Documentação da API de Gerenciamento de Times de Basquete**

Esta documentação fornece informações sobre os endpoints disponíveis na API desenvolvida para gerenciar times de basquete favoritos utilizando Spring Boot.

**1. Get All Times**

   - **Endpoint:** http://localhost:8080/times
   - **Descrição:** Retorna todos os times cadastrados.

**2. Get Times Filtrando por Conference**

   - **Endpoint:** http://localhost:8080/times?conference={conference}
   - **Descrição:** Retorna os times filtrados por conference.

**3. Get Times Filtrando por Name**

   - **Endpoint:** http://localhost:8080/times?name={name}
   - **Descrição:** Retorna os times filtrados por nome.

**4. Get All Times Favoritos**

   - **Endpoint:** http://localhost:8080/timesfavoritos
   - **Descrição:** Retorna todos os times favoritos cadastrados.

**5. Get Times Favoritos Filtrando por Conference**

   - **Endpoint:** http://localhost:8080/timesfavoritos?conference={conference}
   - **Descrição:** Retorna os times favoritos filtrados por conference.

**6. Get Times Favoritos Filtrando por Name**

   - **Endpoint:** http://localhost:8080/timesfavoritos?name={name}
   - **Descrição:** Retorna os times favoritos filtrados por nome.

**7. Post Times Favoritos**

   - **Endpoint:** http://localhost:8080/timesfavoritos
   - **Descrição:** Adiciona um novo time favorito. Deve receber um JSON contendo as informações do time.

**8. Put / Update Times Favoritos**

   - **Endpoint:** http://localhost:8080/timesfavoritos/{id}
   - **Descrição:** Atualiza as informações de um time favorito existente. Deve receber um JSON contendo as informações atualizadas.

**9. Delete Times Favoritos**

   - **Endpoint:** http://localhost:8080/timesfavoritos/{id}
   - **Descrição:** Remove um time favorito com base no ID fornecido.

**Observações:**

- Certifique-se de substituir `{conference}`, `{name}`, e `{id}` pelos valores desejados nos endpoints que requerem parâmetros.
- Nos endpoints de POST e PUT, é necessário enviar um JSON válido contendo as informações do time.

Esta documentação fornece uma visão geral dos endpoints disponíveis na API. Certifique-se de utilizar métodos HTTP apropriados (GET, POST, PUT, DELETE) para interagir com a API de acordo com as operações desejadas.

O projeto proporciona uma experiência de uso intuitiva, permitindo aos usuários acompanhar e gerenciar seus times de basquete favoritos de forma eficiente e organizada.
