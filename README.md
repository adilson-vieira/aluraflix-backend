# #aluraflix-backend
Desenvolvimento de uma API RESTFull para o evento Alura Challenge Back-end em julho/agosto de 2021. A proposta do projeto é primeira versão de uma plataforma para compartilhamento de vídeos.

# Requisitos
O projeto utiliza maven, openJDK versão 11 com o framework spring boot versão 2.5.2 e banco de dados mariadb versão 15.1.

# Projeto e Descrição da API

**1) Baixe o projeto a partir deste repositório.** <br />
Importe o projeto para o ambiente de desenvolvimento (IDE) de sua preferência. As dependências serão satisfeitas conforme declaradas no arquivo de projeto [pom](https://github.com/adilson-vieira/aluraflix-backend/blob/main/pom.xml).

**2) Crie o banco de dados no mariadb.**

No shell do linux, digite os comandos:

`$ mariadb -u root -p    // abre o prompt do mariadb` <br />
`MariaDB [(none)]> create datebase aluraflix   // cria a base de dados "aluraflix" ` <br />
`MariaDB [(none)]> use aluraflix  // muda para base de dados recém criada ` <br />
`Database changed` <br />
`MariaDB [aluraflix]>`

Altere as informações de usuário e senha do arquivo [application.properties](https://github.com/adilson-vieira/aluraflix-backend/blob/main/src/main/resources/application.properties).

**3) Populando o banco com alguns vídeos:**

No prompt do mariadb digite a sequência de comandos abaixo. Esses registros podem servir para apresentação da API bem como para os testes.

`INSERT INTO videos(titulo, descricao, url) VALUES("Como Funcionam Os Emuladores com Fábio Akita","descrição do video aqui", "https://www.youtube.com/watch?v=9qx7qjKhJ1Q");`

`INSERT INTO videos(titulo, descricao, url) VALUES("O que é Data Science?","descrição do video aqui", "https://www.youtube.com/watch?v=5b9Z8toVaAU");`

`INSERT INTO videos(titulo, descricao, url) VALUES("O que é SQL e NoSQL?","descrição do video aqui", "https://www.youtube.com/watch?v=aure5d3B88g");`

`INSERT INTO videos(titulo, descricao, url) VALUES("O que é o C#?","descrição do video aqui", "https://www.youtube.com/watch?v=yj0FR_gm0sM");`

`INSERT INTO videos(titulo, descricao, url) VALUES("Quatro Mitos sobre Programação","descrição do video aqui", "https://www.youtube.com/watch?v=yj0FR_gm0sM");`

**3) Descrição da API:**

**`GET  /videos`**     lista todos os vídeos ou retorna a lista vazia. Retorna um `http status code 200` se a requição obteve sucesso ou `http status code 204`.

**`GET /videos/{id}`**    busca um determinado vídeo por seu id.Retorna um `http status code 200` se a requição obteve sucesso ou `http status code 404`caso contrário.

**`POST /videos`**     utiliza um `json` com `titulo`, `descricao` e `url` como payload de body. Retorna um `http status code 200` se a requição obteve sucesso ou `http status code 400` caso contrário. Exemplo do `json`: 
<br />`{` <br />
          &nbsp;&nbsp;&nbsp;&nbsp; `"titulo": "título do novo vídeo",` <br />
          &nbsp;&nbsp;&nbsp;&nbsp;`"descricao": "descrição do novo vídeo",`   
          &nbsp;&nbsp;&nbsp;&nbsp;`"url": "https://www.youtube.com/watch?v=code_here"`     
`}` <br />

**`DELETE /videos/{id}`** exclui um determinado vídeo informando o id. Retorna um `http status code 200` se a requição obteve sucesso ou `http status code 404` caso contrário.

**`PUT /videos/{id}`**     utiliza um `json` com `titulo`, `descricao` e `url` como payload de body. Retorna um `http status code 200` se a requição obteve sucesso ou `http status code 404` caso contrário. Exemplo do `json`: 
<br />`{` <br />
          &nbsp;&nbsp;&nbsp;&nbsp; `"titulo": "novo título do vídeo",` <br />
          &nbsp;&nbsp;&nbsp;&nbsp;`"descricao": "nova descrição do vídeo",`   
          &nbsp;&nbsp;&nbsp;&nbsp;`"url": "https://www.youtube.com/watch?v=code_here"`     
`}` <br />

**4) Testes**

Os testes das requisições foram executados utilizando o [Postman](https://www.postman.com).

