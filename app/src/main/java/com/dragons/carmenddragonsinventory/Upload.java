package com.dragons.carmenddragonsinventory;

public class Upload {
    private String name;
    private String imageUri;

    public Upload(){
        //empty constructor needed
    }

    public Upload(String name, String imgUri){

        if(name.trim().equals("")){
            this.name = "No Name";
        }
        this.name = name;
        this.imageUri = imgUri;
    }

    public String getName(){
        return name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
