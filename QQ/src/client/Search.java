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

public class Search {
	private String keys;

	public Search(String keys) {
		super();
		this.keys = keys;
	}
	
	Socket socket = null;
	OutputStream os = null;
	PrintWriter pw = null;
	InputStream is = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	String message = null;
	boolean key=false;
	public String search(){
		try {
			socket = new Socket("127.0.0.1", 8888);
			// 获取输出流向服务端写入数据
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("S&"+keys);
			pw.flush();
			socket.shutdownOutput();
			// 获取输入流接受服务端返回的信息
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			while ((message = br.readLine()) != null) {
				//System.out.println("sign" + message);
				String msg=message.substring(0,1);
				if(msg.equals("1"))//字符串包含“1”
					
					key=true;
				else {
					
					key=false;
					JOptionPane.showMessageDialog(null, "Error&没有相关用户！");
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
		return keys;
	}
	
}
