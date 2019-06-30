package com.example.medkit;

public class orderadressgettersetterclass {
   private String name;

    private String address;
    private String phone;
    private String alternatephone;
    private String note;
   public orderadressgettersetterclass()
   {

   }

    public orderadressgettersetterclass(String name, String address, String phone, String alternatephone, String note) {

        this.name = name;
        this.address = address;
        this.phone = phone;
        this.alternatephone = alternatephone;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlternatephone() {
        return alternatephone;
    }

    public void setAlternatephone(String alternatephone) {
        this.alternatephone = alternatephone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
