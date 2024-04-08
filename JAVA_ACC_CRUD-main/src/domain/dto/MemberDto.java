package domain.dto;

public class MemberDto {
    private String username;
    private String password;
    private String email;
    private Integer phone;
    // 디폴트 생성자
    public MemberDto() {
    }
    
    public MemberDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 회원가입 생성자
    public MemberDto(String username, String password, String email, Integer phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
    // 게터 세터
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getPhone() {
        return phone;
    }
    public void setPhone(Integer phone) {
        this.phone = phone;
    }
    // 자기 소개
    @Override
    public String toString() {
        return "MemberDto [username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone
                + "]";
    }
    
}
