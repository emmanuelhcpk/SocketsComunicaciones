
LIBS=-lzmq

#target: dependencies
#[tab] system command

# $@ -> reemplaza por el nombre de la regla(target)
# $< -> reemplaza por el nombre de la primera dependencia
#%.o:%.c
#	$(CC) -c $< -o $@

all: serverZMQ 

serverZMQ: ./src/serverZeroMQ.cpp
	g++ $< -o $@ $(LIBS)
	
clientZMQ: ./src/clientZeroMQ.cpp
	g++ $< -o $@ $(LIBS)
	
clean: serverZMQ
	rm -rf serverZMQ


