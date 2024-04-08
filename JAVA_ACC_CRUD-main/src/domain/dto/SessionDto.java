package domain.dto;

import java.util.List;

public class SessionDto {
    private String uuid;
    private String username;
    public SessionDto() {
    }
    public SessionDto(String uuid, String username) {
        this.uuid = uuid;
        this.username = username;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "SessionDto [uuid=" + uuid + ", username=" + username + "]";
    }
}
