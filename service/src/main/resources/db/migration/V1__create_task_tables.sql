CREATE TABLE task (
	id uuid NOT NULL,
	title varchar(100) NOT NULL,
	description varchar(250) NOT NULL,
	finish_at timestamp with time zone NOT NULL,
	is_finished boolean NOT NULL DEFAULT FALSE,
	created_at timestamp with time zone NOT NULL,
	updated_at timestamp with time zone NOT NULL,
	primary key(id)
);