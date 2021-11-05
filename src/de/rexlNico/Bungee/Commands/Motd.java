package de.rexlNico.Bungee.Commands;

import java.io.File;
import java.io.IOException;

import javax.annotation.Generated;

import de.rexlNico.Bungee.Main.Main;
import de.rexlNico.Bungee.Methodes.Var;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.conf.YamlConfig;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Motd extends Command{

	private Main plugin;

	public Motd(String name) {
		super(name);
	}

	
	
	@Override
    public void execute(final CommandSender sender, final String[] args) {
		this.plugin = Main.getPlugin();
        if (args.length > 1) {
            if (args[0].equals("1")) {
                if (sender.hasPermission(Var.perms+"motd")) {
                    return;
                }
                
                String motd = "";
                for (int i = 1; i < args.length; ++i) {
                    final String arg = String.valueOf(args[i]) + " ";
                    motd = String.valueOf(motd) + arg;
                }
                
                this.plugin.getConfig().set("motd.l1", motd);
                this.plugin.saveConfig();
                motd = ChatColor.translateAlternateColorCodes('&', motd);
                sender.sendMessage("Die MOTD Line 1 ist nun:" +motd);
                return;
            }else if (args[0].equals("2")) {
                if (sender.hasPermission(Var.perms+"motd")) {
                    return;
                }
                
                String motd = "";
                for (int i = 1; i < args.length; ++i) {
                    String arg = String.valueOf(args[i]) + " ";
                    motd = String.valueOf(motd) + arg;
                }
                
                this.plugin.getConfig().set("motd.l2", motd);
                this.plugin.saveConfig();
                sender.sendMessage("§4Die MOTD Line 2 ist nun§7:§r" +motd.replaceAll("&", "§"));
                return;
            }
        }
        sender.sendMessage(ChatColor.RED + "§4/motd §e<1/2>");
    }

}
