package de.rexlNico.Bungee.Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.common.io.ByteStreams;

import de.rexlNico.Bungee.Commands.Hub;
import de.rexlNico.Bungee.Commands.Motd;
import net.md_5.bungee.config.*;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

public class Main extends Plugin implements Listener{
	private static Main plugin;
	   private Configuration config;
	    Favicon favicon;
	    
	    public void onEnable() {
	    	plugin = this;
	        this.saveDefaultConfig();
	        this.reloadConfig();
	        this.getProxy().getPluginManager().registerListener((Plugin)this, (Listener)this);
	        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new Motd("motd"));
	        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new Hub("hub"));
	    }
	    
	    public static Main getPlugin() {
			return plugin;
		}
	    
	    public Configuration getConfig() {
	        return this.config;
	    }
	    
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		public void reloadConfig() {
	        try {
	            this.config = ConfigurationProvider.getProvider((Class)YamlConfiguration.class).load(new File(this.getDataFolder(), "config.yml"));
	        }
	        catch (IOException e) {
	            throw new RuntimeException("Unable to load configuration", e);
	        }
	        final File file = new File(this.getDataFolder(), "server-icon.png");
	        if (file.exists()) {
	            try {
	                final BufferedImage bufferedImage = ImageIO.read(file);
	                this.favicon = Favicon.create(bufferedImage);
	            }
	            catch (IOException e2) {
	                this.getLogger().warning("Favicon file is invalid or missing.");
	            }
	        }
	    }
	    
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		public void saveConfig() {
	        try {
	            ConfigurationProvider.getProvider((Class)YamlConfiguration.class).save(this.getConfig(), new File(this.getDataFolder(), "config.yml"));
	        }
	        catch (IOException e) {
	            throw new RuntimeException("Unable to save configuration", e);
	        }
	    }
	    
	    private void saveDefaultConfig() {
	        if (!this.getDataFolder().exists()) {
	            this.getDataFolder().mkdir();
	        }
	        final File configFile = new File(this.getDataFolder(), "config.yml");
	        if (!configFile.exists()) {
	            try {
	                configFile.createNewFile();
	                Throwable t = null;
	                try {
	                    final InputStream is = this.getResourceAsStream("config.yml");
	                    try {
	                        final OutputStream os = new FileOutputStream(configFile);
	                        try {
	                            ByteStreams.copy(is, os);
	                        }
	                        finally {
	                            if (os != null) {
	                                os.close();
	                            }
	                        }
	                        if (is != null) {
	                            is.close();
	                        }
	                    }
	                    finally {
	                        if (t == null) {
	                            final Throwable t2 = null;
	                            t = t2;
	                        }
	                        else {
	                            final Throwable t2 = null;
	                            if (t != t2) {
	                                t.addSuppressed(t2);
	                            }
	                        }
	                        if (is != null) {
	                            is.close();
	                        }
	                    }
	                }
	                finally {
	                    if (t == null) {
	                        final Throwable t3 = null;
	                        t = t3;
	                    }
	                    else {
	                        final Throwable t3 = null;
	                        if (t != t3) {
	                            t.addSuppressed(t3);
	                        }
	                    }
	                }
	            }
	            catch (IOException e) {
	                throw new RuntimeException("Unable to create configuration file", e);
	            }
	        }
	    }
	    
	    @EventHandler(priority = 64)
	    public void onServerListPing(final ProxyPingEvent event) {
	    	this.reloadConfig();
	        if (this.getConfig().getString("motd") != null) {
	            final ServerPing ping = event.getResponse();
	            String motd1 = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("motd.l1"));
	            String motd2 = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("motd.l2"));
	            ping.setDescription(motd1+"\n" +motd2);
	            if (this.favicon != null) {
	                ping.setFavicon(this.favicon);
	            }
	            event.setResponse(ping);
	        }
	    }
}
