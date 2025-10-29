CREATE TABLE contato (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    contato_emergencia VARCHAR(255),
    usuario_cpf VARCHAR(14) NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_cpf) REFERENCES usuario(cpf)
);