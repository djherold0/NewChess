package multiplayerchess;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable{ 
    protected int          serverPort   = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    public int             playerCount,gamecount;
    LinkedBlockingQueue<Socket> waitingQueue;
    public Socket[] pList;
    
    public Server(int port){
        this.serverPort = port;
        this.playerCount=this.gamecount=0;
        waitingQueue = new LinkedBlockingQueue<Socket>();
    }

    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }        
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }       
            
            waitingQueue.add(clientSocket);
            playerCount++;
            System.out.println("SERVER: Player added to queue. Count so far: "+playerCount);
            if(playerCount==2)
                createGame();
            
        }
        System.out.println("Server Stopped.") ;
    }

    public void createGame(){
        try {
            Socket p1 = waitingQueue.take();
            Socket p2 = waitingQueue.take();
            new Thread(
                new ChessGame(p1,p2, gamecount)).start();
           gamecount++;
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerCount=0;
    }
    
    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
        System.out.println("SERVER: Socket opened on "+serverPort);
    }

}