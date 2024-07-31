package halohya.content;

import arc.graphics.Color;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.graphics.CacheLayer;
import mindustry.type.Category;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.power.ThermalGenerator;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.meta.Attribute;

import static mindustry.type.ItemStack.with;

public class Blocks {
    public static Block
    a,b,c,e;
    public static void load(){
        a = new LiquidStackConveyor("fff"){{
            requirements(Category.distribution, with(Items.surgeAlloy, 1, Items.tungsten, 1));
            health = 130;
            speed = 5f / 60f;
            consumeLiquid(Liquids.cryofluid,1/60f);
            hasLiquids = true;
            underBullets = true;
        }};
        b = new AttributeCrafter("uwu"){{
            requirements(Category.crafting, with(Items.copper, 1));
            consumeLiquid(Liquids.water, 1);
            consumePower(0.5f);
            size = 3;
            attribute = Meow.neoplasmic;
            placeableLiquid = true;
            minEfficiency = 9f - 0.0001f;
            displayEfficiency = false;
            hasLiquids = true;
            outputLiquid = new LiquidStack(Liquids.neoplasm, 30f / 60f);
            liquidCapacity = 60f;
            floating = true;
        }};
        c = new Floor("floor-neoplasm"){{
            attributes.set(Meow.neoplasmic, 1f);
            drownTime = 150f;
            statusDuration = 240f;
            speedMultiplier = 0.5f;
            variants = 0;
            liquidMultiplier = 0.5f;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            emitLight = true;
            lightRadius = 25f;
            lightColor = Color.cyan.cpy().a(0.19f);
        }};
        e = new Floor("1"){{
            attributes.set(Meow.neoplasmic, 1f);
        }};
    }
}
