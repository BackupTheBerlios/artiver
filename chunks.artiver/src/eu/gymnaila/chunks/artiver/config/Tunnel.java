/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.config;

/**
 *
 * @author User
 */
import com.jcraft.jsch.*;
   
  public class Tunnel {
      
      private String host;
      private String user;
      private String password;
      
      // Default config for SSH-Mysql tunnel
      private int port = 22;
      private int tunnelLocalPort=3306;
      private String tunnelRemoteHost="127.0.0.1";
      private int tunnelRemotePort=3306;
          
      //JSch
      private JSch jsch=new JSch();
      private Session session;
          
      public Tunnel(String host, String user, String password)
      {
          this.host = host;
          this.user = user;
          this.password = password;
      }
      
      public Tunnel(String host, String user, String password, int port)
      {
          this.host = host;
          this.user = user;
          this.password = password;
          this.port = port;
      }
      
      public Tunnel(String host, String user, String password, int port, int tunnelLocalPort, String tunnelRemoteHost, int tunnelRemotePort)
      {
          this.host = host;
          this.user = user;
          this.password = password;
          this.port = port;
          this.tunnelLocalPort = tunnelLocalPort;
          this.tunnelRemoteHost = tunnelRemoteHost;
          this.tunnelRemotePort = tunnelRemotePort;
      }
      
      public void go() throws JSchException{
//          String host="agjava.dyndns.org";
//          String user="dennis";
//          String password="0wn3d_y0u";
//          int port=22;
          
          session = jsch.getSession(user, host, port);
          session.setPassword(password);
          localUserInfo lui=new localUserInfo();
          session.setUserInfo(lui);
          session.connect();
          session.setPortForwardingL(tunnelLocalPort,tunnelRemoteHost,tunnelRemotePort);
          System.out.println("Connected");
     
      }

    public int getTunnelLocalPort() {
        return tunnelLocalPort;
    }

    public void setTunnelLocalPort(int tunnelLocalPort) {
        this.tunnelLocalPort = tunnelLocalPort;
    }

    public String getTunnelRemoteHost() {
        return tunnelRemoteHost;
    }

    public void setTunnelRemoteHost(String tunnelRemoteHost) {
        this.tunnelRemoteHost = tunnelRemoteHost;
    }

    public int getTunnelRemotePort() {
        return tunnelRemotePort;
    }

    public void setTunnelRemotePort(int tunnelRemotePort) {
        this.tunnelRemotePort = tunnelRemotePort;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public void close() {
        if(session != null && session.isConnected())
        {
            session.disconnect();
        }
    }
     
      
      
    class localUserInfo implements UserInfo{
      String passwd;
        @Override
      public String getPassword(){ return passwd; }
        @Override
      public boolean promptYesNo(String str){return true;}
        @Override
      public String getPassphrase(){ return null; }
        @Override
      public boolean promptPassphrase(String message){return true; }
        @Override
      public boolean promptPassword(String message){return true;}
        @Override
      public void showMessage(String message){}
   }     
  } 