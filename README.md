### SearchRobot
# First setup and launch

1.  Open CMD in .../ProjectSearchRobot/
2.  Write in CMD
```sh  
docker build -t db_as_mariadb .
```
3.  Write in CMD
```sh
docker run --name mariadb -p 4306:3306 -p 5567:5567 -p 5444:5444 -p 5568:5568 -e MYSQL_ROOT_PASSWORD=mypass -d mariadb
```
4.  Open SearchRobot.bat file
5.  Done!
 
# Future launches
1.  Write in CMD
```sh
docker container ps -l
```
2.  Get CONTAINER ID
3.  Write in CMD
```sh
docker start CONTAINER ID
```
4.  Open SearchRobot.bat file
5.  Done!

