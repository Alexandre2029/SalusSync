CREATE TABLE batimento (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    hora TIMESTAMP NOT NULL,
    batimentos_minutos INTEGER NOT NULL,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);