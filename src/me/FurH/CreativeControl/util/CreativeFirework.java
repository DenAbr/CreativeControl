package me.FurH.CreativeControl.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 *
 * @author FurmigaHumana
 */
public class CreativeFirework {

    public static String getFireWork(String ret, ItemStack item) {
        FireworkMeta meta = (FireworkMeta) item.getItemMeta();

        if (!meta.hasEffects()) {
            return ret;
        }

        String effects = "";
        ret += ":" + meta.getPower();

        for (FireworkEffect effect : meta.getEffects()) {

            List<Integer> colors = new ArrayList<Integer>();
            for (Color color : effect.getColors()) {
                colors.add(getColor(color));
            }

            List<Integer> fadecolors = new ArrayList<Integer>();
            for (Color color : effect.getFadeColors()) {
                fadecolors.add(getColor(color));
            }

            String ef = "{"+getType(effect.getType()) +
                    ";" + colors +
                    ";" + fadecolors +
                    ";" + (effect.hasFlicker() ? "1" : "0") +
                    ";" + (effect.hasTrail() ? "1" : "0") + "}. ";
            effects += ef;
        }
        effects = effects.substring(0, effects.length() - 2);

        return ret += ":" + effects;
    }

    public static ItemStack getFireWork(ItemStack stack, String string) {
        String[] inv = string.split(":");

        int power = Integer.parseInt(inv[5]);
        FireworkMeta meta = (FireworkMeta) stack.getItemMeta();

        List<String> effects = new ArrayList<String>();
        if (!inv[6].equals("[]")) {
            effects = Arrays.asList(inv[6].substring(1, inv[6].length() - 1).split("\\."));
        }
        
        for (String effect : effects) {
            FireworkEffect.Builder builder = FireworkEffect.builder();
            String[] data = effect.split(";");
            
            builder.with(getType(Integer.parseInt(data[0])));
            
            for (String color : CreativeUtil.toStringHashSet(data[1], ", ")) {
                builder.withColor(getColor(Integer.parseInt(color)));
            }
            
            for (String color : CreativeUtil.toStringHashSet(data[2], ", ")) {
                builder.withFade(getColor(Integer.parseInt(color)));
            }
            
            builder.flicker(Integer.parseInt(data[3]) == 1);
            builder.trail(Integer.parseInt(data[4]) == 1);
            
            meta.addEffect(builder.build());
        }

        meta.setPower(power);
        stack.setItemMeta(meta);

        return stack;
    }
    
    public static Type getType(int id) {
        switch (id) {
            case 0:
                return Type.BALL;
            case 1:
                return Type.BALL_LARGE;
            case 2:
                return Type.BURST;
            case 3:
                return Type.CREEPER;
            case 4:
                return Type.STAR;
            default:
                return Type.BALL;
        }
    }
    
    public static int getType(Type type) {
        if (type == Type.BALL) {
            return 0;
        } else
        if (type == Type.BALL_LARGE) {
            return 1;
        } else
        if (type == Type.BURST) {
            return 2;
        } else
        if (type == Type.CREEPER) {
            return 3;
        } else
        if (type == Type.STAR) {
            return 4;
        }
        return 0;
    }
    
    public static int getColor(Color color) {
        return color.asRGB();
    }
    
    public static Color getColor(int rgb) {
        return Color.fromRGB(rgb);
    }
}
