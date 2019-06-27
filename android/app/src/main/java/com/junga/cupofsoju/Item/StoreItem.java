package com.junga.cupofsoju.Item;

import android.graphics.drawable.Drawable;

public class StoreItem {
    String icon;
    String name;
    String address;
    String call;

    public StoreItem(String icon, String name, String address, String call) {
        this.icon = icon;
        this.name = name;
        this.address = address;
        this.call = call;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }
}
