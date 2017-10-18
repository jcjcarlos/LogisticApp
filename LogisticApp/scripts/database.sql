CREATE SEQUENCE surrogate_localidade START 1;

CREATE SEQUENCE surrogate_rota START 1;

-- TABELA DE LOCALIDADES
CREATE TABLE LOCALIDADE (
	id INT NOT NULL,
	descricao VARCHAR(30) NOT NULL UNIQUE,
	CONSTRAINT PK_LOCALIDADE PRIMARY KEY(id)
);

-- TABELA DE ROTAS
CREATE TABLE ROTA (
	id INT NOT NULL,
	nome VARCHAR(30) NOT NULL UNIQUE,
	idLocalidadeOrigem INT NOT NULL,
	idLocalidadeDestino INT NOT NULL,
	capacidadeTotal FLOAT NULL,
	capacidadeAlocada FLOAT NULL,
	custoGrama FLOAT NULL,
	tempoEntrega INT NULL,
	tipo CHAR NULL,
	CONSTRAINT PK_ROTA PRIMARY KEY(id),
	CONSTRAINT FK_ORIGEM FOREIGN KEY(idLocalidadeOrigem) REFERENCES LOCALIDADE(id),
	CONSTRAINT FK_DESTINO FOREIGN KEY(idLocalidadeDestino) REFERENCES LOCALIDADE(id)
);

-- TABELA DE TRECHOS
-- CASO A ROTA FOR DO TIPO 'F' (FRACIONADA), CRIAR O VÍNCULO DA MESMA COM SEUS TRECHOS
-- ORGANIZÁ-LOS DE ACORDO COM A ORDEM
CREATE TABLE TRECHO (
	idRotaFracionada INT NOT NULL,
	idRotaTrecho INT NOT NULL,
	ordem INT,
	CONSTRAINT FK_FRACIONADA FOREIGN KEY(idRotaFracionada) REFERENCES ROTA(id),
	CONSTRAINT FK_TRECHO FOREIGN KEY(idRotaTrecho) REFERENCES ROTA(id)
);

INSERT INTO LOCALIDADE(id, descricao) VALUES
(NEXTVAL('surrogate_localidade'), 'Patópolis'),
(NEXTVAL('surrogate_localidade'), 'Gansópolis'),
(NEXTVAL('surrogate_localidade'), 'Spoornevil'),
(NEXTVAL('surrogate_localidade'), 'Ratolandia');

INSERT INTO ROTA(id, nome, idLocalidadeOrigem, idLocalidadeDestino, capacidadeTotal, capacidadeAlocada, custoGrama, tempoEntrega, tipo) VALUES
(NEXTVAL('surrogate_rota'), 'EW001', 1, 2, 100, 0, 0.18, 3, 'D'),
(NEXTVAL('surrogate_rota'), 'EW101', 2, 3, 200, 0, 0.33, 4, 'D'),
(NEXTVAL('surrogate_rota'), 'WS205', 3, 4, 300, 0, 0.5, 2, 'D'),
(NEXTVAL('surrogate_rota'), 'ES203', 1, 4, 100, 0, 1.01, 11, 'F');

INSERT INTO TRECHO(idRotaFracionada, idRotaTrecho, ordem) VALUES
(4, 1, 0),
(4, 2, 1),
(4, 3, 2);
