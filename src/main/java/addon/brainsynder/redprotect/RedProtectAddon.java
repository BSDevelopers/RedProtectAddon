package addon.brainsynder.redprotect;

import br.net.fabiozumbi12.RedProtect.Bukkit.RedProtect;
import br.net.fabiozumbi12.RedProtect.Bukkit.Region;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import simplepets.brainsynder.addon.AddonConfig;
import simplepets.brainsynder.addon.presets.RegionAddon;
import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.plugin.SimplePets;
import simplepets.brainsynder.api.user.PetUser;
import simplepets.brainsynder.debug.DebugLevel;

import java.util.List;

@Namespace(namespace = "RedProtect")
public class RedProtectAddon extends RegionAddon {
    private boolean outsideRegion = true;

    private boolean spawningDefault = true;
    private boolean spawningAdmin = false;

    private boolean movingDefault = true;
    private boolean movingAdmin = false;

    private boolean ridingDefault = true;
    private boolean ridingAdmin = false;

    private boolean mountingDefault = true;
    private boolean mountingAdmin = false;


    @Override
    public boolean shouldEnable() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("RedProtect");
        if (plugin == null) {
            SimplePets.getDebugLogger().debug(DebugLevel.ERROR, "RedProtect wasn't found!");
            SimplePets.getDebugLogger().debug(DebugLevel.ERROR, "Please download it from: https://www.spigotmc.org/resources/15841/");
            return false;
        }
        return true;
    }

    @Override
    public void init() {
        RedProtect.get().getAPI().addFlag("pet-spawning", spawningDefault, spawningAdmin);
        RedProtect.get().getAPI().addFlag("pet-moving", movingDefault, movingAdmin);
        RedProtect.get().getAPI().addFlag("pet-riding", ridingDefault, ridingAdmin);
        RedProtect.get().getAPI().addFlag("pet-mounting", mountingDefault, mountingAdmin);
    }

    @Override
    public void loadDefaults(AddonConfig config) {
        config.addDefault("allow-outside-regions", true,
                "If enabled this will allow the pets to be used outside of regions (aka if there is no region in the world/location)");

        config.addDefault("pet-spawning.default", true,
                "The default value the flag should be (Will be ignored if 'admin-only' is enabled)");
        config.addDefault("pet-spawning.admin-only", false,
                "Admins are only able to modify this flag (default value is ignored)");

        config.addDefault("pet-moving.default", true,
                "The default value the flag should be (Will be ignored if 'admin-only' is enabled)");
        config.addDefault("pet-moving.admin-only", false,
                "Admins are only able to modify this flag (default value is ignored)");

        config.addDefault("pet-riding.default", true,
                "The default value the flag should be (Will be ignored if 'admin-only' is enabled)");
        config.addDefault("pet-riding.admin-only", false,
                "Admins are only able to modify this flag (default value is ignored)");

        config.addDefault("pet-mounting.default", true,
                "The default value the flag should be (Will be ignored if 'admin-only' is enabled)");
        config.addDefault("pet-mounting.admin-only", false,
                "Admins are only able to modify this flag (default value is ignored)");

        outsideRegion = config.getBoolean("allow-outside-regions", true);

        spawningDefault = config.getBoolean("pet-spawning.default", true);
        spawningAdmin = config.getBoolean("pet-spawning.admin-only", false);

        movingDefault = config.getBoolean("pet-moving.default", true);
        movingAdmin = config.getBoolean("pet-moving.admin-only", false);

        ridingDefault = config.getBoolean("pet-riding.default", true);
        ridingAdmin = config.getBoolean("pet-riding.admin-only", false);

        mountingDefault = config.getBoolean("pet-mounting.default", true);
        mountingAdmin = config.getBoolean("pet-mounting.admin-only", false);
        super.loadDefaults(config);
    }

    @Override
    public double getVersion() {
        return 0.1;
    }

    @Override
    public String getAuthor() {
        return "brainsynder";
    }

    @Override
    public List<String> getDescription() {
        return Lists.newArrayList(
                "&7This addon hooks into",
                "&7RedProtect so you can utilize",
                "&7the flags added via this addon: ",
                "&7- pet-spawning",
                "&7- pet-moving",
                "&7- pet-riding",
                "&7- pet-mounting"
        );
    }

    @Override
    public boolean isSpawningAllowed(PetUser petUser, Location location) {
        Region region = RedProtect.get().getAPI().getRegion(location);
        if (region == null) return outsideRegion;
        return region.getFlagBool("pet-spawning");
    }

    @Override
    public boolean isMovingAllowed(PetUser petUser, Location location) {
        Region region = RedProtect.get().getAPI().getRegion(location);
        if (region == null) return outsideRegion;
        return region.getFlagBool("pet-moving");
    }

    @Override
    public boolean isRidingAllowed(PetUser petUser, Location location) {
        Region region = RedProtect.get().getAPI().getRegion(location);
        if (region == null) return outsideRegion;
        return region.getFlagBool("pet-riding");
    }

    @Override
    public boolean isMountingAllowed(PetUser petUser, Location location) {
        Region region = RedProtect.get().getAPI().getRegion(location);
        if (region == null) return outsideRegion;
        return region.getFlagBool("pet-mounting");
    }
}
