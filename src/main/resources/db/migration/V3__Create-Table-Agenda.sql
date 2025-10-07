CREATE TABLE agenda (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descricao VARCHAR(255),
    data TIMESTAMP NOT NULL,
    usuario_id BIGINT NOT NULL,
    medico_cpf VARCHAR(14) NOT NULL,
    situacao VARCHAR(100),
    CONSTRAINT fk_agenda_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_agenda_medico FOREIGN KEY (medico_cpf) REFERENCES medico(cpf)
);