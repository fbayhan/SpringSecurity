package com.fatih.SpringSecurity.dtos;

public class RenewPasswordDTO {
    private  String newPassword;
    private  String uuid;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "RenewPasswordDTO{" +
                "newPassword='" + newPassword + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
