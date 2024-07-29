package halohya.content;

import arc.graphics.Color;
import halohya.ArcPart;
import mindustry.content.Liquids;
import mindustry.entities.bullet.ContinuousLaserBulletType;
import mindustry.entities.bullet.LaserBoltBulletType;
import mindustry.entities.part.HaloPart;
import mindustry.entities.part.ShapePart;
import mindustry.gen.UnitEntity;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

import java.security.cert.TrustAnchor;

public class myunits {
    public static UnitType
    teast, god
            ;
    public static void load(){
        teast = new UnitType("nya"){{
            this.constructor=UnitEntity::create;
            flying = true;
            lowAltitude = true; //на уровень ниже под эффектами
            health = 100;
            accel = 0.08f; //ускор и замедло
            drag = 0.05f;
            speed = 2;
            engineOffset = 4; //движок
            engineSize = 3;
            weapons.addAll(
                new Weapon(){{
                    mirror = false;
                    x = 0;
                    y = 0;
                    shootX = 0;
                    shootY = 8;
                    reload = 300; //перезарядка
                    continuous = alwaysContinuous = true; //снаряд двигается с юнитом
                    bullet = new ContinuousLaserBulletType() {{
                            colors = new Color[]{Liquids.slag.color, Pal.accent};
                            lifetime = 10;
                            width = 5; //ширина
                            length = 140; //длинна
                        }};
                }}
            );
            parts.addAll(
                new HaloPart(){{
                    progress = PartProgress.warmup;
                    x = -8;
                    y = -3.5f;
                    mirror = true; //ты серьёзно не знаешь англ?
                    shapes = 3;
                    sides = 3;
                    shapeRotation = 180;
                    tri = true; //одныне ти треугло
                    haloRotateSpeed = -1;
                    haloRadius = 4;
                    triLength = 0;
                    triLengthTo = 1;
                    color = Pal.accent;
                    layer = Layer.effect;
                }},
                new HaloPart(){{
                    x = -8;
                    y = -3.5f;
                    mirror = true;
                    shapes = 3;
                    sides = 3;
                    haloRotateSpeed = -1; //скорость поворота вокруг юнита, а НЕ ВОКРУГ СЕБЯ БЛЯТЬ
                    haloRadius = 4;
                    triLength = 0; //длинное треугло
                    triLengthTo = 2.5f;
                    tri = true;
                    color = Pal.accent;
                    layer = Layer.effect;
                }},
                new ShapePart(){{
                    x = -8;
                    y = -3.5f;
                    mirror = true;
                    sides = 6;
                    radius = 3;
                    rotateSpeed = 1;
                    hollow = true;
                    stroke = 0; //ширина
                    strokeTo = 1;
                    color = Liquids.slag.color;
                    layer = Layer.effect; //уровень (на уровне эффектов)
                }}
            );
        }};
        god = new UnitType("fyr"){{
            this.constructor=UnitEntity::create;
            health = 100;
            weapons.addAll(
                new Weapon(){{
                    reload = 300;
                    bullet = new LaserBoltBulletType() {{
                        frontColor = Liquids.ozone.color;
                    }};
                }}
            );
            parts.addAll(
                new HaloPart(){{//центральный
                    progress = PartProgress.warmup;
                    color = Liquids.ozone.color;
                    layer = Layer.effect;
                    hollow = true;
                    y = -10;
                    radius = 4;
                    rotateSpeed = 1.5f;
                    sides = 3;
                    shapes = 1;
                    stroke = 0;
                    strokeTo = 1;
                }},
                new HaloPart(){{//по краям круги
                    progress = PartProgress.warmup;
                    color = Liquids.ozone.color;
                    layer = Layer.effect;
                    hollow = true;
                    sides = 30;
                    radius = 3;
                    haloRadius = 10;
                    haloRotateSpeed = -0.7f;
                    stroke = 0;
                    strokeTo = 1;
                }},
                new ArcPart(){{//пунктир
                    progress = PartProgress.warmup;
                    color = Liquids.ozone.color;
                    layer = Layer.effect;
                    radius = 7.5f;
                    shapeFraction = 45/360f;
                    shapes = 6;
                    rotateSpeed = 0.5f;
                    stroke = 0;
                    strokeTo = 1;
                }},
                new HaloPart(){{//треуглы 1
                    progress = PartProgress.warmup;
                    color = Liquids.ozone.color;
                    layer = Layer.effect;
                    tri = true;
                    triLength = 0;
                    triLengthTo = 1;
                    haloRadius = 7.8f;
                    haloRotateSpeed = -0.7f;
                }},
                new HaloPart(){{//треуглы 2 (от щели)
                    progress = PartProgress.warmup;
                    color = Liquids.ozone.color;
                    layer = Layer.effect;
                    tri = true;
                    triLength = 0;
                    triLengthTo = 1;
                    haloRadius = 7.8f;
                    haloRotateSpeed = -0.7f;
                    shapeRotation = 180;
                }}
            );
        }};
    }
}
