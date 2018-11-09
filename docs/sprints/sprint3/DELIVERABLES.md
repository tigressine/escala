vision statement  
----------------  
Escala is a simulation game that gives players the opportunity to spread a fictional commercial product around the world. This software features educational aspects but is primarily targeted at gamers. Many of the world’s cultural, social, logistical, and economic complexities are baked into the simulation engine, which means this game offers insight into the systems of product diffusion and economies-of-scale that many people take for granted.  

Escala is inspired by [Plague Inc.](https://www.ndemiccreations.com/en/22-plague-inc), a mobile application whose primary objective is to enable players to destroy humanity with a biblical pandemic... Unlike Plague Inc., Escala’s objective is much more benign and *civilization-friendly*.

backlogs  
--------  
[Product Backlog](https://github.com/tgsachse/escala/issues?utf8=%E2%9C%93&q=is%3Aissue+label%3A%22product+backlog%22+)  
[Sprint Backlog](https://github.com/tgsachse/escala/issues?utf8=%E2%9C%93&q=is%3Aissue+label%3A%22sprint+2+backlog%22+)  

requirements
------------
- An on-screen slider can change the resolution.
- Each product has unique charateristics.
- Game has a pause button to pause game.
- Game has a play button to play game.
- Game starts in the middle of the screen.
- Game has a fastforward button to increase speed of the game.
- Each region's status is indicated with graphics.
- Regions can be highlighted and clicked on.
- Regions are highlighted when hovered.
- Statistics are stored and shown on the screen.
- Game has graphs on the bottom of screen to diplay statistics.
- All stats values are an integer from 1 to 100.
- Sprites move along a defined path.
- Events are stored in the database.
- Events can be selected at random.
- Game has a settings page with multiple settings.
- This settings page is accessible from the menu.
- Users can enter a product name.
- This product name is used throughout the game.
- Each region's characteristics are unique.
- Each region has an effect on gameplay.
- Time passes automatically at a steady rate.
- Statistics are automatically updated as time passes.
- Users can buy goods with in-game cash.

demonstration  
-------------
An early demonstration video is available [here](https://youtu.be/IEVUkmqEtiI).

tests  
-----
Manual (plain text) tests are located [here](https://github.com/tgsachse/escala/blob/master/tests/MANUAL_TESTS.md). To run database unit tests, clone the repository and execute the test command like this:
```
git clone -b master https://www.github.com/tgsachse/escala.git
cd escala
./operations.sh --test
```

code  
----  
This game requires [Java8](http://www.oracle.com/technetwork/java/javase/downloads/index.html). Download the zip [here](https://github.com/tgsachse/escala/releases/download/v0.2/escala.zip) and unzip the contents. To the run game, double click `run.sh` on Linux and macOS or `run.bat` on Windows. The scripts can also be executed from the command line.
