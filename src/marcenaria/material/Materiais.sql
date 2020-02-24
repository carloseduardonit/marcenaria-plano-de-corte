/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Carlos
 * Created: 13/01/2020
 */

create database if not exists Material;
use Material;
create table if not exists Chapa( 
idChapa int primary key auto_increment,
quantidade int default 0, 
comprimento double (7,2),
largura double (7,2),
espessura double(4,2),
preco double (10,2),
tipoMaterial varchar(30),
idFornecedor int not null default 0,
Textura boolean default true,
dataInclução time,
foreign key(idFornecedor) references persons.Fornecedor(idFornecedor));
insert into Chapa(quantidade, comprimento, largura, espessura, preco, tipoMaterial, idFornecedor) 
values 
(10,220.00,160.00,0.4,0.00,"Compensado",1),(10,220.00,160.00,0.6,0.00,"Compensado",1),
(10,220.00,160.00,1.0,0.00,"Compensado",1),(10,220.00,160.00,1.5,0.00,"Compensado",1),
(10,220.00,160.00,1.8,0.00,"Compensado",1),(10,220.00,160.00,2.0,0.00,"Compensado",1),
(10,220.00,160.00,0.4,0.00,"Compensado",2),(10,220.00,160.00,0.6,0.00,"Compensado",2),
(10,220.00,160.00,1.0,0.00,"Compensado",2),(10,220.00,160.00,1.5,0.00,"Compensado",2),
(10,220.00,160.00,1.8,0.00,"Compensado",2),(10,220.00,160.00,2.0,0.00,"Compensado",2),
(10,220.00,160.00,0.4,0.00,"MDF Comum sem revestimento",1),(10,220.00,160.00,0.6,0.00,"MDF Comum sem revestimento",1),
(10,220.00,160.00,1.0,0.00,"MDF Comum sem revestimento",1),(10,220.00,160.00,1.5,0.00,"MDF Comum sem revestimento",1),
(10,220.00,160.00,1.8,0.00,"MDF Comum sem revestimento",1),(10,220.00,160.00,2.0,0.00,"MDF Comum sem revestimento",1),
(10,220.00,160.00,0.4,0.00,"MDF Comum sem revestimento",2),(10,220.00,160.00,0.6,0.00,"MDF Comum sem revestimento",2),
(10,220.00,160.00,1.0,0.00,"MDF Comum sem revestimento",2),(10,220.00,160.00,1.5,0.00,"MDF Comum sem revestimento",2),
(10,220.00,160.00,1.8,0.00,"MDF Comum sem revestimento",2),(10,220.00,160.00,2.0,0.00,"MDF Comum sem revestimento",2),
(10,220.00,160.00,0.4,0.00,"MDF Comum com revestimento 2F",1),(10,220.00,160.00,0.6,0.00,"MDF Comum com revestimento 2F",1),
(10,220.00,160.00,1.0,0.00,"MDF Comum com revestimento 2F",1),(10,220.00,160.00,1.5,0.00,"MDF Comum com revestimento 2F",1),
(10,220.00,160.00,1.8,0.00,"MDF Comum com revestimento 2F",1),(10,220.00,160.00,2.0,0.00,"MDF Comum com revestimento 2F",1),
(10,220.00,160.00,0.4,0.00,"MDF Comum com revestimento 2F",2),(10,220.00,160.00,0.6,0.00,"MDF Comum com revestimento 2F",2),
(10,220.00,160.00,1.0,0.00,"MDF Comum com revestimento 2F",2),(10,220.00,160.00,1.5,0.00,"MDF Comum com revestimento 2F",2),
(10,220.00,160.00,1.8,0.00,"MDF Comum com revestimento 2F",2),(10,220.00,160.00,2.0,0.00,"MDF Comum com revestimento 2F",2),
(10,220.00,160.00,0.4,0.00,"MDF resistente umidade sem revestimento",1),(10,220.00,160.00,0.6,0.00,"MDF resistente umidade sem revestimento",1),(10,220.00,160.00,1.0,0.00,"MDF resistente umidade sem revestimento",1),
(10,220.00,160.00,1.5,0.00,"MDF resistente umidade sem revestimento",1),(10,220.00,160.00,1.8,0.00,"MDF resistente umidade sem revestimento",1),(10,220.00,160.00,2.0,0.00,"MDF resistente umidade sem revestimento",1),
(10,220.00,160.00,0.4,0.00,"MDF resistente umidade sem revestimento",2),(10,220.00,160.00,0.6,0.00,"MDF resistente umidade sem revestimento",2),(10,220.00,160.00,1.0,0.00,"MDF resistente umidade sem revestimento",2),
(10,220.00,160.00,1.5,0.00,"MDF resistente umidade sem revestimento",2),(10,220.00,160.00,1.8,0.00,"MDF resistente umidade sem revestimento",2),(10,220.00,160.00,2.0,0.00,"MDF resistente umidade sem revestimento",2),
(10,220.00,160.00,0.4,0.00,"MDF resistente umidade com revestimento 2F",1),(10,220.00,160.00,0.6,0.00,"MDF resistente umidade com revestimento 2F",1),(10,220.00,160.00,1.0,0.00,"MDF resistente umidade com revestimento 2F",1),
(10,220.00,160.00,1.5,0.00,"MDF resistente umidade com revestimento 2F",1),(10,220.00,160.00,1.8,0.00,"MDF resistente umidade com revestimento 2F",1),(10,220.00,160.00,2.0,0.00,"MDF resistente umidade com revestimento 2F",1),
(10,220.00,160.00,0.4,0.00,"MDF resistente umidade com revestimento 2F",2),(10,220.00,160.00,0.6,0.00,"MDF resistente umidade com revestimento 2F",2),(10,220.00,160.00,1.0,0.00,"MDF resistente umidade com revestimento 2F",2),
(10,220.00,160.00,1.5,0.00,"MDF resistente umidade com revestimento 2F",2),(10,220.00,160.00,1.8,0.00,"MDF resistente umidade com revestimento 2F",2),(10,220.00,160.00,2.0,0.00,"MDF resistente umidade com revestimento 2F",2);

create table if not exists Pedaco(
idPedaco int primary key auto_increment,
quantidade int default 0,
comprimento double(7,2) not null,
largura double(7,2) not null,
espessura double(4,2) not null,
preco double(10,2) not null,
tipoMaterial varchar(30) not null,
idChapa int default 0,
idPeca int default '0',
textura boolean default true,
incData timestamp,
foreign key (idChapa) references Chapa (idChapa));

create table if not exists Peca(
idPeca int primary key auto_increment,
quantidade int not null default 0,
comprimento double(7,2) not null default 0,
largura double(7,2) not null default 0,
espessura double(4,2) not null default 0,
preco double(10,2) not null default 0,
tipoMaterial varchar(30) not null,
podeVirar boolean default false,
idChapa int not null default 0,
idPedaco int not null default 0,
foreign key(idPedaco) references Pedaco(idPedaco),
foreign key(idChapa) references Chapa (idChapa));
