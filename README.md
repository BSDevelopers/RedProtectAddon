# RedProtect
This is an addon for the SimplePets v5 plugin.

![RedProtect Flags](https://i.imgur.com/g3MXlmw.png)
### Requirements:
- [RedProtect](https://www.spigotmc.org/resources/15841/) Plugin `(At least v7.7.2)`

### Default configuration (Located in `plugins/SimplePets/Addons/config/RedProtect.yml`)
```yaml
# If enabled this will allow the pets to be used outside of regions (aka if there is no region in the world/location)
allow-outside-regions: true

pet-spawning:
  # The default value the flag should be (Will be ignored if 'admin-only' is enabled)
  default: true
  # Admins are only able to modify this flag (default value is ignored)
  admin-only: false

pet-moving:
  # The default value the flag should be (Will be ignored if 'admin-only' is enabled)
  default: true
  # Admins are only able to modify this flag (default value is ignored)
  admin-only: false

pet-riding:
  # The default value the flag should be (Will be ignored if 'admin-only' is enabled)
  default: true
  # Admins are only able to modify this flag (default value is ignored)
  admin-only: false

pet-mounting:
  # The default value the flag should be (Will be ignored if 'admin-only' is enabled)
  default: true
  # Admins are only able to modify this flag (default value is ignored)
  admin-only: false

# What should the bypass permission be set to?
bypass-permission: pet.redprotect.bypass

checks:
  spawning:
    # Should the addon check when a pet is spawned?
    enabled: true
    # This message is only visible when you hover over the 'pet failed to spawn' message
    # You can make this blank or 'null' if you want no message
    reason: '&cYour pet is not able to be spawned in this area'
    
  mounting:
    # Should the addon check when the player mounts the pet?
    enabled: true
    # Should the pet be removed or should it just be canceled?
    remove-pet: true
    
  moving:
    # Should the addon check when a pet moves?
    enabled: true
    # Should the pet be removed or should it just be canceled?
    remove-pet: true
    
  riding:
    # Should the addon check when the pets owner is riding it?
    enabled: true
    # Should the pet be removed or should it just be canceled?
    remove-pet: true
    # Should the player be dismounted (if remove-pet is set to true this is ignored)?
    dismount: true
```