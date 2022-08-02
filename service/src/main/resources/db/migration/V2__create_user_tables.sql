CREATE TABLE user (
	id uuid NOT NULL,
	name varchar(255) NOT NULL,
	email varchar(200) NOT NULL,
	password varchar(100) NOT NULL,
	created_at timestamp with time zone NOT NULL,
	updated_at timestamp with time zone NOT NULL,
	primary key(id)
);