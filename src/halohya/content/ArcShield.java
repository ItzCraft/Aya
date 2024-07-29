package halohya.content;

import mindustry.content.Blocks;
import mindustry.gen.Building;
import mindustry.gen.Team;
import mindustry.graphics.Pal;
import mindustry.graphics.Draw;
import mindustry.graphics.g3d.ShadowBatch;
import mindustry.ui.Bar;
import mindustry.world.Block;
import mindustry.world.blocks.defense.ForceProjector;
import mindustry.world.blocks.defense.ForceProjector.ForceBuild;
import mindustry.world.blocks.defense.ForceProjector.ForceBuild;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.Stats;
import arc.math.Mathf;
import arc.util.ArcAnnotate;
import arc.util.Intervals;
import arc.util.Angles;
import arc.struct.Array;
import arc.struct.IntSeq;
import arc.math.geom.Geometry;
import arc.graphics.Color;

public class ArcShield extends ForceProjector {

    public ArcShield(String name){
        super(name);
        // Установите свойства вашего блока, если нужно
        this.radius = 15f * tilesize; // Примерный радиус
        this.shieldHealth = 1000f; // Примерное здоровье щита
        this.shieldRotation = 0f;
    }

    @Override
    public void drawPlace(float x, float y, float rotation, boolean valid){
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
    public void draw(){
        super.draw();
        if (Groups.build.size > 0){
            Draw.color(team.color, Color.white, Mathf.clamp(hit));
            drawArc(x, y, radius, arcAngle, team.color);
        }
    }

    @Override
    public void updateTile(){
        super.updateTile();
    }

    @Override
    public void draw(){
        super.draw();
        float realRadius = realRadius();
        if (realRadius > 0 && !broken){
            paramEntity = this;
            paramEffect = absorbEffect;
            Groups.bullet.intersect(x - realRadius, y - realRadius, realRadius * 2f, realRadius * 2f, shieldConsumer);
        }
    }

    public class ArcShieldBuild extends ForceBuild {
        @Override
        public void draw(){
            super.draw();
            if (!broken){
                float realRadius = realRadius();
                Draw.color(team.color, Color.white, Mathf.clamp(hit));
                drawArc(x, y, realRadius, arcAngle, team.color);
            }
        }

        @Override
        public void updateTile(){
            super.updateTile();
        }

        @Override
        public void draw(){
            super.draw();
            float realRadius = realRadius();
            if (realRadius > 0 && !broken){
                paramEntity = this;
                paramEffect = absorbEffect;
                Groups.bullet.intersect(x - realRadius, y - realRadius, realRadius * 2f, realRadius * 2f, shieldConsumer);
            }
        }
    }
}
