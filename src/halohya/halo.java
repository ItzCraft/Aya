package halohya;

import halohya.content.Blocks;
import halohya.content.Meow;
import halohya.content.myunits;
import mindustry.mod.*;

public class halo extends Mod{

    public halo(){

    }
@Override
    public void loadContent(){
        Meow.load();
        myunits.load();
        Blocks.load();
    }

}
