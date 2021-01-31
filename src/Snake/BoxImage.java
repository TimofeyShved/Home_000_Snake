package Snake;

import java.awt.*;

public enum BoxImage
{
    Sapple,
    Shead,
    ;

    public Image image;
    /*
    public Image image;
    */
    BoxImage getnextNumberBox ()
    {
        return BoxImage.values()[this.ordinal()+1];
    }

    int getNumber()
    {
        return this.ordinal();
    }

}
