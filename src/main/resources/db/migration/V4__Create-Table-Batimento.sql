CREATE TABLE batimento (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    hora TIMESTAMP NOT NULL,
    batimentos_minutos INTEGER NOT NULL,
    usuario_cpf VARCHAR(14) NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_cpf) REFERENCES usuario(cpf)
);