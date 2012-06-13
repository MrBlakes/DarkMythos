package me.botsko.mythos.listeners;

import me.botsko.mythos.Mythos;
import me.botsko.mythos.curses.CurseBase;
import me.botsko.mythos.curses.CurseChoice;
import me.botsko.mythos.directory.Directory;
import me.botsko.mythos.spells.SpellBase;
import me.botsko.mythos.spells.SpellChoice;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class MythosPlayerInteractEvent implements Listener {
	
	private Mythos plugin;
	private SpellChoice sc;
	private CurseChoice cc;
	private Directory dr;
	
	/**
	 * 
	 * @param plugin
	 */
	public MythosPlayerInteractEvent( Mythos plugin ){
		this.plugin = plugin;
		this.sc = new SpellChoice( plugin );
		this.cc = new CurseChoice( plugin );
		this.dr = new Directory();
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteractEvent(final PlayerInteractEvent event){
		
		Player player = event.getPlayer();
		
		// Ensure they're right-clicking
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			// Ensure they're using a book
			if(player.getItemInHand().getType() == Material.BOOK){
				
				// Use the durability to find the award id
				SpellBase spell = sc.chooseSpell( dr.getSpells( event.getClickedBlock() ), player.getItemInHand().getDurability() );
				if(spell != null){
					
					spell.setPlayer( player );
					
					// If the item is cursed, apply the curse and skip using it
					CurseBase curse = cc.chooseRandomCurse( dr.getCurses(), spell );
					if(curse == null){
					
						// Get the block break award
						if( spell.useSpellPlayerInteract(event) ){
						
							// Message the player
							player.sendMessage( plugin.playerMsg( spell.getSpellUseMessage() ));
							
						}
					} else {
						
						curse.applyCurse(player);
						
						// Tell them about the curse
						player.sendMessage( plugin.playerMsg( curse.getMessage() ));
						
					}
				}
			}
		}
	}
}
