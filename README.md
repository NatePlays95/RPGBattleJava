# RPGBattleJava
This is an assigment for my third semester in CS.
It features saving and loading progress, leveling up and allocating stats at will.

Background images are scraped from the internet, and battler sprites are ripped from *RPG ツクール GB* [RPG Maker for the Gameboy]

### Download
(MAY BE BROKEN)<br>
To download the executable, on the right side of this repository main screen is a Releases section. Click the *"Latest Release"* one and download the .zip file.<br>
To run from the repo, open the ``src`` folder in any terminal, and run ``java Main``.

<br><br>
![ss1](github-img/ss1.png)
![ss2](github-img/ss2.png)
![ss3](github-img/ss3.png)
<br><br>

### Technical
All the code was made in Java, using Javax Swing for user interfaces, and JSmooth for building the executable.
This project was originally developed in Repl.it, so there is no git history for version 1.0 and earlier.
<br><br>
As per required of the assignment, the battle manager implements a Model-View-Controller structure.
The rest of the windows try to respect it as well, but they break some rules in the name of simplicity.
