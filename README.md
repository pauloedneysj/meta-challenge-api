# Apresentação do Projeto

Bem-vindo ao servidor do Meta Challenge, projeto desenvolvido com Spring Boot! Este é um sistema de gerenciamento de usuários e produtos, projetado com foco na simplicidade e eficiência.

## Sobre o Projeto

Este projeto foi criado com o objetivo de fornecer uma plataforma robusta e segura para o cadastro de usuários e produtos. Ele é ideal para pequenas e médias empresas que desejam gerenciar seus produtos de maneira eficiente.

## Recursos

O sistema é dividido em dois níveis de permissão: `Admin` e `User`.

- **Admin**: O usuário com permissão de administrador tem acesso a todos os recursos do site. Isso inclui a capacidade de visualizar, criar, editar e excluir qualquer usuário ou produto no sistema.

- **User**: O usuário comum tem acesso limitado, podendo apenas cadastrar e editar os produtos que ele mesmo criou. Isso garante que cada usuário possa gerenciar seus próprios produtos de maneira eficiente, sem interferir nos produtos de outros usuários.

# API Java com Maven, Spring Boot, Spring Security e Spring MongoDB

Este projeto é uma API desenvolvida com Java, utilizando o gerenciador de dependências Maven e o framework Spring Boot. Ele também incorpora o Spring Security para autenticação e autorização, e o Spring MongoDB para persistência de dados.

## Configuração do Ambiente de Desenvolvimento

Para configurar o ambiente de desenvolvimento, você precisará ter o Java e o Maven instalados em sua máquina. Você pode verificar se eles estão instalados corretamente executando os seguintes comandos no terminal:

```bash
java -version
mvn -version
```

## Executando a Aplicação

Para executar a aplicação, navegue até o diretório raiz do projeto e execute o seguinte comando:

```bash
mvn spring-boot:run
```

A aplicação será iniciada e estará disponível no endereço `http://localhost:8093`.

## Testes

Para executar os testes unitários e de integração, use o seguinte comando:

```bash
mvn test
```

## Deploy

Para construir o projeto e gerar um arquivo JAR, use o seguinte comando:

```bash
mvn clean install
```

O arquivo JAR será gerado no diretório `target`.

## Segurança

Este projeto utiliza o Spring Security para fornecer autenticação e autorização. Certifique-se de configurar adequadamente as credenciais de usuário e as funções de acesso.

## Banco de Dados

Este projeto utiliza o Spring MongoDB para persistência de dados. Certifique-se de ter uma instância do MongoDB em execução e configure adequadamente a string de conexão no arquivo `application.properties`.

## Ajuda Adicional

Para obter mais ajuda sobre o Spring Boot, consulte a [Referência Oficial do Spring Boot](https://spring.io/projects/spring-boot#learn). Para mais informações sobre o Maven, consulte a [Documentação Oficial do Maven](https://maven.apache.org/guides/index.html).
