create table if not exists pessoa(
id int  not null auto_increment primary key,
nomeCompleto varchar(200) not null,
tipoPessoa varchar(2) not null,
login varchar(20) unique not null,
senha varchar(10) not null);
drop table if exists pessoa;

insert into pessoa(pessoa.nomeCompleto,pessoa.tipoPessoa,pessoa.login,pessoa.senha) values("Carlos Eduardo dos Santos Figueiredo","PF","CarlosTecnico","39568");
select pessoa.nomeCompleto,pessoa.tipoPessoa,pessoa.login,pessoa.senha from pessoa where id=1;
update pessoa set pessoa.nomeCompleto = "Carlos Eduardo dos Santos Figueiredo",pessoa.tipoPessoa="PJ",pessoa.login="Carlos",pessoa.senha="39568" where id =1;

