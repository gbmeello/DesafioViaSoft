Desafio Tecnico para concorrer a Vaga de Desenvolvedor Pleno na Empresa ViaSoft.

Objetivo
Imagine (sem realizar a integração) que você precisa enviar e-mail mediante plataformas como AWS e OCI. 
O teste consiste em criar uma aplicação REST com endpoint que recebe dados para envio de email, com apenas uma requisição, sem alterar o objeto de entrada, 
dependendo da configuração setada em application.properties o objeto deve ser adaptado para novas classes, também deve ser serializado e impresso no console.

Ambiente de desenvolvimento Solicitada
Java 17, Maven e a lib Spring Web. Pode ser utilizado outras libs em conjunto.

Requisitos
1 - Crie uma aplicação REST com um endpoint, que deverá receber um objeto e processar as informações.
A requisição deve ser feita utilizando o Postman ou uma ferramenta similar.
2 - Crie uma classe DTO e Modele os atributos contendo as seguintes informações:
E-mail do destinatário
Nome do destinatário
Email do remetente
Assunto
Conteúdo
2.1 - O objeto recebido no Controller será uma instância da classe criada acima.
3 - Crie em application.properties um atributo chamado mail.integracao, que o valor pode ser configurado com OCI ou AWS.
4 - Crie a classe chamada EmailAwsDTO, com os seguintes atributos:
recipient
E-mail destinatário: Max: 45 caracteres
recipientName
Nome destinatário. Max: 60 caracteres
sender
E-mail remetente. Max: 45 caracteres
subject
Assunto do e-mail. Max: 120 caracteres
content
Conteúdo do e-mail. Max: 256 caracteres

5 - Crie a classe chamada EmailOciDTO, com os seguintes atributos:
recipientEmail
E-mail destinatário: Max: 40 caracteres
recipientName
Nome destinatário. Max: 50 caracteres
senderEmail
E-mail remetente. Max: 40 caracteres
subject
Assunto do e-mail. Max: 100 caracteres
body
Conteúdo do e-mail. Max: 250 caracteres

6 - Ao receber a requisição, adaptar a informação do objeto para a classe EmailAwsDTO ou EmailOciDTO, dependendo da configuração em application.properties.
7 - Serializar o objeto em JSON e imprimir no console.
8 - Se a requisição ocorrer com sucesso retornar status 204.
9 - Se a requisição falhar, tratar os erros com status 400 ou 500.
----------------------------------------------------------xx------------------------------------------------------------------------------------------------

Resumo das etapas e como foi elaborado o projeto.

1 - Após analise do desafio, gerei a base do projeto no spring initializer com as libs que foram solicitadas.
2 - Foi elaborado toda a estrutura do projeto em seus packages.
3 - Implementamos os DTOS solicitados.
4 - Implementamos o Service Solicitado, fazendo a injecao da dependencia do mailIntegration permitindo que o codigo seja alterado externamente,
em seguida a função de enviar o email com a condicional de AWS e OCI e serializando utilizando o ObjectMapper dando a possibilidade de imprimir no console, logo após o mapeamento dos DTOS com o truncate que limita o tamanho dos mesmos.
5 - Implementamos o Controller definindo o endpoit e injetando o service, passando o metodo que recebe o objeto DTO com anotacao do @Valid que indica que o objeto tem que ser validado antes de ser processado, e o try catch da condicional do envio do email,
tudo isso em uma requisição POST.
6 - Implementamos a Classe de TEST do Controller, para realizar os Testes de Unitários se estão fazendo todas as funcoes.
7 - Implementamos a Classe de TEST do Service, para realizar os Testes de Integração.

