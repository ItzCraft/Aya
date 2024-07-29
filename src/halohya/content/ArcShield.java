package halohya.content;

import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.math.geom.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;

import static mindustry.Vars.*;

public class ArcShield extends ForceProjector {
    public float arcAngle = 45f; // Угол арки в градусах

    public ArcShield(String name){
        super(name);
        this.radius = 15f * tilesize; // Примерный радиус
        this.shieldHealth = 1000f; // Примерное здоровье щита
        this.shieldRotation = 0f;
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);
        drawArc(x * tilesize + offset, y * tilesize + offset, radius, arcAngle, player.team().color);
    }

    @Override
    public void init(){
        super.init();
        updateClipRadius(radius + phaseRadiusBoost + 3f);
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("shield", (ForceBuild entity) -> new Bar("stat.shieldhealth", Pal.accent, 
            () -> entity.broken ? 0f : 1f - entity.buildup / (shieldHealth + phaseShieldBoost * entity.phaseHeat)).blink(Color.white));
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.shieldHealth, shieldHealth, StatUnit.none);
    }

    @Override
    public void draw() {
        super.draw();
        if (Groups.build.size > 0){
            Draw.color(team.color, Color.white, Mathf.clamp(hit));
            drawArc(x, y, radius, arcAngle, team.color);
        }
    }

    public void drawArc(float x, float y, float radius, float angle, Color color){
        Draw.color(color, 0.3f);
        Lines.stroke(1.5f);
        float step = 1f; // Шаг в градусах для рисования арки (пасиба чату джпт) 
        for (float i = -angle / 2; i <= angle / 2; i += step){
            float rad = Mathf.degRad * i;
            float xOffset = Angles.trnsx(rad, radius);
            float yOffset = Angles.trnsy(rad, radius);
            Lines.lineAngle(x + xOffset, y + yOffset, rad, step * Mathf.degRad * radius);
        }
        Draw.reset();
    }

    public class ArcShieldBuild extends ForceBuild{
        @Override
        public void draw(){
            super.draw();
            if (!broken) {
                float realRadius = realRadius();
                if (realRadius > 0.001f){
                    Draw.color(team.color, Color.white, Mathf.clamp(hit));
                    Draw.z(Layer.shields);
                    drawArc(x, y, realRadius, arcAngle, team.color);
                }
            }
        }

        @Override
        public void updateTile(){
            super.updateTile();
            deflectBullets();
        }

        @Override
        public void deflectBullets(){
            float realRadius = realRadius();
            if (realRadius > 0 && !broken) {
                paramEntity = this;
                paramEffect = absorbEffect;
                Groups.bullet.intersect(x - realRadius, y - realRadius, realRadius * 2f, realRadius * 2f, shieldConsumer);
            }
        }
    }
}
