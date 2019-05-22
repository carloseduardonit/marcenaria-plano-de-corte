create  table certidaoNascimento(
-- dados  do nascimento
idPessoa int unique ,
dataNascimento date not null,
bairroNascimento varchar(100) not null,
cidadeNascimento varchar(100)not null,
estadoNascimento varchar(100) not null,
-- dados da filiacao
nomeMae varchar(200) not null default "Não informado",
nomePai varchar(200) not null default "Não informado",
-- dados  do cartorio
nomeLivro varchar(110)not null default"Livro de Certidão de Nascimento",
nFolhas int,
nTermo int,
bairroRegistro varchar(100) not null,
cidadeRegistro varchar(100)not null,
estadoRegistro varchar(100) not null,
foreign key (idPessoa) references pessoa(id));
SELECT * FROM teste.certidaonascimento;
insert into  teste.certidaonascimento(idPessoa,
dataNascimento,bairroNascimento,cidadeNascimento,estadoNascimento,
nomeMae,nomePai,
nomeLivro,nFolhas,nTermo,bairroRegistro,cidadeRegistro,estadoRegistro) 
values(1,
"1987/09/18","Centro","Sao gonçalo","RJ",
"Maria do carmo","Carlos Augusto", 
"Certidão de Nascimento",10,39568,"Centro","Sao gonçalo","RJ"
) ;
drop table certidaonascimento;