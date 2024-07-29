package halohya.content;

import mindustry.gen.Team;
import mindustry.graphics.Draw;
import mindustry.graphics.g3d.ShadowBatch;
import arc.util.Angles;
import arc.util.Mathf;
import arc.struct.Array;
import arc.graphics.Color;
import mindustry.entities.Building;
import mindustry.entities.type.TileEntity;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.Shield;
import mindustry.world.blocks.defense.Shield.ShieldBuild;

public class ArcShield extends Shield {
    public float radius = 15f; // Радиус арки
    public float arcAngle = 45f; // Угол арки

    public ArcShield(String name){
        super(name);
        // Конструктор по умолчанию
    }

    @Override
    public void drawPlace(float x, float y, float rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);
        Draw.color(Color.white);
        drawArc(x * tilesize, y * tilesize, radius, arcAngle, team.color);
    }

    private void drawArc(float x, float y, float radius, float angle, Color color){
        // Отрисовка арки
        Draw.color(color);
        Draw.arc(x, y, radius, 0, angle);
    }

    public class ArcShieldBuild extends ShieldBuild {
        @Override
        public void draw(){
            super.draw();
            Draw.color(team.color);
            drawArc(x, y, radius, arcAngle, team.color);
        }
        
        @Override
        public void updateTile(){
            super.updateTile();
            // Обновление состояния
        }
    }
}
