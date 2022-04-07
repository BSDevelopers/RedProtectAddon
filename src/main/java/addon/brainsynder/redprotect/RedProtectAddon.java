package addon.brainsynder.redprotect;

import br.net.fabiozumbi12.RedProtect.Bukkit.RedProtect;
import br.net.fabiozumbi12.RedProtect.Bukkit.Region;
import org.bukkit.Location;
import simplepets.brainsynder.addon.AddonConfig;
import simplepets.brainsynder.addon.presets.RegionModule;
import simplepets.brainsynder.api.Namespace;
import simplepets.brainsynder.api.user.PetUser;

@Namespace(namespace = "RedProtect")
public class RedProtectAddon extends RegionModule {
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
