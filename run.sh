javac -cp ".:lib/mysql-connector-j-9.0.0/mysql-connector-j-9.0.0.jar" AdminGUI.java DatabaseQueries.java -d bin
java -cp "bin:lib/mysql-connector-j-9.0.0/mysql-connector-j-9.0.0.jar" AdminGUI "jdbc:mysql://localhost:3306/dev" "root" "" "com.mysql.cj.jdbc.Driver"
