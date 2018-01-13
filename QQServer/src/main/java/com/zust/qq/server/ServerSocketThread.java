package com.zust.qq.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSocketThread extends Thread {

    private Socket socket;

    public ServerSocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
		boolean flag=false;
        try {
          
            is = socket.getInputStream();
         
            isr = new InputStreamReader(is);
         
            br = new BufferedReader(isr); //
            String message = null;
            while ((message = br.readLine()) != null) {
                System.out.println("clientMessage" + message);
				if(message.equals("admin&123")) flag=true;
            }
          
            socket.shutdownInput();
 
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            if(flag)
            	pw.write("1");
            else
            	pw.write("0");
            pw.flush();
          
            socket.shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (pw != null) {
                pw.close();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}