--LISTAR ALUNOS QUE POSSUEM CARTEIRINHA
delimiter $$
drop procedure if exists listarAlunosCarteirinha $$
create procedure listarAlunosCarteirinha()
begin
	select * from pi_aluno, pi_carteirinha where pi_aluno.matricula = pi_carteirinha.aluno_matricula;
end $$
delimiter ;

--LISTAR ALUNOS QUE NÃO POSSUEM CARTEIRINHA
delimiter $$
drop procedure if exists listarAlunosSemCarteirinha $$
create procedure listarAlunosSemCarteirinha()
begin
	select * from pi_aluno, pi_carteirinha where pi_aluno.matricula != pi_carteirinha.aluno_matricula;
end $$
delimiter ;

--LISTAR ALUNOS POR TURMA
delimiter $$
drop procedure if exists listarAlunosPorTurma $$
create procedure listarAlunosPorTurma(turma varchar(255))
begin
	select * from pi_aluno where pi_aluno.turma = turma;
end $$
delimiter ;

--LISTAR ALUNOS POR CURSO
delimiter $$
drop procedure if exists listarAlunosPorCurso $$
create procedure listarAlunosPorCurso(curso int)
begin
	select * from pi_aluno where pi_aluno.curso_id = curso;
end $$
delimiter ;

--LISTAR CARTEIRINHAS ATIVAS
delimiter $$
drop procedure if exists listarCarteirinhasAtivas $$
create procedure listarCarteirinhasAtivas()
begin
	select * from pi_carteirinha where CURDATE() <= pi_carteirinha.validade;
end $$
delimiter ;

--LISTAR CARTEIRINHAS FORA DA VALIDADE
delimiter $$
drop procedure if exists listarCarteirinhasVencidas $$
create procedure listarCarteirinhasVencidas()
begin
	select * from pi_carteirinha where CURDATE() > pi_carteirinha.validade;
end $$
delimiter ;