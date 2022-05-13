# Game-of-Throne (Game of thrones battle information)
Develop a pseudo backend application which would let users list game of thrones battles conveniently.


**Case Study**
Develop a pseudo backend application which would let users list game of thrones battles
conveniently.

— Use of Data dump (available in CSV/MySQL) for GOT detail : **GOT/src/main/resources/battles.csv**

● Data Parameters: { name, year, battle_number, attacker_king, defender_king,
attacker_outcome, battle_type, major_death, major_capture, attacker_size,
defender_size, attacker_commander, defender_commander, summer, location,
region, note }

● Some parameters may have null value in it. handle it gracefully!

Use MySQL database to store the dataset (Normalize the database by creating
different tables). Write a code to read attached CSV and store data to different tables
in MySQL.


Expose interactive API designed with basic function with response containing places data
(JSON response).

● List places (JSON response containing region and locations) # /list

● API call responding total number of data (records) # /count

● To get all details of a specific battle by passing the battle number: # /battleNumber/{battleId}
