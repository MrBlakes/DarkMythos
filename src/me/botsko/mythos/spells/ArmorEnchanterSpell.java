package me.botsko.mythos.spells;

import java.util.Random;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ArmorEnchanterSpell extends SpellBase implements Spell {

	Random r = new Random();
	
	/**
	 * 
	 * @return
	 */
	@Override
	public short getSpellId(){
		return 14;
	}
	
	
	/**
	 * Returns the weighting of the award
	 */
	@Override
	public int getWeight(){
		return 80;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getAwardMessage(){
		return "You discovered a very rare spell: Hephaestus' spell of Armor Enchanter";
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getSpellUseMessage(){
		return "Used spell Armor enchanter! Spell book consumed.";
	}
	
	
	/**
	 * 
	 */
	@Override
	public boolean getBlockBreakAward(BlockBreakEvent event){
		
		block = event.getBlock();
		if( block.getType() == Material.GRAVEL || block.getType() == Material.LEAVES ){
			
			// Set item
			ItemStack i = new ItemStack(Material.BOOK, 1);
			i.setDurability( getSpellId() );
			
			// Drop the item
			block.getWorld().dropItemNaturally(block.getLocation(), i);
			
			// Boom!
			MythosUtil.awardThunder( block );

			return true;
			
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	@Override
	public boolean useSpellPlayerInteract(PlayerInteractEvent event){
		player.getInventory().getHelmet().addEnchantment(getRandomEnchantment("helmet"), 1);
		player.getInventory().getChestplate().addEnchantment(getRandomEnchantment("chestplate"), 1);
		player.getInventory().getLeggings().addEnchantment(getRandomEnchantment("leggings"), 1);
		player.getInventory().getBoots().addEnchantment(getRandomEnchantment("boots"), 1);
		return true;
	}
	public Enchantment getRandomEnchantment(String armorType){
		if (armorType == "helmet"){
			int ench = r.nextInt(7);
			if (ench == 0 || ench == 1)
				return null;
			if (ench == 2)
				return Enchantment.OXYGEN;
			if (ench == 3)
				return Enchantment.PROTECTION_ENVIRONMENTAL;
			if (ench == 4)
				return Enchantment.PROTECTION_EXPLOSIONS;
			if (ench == 5)
				return Enchantment.PROTECTION_FIRE;
			if (ench == 6)
				return Enchantment.PROTECTION_PROJECTILE;
			if (ench == 7)
				return Enchantment.WATER_WORKER;
		}  if (armorType == "chestplate" || armorType == "leggings"){
			int ench = r.nextInt(5);
			if (ench == 0 || ench == 1)
				return null;
			if (ench == 3)
				return Enchantment.PROTECTION_ENVIRONMENTAL;
			if (ench == 4)
				return Enchantment.PROTECTION_EXPLOSIONS;
			if (ench == 5)
				return Enchantment.PROTECTION_FIRE;
			if (ench == 2)
				return Enchantment.PROTECTION_PROJECTILE;
		}  if (armorType == "boots"){
			int ench = r.nextInt(6);
			if (ench == 0 || ench == 1)
				return null;
			if (ench == 2)
				return Enchantment.PROTECTION_ENVIRONMENTAL;
			if (ench == 3)
				return Enchantment.PROTECTION_EXPLOSIONS;
			if (ench == 4)
				return Enchantment.PROTECTION_FIRE;
			if (ench == 5)
				return Enchantment.PROTECTION_PROJECTILE;
			if (ench == 6)
				return Enchantment.PROTECTION_FALL;
		}
		return null;
		}
}