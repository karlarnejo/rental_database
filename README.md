# DVD Rental System
This project was created during my very early years of programming after having been introduced to database and Java Swing in our introduction to database class. So please, pardon if some algorithms were not optimized, some resources were hardcoded, and some comment blocks as well that were written in my native tongue (Bisaya). Will change them when I have the time! :)

## Installation

* Import the folder as an existing project.
* Import database backup in PostgreSQL.
* Edit the DatabaseConnection to fit yours.
* Run the LoginGUI.java

## Usage

* Check login credentials in ```customer``` table and ```staff``` table for its corresponding privileges.
* If you have to add a movie or game, please create a folder under ```\Pictures\Rental Videos and Games\Games``` or ```\Pictures\Rental Videos and Games\Movies``` with its corresponding Movie or Game name. The name has to be exactly the same as what you entered in the interface. Example: ```\Pictures\Rental Videos and Games\Super Mario```
  * Add (4) four pictures in its respective folders with file names ```<game_or_movie_name>1``` for the main picture, and ```<game_or_movie_name>2```...up to ```<game_or_movie_name>4``` for the side pictures. Example: ```Super Mario1```, ```Super Mario2```, ```Super Mario3```, ```Super Mario4```.
  * Resize ```<picture1>``` to ```300x405``` and the rest of the pictures to ```169x199```.
  * Must me in ```jpg``` format
* The UI pictures are located in ```\Pictures\Rental Videos and Games\Design\Pandora's Box```.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Notes
Will update things when I have the time like adding a dependency manager since this creatted was way before I had the knowledge of the best practices in the industry.

## License
[MIT](https://choosealicense.com/licenses/mit/)