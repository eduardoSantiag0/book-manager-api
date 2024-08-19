CREATE TABLE livros (
    id BIGINT NOT NULL PRIMARY KEY SERIAL,
    nomeLivro varchar(100) NOT NULL UNIQUE,
    autor varchar(100) NOT NULL,
    anoPublicacao INTEGER NOT NULL,
    disponivel BOOLEAN,
    genero varchar(50)
);