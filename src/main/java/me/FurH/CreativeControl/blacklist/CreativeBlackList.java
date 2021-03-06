package me.FurH.CreativeControl.blacklist;

import java.util.HashSet;
import me.FurH.CreativeControl.stack.CreativeItemStack;

/**
 *
 * @author FurmigaHumana
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class CreativeBlackList {

    public boolean isBlackListed(HashSet<CreativeItemStack> source, CreativeItemStack check) {

        if (source.contains(check)) {
            return true;
        }

        for (CreativeItemStack item : source) {
            if (item.equals(check)) {
                source.add(check); return true;
            }
        }

        return false;
    }
    
    public HashSet<CreativeItemStack> buildHashSet(HashSet<String> source) {
        HashSet<CreativeItemStack> ret = new HashSet<CreativeItemStack>();
        
        for (String string : source) {
            
            if (!isClearInteger(string)) {
                continue;
            }
            
            if (!string.contains(":")) {
                ret.add(new CreativeItemStack(Integer.parseInt(string), (byte) -1)); continue;
            }
            
            ret.add(new CreativeItemStack(Integer.parseInt(string.split(":")[0]), Byte.parseByte(string.split(":")[1])));
        }
        
        return ret;
    }
    
    private boolean isClearInteger(String string) {

        try {
            Integer.parseInt(string.replaceAll("[^0-9-.]", ""));
        } catch (Exception ex) {
            return false;
        }
        
        return true;
    }
}
