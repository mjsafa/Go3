# Go3
A sample implementation for Game of three sample project
 
 #how to run:
 download the executable folder in the git repository. there is an executable war file in it. 
 There is also two executable bat files to run two player programs (java 8 must be installed and JAVA_HOME should be set before running bat files).
 After running player programs, player 1 will start on port 8085 and player 2 on port 8075.
  
  You can go to localhost: 8085 and start play. After going to main page of the game, you will have a
  "Start Game" button there. Clicking on it will open a dialog asking you the host and ip 
  address of remote player you want to play with. If you have run supplied bat files, leave the values
  to "localhost" and 8075 (address for second player).
  
  there is a toggle button on the dialog form, determining whether to play automatically or 
  after every number received, user sould click on the "request answer" button to continue the game.
  
  # Architecture and Design
  There is a backend service implemented using Spring boot taht runs on an embedded tomcat usong Spring boo
   
   For the user interface, a polymer single page application is implemented that communicate with 
   backend services through STOMP over WebSocket. There is a true two way communication between ui and services,
   when a new answer is received from remote player, it will send it immediately to the client.
    
   2 player services communicate with each other through Restful web services (implemented by spring MVC).
    when one player starts a game, 
   a separate thread will run in each process, waiting for a new number to answer it.
   
   if one player is not accessible through Rest API, system will inform client and will retry sending answer to the player.
   
   Feign framework is used to declaratively implement rest service client using interface and annotations.
   
   