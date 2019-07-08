package com.firecontrol.common;

/**
 * Created by mariry on 2019/7/2.
 */

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

@Service
public class FireSocketService {
        private int port= 1936;
        private String host= "172.16.20.83";

        public FireSocketService() {}

        public FireSocketService(String host, int port){
            this.host= host;
            this.port= port;
        }

        public void send(FireSocket packets) throws UnknownHostException, IOException{
            Socket socket= new Socket(host, port);

            ObjectOutputStream os= new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(packets);
            socket.getOutputStream().flush();
            socket.close();
        }
}
