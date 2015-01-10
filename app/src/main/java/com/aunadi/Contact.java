package com.aunadi;

/**
 * Created by edemuck dev on 10.01.2015.
 */
public class Contact {

    private String name;
    //private byte picture;
    private boolean isSelected;

    public Contact(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (isSelected != contact.isSelected) return false;
        if (!name.equals(contact.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (isSelected ? 1 : 0);
        return result;
    }
}
