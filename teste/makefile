CLIENT=OlaMundoClient
SERVER=OlaMundoServer
IMPL=OlaMundoImpl
INTERFACE=OlaMundoInterface

default:
	javac *.java
	rmic $(IMPL)

server:
	java -Djava.security.policy=java.policy $(SERVER)

client: 
	java -Djava.security.policy=java.policy $(CLIENT)

clean:
	$(RM) *.class
