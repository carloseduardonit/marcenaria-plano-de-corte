/**
 * Author:  Carlos
 * Created: 05/04/2019
 */
-- criação da tabela CPF se ela não existir
create table if not exists CPF(
 idPessoa int not null,
 numCPF int8 not null primary key,
 dataEmissaoCPF datetime not null,
 foreign key (idPessoa) references pessoa(id)
);
-- deletado da tabela CPF se ela existis 
drop table if exists CPF;
-- inserção de dados na tabela
insert into CPF(idPessoa,numCPF,dataEmissaoCPF)values(1,"11684955718",'1987/09/18');
-- selecionada dados na tabela CPF
select * from CPF;
--
delete from cpf where cpf.idPessoa = 1;
--
delete from cpf where cpf.numCPF = 11684955718;
--
delete from cpf where cpf.numCPF = 11684955718 or cpf.idPessoa = 1;
--
delete from cpf where cpf.numCPF = 11684955718 and cpf.idPessoa = 1;