INSERT INTO avisepdb.user_avis(
	user_type,id, email, first_name, last_name, login, passwd)
	VALUES ('ADMIN', 0000000000, 'admin@avisep.fr', 'Mister', 'Admin', 'adminavisep', 'falconteamadmin'),
	('ETEACHER' , 0000000001, 'teacher1@avisep.fr', 'Miss', 'Teacher', 'teacheravisep1', 'falconteamteacher1'),
	('STUDENT' , 0000000002, 'acosson@avisep.fr', 'Adrien', 'Cosson', 'adriencosson', 'adrienfalcon'),
	('STUDENT' , 0000000003, 'psimao@avisep.fr', 'Pedro', 'Simao', 'spedro', 'falcon'),
	('STUDENT' , 0000000004, 'aquinq@avisep.fr', 'Antoine', 'Quinquenel', 'aquinq', 'antoinefalcon'),
	('VTEACHER' , 0000000005, 'teacher2@avisep.fr', 'Mister', 'Teacher', 'teacher', 'falcon');

INSERT INTO avisepdb.module(
	id, description, name)
	VALUES (5000000000, 'Scrum et Agile', 'Génie Logiciel'),
	(5000000001, 'Théorie pour la sécurité informatique', 'Cybersécurité'),
	(5000000002, 'Projet de développement', 'Technologies Web'),
	(5000000003, 'Anglais', 'Langues et Cultures 1'),
	(5000000004, 'LV2', 'Langues et Cultures 2'),
	(5000000005, 'Enseignement Culturel', 'Langues et Cultures 3'),
	(5000000006, 'Théorie Statistiques', 'Mathématiques');


INSERT INTO avisepdb.user_avis_module(
	user_avis_id, module_id)
	VALUES (0000000002, 5000000000),
	(0000000002, 5000000001),
	(0000000002, 5000000003),
	(0000000002, 5000000004),
	(0000000002, 5000000005),
	(0000000003, 5000000000),
	(0000000003, 5000000002),
	(0000000003, 5000000003),
	(0000000004, 5000000005),
	(0000000004, 5000000006),
	(0000000001, 5000000000),
	(0000000001, 5000000002),
	(0000000005, 5000000003),
	(0000000005, 5000000004),
	(0000000005, 5000000005);
	
INSERT INTO avisepdb.classe(id, name)
	VALUES (4000000000, 'Classe A'),
(4000000001, 'Classe B'),
(4000000002, 'Classe C'),
(4000000003, 'Classe D');

INSERT INTO avisepdb.user_avis_classe(
	user_avis_id, classe_id)
	VALUES (0000000002, 4000000000),
	(0000000002, 4000000001),
	(0000000002, 4000000003),
	(0000000003, 4000000000),
	(0000000003, 4000000001),
	(0000000004, 4000000002),
	(0000000004, 4000000003),
	(0000000001, 4000000000),
	(0000000005, 4000000003);

	
	INSERT INTO avisepdb.cours(id, c_date, description, module_id)
	VALUES (-7000000000, '2017-03-16', 'Cours Agile 1', 5000000000),
	(-7000000001, '2017-03-23', 'Cours Agile 2', 5000000000),
	(-7000000002, '2017-03-30', 'Cours Agile 3', 5000000000),
	(-7000000003, '2017-04-20', 'Cours Agile 4', 5000000000),
	(-7000000004, '2017-05-04', 'Cours Scrum 1', 5000000000),
	(-7000000005, '2017-01-08', 'Théorie 1', 5000000001),
	(-7000000006, '2017-01-24', 'Théorie 2', 5000000001),
	(-7000000007, '2017-03-06', 'Cours Java', 5000000002),
	(-7000000008, '2017-04-23', 'Cours jQuery', 5000000002),
	(-7000000009, '2017-02-12', 'Grammaire', 5000000003),
	(-7000000010, '2017-02-25', 'Film', 5000000003),
	(-7000000011, '2017-01-07', 'Introduction', 5000000004),
	(-7000000012, '2017-03-06', 'Conjugaison', 5000000004),
	(-7000000013, '2017-05-02', 'Film', 5000000004),
	(-7000000014, '2017-01-22', 'Géopolitique 1', 5000000005),
	(-7000000015, '2017-03-14', 'Géopolitique 2', 5000000005),
	(-7000000016, '2017-02-18', 'Suites Numériques', 5000000006),
	(-7000000017, '2017-02-21', 'Théorèmes', 5000000006),
	(-7000000018, '2017-04-05', 'Entrainement au partiel', 5000000006);
	
INSERT INTO avisepdb.salle(id, location, name, cours_id)
	VALUES (-6000000000, 'NDC', 'N16', -7000000005),
	(-6000000001, 'NDC', 'N16', -7000000012),
	(-6000000002, 'NDC', 'N28', -7000000002),
	(-6000000003, 'NDC', 'N48', -7000000006),
	(-6000000004, 'NDC', 'N410', -7000000009),
	(-6000000005, 'NDL', 'L012',-7000000003),
	(-6000000006, 'NDC', 'L012',-7000000017),
	(-6000000007, 'NDC', 'L114', -7000000000),
	(-6000000008, 'NDC', 'L206', -7000000010),
	(-6000000009, 'NDC', 'L220', -7000000015),
	(-6000000010, 'NDC', 'L416', -7000000011);
	
	INSERT INTO avisepdb.student(id,promo)
	VALUES (0000000002, 2018),
	(0000000003, 2018),
	(0000000004, 2019);
	
	UPDATE avisepdb.user_avis
	SET role='ADMIN'
	WHERE id=0000000000;
    
    UPDATE avisepdb.user_avis
	SET role='ETEACHER'
	WHERE id=0000000001;
    
    UPDATE avisepdb.user_avis
	SET role='STUDENT'
	WHERE id=0000000002;
    
     UPDATE avisepdb.user_avis
	SET role='STUDENT'
	WHERE id=0000000003;
    
     UPDATE avisepdb.user_avis
	SET role='STUDENT'
	WHERE id=0000000004;
    
    UPDATE avisepdb.user_avis
	SET role='VTEACHER'
WHERE id=0000000005;