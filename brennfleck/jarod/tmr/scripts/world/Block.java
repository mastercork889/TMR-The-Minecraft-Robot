package brennfleck.jarod.tmr.scripts.world;

import net.minecraft.block.Block.SoundType;
import brennfleck.jarod.pathing.AStar;

/**
 * Holds data for a block.
 * 
 * @author Jarod Brennfleck
 */
public class Block {
	private net.minecraft.block.Block actualBlock;
	private int idNum;
	private String name;
	private float slipperiness;
	private SoundType stepSound;
	private boolean canProvidePower;
	private boolean isCollidable;
	private boolean isNormalCube;
	private Material material;
	private int metadata;
	
	/**
	 * Constructs a block object buy it's ID.
	 */
	public Block(int id) {
		this(net.minecraft.block.Block.getBlockById(id), 0);
	}
	
	/**
	 * Constructs a block object by it's ID and metadata.
	 */
	public Block(int id, int metadata) {
		this(net.minecraft.block.Block.getBlockById(id), metadata);
	}
	
	public Block(Location l) {
		this(World.getRealWorld().getBlock((int) l.getX(), (int) l.getY(), (int) l.getZ()), World.getRealWorld().getBlockMetadata((int) l.getX(), (int) l.getY(), (int) l.getZ()));
	}
	
	private Block(net.minecraft.block.Block block, int metadata) {
		this.idNum = net.minecraft.block.Block.getIdFromBlock(block);
		this.name = block.getLocalizedName();
		this.slipperiness = block.slipperiness;
		this.stepSound = block.stepSound;
		this.canProvidePower = block.canProvidePower();
		this.isCollidable = !AStar.canBlockBeWalkedThrough(this.idNum);
		this.isNormalCube = block.isBlockNormalCube();
		this.material = Material.getMaterial(block.getMaterial());
		this.metadata = metadata;
	}
	
	/**
	 * Returns the best tool that can harvetst this block.
	 */
//	public ItemType getBestTool() {
//		if(material.isAnyOf(Material.IRON, Material.ANVIL, Material.ROCK)) return ItemType.PICKAXE;
//		if(material.isAnyOf(Material.GRASS, Material.GROUND, Material.SAND, Material.SNOW)) return ItemType.SHOVEL;
//		return ItemType.OTHER;
//	}
	
	/**
	 * Returns the id of the block.
	 */
	public int getID() {
		return this.idNum;
	}
	
	/**
	 * Returns the metadata of the block.
	 */
	public int getMetadata() {
		return this.metadata;
	}
	
	/**
	 * Returns whether or not this block is collidable.
	 */
	public boolean isCollidable() {
		return this.isCollidable;
	}
	
	/**
	 * Returns the String of this object. <br>
	 * EG: "id=0, name=air, collides=false"
	 */
	public String toString() {
		return "id=" + idNum + ", name=" + name + ", collides=" + isCollidable;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Stores the data for all block materials.
	 * 
	 * @author Jarod Brennfleck
	 */
	public enum Material {
		AIR(net.minecraft.block.material.Material.air),
		IRON(net.minecraft.block.material.Material.iron),
		ICE(net.minecraft.block.material.Material.ice),
		ANVIL(net.minecraft.block.material.Material.anvil),
		CARPET(net.minecraft.block.material.Material.carpet),
		CIRCUITS(net.minecraft.block.material.Material.circuits),
		GRASS(net.minecraft.block.material.Material.grass),
		GROUND(net.minecraft.block.material.Material.ground),
		GLASS(net.minecraft.block.material.Material.glass),
		WOOD(net.minecraft.block.material.Material.wood),
		ROCK(net.minecraft.block.material.Material.rock),
		SAND(net.minecraft.block.material.Material.sand),
		SNOW(net.minecraft.block.material.Material.snow);
		private net.minecraft.block.material.Material material;
		
		Material(net.minecraft.block.material.Material material) {
			this.material = material;
		}
		
		/**
		 * Finds out whether this material is in the list of materials passed.
		 */
		public boolean isAnyOf(Material... mat) {
			for(Material m : mat)
				if(m == this) return true;
			return false;
		}
		
		/**
		 * Returns the material based off of the Minecraft material passed.
		 */
		public static Material getMaterial(net.minecraft.block.material.Material material) {
			for(Material mat : Material.values()) {
				if(mat.material == material) {
					return mat;
				}
				if(material == net.minecraft.block.material.Material.craftedSnow) return SNOW;
			}
			return Material.AIR;
		}
	}
}