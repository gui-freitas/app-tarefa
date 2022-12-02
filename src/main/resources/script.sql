CREATE TABLE tarefa (
	id bigint not null auto_increment,
	descricao varchar(100) not null,
	prioridade int not null,
	status int,
	primary key(id)
);

insert into tarefa (descricao, prioridade, status) values ('Desenvolver', 1, 1);
insert into tarefa (descricao, prioridade) values ('Desenvolver testes', 1);
insert into tarefa (descricao, prioridade, status) values ('Desenvolver sql', 2, 2);
