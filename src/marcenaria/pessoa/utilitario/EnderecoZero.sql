/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Carlos
 * Created: 31/12/2019
 */
create database if not exists persons;
Use persons;
create table if not exists endereco (
idEndereco int auto_increment primary key, 
idPessoa int not null default 0,
idCliente int not null default 0, 
idFornecedor int not null default 0, 
numero int not null default 0, 
quantEndereco int not null default 0, 
complemento varchar(200), 
cep varchar(9) not null, 
foreign key (idPessoa) references Pessoa (idPessoa),
foreign key (idCliente) references Cliente(idCliente),
foreign key (idFornecedor) references Fornecedor(idFornecedor));
insert into Endereco(idPessoa, idCliente, idFornecedor, cep, numero, complemento) values (1,1,1,"24752-640",0,"Quadra 44 lote 11"),(2,2,2,"24752-545",0,"Quadra 44 lote 11");