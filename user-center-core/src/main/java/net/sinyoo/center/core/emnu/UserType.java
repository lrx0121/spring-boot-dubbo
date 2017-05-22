package net.sinyoo.center.core.emnu;

/**
 * 用户类型
 */
public enum UserType {

  DOCTOR("医生"),ADMIN("管理员"),FACTORY("药企");

  private String name;

  private UserType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
