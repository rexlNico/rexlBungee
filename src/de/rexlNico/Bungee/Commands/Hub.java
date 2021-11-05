package de.rexlNico.Bungee.Commands;

import de.rexlNico.Bungee.Methodes.Var;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Hub extends Command{

	public Hub(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer){
			ProxiedPlayer p = (ProxiedPlayer)sender;
				p.connect(BungeeCord.getInstance().getServerInfo("Lobby-01"));
				p.sendMessage("§cVerbinden...");
					
		}else{
			sender.sendMessage(Var.console);
		}
	}


}
