CREATE TABLE calorias (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    hora TIMESTAMP NOT NULL,
    calorias INTEGER NOT NULL,
    usuario_cpf VARCHAR(14) NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_cpf) REFERENCES usuario(cpf)
);