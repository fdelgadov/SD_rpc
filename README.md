# Compilacion
## Creación de .jar
jar -cf RPC.jar RPC\*

## Compilación se programa incluyendo dependencia .jar
javac -cp RPC.jar *.java

# Ejecución
java -cp .;RPC.jar Server
java -cp .;RPC.jar Client