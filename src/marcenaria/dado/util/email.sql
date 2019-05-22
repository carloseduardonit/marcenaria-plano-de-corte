select * from pessoa;
create table if not exists email (
idpessoal int not null,
quantEmail int not null,
email varchar(30) not null,
tipoemail varchar(20) not null,
foreign key email(idpessoal) references pessoa(id)
);
drop table if exists email;
select * from email;