package exceptions;

import lombok.Getter;

import java.io.IOException;

@Getter
public class SocketCreateException extends IOException {

    private final String url;

    public SocketCreateException(String message, String url){
        super(message);
        this.url = url;
    }
}
