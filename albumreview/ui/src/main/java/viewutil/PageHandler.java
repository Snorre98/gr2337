package viewutil;


public class PageHandler{
  private String username;
  private String password;

  public void setUser(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
