/**
 * Author:  Carlos
 * Created: 04/04/2019
 */
 use teste;
 
create table if not  exists identidade (
idPessoa int not null,
dataEmissaoIdentidade date not null,
orgaoEmissao varchar(20) not null,
estadoEmissao varchar(20) not null,
numIdentidade int not null,
foreign key (idPessoa)references pessoa(id)
);
drop table if exists identidade;