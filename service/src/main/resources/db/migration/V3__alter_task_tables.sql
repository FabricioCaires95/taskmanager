ALTER TABLE task
ADD user_id uuid;

ALTER TABLE task
ADD FOREIGN KEY(user_id) REFERENCES user(id);