// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Color.java

package com.oneisall.learn.java.basic.enums;


public class Color extends Enum
{

    public static Color[] values()
    {
        return (Color[])$VALUES.clone();
    }

    public static Color valueOf(String name)
    {
        return (Color)Enum.valueOf(com/oneisall/learn/java/basic/enums/Color, name);
    }

    private Color(String s, int i)
    {
        super(s, i);
    }

    public String mixture(String injection)
    {
        throw new AbstractMethodError();
    }


    public static final Color RED;
    public static final Color BLACK;
    private static final Color $VALUES[];

    static 
    {
        RED = new Color("RED", 0) {

            public String mixture(String injection)
            {
                return (new StringBuilder()).append("\u7EA2\u8272+").append(injection).toString();
            }

        }
;
        BLACK = new Color("BLACK", 1);
        $VALUES = (new Color[] {
            RED, BLACK
        });
    }
}
