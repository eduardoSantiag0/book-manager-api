CREATE TABLE livros (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nomeLivro varchar(100) NOT NULL UNIQUE,
    autor varchar(100) NOT NULL,
    anoPublicacao INTEGER NOT NULL,
    disponivel BOOLEAN,
    genero varchar(50)
);