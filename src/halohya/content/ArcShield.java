package halohya.content;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.blocks.defense.BaseShield;
import static mindustry.Vars.tilesize;
import static mindustry.Vars.player;

public class ArcShield extends BaseShield {
    public float angle = 45f; // Угол арки в градусах
    public float radius = 15f * tilesize; // Радиус арки в пикселях

    public ArcShield(String name){
        super(name);
        radius = 15f * tilesize; // Перевод радиуса из блоков в пиксели
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);
        drawArc(x * tilesize + offset, y * tilesize + offset, radius, angle, player.team().color);
    }

    @Override
    public void init(){
        super.init();
        updateClipRadius(radius);
    }

    public void drawArc(float x, float y, float radius, float angle, Color color){
        Draw.color(color, 0.3f);
        Lines.stroke(1.5f);
        float increment = 1f; // Шаг в градусах (пасиба чату джпт)
        for (float i = -angle / 2; i <= angle / 2; i += increment){
            float rad = Mathf.degRad * i;
            float xOffset = Angles.trnsx(rad, radius);
            float yOffset = Angles.trnsy(rad, radius);
            Lines.lineAngle(x + xOffset, y + yOffset, rad, increment * Mathf.degRad * radius);
        }
        Draw.reset();
    }

    public class ArcShieldBuild extends BaseShieldBuild{
        @Override
        public void draw(){
            super.draw();
            drawArc(x, y, radius(), angle, team.color);
        }

        @Override
        public void drawSelect(){
            super.drawSelect();
            drawArc(x, y, radius(), angle, team.color);
        }

        @Override
        public void updateTile(){
            super.updateTile();
        }
    }
}
