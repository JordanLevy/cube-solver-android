package com.jlevy.cubesolver;

public class Cubie {

    int type;
    Sticker[] sticker; //[0] is orientation sticker

    public Cubie(int type)
    {
        this.sticker = new Sticker[3];
        this.type = type;
        for(int i = 0; i < type; i++)
        {
            sticker[i] = new Sticker();
        }
    }

    public void twist(int dir)
    {
        if(type == 2)
        {
            Sticker temp = sticker[0];
            sticker[0] = sticker[1];
            sticker[1] = temp;
        }
        else if(type == 3)
        {
            if(dir == 1) {
                Sticker temp = sticker[0];
                sticker[0] = sticker[2];
                sticker[2] = sticker[1];
                sticker[1] = temp;
            }
            else if(dir == -1)
            {
                Sticker temp = sticker[0];
                sticker[0] = sticker[1];
                sticker[1] = sticker[2];
                sticker[2] = temp;
            }
        }
        else
        {
            return;
        }
    }

    public boolean hasColour(int col)
    {
        for(int i = 0; i < type; i++)
        {
            if(sticker[i].colour == col)
            {
                return true;
            }
        }
        return false;
    }

    public boolean hasColours(int col1, int col2)
    {
        return (hasColour(col1) && hasColour(col2));
    }

    public boolean hasColours(int col1, int col2, int col3)
    {
        return (hasColour(col1) && hasColour(col2) && hasColour(col3));
    }

    public int getCornerOrientation()
    {
        for(int i = 0; i < 3; i++)
        {
            if(sticker[i].colour == 0 || sticker[i].colour == 3)
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < 3; i++) {
            if (sticker[i] != null) {
                buf.append("sticker[" + i + "]-");
                buf.append(sticker[i].colour + " ");
            }
        }
        return buf.toString();
    }

}
