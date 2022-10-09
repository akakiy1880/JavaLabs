package bd;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SSHTunnel {

    String host, user, password;
    int port;
    String tunnelRemoteHost;
    int tunnelLocalPort, tunnelRemotePort;

    public int create() {
        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setUserInfo(new LocalUserInfo());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(3000);
            return session.setPortForwardingL(tunnelLocalPort, tunnelRemoteHost, tunnelRemotePort);
        } catch (JSchException ex) {
            ex.printStackTrace();
            System.out.print("Подключение через SSH-туннель не было установлено: отключение.");
            System.exit(0);
        }
        return -1;
    }

    private class LocalUserInfo implements UserInfo {

        @Override
        public String getPassphrase() {
            return null;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public boolean promptPassword(String s) {
            return true;
        }

        @Override
        public boolean promptPassphrase(String s) {
            return true;
        }

        @Override
        public boolean promptYesNo(String s) {
            return true;
        }

        @Override
        public void showMessage(String s) {}
    }
}
