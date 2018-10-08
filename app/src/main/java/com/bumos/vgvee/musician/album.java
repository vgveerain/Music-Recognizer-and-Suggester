package com.bumos.vgvee.musician;

import java.util.ArrayList;

public class album {
    ArrayList<image> image;

    public album(ArrayList<com.bumos.vgvee.musician.image> image) {
        this.image = image;
    }

    public ArrayList<com.bumos.vgvee.musician.image> getImage() {
        return image;
    }

    public void setImage(ArrayList<com.bumos.vgvee.musician.image> image) {
        this.image = image;
    }
}
