package pl.konczak.nzoz.ereceptaapp.config;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

public class ClientKeystorePasswordCallback
        implements CallbackHandler {

    public ClientKeystorePasswordCallback() {
        // WSHandlerConstants.
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

        // set the password for our message.
        pc.setPassword("UXG9DxASCm");
    }
}

