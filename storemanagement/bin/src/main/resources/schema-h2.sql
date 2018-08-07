CREATE TABLE IF NOT EXISTS product(
idProduct IDENTITY PRIMARY KEY,
product VARCHAR(15) NOT NULL,
price DOUBLE NOT NULL,
stock INT NOT NULL
);

CREATE TABLE IF NOT EXISTS storeuser(
	idStoreUser IDENTITY PRIMARY KEY,
	storeuser VARCHAR(15),
	password VARCHAR(64)
);
