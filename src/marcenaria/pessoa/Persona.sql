create database if not exists persons;
use persons;
create table if not exists pessoa (
idpessoa int primary key auto_increment, 
login varchar(15) not  null unique,
senha varchar(15) not null,
tipoPessoa varchar(2) not null,
nome varchar(100) not null);
insert into pessoa(login, senha, tipoPessoa,nome) values ("usuario","usuario123","PF","Usuario0"),("system","system123","PJ","system");
create table if not exists Cliente(
idPessoa int not null unique, 
login varchar(15) not  null unique,
idCliente int  auto_increment primary key, 
docum varchar(14) not null unique, 
foreign key (login) references Pessoa(login),
foreign key (idPessoa) references Pessoa(idPessoa));
insert into cliente(idPessoa,login,idcliente,docum) values (1,"usuario",1,"12345678901"),(2,"system",2,"12345678901234");
create table if not exists  Fornecedor(
idPessoa int not null unique, login varchar(15) not  null unique, 
idFornecedor int  auto_increment primary key, 
docum varchar(14) not null unique, 
foreign key (login) references Pessoa(login),
foreign key (idPessoa) references Pessoa(idPessoa));
insert into fornecedor(idPessoa,login,idFornecedor,docum) values (1,"usuario",1,"12345678901"),(2,"system",2,"12345678901234");