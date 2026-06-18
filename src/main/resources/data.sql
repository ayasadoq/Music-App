delete from cours;
delete from instrument;
delete from prof;

insert into prof (id, nom, specialite) values
    (1, 'anwar l3ami9', 'Guitare'),
    (2, 'aya sado9', 'Piano'),
    (3, 'ihssane lmokallafa', 'Violon'),
    (4, 'dali ali', 'Saxophone'),
    (5, 'malex othman', 'Chant');

insert into instrument (id, nom, famille, disponibilite) values
    (1, 'Guitare acoustique', 'CORDES', true),
    (2, 'Guitare electrique', 'CORDES', false),
    (3, 'Piano Yamaha', 'CLAVIERS', true),
    (4, 'Violon 4/4', 'CORDES', true),
    (5, 'Saxophone alto', 'VENTS', true),
    (6, 'Flute traversiere', 'VENTS', true),
    (7, 'Batterie complete', 'PERCUSSIONS', false),
    (8, 'Trompette', 'VENTS', true),
    (9, 'Contrebasse', 'CORDES', false),
    (10, 'Clavier numerique', 'CLAVIERS', true);

insert into cours (id, jour, heure_debut, duree_min, niveau, prof_id) values
    (1, 'LUNDI', '09:00:00', 60, 'DEBUTANT', 1),
    (2, 'LUNDI', '11:00:00', 45, 'INTERMEDIAIRE', 2),
    (3, 'MARDI', '10:00:00', 60, 'AVANCE', 3),
    (4, 'MERCREDI', '14:00:00', 90, 'DEBUTANT', 4),
    (5, 'JEUDI', '09:00:00', 60, 'INTERMEDIAIRE', 5),
    (6, 'JEUDI', '15:00:00', 45, 'DEBUTANT', 1),
    (7, 'VENDREDI', '10:00:00', 60, 'AVANCE', 2),
    (8, 'SAMEDI', '10:00:00', 90, 'INTERMEDIAIRE', 3);

alter table prof alter column id restart with 6;
alter table instrument alter column id restart with 11;
alter table cours alter column id restart with 9;
