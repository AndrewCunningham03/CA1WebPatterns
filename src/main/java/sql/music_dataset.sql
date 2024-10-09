INSERT INTO `users` (`username`, `email`, `password`, `userType`)
VALUES ('Toby', 'toby@gmail.com', 'ron', 1),
       ('Sam', 'sam@gmail.com', 'goal', 1),
       ('John', 'john@gmail.com', 'gojo', 1),
       ('Andrew', 'andrew@gmail.com', 'den', 2);


INSERT INTO `artist` (`artistName`, `genre`, `hometown`, `dateOfBirth`)
VALUES ('Jimi Hendrix', 'Rock', 'Seattle Washington', '1942-09-27'),
       ('Kendrick Lamar', 'Hip Hop', 'Compton, California', '1987-06-17'),
       ('Playboi Carti', 'Hip Hop', 'Riverdale Georgia', '1995-09-13'),
       ('Erykah Badu', 'RnB/Soul', 'Dallas Texas', '1971-02-26'),
       ('Prince', 'RnB/Soul', 'Minneapolis, Minnesota', '1958-06-7');

INSERT INTO `album` (`albumName`, `artistName`, `numberOfSongs`, `releaseDate`, `albumLength`)
VALUES ('Electric Ladyland', 'Jimi Hendrix', 16, '1968-10-16', '1:28:00'),
       ('Sign O The Times', 'Prince', 16, '1987-03-30', '1:20:00'),
       ('Mamas Gun', 'Erykah Badu', 16, '2000-09-21', '1:16:00'),
       ('Whole Lotta Red', 'Playboi Carti', 24, '2020-12-25', '1:03:00'),
       ('To Pimp A Butterfly', 'Kendrick Lamar', 16, '2015-03-15', '1:19:00');


INSERT INTO `songs` (`songName`, `albumName`, `artistName`, `songLength`)
VALUES ('Come on', 'Electric Ladyland', 'Jimi Hendrix', '00:04:59'),
       ('Wesleys Theory', 'To Pimp A Butterfly', 'Kendrick Lamar', '00:04:34'),
       ('Meh', 'Whole Lotta Red', 'Playboi Carti', '00:02:23'),
       ('Housequake', 'Sign o The Times', 'Playboi Carti', '00:03:32'),
       ('A.D.2000', 'Mamas Gun', 'Erykah Badu', '00:05:23');

INSERT INTO `playlist` (`playlistName`, `numberOfSongs`, `private`)
VALUES ('Rap Cavier', 50, FALSE),
       ('best rnb playlist', 25, TRUE),
       ('Happy Place', 100, TRUE),
       ('Rap World', 67, FALSE),
       ('Rock Life', 50, FALSE);

INSERT INTO `playlistSong` (`playlistName`, `songName`)
VALUES ('Rap Cavier', 'Meh'),
       ('best rnb playlist', 'A.D.2000'),
       ('Happy Place', 'Housequake'),
       ('Rap World', 'Wesleys Theory'),
       ('Rock Life', 'Come On');



INSERT INTO `rating` (`username`, `songName`, `rating`)
VALUES ('Toby', 'Meh', 4),
       ('Sam', 'Wesleys Theory', 5),
       ('John', 'Come On', 5),
       ('Andrew', 'Housequake', 5),
       ('Toby', 'A.D.2000', 5);




