package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class cQuit {
	private String name;
	public cQuit(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
	}

	Socket socket = null;
	OutputStream os = null;
	PrintWriter pw = null;
	InputStream is = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	String message = null;
	boolean key=false;
	public boolean Quit(){
		try {
			socket = new Socket("127.0.0.1", 8888);
			// 获取输出流向服务端写入数据
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("Q&"+name);
			pw.flush();
			socket.shutdownOutput();
			// 获取输入流接受服务端返回的信息
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			while ((message = br.readLine()) != null) {
				//System.out.println("sign" + message);
				if(message.equals("1"))
					key=true;
				else {
					key=false;
					JOptionPane.showMessageDialog(null, "Error&该账号已退出！");
				}
			}
			socket.shutdownInput();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {

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
			if (pw != null) {
				pw.close();
			}
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return true;
	}
}