CREATE DATABASE checkers;
USE checkers;

CREATE TABLE Variants(
    ID int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(128)
);

CREATE TABLE Matches(
    ID int unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
    board_width int unsigned,
    board_height int unsigned,
    variant_id int unsigned,
    FOREIGN KEY (variant_id) REFERENCES Variants(ID)
);

CREATE TABLE Moves(
    turn_number int unsigned,
    move_number int unsigned,
    starting_x int unsigned,
    starting_y int unsigned,
    target_x int unsigned,
    target_y int unsigned,
    match_id int unsigned,
    PRIMARY KEY (turn_number, move_number),
    FOREIGN KEY (match_id) REFERENCES Matches(ID)
);