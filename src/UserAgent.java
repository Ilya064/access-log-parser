import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgent {
    private String os;
    private String browser;

    public UserAgent(String userAgentString) {
        os = extractOperatingSystem(userAgentString);
        browser = extractBrowser(userAgentString);
    }

    private String extractOperatingSystem(String userAgentString) {
        String os = "Other";

        if (userAgentString.contains("Windows")) {
            os = "Windows";
        } else if (userAgentString.contains("Mac OS")) {
            os = "macOS";
        } else if (userAgentString.contains("Linux")) {
            os = "Linux";
        }

        return os;
    }

    private String extractBrowser(String userAgentString) {
        String browser = "Other";

        if (userAgentString.contains("Edge")) {
            browser = "Edge";
        } else if (userAgentString.contains("Firefox")) {
            browser = "Firefox";
        } else if (userAgentString.contains("Chrome")) {
            browser = "Chrome";
        } else if (userAgentString.contains("Opera")) {
            browser = "Opera";
        }

        return browser;
    }

    public String getOs() {
        return os;
    }

    public String getBrowser() {
        return browser;
    }
}