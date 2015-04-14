 
// autor: Alexis Rodríguez Gutiérrez 
// Prueba Listen Server con C++ y ZeroMQ

#include <zmq.hpp>
#include <string>
#include <iostream>
#ifndef _WIN32
#include <unistd.h>
#else
#include <windows.h>

#define sleep(n)    Sleep(n)
#endif


int main () {
  
  //  Prepare our context and socket
  zmq::context_t context (1);
  zmq::socket_t socket (context, ZMQ_REP);
  socket.bind ("tcp://*:5555");
  
  std::cout << "Listen in port 5555" << std::endl;
  
  while (true) {
        zmq::message_t request;

        //  Wait for next request from client
        socket.recv (&request);
	std::string rpl = std::string(static_cast<char*>(request.data()), request.size());
        std::cout << rpl  << std::endl;

        //  Do some 'work'
//        sleep(1);

//         Send reply back to client
        zmq::message_t reply (5);
        memcpy ((void *) reply.data (), "ok", 2);
        socket.send (reply);
    }
    return 0;
}
