CREATE TABLE tarefa (
	id bigint not null auto_increment,
	descricao varchar(100) not null,
	prioridade int not null,
	status int,
	primary key(id)
);

CREATE TABLE usuario (
	id bigint not null auto_increment,
	nome varchar(100) not null,
	email varchar(50) not null,
	senha varchar(200) not null,
	primary key(id)
);


