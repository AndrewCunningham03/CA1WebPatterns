INSERT INTO `users` (`username`, `email`, `password`, `userType`)
VALUES ('Toby', 'toby@gmail.com', 'ron', 1),
       ('Sam', 'sam@gmail.com', 'goal', 1),
       ('John', 'john@gmail.com', 'gojo', 1),
       ('Andrew', 'andrew@gmail.com', 'den', 2);


INSERT INTO `artist` (`artistID`, `artistName`, `genre`, `hometown`, `dateOfBirth`)
VALUES (1, 'Jimi Hendrix', 'Rock', 'Seattle Washington', '1942-09-27'),
       (2, 'Kendrick Lamar', 'Hip Hop', 'Compton California', '1987-06-17'),
       (3, 'Playboi Carti', 'Hip Hop', 'Riverdale Georgia', '1995-09-13'),
       (4, 'Erykah Badu', 'RnB/Soul', 'Dallas Texas', '1971-02-26'),
       (5, 'Prince', 'RnB/Soul', 'Minneapolis, Minnesota', '1958-06-7');

INSERT INTO `album` (`albumID`, `albumName`, `artistID`, `numberOfSongs`, `releaseDate`, `albumLength`)
VALUES (1, 'Electric Ladyland', 1, 16, '1968-10-16', '1:28:00'),
       (2, 'Sign O The Times', 5, 16, '1987-03-30', '1:20:00'),
       (3, 'Mamas Gun', 4, 16, '2000-09-21', '1:16:00'),
       (4, 'Whole Lotta Red', 3, 24, '2020-12-25', '1:03:00'),
       (5, 'To Pimp A Butterfly', 2, 16, '2015-03-15', '1:19:00');


INSERT INTO `songs` (`songID`,`songName`, `albumID`, `artistID`, `songLength`)
VALUES (1, 'Come on', 1, 1, '00:04:59'),
       (2, 'Wesleys Theory', 5, 2, '00:04:34'),
       (3, 'Meh', 4, 3, '00:02:23'),
       (4, 'Housequake', 2, 5, '00:03:32'),
       (5, 'A.D.2000', 3, 4, '00:05:23');

INSERT INTO `playlist` (`playlistID`,`playlistName`, `userName`,`numberOfSongs`, `statusPrivate`)
VALUES (1, 'Rap Cavier', 'Toby', 50, FALSE),
       (2, 'best rnb playlist', 'Sam', 25, TRUE),
       (3, 'Happy Place', 'John', 100, TRUE),
       (4, 'Rap World', 'Andrew', 67, FALSE),
       (5, 'Rock Life', 'Toby', 50, FALSE);

INSERT INTO `playlistSong` (`playlistID`, `songID`)
VALUES (1, 3),
       (2, 5),
       (3, 4),
       (4, 2),
       (5, 1);



INSERT INTO `rating` (`username`, `songID`, `userRating`)
VALUES ('Toby', 3, 4),
       ('Sam', 2, 4.6),
       ('John', 1, 5),
       ('Andrew', 4, 3.4),
       ('Toby', 5, 3.9);




