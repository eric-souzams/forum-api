INSERT INTO Person(email, name, password) VALUES('Eric Magalhaes', 'eric@email.com', '$2a$10$CvajJjg4GK92/Qc3XNehW.Ho.KdF1dCgHWWLLTzocL8Q.J7trA6L6');

INSERT INTO Matter(name) VALUES('Spring Boot');
INSERT INTO Matter(name) VALUES('Spring Security');
INSERT INTO Matter(name) VALUES('Spring Data JPA');

INSERT INTO Topic(title, description, status, created_at, last_update_at, ended_at, matter_id, author_id) VALUES('What if this letter here?', 'I do not what means the method ResponseEntity on Spring Framework.', 'SOLVED', '2021-06-01T11:41:29.2660705-03:00', '2021-07-07T19:37:30.458126-03:00', '2021-07-02T21:41:29.2660705-03:00', 1, 1);
INSERT INTO Topic(title, description, status, created_at, ended_at, matter_id, author_id) VALUES('What if this letter here?', 'I do not what means the method ResponseEntity on Spring Framework.', 'SOLVED', '2021-02-02T21:41:29.2660705-03:00', '2021-07-02T21:41:29.2660705-03:00', 2, 1);
INSERT INTO Topic(title, description, status, created_at, ended_at, matter_id, author_id) VALUES('What if this letter here?', 'I do not what means the method ResponseEntity on Spring Framework.', 'SOLVED', '2021-04-03T15:41:29.2660705-03:00', '2021-07-02T21:41:29.2660705-03:00', 3, 1);

INSERT INTO Answer(created_at, message, author_id, topic_id) VALUES ('2021-04-03T15:41:29.2660705-03:00', 'Acho que não existe uma resposta logica possivel para isso.', 1, 1);
INSERT INTO Answer(created_at, message, author_id, topic_id) VALUES ('2021-04-03T15:41:29.2660705-03:00', 'Acho que não existe uma resposta logica possivel para isso.', 1, 1);