# #aluraflix-backend
Desenvolvimento de uma API REST para o evento *Alura Challenge Back-end* em julho/agosto de 2021. A proposta do projeto é o desenvolvimento da primeira versão de uma plataforma para compartilhamento de vídeos. 
<br />

# Requisitos
O projeto utiliza maven, openJDK versão 11 com o framework spring boot versão 2.5.2 e banco de dados mariadb versão 15.1.

# Projeto e Descrição da API

**1) Baixe o projeto a partir deste repositório.** <br />

No shell do linux, digite o comando: <br />
`$ git clone https://github.com/adilson-vieira/aluraflix-backend.git`

Importe o projeto para o ambiente de desenvolvimento (IDE) de sua preferência. As dependências serão satisfeitas conforme declaradas no arquivo de projeto [pom](https://github.com/adilson-vieira/aluraflix-backend/blob/main/pom.xml) após a importação.

**2) Crie o banco de dados no mariadb.**

No shell do linux, digite os comandos:

`$ mariadb -u root -p    // abre o prompt do mariadb` <br />
`MariaDB [(none)]> create database aluraflix;   // cria a base de dados "aluraflix" ` <br />
`MariaDB [(none)]> use aluraflix;  // muda para base de dados recém criada ` <br />
`Database changed` <br />
`MariaDB [aluraflix]>`

Altere as informações de usuário, senha, nome da base de dados no arquivo [application.properties](https://github.com/adilson-vieira/aluraflix-backend/blob/main/src/main/resources/application.properties) caso necessário.

**3) Populando o banco com alguns vídeos para testes:**

*Esse tópico é opcional. Caso queira, utilize as funcionalidades disponíveis na API conforme o tópico 4.*

No prompt do mariadb digite a sequência de comandos abaixo. Esses registros podem servir para apresentação da API bem como para os testes.

Tabela de videos:

`INSERT INTO videos(titulo, descricao, url, categoria_id) VALUES("Como Funcionam Os Emuladores com Fábio Akita","descrição do video aqui", "https://www.youtube.com/watch?v=9qx7qjKhJ1Q", 1);`

`INSERT INTO videos(titulo, descricao, url, categoria_id) VALUES("O que é Data Science?","descrição do video aqui", "https://www.youtube.com/watch?v=5b9Z8toVaAU", 1);`

`INSERT INTO videos(titulo, descricao, url, categoria_id) VALUES("O que é SQL e NoSQL?","descrição do video aqui", "https://www.youtube.com/watch?v=aure5d3B88g", 1);`

`INSERT INTO videos(titulo, descricao, url, categoria_id) VALUES("O que é o C#?","descrição do video aqui", "https://www.youtube.com/watch?v=yj0FR_gm0sM", 1);`

`INSERT INTO videos(titulo, descricao, url, categoria_id) VALUES("Quatro Mitos sobre Programação","descrição do video aqui", "https://www.youtube.com/watch?v=yj0FR_gm0sM", 1);`

Tabela de categorias:

Por convenção o `id =  1` possui o atributo `titulo = "LIVRE"`. Insira novas categorias com o comando:
`INSERT INTO categorias(titulo, cor) VALUES("nome_da_categoria", "#cor_em_hexadecimal")`

**4) Descrição da API:** 

Videos:

**`GET  /videos`**     Lista todos os vídeos ou retorna a lista vazia. Retorna um `http status code 200` se a requisição obteve sucesso ou `http status code 204` se a lista está vazia.

**`GET  /videos/pagina?pagina=_ &qtd=_`**     Lista os vídeos por paginacão e retorna a página com a quantidade de vídeos de acordo com os valores dos parâmetros `pagina` e `qtd` ou retorna a página com a lista vazia. Retorna um `http status code 200` se a requisição obteve sucesso ou `http status code 204` se a lista está vazia.

**`GET /videos/{id}`**    Busca um determinado vídeo por seu id. Retorna um `http status code 200` se a requisição obteve sucesso ou `http status code 404`caso não encontrado.

**`GET  /videos/?search=_`**     Pesquisa o valor informado em `search` nos títulos dos vídeos e retorna uma lista com as correspondências dos vídeos ou retorna a lista vazia caso não encontrada alguma correspondência. Retorna um `http status code 200` se a requisição obteve sucesso ou `http status code 204` se alista está vazia.

**`POST /videos`**     Cadastra um `json` com `titulo`, `descricao` e `url` como payload de body. Retorna um `http status code 201` se a requisição obteve sucesso. Exemplo do `json`: 
<br />`{` <br />
          &nbsp;&nbsp;&nbsp;&nbsp; `"titulo": "título do novo vídeo",` <br />
          &nbsp;&nbsp;&nbsp;&nbsp;`"descricao": "descrição do novo vídeo",`   
          &nbsp;&nbsp;&nbsp;&nbsp;`"url": "https://www.youtube.com/watch?v=code_here"`     
`}` <br />

**`DELETE /videos/{id}`**   Exclui um determinado vídeo informando o id. Retorna um `http status code 200` se a requisição obteve sucesso ou `http status code 404` caso contrário.

**`PUT /videos/{id}`**    Atualiza um vídeo e utiliza um `json` com `titulo`, `descricao` e `url` como payload de body. Retorna um `http status code 200` se a requisição obteve sucesso ou `http status code 404` caso contrário. Exemplo do `json`: 
<br />`{` <br />
          &nbsp;&nbsp;&nbsp;&nbsp; `"titulo": "novo título do vídeo",` <br />
          &nbsp;&nbsp;&nbsp;&nbsp;`"descricao": "nova descrição do vídeo",`   
          &nbsp;&nbsp;&nbsp;&nbsp;`"url": "https://www.youtube.com/watch?v=code_here"`     
`}` <br />

Categorias: 

**`GET  /categorias`**  <br /> 
**`GET  /categorias/{id}`** <br />
**`GET  /categorias/{id}/videos`** <br />
**`POST  /categorias`** <br />
**`PUT  /categorias/{id}`** <br />
**`DELETE  /categorias/{id}`** <br />

Relatorios: <br />
**`GET /categorias/relatorios`** <br />

**5) Testes**

Os testes das requisições foram executados utilizando o [Postman](https://www.postman.com).

