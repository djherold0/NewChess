/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multiplayerchess;

import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import multiplayerchess.Piece.COLOR;
public class Driver {
    
        public static void main(String[] args) throws InterruptedException {
            try {
//                Server server = new Server(8080);
//                new Thread(server).start();
//                Thread.sleep(2000);
                
//                Player p1 = new Player();
//                //p1.team=COLOR.WHITE;
//                new Thread(p1).start();
                
                Player p2 = new Player();
                //p2.team=COLOR.BLACK;
                new Thread(p2).start();
            } catch (Exception ex) {
                Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    
}
