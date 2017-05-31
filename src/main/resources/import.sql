INSERT INTO calendar VALUES (DEFAULT,'Uni Calendar', 'Professional') ;
INSERT INTO calendar VALUES (DEFAULT ,'My calendar', 'Personal') ;

INSERT INTO event VALUES (DEFAULT, 'description1', '2015-01-01 2:00:00', '2015-01-01 1:00:00', 'event1', 1);
INSERT INTO event VALUES (DEFAULT, 'description1', '2015-01-02 3:00:00', '2015-01-02 2:00:00', 'event2', 1);
INSERT INTO event VALUES (DEFAULT, 'description1', '2015-01-03 2:00:00', '2015-01-03 1:00:00', 'event3', 1);
INSERT INTO event VALUES (DEFAULT, 'description1', '2015-01-04 2:00:00', '2015-01-04 1:00:00', 'event4', 1);
-- (id, title, start, end, description)

INSERT INTO nj_user VALUES(DEFAULT,CURRENT_TIMESTAMP,'kostas@gmail.com','1234567890',NULL,'kostas');
INSERT INTO nj_user VALUES(DEFAULT,CURRENT_TIMESTAMP,'giannis@gmail.com','1236560890',NULL,'giannis');
INSERT INTO nj_user VALUES(DEFAULT,CURRENT_TIMESTAMP,'yorgos@gmail.com','1134467690',NULL,'yorgos');


INSERT INTO attendant VALUES (DEFAULT, 1);
INSERT INTO attendant VALUES (DEFAULT, 2);
INSERT INTO attendant VALUES (DEFAULT, 3);

INSERT INTO event_attendant VALUES (1, 1);
INSERT INTO event_attendant VALUES (2, 1);
INSERT INTO event_attendant VALUES (2, 2);
INSERT INTO event_attendant VALUES (3, 1);
INSERT INTO event_attendant VALUES (3, 2);
INSERT INTO event_attendant VALUES (3, 3);