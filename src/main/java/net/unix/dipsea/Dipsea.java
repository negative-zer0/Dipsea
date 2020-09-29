package net.unix.dipsea;

import sun.misc.SharedSecrets;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Unix
 * @since 29.09.2020
 */

public class Dipsea
{
    private Type type;
    private String key;

    public void init(Type type, String key) {
        this.type = type;
        this.key = key;
    }

    public void init(Type type) {
        if (Objects.isNull(key))
            throw new UnsupportedOperationException("Key must be String");

        this.type = type;
    }

    public String doFinal(String string) {
        switch (type) {
            case ENCRYPT: {
                final StringBuilder stringBuilder = new StringBuilder();
                string.chars().forEach(character -> stringBuilder.append((char) (character * (key.length() - 0x01 + key.chars()
                        .map(i -> i >> 1)
                        .sum() >>> ~(~SharedSecrets.getJavaLangAccess().getConstantPool(HashMap.class).getSize()))))
                );

                return stringBuilder.toString();
            }
            case DECRYPT: {
                final StringBuilder stringBuilder = new StringBuilder();
                string.chars().forEach(character -> stringBuilder.append((char) (character / (key.length() - 0x01 + key.chars()
                        .map(i -> i >> 1)
                        .sum() >>> ~(~SharedSecrets.getJavaLangAccess().getConstantPool(HashMap.class).getSize()))))
                );

                return stringBuilder.toString();
            }
            default:
                return "error 404";
        }
    }

    enum Type
    {
        ENCRYPT,
        DECRYPT
    }
}