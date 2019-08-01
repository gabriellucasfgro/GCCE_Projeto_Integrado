
--SCRIPT DE CRIAÇÃO RETIRADO DO MYSQL WORKBENCH >>
	
	-- MySQL Workbench Forward Engineering

	SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
	SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
	SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

	-- -----------------------------------------------------
	-- Schema tads17_gabriel
	-- -----------------------------------------------------

	-- -----------------------------------------------------
	-- Schema tads17_gabriel
	-- -----------------------------------------------------
	CREATE SCHEMA IF NOT EXISTS `tads17_gabriel` DEFAULT CHARACTER SET utf8 ;
	USE `tads17_gabriel` ;

	-- -----------------------------------------------------
	-- Table `tads17_gabriel`.`pi_curso`
	-- -----------------------------------------------------
	CREATE TABLE IF NOT EXISTS `tads17_gabriel`.`pi_curso` (
	  `id` INT NOT NULL,
	  `nome` VARCHAR(255) NOT NULL,
	  PRIMARY KEY (`id`))
	ENGINE = InnoDB;


	-- -----------------------------------------------------
	-- Table `tads17_gabriel`.`pi_aluno`
	-- -----------------------------------------------------
	CREATE TABLE IF NOT EXISTS `tads17_gabriel`.`pi_aluno` (
	  `matricula` BIGINT NOT NULL,
	  `curso_id` INT NOT NULL,
	  `nome` VARCHAR(255) NOT NULL,
	  `turma` VARCHAR(45) NOT NULL,
	  PRIMARY KEY (`matricula`, `curso_id`),
	  INDEX `fk_pi_aluno_pi_curso_idx` (`curso_id` ASC),
	  CONSTRAINT `fk_pi_aluno_pi_curso`
	    FOREIGN KEY (`curso_id`)
	    REFERENCES `tads17_gabriel`.`pi_curso` (`id`)
	    ON DELETE NO ACTION
	    ON UPDATE NO ACTION)
	ENGINE = InnoDB;


	-- -----------------------------------------------------
	-- Table `tads17_gabriel`.`pi_carteirinha`
	-- -----------------------------------------------------
	CREATE TABLE IF NOT EXISTS `tads17_gabriel`.`pi_carteirinha` (
	  `aluno_matricula` BIGINT NOT NULL,
	  `validade` DATE NOT NULL,
	  `data_de_emissao` DATE NOT NULL,
	  `via` INT NOT NULL,
	  PRIMARY KEY (`aluno_matricula`),
	  CONSTRAINT `fk_pi_carteirinha_pi_aluno1`
	    FOREIGN KEY (`aluno_matricula`)
	    REFERENCES `tads17_gabriel`.`pi_aluno` (`matricula`)
	    ON DELETE NO ACTION
	    ON UPDATE NO ACTION)
	ENGINE = InnoDB;


	SET SQL_MODE=@OLD_SQL_MODE;
	SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
	SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- SCRIPT DE POVOAMENTO (APENAS TADS) >>

	INSERT INTO pi_curso (nome) values("Tecnologia em Análise e Desenvolvimento de Sistemas");
	INSERT INTO pi_curso (nome) values("Tecnologia em Gestão Ambiental");
	INSERT INTO pi_curso (nome) values("Tecnologia em Manutenção Industrial");
	INSERT INTO pi_curso (nome) values("Licenciatura em Ciências Sociais");
	INSERT INTO pi_curso (nome) values("Licenciatura em Física");

	--100 registros
	INSERT INTO `pi_aluno` (`matricula`,`nome`,`turma`,`curso_id`) VALUES (1,"Leonard","TADS18",1),(2,"Chava","TADS17",1),(3,"Bernard","TADS17",1),(4,"Jeremy","TADS17",1),(5,"Mannix","TADS18",1),(6,"Jada","TADS16",1),(7,"Althea","TADS17",1),(8,"Malcolm","TADS18",1),(9,"Deacon","TADS18",1),(10,"Joshua","TADS18",1);
	INSERT INTO `pi_aluno` (`matricula`,`nome`,`turma`,`curso_id`) VALUES (11,"Ciaran","TADS16",1),(12,"Brody","TADS18",1),(13,"Hiroko","TADS16",1),(14,"Jonah","TADS17",1),(15,"Moana","TADS16",1),(16,"Kaitlin","TADS18",1),(17,"Buffy","TADS17",1),(18,"Solomon","TADS18",1),(19,"Fallon","TADS16",1),(20,"Ori","TADS16",1);
	INSERT INTO `pi_aluno` (`matricula`,`nome`,`turma`,`curso_id`) VALUES (21,"Mollie","TADS17",1),(22,"Kadeem","TADS17",1),(23,"Jacob","TADS18",1),(24,"Oren","TADS16",1),(25,"Adena","TADS17",1),(26,"Carter","TADS17",1),(27,"Preston","TADS17",1),(28,"Oren","TADS18",1),(29,"Emma","TADS18",1),(30,"Christopher","TADS18",1);
	INSERT INTO `pi_aluno` (`matricula`,`nome`,`turma`,`curso_id`) VALUES (31,"Adam","TADS16",1),(32,"Tucker","TADS17",1),(33,"Solomon","TADS16",1),(34,"Chaney","TADS18",1),(35,"Harper","TADS17",1),(36,"Francis","TADS17",1),(37,"Lillith","TADS16",1),(38,"Freya","TADS16",1),(39,"Kareem","TADS17",1),(40,"Adele","TADS16",1);
	INSERT INTO `pi_aluno` (`matricula`,`nome`,`turma`,`curso_id`) VALUES (41,"Alfonso","TADS16",1),(42,"Emi","TADS16",1),(43,"Joy","TADS17",1),(44,"Justina","TADS18",1),(45,"Cassandra","TADS16",1),(46,"Harper","TADS18",1),(47,"Paul","TADS17",1),(48,"Amy","TADS17",1),(49,"Pamela","TADS16",1),(50,"Curran","TADS17",1);
	INSERT INTO `pi_aluno` (`matricula`,`nome`,`turma`,`curso_id`) VALUES (51,"Kiara","TADS17",1),(52,"Amity","TADS18",1),(53,"Carolyn","TADS17",1),(54,"Hu","TADS18",1),(55,"Hedy","TADS16",1),(56,"Aimee","TADS18",1),(57,"August","TADS17",1),(58,"Herrod","TADS17",1),(59,"Sloane","TADS18",1),(60,"Daniel","TADS17",1);
	INSERT INTO `pi_aluno` (`matricula`,`nome`,`turma`,`curso_id`) VALUES (61,"Neville","TADS16",1),(62,"Warren","TADS17",1),(63,"Quynn","TADS17",1),(64,"Wyoming","TADS17",1),(65,"Mara","TADS17",1),(66,"Cade","TADS18",1),(67,"Rahim","TADS18",1),(68,"Cruz","TADS18",1),(69,"Donna","TADS18",1),(70,"Kevin","TADS18",1);
	INSERT INTO `pi_aluno` (`matricula`,`nome`,`turma`,`curso_id`) VALUES (71,"Hanna","TADS16",1),(72,"Victoria","TADS17",1),(73,"Freya","TADS16",1),(74,"Pearl","TADS18",1),(75,"Marcia","TADS17",1),(76,"Emily","TADS16",1),(77,"Xyla","TADS17",1),(78,"Vivian","TADS16",1),(79,"Sean","TADS16",1),(80,"Sharon","TADS17",1);
	INSERT INTO `pi_aluno` (`matricula`,`nome`,`turma`,`curso_id`) VALUES (81,"Cally","TADS17",1),(82,"Mira","TADS18",1),(83,"Catherine","TADS18",1),(84,"Claudia","TADS16",1),(85,"Kaseem","TADS16",1),(86,"Tana","TADS18",1),(87,"Amy","TADS18",1),(88,"Marvin","TADS17",1),(89,"India","TADS17",1),(90,"Eleanor","TADS16",1);
	INSERT INTO `pi_aluno` (`matricula`,`nome`,`turma`,`curso_id`) VALUES (91,"Nell","TADS16",1),(92,"Merrill","TADS18",1),(93,"Lysandra","TADS16",1),(94,"Daniel","TADS18",1),(95,"Xerxes","TADS17",1),(96,"Malik","TADS18",1),(97,"Jermaine","TADS17",1),(98,"Natalie","TADS18",1),(99,"Amanda","TADS18",1),(100,"Garrett","TADS17",1);

