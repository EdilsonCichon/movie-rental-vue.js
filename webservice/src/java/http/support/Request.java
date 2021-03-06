package http.support;

import javax.json.Json;
import java.io.StringReader;
import javax.json.JsonObject;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

public class Request {
    private HttpServletRequest servletRequest;

    public Request(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
    
    /**
     * Captura o conteúdo (body) da requisição em texto puro, e converte para 
     * um JsonObject manipulável.
     * 
     * @return
     * @throws Exception 
     */
    public JsonObject getContent() throws Exception {
        String data = servletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        if (data.equals(""))
            throw new Exception("Request Content is empty.");
        JsonObject dataJson = Json.createReader(new StringReader(data)).readObject();
        return dataJson;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }
    
    /**
     * Get parameter {name} in the request.
     * 
     * @param name
     * @return 
     */
    public String get(String name) {
        return getServletRequest().getParameter(name);
    }
}
