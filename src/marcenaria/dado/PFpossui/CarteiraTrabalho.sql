create table if not exists CarteiraTrabalho(
idpessoa int not null,
foreign key (idpessoa) references pessoa(id)
);
drop table if exists CarteiraTrabalho;
