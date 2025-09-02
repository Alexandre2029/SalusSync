CREATE TABLE agenda (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descricao VARCHAR(255),
    data TIMESTAMP NOT NULL,
    usuario_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    CONSTRAINT fk_agenda_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_agenda_medico FOREIGN KEY (medico_id) REFERENCES medico(id)
);