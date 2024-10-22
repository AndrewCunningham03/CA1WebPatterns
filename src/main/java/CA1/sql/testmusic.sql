DROP
DATABASE IF EXISTS testmusic;
CREATE
DATABASE IF NOT EXISTS testmusic;

USE
testmusic;


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
    artistID    int(255)    NOT NULL,
    artistName  varchar(50) NOT NULL,
    genre       varchar(50) NOT NULL,
    hometown    varchar(50) NOT NULL,
    dateOfBirth DATE,
    PRIMARY KEY (artistID)
);


CREATE TABLE album
(
    albumID       int(255) NOT NULL,
    albumName     varchar(255) NOT NULL,
    artistID      int (255) NOT NULL,
    numberOfSongs int(50) NOT NULL,
    releaseDate   DATE,
    albumLength   Time,
    PRIMARY KEY (albumID),
    FOREIGN KEY (artistID) REFERENCES artist (artistID)
);



CREATE TABLE songs
(
    songID     int(255)    NOT NULL,
    songName   varchar(50) NOT NULL,
    albumID    int(255) NOT NULL,
    artistID   int(255) NOT NULL,
    songLength Time,
    PRIMARY KEY (songID),
    FOREIGN KEY (albumID) REFERENCES album (albumID),
    FOREIGN KEY (artistID) REFERENCES artist (artistID)
);


CREATE TABLE playlist
(
    playlistID    int(255)  NOT NULL,
    playlistName  varchar(50) NOT NULL,
    username      varchar(50) NOT NULL,
    statusPrivate  BOOLEAN,
    PRIMARY KEY (playlistID),
    FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE playlistSong
(
    playlistID   int(255) NOT NULL,
    songID       int(255) NOT NULL,
    PRIMARY KEY (playlistID, songID),
    FOREIGN KEY (playlistID) REFERENCES playlist (playlistID),
    FOREIGN KEY (songID) REFERENCES songs (songID)
);

CREATE TABLE rating
(
    username varchar(50) NOT NULL,
    songID  int(255) NOT NULL,
    userRating   double NOT NULL,
    PRIMARY KEY (username, songID),
    FOREIGN KEY (username) REFERENCES users (username),
    FOREIGN KEY (songID) REFERENCES songs (songID)
);


