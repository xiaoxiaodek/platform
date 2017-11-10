package com.www.platform.entity;

/**
 * Created by upsmart on 17-7-21.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  上午9:37
 */
public class User {
    String user_id;
    String password;
    String salt;
    String role_ids;
    boolean locked;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRole_ids() {
        return role_ids;
    }

    public void setRole_ids(String role_ids) {
        this.role_ids = role_ids;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
