CLIENT=PaintClient
SERVER=PaintServer
IMPL=PaintImpl
INTERFACE=PaintInterface
REG=rmiregistry

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
	javac *.java
	java PaintClient

kill:
	pkill rmiregistry
