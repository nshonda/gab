CLIENT=PaintClient
SERVER=PaintServer
IMPL=PaintImpl
INTERFACE=PaintInterface

default:
	javac *.java
	rmic $(IMPL)
	rmiregistry &

server:
	java -Djava.security.policy=java.policy $(SERVER)

client: 
	java -Djava.security.policy=java.policy $(CLIENT)

clean:
	$(RM) *.class
teste:
	javac classes/*.java
	javac PaintClient.java
	java PaintClient
