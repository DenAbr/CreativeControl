package me.FurH.CreativeControl.stack;

/**
 *
 * @author FurmigaHumana
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class CreativeItemStack {

    private int     type;
    private byte    data;
    
    public CreativeItemStack(int type, byte data) {
        this.type = type;
        this.data = data;
    }
    
    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof CreativeItemStack)) {
            return false;
        }

        CreativeItemStack stack = (CreativeItemStack)object;

        if (stack.type != type) {
            return false;
        }

        if (data == -1) {
            return true;
        }

        return data == stack.data;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        
        hash = 17 * hash + this.type;
        hash = 17 * hash + this.data;

        return hash;
    }
}