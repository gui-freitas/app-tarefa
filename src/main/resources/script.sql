CREATE TABLE tarefa (
	id bigint not null auto_increment,
	descricao varchar(100) not null,
	prioridade int not null,
	status int,
	primary key(id)
);