--STORED PROCEDURES >>

	-- BUSCA ALUNO COM OS DADOS NECESSÁRIOS PARA EMITIR A CARTEIRINHA
	delimiter $$
	drop procedure if exists getAluno $$
	create procedure getAluno(matricul BIGINT)
	begin
		begin
		SELECT pi_aluno.nome, matricula, pi_curso.nome as curso, validade 
		FROM pi_carteirinha, pi_aluno, pi_curso 
		WHERE matricula = pi_carteirinha.aluno_matricula 
		and curso_id = id 
		and matricula = matricul;
	end $$
	delimiter ;

	-- LISTAR ALUNOS POR TURMA COM OS DADOS NECESSÁRIOS PARA EMITIR A CARTEIRINHA
	delimiter $$
	drop procedure if exists getAlunosPorTurma $$
	create procedure getAlunosPorTurma(turm varchar(255))
	begin
		select pi_aluno.nome, pi_aluno.matricula, pi_curso.nome as curso, pi_aluno.turma, validade 
		from pi_aluno, pi_curso 
		where pi_aluno.turma like concat("%", turm, "%") 
		and pi_aluno.curso_id = pi_curso.id 
		and matricula = aluno_matricula;
	end $$
	delimiter ;

	-- LISTAR ALUNOS POR CURSO
	delimiter $$
	drop procedure if exists listarAlunosPorCurso $$
	create procedure listarAlunosPorCurso(curs varchar(255))
	begin
		select pi_aluno.nome, pi_aluno.matricula, pi_curso.nome as curso, pi_aluno.turma 
		from pi_aluno, pi_curso 
		where pi_curso.nome like concat("%", curs, "%") 
		and pi_aluno.curso_id = pi_curso.id;
	end $$
	delimiter ;

	--LISTAR ALUNOS POR CURSO QUE NÃO POSSUEM CARTEIRINHA
	delimiter $$
	drop procedure if exists listarAlunosPorCursoSemCarteirinha $$
	create procedure listarAlunosPorCursoSemCarteirinha(curs varchar(255))
	begin
		select pi_aluno.nome, pi_aluno.matricula, pi_curso.nome as curso, pi_aluno.turma 
		from pi_aluno, pi_curso, pi_carteirinha 
		where pi_curso.nome like concat("%", curs, "%") 
		and pi_aluno.curso_id = pi_curso.id 
		and matricula not in (select aluno_matricula 
							from pi_carteirinha) 
		group by matricula;
	end $$
	delimiter ;


	--LISTAR ALUNOS POR TURMA
	delimiter $$
	drop procedure if exists listarAlunosPorTurma $$
	create procedure listarAlunosPorTurma(turma varchar(255))
	begin
		select pi_aluno.nome, pi_aluno.matricula, pi_curso.nome as curso, pi_aluno.turma, validade 
		from pi_aluno, pi_curso 
		where pi_aluno.turma like concat("%", turm, "%") 
		and pi_aluno.curso_id = pi_curso.id 
		and matricula = aluno_matricula;
	end $$
	delimiter ;

	--LISTAR ALUNOS POR TURMA QUE NÃO POSSUEM CARTEIRINHA
	delimiter $$
	drop procedure if exists listarAlunosPorTurmaSemCarteirinha $$
	create procedure listarAlunosPorTurmaSemCarteirinha(turma varchar(255))
	begin
		select pi_aluno.nome, pi_aluno.matricula, pi_curso.nome as curso, pi_aluno.turma 
		from pi_aluno, pi_curso, pi_carteirinha 
		where pi_aluno.turma like concat("%", turm, "%") 
		and pi_aluno.curso_id = pi_curso.id 
		and matricula not in (select aluno_matricula 
								from pi_carteirinha) 
		group by matricula;
	end $$
	delimiter ;

	--LISTAR CARTEIRINHAS ATIVAS
	delimiter $$
	drop procedure if exists listarCarteirinhasAtivas $$
	create procedure listarCarteirinhasAtivas()
	begin
		select matricula, pi_aluno.nome, pi_curso.nome as curso, turma, validade, data_de_emissao, via 
		from pi_aluno, pi_carteirinha, pi_curso 
		where pi_aluno.matricula = pi_carteirinha.aluno_matricula 
		and pi_curso.id = pi_aluno.curso_id 
		and CURDATE() < pi_carteirinha.validade;
	end $$
	delimiter ;

	--LISTAR CARTEIRINHAS FORA DA VALIDADE
	delimiter $$
	drop procedure if exists listarCarteirinhasVencidas $$
	create procedure listarCarteirinhasVencidas()
	begin
		select matricula, pi_aluno.nome, pi_curso.nome as curso, turma, validade, data_de_emissao, via 
		from pi_aluno, pi_carteirinha, pi_curso 
		where pi_aluno.matricula = pi_carteirinha.aluno_matricula 
		and pi_curso.id = pi_aluno.curso_id 
		and CURDATE() > pi_carteirinha.validade;
	end $$
	delimiter ;

