create table if not exists telefone(
idpessoa int not null,
DDI int not null default 55,
DDD int not null,
telefone varchar(10) not null,
tipotelefone varchar(20) not null,
foreign key (idpessoa) references pessoa (id)
);
drop table if exists telefone;               