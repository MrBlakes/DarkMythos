package me.botsko.mythos.spells;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SkeletonSummonerSpell extends SpellBase implements Spell {

	
	/**
	 * 
	 * @return
	 */
	public short getSpellId(){
		return 10;
	}
	
	
	/**
	 * Returns the weighting of the award
	 */
	public int getWeight(){
		return 4;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getAwardMessage(){
		return "You have discovered a magical spell: Skeleton Summoner!";
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getSpellUseMessage(){
		return "Used spell Skeleton Summoner! Spell books consumed.";
	}
	
	
	/**
	 * 
	 */
	public boolean getBlockBreakAward(BlockBreakEvent event){
		block = event.getBlock();
		if( block.getType() == Material.GRASS || block.getType() == Material.DIRT ){
			dropSpellBook();
			return true;	
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public boolean useSpellPlayerInteract(PlayerInteractEvent event, Player player){
		player.getWorld().spawnCreature(player.getLocation(), EntityType.SKELETON);
		MythosUtil.subtractFromHand( player );
		return true;
	}
}

