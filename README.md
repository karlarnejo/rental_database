# DVD Rental System
This project was created during my very early years of programming after having been introduced to database and Java Swing in our introduction to database class. So please, pardon if some algorithms were not optimized, or if some resources were hardcoded. Some comment blocks were also written in my native tongue (Bisaya). Will change them when I have the time! :)

## Installation

* Import the folder as an existing Maven project.
* Import database backup in PostgreSQL.
* Edit the DatabaseConnection to fit yours.
* Head over to the ```/lib``` folder and you'll see a ```.jar``` file called ```rs2xml.jar```. Run ```install:install-file -Dfile="<base_directory>\rental_database\lib\rs2xml.jar" -DgroupId="net.proteanit.sql" -DartifactId="rs2xml" -Dversion=1.0 -Dpackaging="jar"``` in Maven build to import it locally since this is no available in the [Maven Repository](https://mvnrepository.com/).
* Ensure that your maven project is updated.
* Run ```clean compile assembly:single``` in maven build to export the project as ```.jar```.
* Head over to ```/target``` and copy the exported ```.jar```. You can place this anywhere but it is advisable not to just run it in the ```/target``` folder because of the next step.
* Head over to the root of the project folder and copy the ```/Pictures``` folder to the same place you pasted your exported ```.jar```. The ```/Pictures``` folder and the exported ```.jar``` should be on the same level. It is advisable not to directly copy the ```/Pictures``` folder to the ```/target``` folder with the generated ```.jar```  because the whole ```/target``` folder will be deleted and renewed every time your rebuild your project.

## Usage

* Check login credentials in ```customer``` table and ```staff``` table for its corresponding privileges.
* If you have to add a movie or game, please create a folder under ```\Pictures\Games``` or ```\Pictures\Movies``` with its corresponding Movie or Game name. The name has to be exactly the same as what you entered in the interface. Example: ```\Pictures\Super Mario```
  * Add (4) four pictures in its respective folders with file names ```<game_or_movie_name>1``` for the main picture, and ```<game_or_movie_name>2```...up to ```<game_or_movie_name>4``` for the side pictures. Example: ```Super Mario1```, ```Super Mario2```, ```Super Mario3```, ```Super Mario4```.
  * Resize ```<picture1>``` to ```300x405``` and the rest of the pictures to ```169x199```.
  * Must me in ```jpg``` format

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Notes
Will update things when I have the time like adding a dependency manager since this creatted was way before I had the knowledge of the best practices in the industry.

## License
[MIT](https://choosealicense.com/licenses/mit/)