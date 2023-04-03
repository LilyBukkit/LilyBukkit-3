//LilyBukkit start
package io.lilybukkit.lilybukkit3.minecraft;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

// CraftBukkit start
import net.minecraft.server.*;
import org.bukkit.craftbukkit.command.ServerCommandListener;
import org.bukkit.craftbukkit.entity.CraftPlayer;
// CraftBukkit end

public class ConsoleCommandHandler {

    private static Logger a = Logger.getLogger("Minecraft");
    private MinecraftServer server;
    private ICommandListener listener; // CraftBukkit

    public ConsoleCommandHandler(MinecraftServer minecraftserver) {
        this.server = minecraftserver;
    }

    public boolean handle(ServerCommand servercommand) { // CraftBukkit - returns boolean
        String s = servercommand.command;
        ICommandListener icommandlistener = servercommand.commandListener;
        String s1 = icommandlistener.getUsername();
        this.listener = icommandlistener; // CraftBukkit
        ServerConfigurationManager serverconfigurationmanager = this.server.configManager;

        if (!s.toLowerCase().startsWith("help") && !s.toLowerCase().startsWith("?")) {
            if (s.toLowerCase().startsWith("list")) {
                icommandlistener.addHelpCommandMessage("Connected players: " + serverconfigurationmanager.getPlayerList());
            } else if (s.toLowerCase().startsWith("stop")) {
                this.print(s1, "Stopping the server..");
                this.server.stopRunning();
            } else {
                int i;
                WorldServer worldserver;

                if (s.toLowerCase().startsWith("save-all")) {
                    this.print(s1, "Forcing save..");
                    if (serverconfigurationmanager != null) {
                        serverconfigurationmanager.savePlayerStates();
                    }

                    // CraftBukkit start
                    for (i = 0; i < this.server.worlds.size(); ++i) {
                        worldserver = this.server.worlds.get(i);
                        boolean save = worldserver.canSave;
                        worldserver.levelSaving = false;
                        worldserver.saveWorld(true, (IProgressUpdate) null);
                        worldserver.levelSaving = save;
                    }
                    // CraftBukkit end

                    this.print(s1, "Save complete.");
                } else if (s.toLowerCase().startsWith("save-off")) {
                    this.print(s1, "Disabling level saving..");

                    for (i = 0; i < this.server.worlds.size(); ++i) { // CraftBukkit
                        worldserver = this.server.worlds.get(i); // CraftBukkit
                        worldserver.levelSaving = true;
                    }
                } else if (s.toLowerCase().startsWith("save-on")) {
                    this.print(s1, "Enabling level saving..");

                    for (i = 0; i < this.server.worlds.size(); ++i) { // CraftBukkit
                        worldserver = this.server.worlds.get(i); // CraftBukkit
                        worldserver.levelSaving = false;
                    }
                } else {
                    String s2;

                    if (s.toLowerCase().startsWith("op ")) {
                        s2 = s.substring(s.indexOf(" ")).trim();
                        serverconfigurationmanager.opPlayer(s2);
                        this.print(s1, "Opping " + s2);
                        serverconfigurationmanager.sendChatMessageToPlayer(s2, "\u00A7eYou are now op!");
                    } else if (s.toLowerCase().startsWith("deop ")) {
                        s2 = s.substring(s.indexOf(" ")).trim();
                        serverconfigurationmanager.deopPlayer(s2);
                        serverconfigurationmanager.sendChatMessageToPlayer(s2, "\u00A7eYou are no longer op!");
                        this.print(s1, "De-opping " + s2);
                    } else if (s.toLowerCase().startsWith("ban-ip ")) {
                        s2 = s.substring(s.indexOf(" ")).trim();
                        serverconfigurationmanager.banIP(s2);
                        this.print(s1, "Banning ip " + s2);
                    } else if (s.toLowerCase().startsWith("pardon-ip ")) {
                        s2 = s.substring(s.indexOf(" ")).trim();
                        serverconfigurationmanager.pardonIP(s2);
                        this.print(s1, "Pardoning ip " + s2);
                    } else {
                        EntityPlayer entityplayer;

                        if (s.toLowerCase().startsWith("ban ")) {
                            s2 = s.substring(s.indexOf(" ")).trim();
                            serverconfigurationmanager.banPlayer(s2);
                            this.print(s1, "Banning " + s2);
                            entityplayer = serverconfigurationmanager.getPlayerEntity(s2);
                            if (entityplayer != null) {
                                entityplayer.netServerHandlr.disconnect("Banned by admin");
                            }
                        } else if (s.toLowerCase().startsWith("pardon ")) {
                            s2 = s.substring(s.indexOf(" ")).trim();
                            serverconfigurationmanager.pardonPlayer(s2);
                            this.print(s1, "Pardoning " + s2);
                        } else {
                            int j;

                            if (s.toLowerCase().startsWith("kick ")) {
                                // CraftBukkit start - Add kick message compatibility
                                String[] parts = s.split(" ");
                                s2 = parts.length >= 2 ? parts[1] : "";
                                // CraftBukkit end
                                entityplayer = null;

                                for (j = 0; j < serverconfigurationmanager.playerEntities.size(); ++j) {
                                    EntityPlayer entityplayer1 = (EntityPlayer) serverconfigurationmanager.playerEntities.get(j);

                                    if (entityplayer1.username.equalsIgnoreCase(s2)) {
                                        entityplayer = entityplayer1;
                                    }
                                }

                                if (entityplayer != null) {
                                    entityplayer.netServerHandler.disconnect("Kicked by admin");
                                    this.print(s1, "Kicking " + entityplayer.username);
                                } else {
                                    icommandlistener.addHelpCommandMessage("Can\'t find user " + s2 + ". No kick.");
                                }
                            } else {
                                EntityPlayer entityplayer2;
                                String[] astring;

                                if (s.toLowerCase().startsWith("tp ")) {
                                    astring = s.split(" ");
                                    if (astring.length == 3) {
                                        entityplayer = serverconfigurationmanager.getPlayerEntity(astring[1]);
                                        entityplayer2 = serverconfigurationmanager.getPlayerEntity(astring[2]);
                                        if (entityplayer == null) {
                                            icommandlistener.addHelpCommandMessage("Can\'t find user " + astring[1] + ". No tp.");
                                        } else if (entityplayer2 == null) {
                                            icommandlistener.addHelpCommandMessage("Can\'t find user " + astring[2] + ". No tp.");
                                        } else {
                                            entityplayer.netServerHandler.a(entityplayer2.posX, entityplayer2.posY, entityplayer2.posZ, entityplayer2.cameraYaw, entityplayer2.cameraPitch);
                                            this.print(s1, "Teleporting " + astring[1] + " to " + astring[2] + ".");
                                        }
                                    } else {
                                        icommandlistener.addHelpCommandMessage("Syntax error, please provide a source and a target.");
                                    }
                                } else {
                                    String s3;
                                    int k;

                                    if (s.toLowerCase().startsWith("give ")) {
                                        astring = s.split(" ");
                                        if (astring.length != 3 && astring.length != 4) {
                                            return true; // CraftBukkit
                                        }

                                        s3 = astring[1];
                                        entityplayer2 = serverconfigurationmanager.getPlayerEntity(s3);
                                        if (entityplayer2 != null) {
                                            try {
                                                k = Integer.parseInt(astring[2]);
                                                if (Item.itemsList[k] != null) {
                                                    this.print(s1, "Giving " + entityplayer2.username + " some " + k);
                                                    int l = 1;

                                                    if (astring.length > 3) {
                                                        l = this.a(astring[3], 1);
                                                    }

                                                    if (l < 1) {
                                                        l = 1;
                                                    }

                                                    if (l > 64) {
                                                        l = 64;
                                                    }

                                                    entityplayer2.dropPlayerItem(new ItemStack(k, l, 0));
                                                } else {
                                                    icommandlistener.addHelpCommandMessage("There\'s no item with id " + k);
                                                }
                                            } catch (NumberFormatException numberformatexception) {
                                                icommandlistener.addHelpCommandMessage("There\'s no item with id " + astring[2]);
                                            }
                                        } else {
                                            icommandlistener.addHelpCommandMessage("Can\'t find user " + s3);
                                        }
                                    } else if (s.toLowerCase().startsWith("time ")) {
                                        astring = s.split(" ");
                                        if (astring.length != 3) {
                                            return true; // CraftBukkit
                                        }

                                        s3 = astring[1];

                                        try {
                                            j = Integer.parseInt(astring[2]);
                                            WorldServer worldserver1;

                                            if ("add".equalsIgnoreCase(s3)) {
                                                for (k = 0; k < this.server.worlds.size(); ++k) { // CraftBukkit
                                                    worldserver1 = this.server.worlds.get(k); // CraftBukkit
                                                    worldserver1.setTime(worldserver1.getTime() + (long) j);
                                                }

                                                this.print(s1, "Added " + j + " to time");
                                            } else if ("set".equalsIgnoreCase(s3)) {
                                                for (k = 0; k < this.server.worlds.size(); ++k) { // CraftBukkit
                                                    worldserver1 = this.server.worlds.get(k); // CraftBukkit
                                                    worldserver1.setTime((long) j);
                                                }

                                                this.print(s1, "Set time to " + j);
                                            } else {
                                                icommandlistener.addHelpCommandMessage("Unknown method, use either \"add\" or \"set\"");
                                            }
                                        } catch (NumberFormatException numberformatexception1) {
                                            icommandlistener.addHelpCommandMessage("Unable to convert time value, " + astring[2]);
                                        }
                                    } else if (s.toLowerCase().startsWith("say ")) {
                                        s = s.substring(s.indexOf(" ")).trim();
                                        a.info("[" + s1 + "] " + s);
                                        serverconfigurationmanager.sendPacketToAllPlayers(new Packet3Chat("\u00A7d[Server] " + s));
                                    } else if (s.toLowerCase().startsWith("tell ")) {
                                        astring = s.split(" ");
                                        if (astring.length >= 3) {
                                            s = s.substring(s.indexOf(" ")).trim();
                                            s = s.substring(s.indexOf(" ")).trim();
                                            a.info("[" + s1 + "->" + astring[1] + "] " + s);
                                            s = "\u00A77" + s1 + " whispers " + s;
                                            a.info(s);
                                            if (!serverconfigurationmanager.sendPacketToPlayer(astring[1], (Packet) (new Packet3Chat(s)))) {
                                                icommandlistener.addHelpCommandMessage("There\'s no player by that name online.");
                                            }
                                        }
                                    } else if (s.toLowerCase().startsWith("whitelist ")) {
                                        this.handleWhitelist(s1, s, icommandlistener);
                                    } else {
                                        icommandlistener.addHelpCommandMessage("Unknown console command. Type \"help\" for help."); // CraftBukkit
                                        return false; // CraftBukkit
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            this.printHelp(icommandlistener);
        }

        return true; // CraftBukkit
    }

    private void handleWhitelist(String s, String s1, ICommandListener icommandlistener) {
        String[] astring = s1.split(" ");
        this.listener = icommandlistener; // CraftBukkit

        if (astring.length >= 2) {
            String s2 = astring[1].toLowerCase();

            if ("on".equals(s2)) {
                this.print(s, "Turned on white-listing");
                this.server.propertyManagerObj.b("white-list", true);
            } else if ("off".equals(s2)) {
                this.print(s, "Turned off white-listing");
                this.server.propertyManagerObj.b("white-list", false);
            } else if ("list".equals(s2)) {
                Set set = this.server.configManager.e();
                String s3 = "";

                String s4;

                for (Iterator iterator = set.iterator(); iterator.hasNext(); s3 = s3 + s4 + " ") {
                    s4 = (String) iterator.next();
                }

                icommandlistener.addHelpCommandMessage("White-listed players: " + s3);
            } else {
                String s5;

                if ("add".equals(s2) && astring.length == 3) {
                    s5 = astring[2].toLowerCase();
                    this.server.configManager.whitelistIP(s5);
                    this.print(s, "Added " + s5 + " to white-list");
                } else if ("remove".equals(s2) && astring.length == 3) {
                    s5 = astring[2].toLowerCase();
                    this.server.configManager.unwhitelistIP(s5);
                    this.print(s, "Removed " + s5 + " from white-list");
                } else if ("reload".equals(s2)) {
                    this.server.configManager.enableWhitelist();
                    this.print(s, "Reloaded white-list from file");
                }
            }
        }
    }

    private void printHelp(ICommandListener icommandlistener) {
        icommandlistener.addHelpCommandMessage("To run the server without a gui, start it like this:");
        icommandlistener.addHelpCommandMessage("   java -Xmx1024M -Xms1024M -jar minecraft_server.jar nogui");
        icommandlistener.addHelpCommandMessage("Console commands:");
        icommandlistener.addHelpCommandMessage("   help  or  ?               shows this message");
        icommandlistener.addHelpCommandMessage("   kick <player>             removes a player from the server");
        icommandlistener.addHelpCommandMessage("   ban <player>              bans a player from the server");
        icommandlistener.addHelpCommandMessage("   pardon <player>           pardons a banned player so that they can connect again");
        icommandlistener.addHelpCommandMessage("   ban-ip <ip>               bans an IP address from the server");
        icommandlistener.addHelpCommandMessage("   pardon-ip <ip>            pardons a banned IP address so that they can connect again");
        icommandlistener.addHelpCommandMessage("   op <player>               turns a player into an op");
        icommandlistener.addHelpCommandMessage("   deop <player>             removes op status from a player");
        icommandlistener.addHelpCommandMessage("   tp <player1> <player2>    moves one player to the same location as another player");
        icommandlistener.addHelpCommandMessage("   give <player> <id> [num]  gives a player a resource");
        icommandlistener.addHelpCommandMessage("   tell <player> <message>   sends a private message to a player");
        icommandlistener.addHelpCommandMessage("   stop                      gracefully stops the server");
        icommandlistener.addHelpCommandMessage("   save-all                  forces a server-wide level save");
        icommandlistener.addHelpCommandMessage("   save-off                  disables terrain saving (useful for backup scripts)");
        icommandlistener.addHelpCommandMessage("   save-on                   re-enables terrain saving");
        icommandlistener.addHelpCommandMessage("   list                      lists all currently connected players");
        icommandlistener.addHelpCommandMessage("   say <message>             broadcasts a message to all players");
        icommandlistener.addHelpCommandMessage("   time <add|set> <amount>   adds to or sets the world time (0-24000)");
    }

    private void print(String s, String s1) {
        String s2 = s + ": " + s1;

        // CraftBukkit start
        this.listener.addHelpCommandMessage(s1);
        this.informOps("\u00A77(" + s2 + ")");
        if (this.listener instanceof MinecraftServer) {
            return; // Already logged so don't call a.info()
        }
        // CraftBukkit end
        a.info(s2);
    }

    // CraftBukkit start
    private void informOps(String msg) {
        Packet3Chat packet3chat = new Packet3Chat(msg);
        EntityPlayer sender = null;
        if (this.listener instanceof ServerCommandListener) {
            org.bukkit.command.CommandSender commandSender = ((ServerCommandListener) this.listener).getSender();
            if (commandSender instanceof CraftPlayer) {
                sender = ((CraftPlayer) commandSender).getHandle();
            }
        }
        java.util.List<EntityPlayerMP> players = this.server.configManager.playerEntities;
        for (int i = 0; i < players.size(); ++i) {
            EntityPlayer entityPlayer = players.get(i);
            if (sender != entityPlayer && this.server.configManager.isOp(entityPlayer.username)) {
                entityPlayer.netServerHandler.sendPacket(packet3chat);
            }
        }
    }
    // CraftBukkit end

    private int a(String s, int i) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException numberformatexception) {
            return i;
        }
    }
}
//LilyBukkit end