-- TRIGGERS >>

	--Trigger para padronizar o nome e a turma dos alunos cadastrados em caixa alta antes da inserção na tabela

	delimiter $$
	drop trigger if exists padrão_nome_turma$$
	create trigger padrão_nome_turma before insert on pi_aluno for each
	row
	begin
		set new.nome = UPPER(new.nome);
	    set new.turma = UPPER(new.turma);
	end$$
	delimiter ;

	--Trigger para atualizar a via da carteirinha emitida antes da atualização de algum campo na tabela

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

-- EVENTO >>
	
	-- Evento de backup das carteirinhas
	delimiter $$
	drop event if exists backup $$
	create event backup
	on schedule every 1 week starts now( ) ends now() + interval 1 year
	on completion preserve
	enable
	do
	begin
	 	INSERT INTO 
			pi_backup
		SELECT * FROM
			pi_carteirinha AS p
		LEFT JOIN
			pi_backup AS s
		USING(matricula)
		WHERE s.matricula IS NULL;
	end $$
	delimiter ;

-- FUNÇÕES >>

	-- Retorna quantidade de alunos registrados
	delimiter $$
	drop function if exists quantidade_alunos $$
	create function quantidade_alunos()
	returns int
	begin
		SET @quantidade = (SELECT COUNT(matricula) FROM pi_aluno);
		return @quantidade;
	end $$
	delimiter ;

	-- Retorna quantidade de carteirinhas emitidas
	delimiter $$
	drop function if exists quantidade_carteirinhas $$
	create function quantidade_carteirinhas()
	returns int
	begin
		SET @quantidade = (SELECT COUNT(aluno_matricula) FROM pi_carteirinha);
		return @quantidade;
	end $$
	delimiter ;

-- VISÕES >>

	--Relação de dados entre aluno e carteirinha emitida
	CREATE VIEW pi_relacao_aluno_carteirinha 
	AS select matricula, pi_aluno.nome, pi_curso.nome as curso, turma, validade, data_de_emissao, via 
	from pi_aluno, pi_carteirinha, pi_curso 
	where pi_aluno.matricula = pi_carteirinha.aluno_matricula 
	and pi_curso.id = pi_aluno.curso_id;

	--Lista os dados de todos os alunos
	CREATE VIEW pi_relacao_aluno_curso 
	AS SELECT matricula, pi_aluno.nome, pi_curso.nome as curso, turma
	FROM pi_aluno, pi_curso
	WHERE pi_aluno.curso_id = pi_curso.id;

	--Lista os dados de todos os alunos que não possuem carteirinha
	CREATE VIEW pi_relacao_aluno_curso 
	AS SELECT matricula, pi_aluno.nome, pi_curso.nome as curso, turma
	FROM pi_aluno, pi_curso
	WHERE pi_aluno.curso_id = pi_curso.id
	AND matricula not in (select aluno_matricula from pi_carteirinha);

