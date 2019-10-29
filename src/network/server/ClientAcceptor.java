package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientAcceptor implements Runnable {
    private final ExecutorService pool;
    private final ServerSocket serverSocket;
    private final ArrayList<Socket> clients;

    public ClientAcceptor(ExecutorService pool, ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.pool = pool;
        clients = new ArrayList<Socket>();
    }

    public void run() {
        try {
            // accept 4 clients
            while () {
                /*
                 Zunächst wird eine Client-Anforderung entgegengenommen(accept-Methode).
                 Der ExecutorService pool liefert einen Thread, dessen run-Methode
                 durch die run-Methode der Handler-Instanz realisiert wird.
                 Dem Handler werden als Parameter übergeben:
                 der ServerSocket und der Socket des anfordernden Clients.
                */
                Socket cs = serverSocket.accept();  //warten auf Client-Anforderung

                //starte den Handler-Thread zur Realisierung der Client-Anforderung
                pool.execute(new Handler(serverSocket, cs));
            }
        } catch (IOException ex) {
            System.out.println("--- Interrupt NetworkService-run");
        } finally {
            System.out.println("--- Ende NetworkService(pool.shutdown)");
            pool.shutdown();  //keine Annahme von neuen Anforderungen
            try {
                //warte maximal 4 Sekunden auf Beendigung aller Anforderungen
                pool.awaitTermination(4L, TimeUnit.SECONDS);
                if (!serverSocket.isClosed()) {
                    System.out.println("--- Ende NetworkService:ServerSocket close");
                    serverSocket.close();
                }
            } catch (IOException e) {
            } catch (InterruptedException ei) {
            }
        }
    }
}
