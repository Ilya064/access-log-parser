import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {
    private final String ipAddress;
    private final LocalDateTime dateTime;
    private final HttpMethod httpMethod;
    private final String path;
    private final int responseCode;
    private final int responseSize;
    private final String protocol;
    private final String referer;

    public LogEntry(String logString) {
        String[] logComponents = logString.split(" ");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss", Locale.US);
        this.ipAddress = logComponents[0];
        this.dateTime = LocalDateTime.parse(logComponents[3].substring(1), dtf);
        this.httpMethod = HttpMethod.valueOf(logComponents[5].substring(1));
        this.path = logComponents[6];
        this.responseCode = Integer.parseInt(logComponents[8]);
        this.responseSize = (int) Long.parseLong(logComponents[9]);
        this.protocol = logComponents[7];
        this.referer = logComponents[11];
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public long getDataSize() {
        return responseSize;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getReferer() {
        return referer;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "ipAddress='" + ipAddress + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", httpMethod=" + httpMethod +
                ", path='" + path + '\'' +
                ", responseCode=" + responseCode +
                ", dataSize=" + responseSize +
                ", protocol='" + protocol + '\'' +
                ", referer='" + referer + '\'' +
                '}';
    }
}

enum HttpMethod {
    GET, POST, PUT, DELETE
}


