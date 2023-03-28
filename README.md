# LilyBukkit

**LilyBukkit** is a CraftBukkit server core integrated into Rosepad that implements [a modified Bukkit API](https://github.com/Vladg24YT/LilyBukkit-API). The branch you're viewing is for the `Alpha 1.0.16.05_13 Lilypad QA` version from the AlphaVer ARG.

## Decompiling

1. Install [JDK8](https://adoptium.net/temurin/releases/?version=8) and [Git](https://git-scm.com/) (Git Bash on Windows)
2. Clone this repository `$ git clone https://github.com/LilyBukkit/LilyBukkit-3 --recursive`
3. Run `$ ./gradlew prepare injectClasses processPatches pack` and wait for build to finish

## Building

1. Run `$ ./gradlew createPatches ejectClasses`

## Running

For now refer to [LilyBukkit website](https://lilybukkit.github.io/downloads.html)

## Contributing

Since Minecraft is not licensed under a free software license, we can't share its source code, any modifications
must be stored in `.patch` files. Do `./gradlew createPatches ejectClasses` to create new patches. Make sure to
test your patches before making a pull request

> LilyBukkit is licensed under [GPLv3](https://www.gnu.org/licenses/gpl-3.0.en.html) just like Rosepad. If you are publishing your changes,
> make sure you are following the terms of the license
