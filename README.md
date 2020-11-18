# playZone
Data base data structure is given below

Db Name: playzone

------Table  pz_play_lists------------
CREATE TABLE `pz_play_lists` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
ROW_FORMAT=DEFAULT
AUTO_INCREMENT=1


------Table  pz_songs ------------

CREATE TABLE `pz_songs` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NULL DEFAULT '0',
	`singer` VARCHAR(255) NULL DEFAULT '0',
	`play_id` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_pz_songs_pz_play_lists` (`play_id`),
	CONSTRAINT `FK_pz_songs_pz_play_lists` FOREIGN KEY (`play_id`) REFERENCES `pz_play_lists` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
ROW_FORMAT=DEFAULT
AUTO_INCREMENT=26
