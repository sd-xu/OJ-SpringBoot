cd OJ-judgeServer/target
nohup java -jar OJ-0.0.1-SNAPSHOT.jar >msg.log 2>&1 & 
cd ../.. 
cd OJ-backend/target
java -jar OJ-0.0.1-SNAPSHOT.jar
