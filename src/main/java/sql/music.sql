DROP
DATABASE IF EXISTS music;
CREATE
DATABASE IF NOT EXISTS music;

USE
music;


CREATE TABLE users
(
    username varchar(50)         NOT NULL,
    email    varchar(255) UNIQUE NOT NULL,
    password varchar(255)        NOT NULL,
    userType int(1) NOT NULL DEFAULT 1 COMMENT '1 for general user, 2 for admin ',
    PRIMARY KEY (username)
);


CREATE TABLE artist
(
    artistName  varchar(50) NOT NULL,
    genre       varchar(50) NOT NULL,
    hometown    varchar(50) NOT NULL,
    dateOfBirth DATE,
    PRIMARY KEY (artistName)
);


CREATE TABLE album
(
    albumName     varchar(50) NOT NULL,
    artistName    varchar(50) NOT NULL,
    numberOfSongs int(50) NOT NULL,
    releaseDate   DATE,
    albumLength   Time,
    PRIMARY KEY (albumName),
    FOREIGN KEY (artistName) REFERENCES artist (artistName)
);



CREATE TABLE songs
(
    songName   varchar(50) NOT NULL,
    albumName  varchar(50) NOT NULL,
    artistName varchar(50) NOT NULL,
    songLength Time,
    PRIMARY KEY (songName),
    FOREIGN KEY (albumName) REFERENCES album (albumName),
    FOREIGN KEY (artistName) REFERENCES artist (artistName)
);


CREATE TABLE playlist
(
    playlistName  varchar(50) NOT NULL,
    numberOfSongs int(50) NOT NULL,
    private       BOOLEAN,
    PRIMARY KEY (playlistName)
);

CREATE TABLE playlistSong
(
    playlistName varchar(50) NOT NULL,
    songName     varchar(50) NOT NULL,
    PRIMARY KEY (playlistName, songName),
    FOREIGN KEY (playlistName) REFERENCES playlist (playlistName),
    FOREIGN KEY (songName) REFERENCES songs (songName)
);

CREATE TABLE rating
(
    username varchar(50) NOT NULL,
    songName varchar(50) NOT NULL,
    rating   int         NOT NULL,
    PRIMARY KEY (username, songName),
    FOREIGN KEY (username) REFERENCES users (username),
    FOREIGN KEY (songName) REFERENCES songs (songName)
);


