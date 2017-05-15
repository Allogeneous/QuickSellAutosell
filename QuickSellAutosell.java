/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksellautosell;

import me.mrCookieSlime.QuickSell.Shop;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class QuickSellAutosell extends JavaPlugin{
     private static boolean hasPlugin = true;
     
    @Override
    public void onEnable(){
        try{
            Bukkit.getPluginManager().getPlugin("QuickSell");
        }catch(Exception e){
            e.printStackTrace();
            hasPlugin = false;
            System.out.println("[QuickSellAutosell] Could not find plugin, \"QuickSell\", disabled.");
        }
        
        if(hasPlugin){
            System.out.println("[QuickSellAutosell] Hooked into \"QuickSell\", enabled.");
        }
    }
    
    @Override
    public void onDisable(){
        System.out.println("[QuickSellAutosell] disabled.");
    }

      @Override
      public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
          if(commandLabel.equalsIgnoreCase("qsautosell") && hasPlugin){
                if(sender instanceof Player){
                    Player p = (Player) sender;
                        if(p.hasPermission("qsas.canUse")){
                            if(args.length == 0 || args.length > 1){
                                p.sendMessage(ChatColor.RED + "[QuickSellAutosell] Incorrec useage, do /qsautosell [shop]");
                            }else{
                                try{
                                    Shop s = Shop.getShop(args[0]);
                                    if(s.hasUnlocked(p)){
                                        s.sellall((Player) sender, "");
                                    }else{
                                         p.sendMessage(ChatColor.RED + "[QuickSellAutosell] You have not unlocked this shop yet!");
                                    }
                                }catch(NullPointerException e){
                                    p.sendMessage(ChatColor.RED + "[QuickSellAutosell] Please enter a valid shop name!");
                                }
                                
                            }
                                
                        }else{
                            p.sendMessage(ChatColor.RED + "[QuickSellAutosell] You don't have premission to do this!");
                        }
                }  
            }     
          return true;
      }
  
    
}
