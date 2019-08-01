--Trigger para padronizar o nome e a turma dos alunos cadastrados em caixa alta anter da inserção na tabela

delimiter $$
drop trigger if exists padrão_nome_turma$$
create trigger padrão_nome_turma before insert on pi_aluno for each
row
begin
	set new.nome = UPPER(new.nome);
    set new.turma = UPPER(new.turma);
end$$
delimiter ;

--Trigger para atualizar a via da carteirinha emitida quando for anter da atualização de algum campo na tabela

delimiter $$
drop trigger if exists atualiza_via$$
create trigger atualiza_via before update on pi_carteirinha for each
row
begin
	SET new.via = 2;
	SET new.data_de_emissao = CURDATE();
end$$
delimiter ;

--Trigger para definir automaticamente a data de emissão com base na data atual da inserção

delimiter $$
drop trigger if exists data_emissao$$
create trigger data_emissao before insert on pi_carteirinha for each
row
begin
	SET new.via = 1;
	SET new.data_de_emissao = CURDATE();
end$$
delimiter ;