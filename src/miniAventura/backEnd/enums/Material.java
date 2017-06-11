package miniAventura.backEnd.enums;
/**
 * Enumeraci�n del material del arma
 * @author d16genod
 *
 */
public enum Material {
	WOOD(3, 10), IRON(6, 30), STEEL(9, 60);
	
	private int addedDamage;
	private int price;
	
	Material(int valor, int price){
		setAddedDamage(valor);
		setPrice(price);
		
	}
	/**
	 * A�ade da�o a la base segun el material elegido
	 * @return
	 */
	public int getAddedDamage() {
		return addedDamage;
	}
	
	/**
	 * Setter del da�o a�adido segun el material
	 * @param addedDamage
	 */
	public void setAddedDamage(int addedDamage) {
		this.addedDamage = addedDamage;
	}
	
	/**
	 * devuelve el precio seg�n el tipo de material
	 * @return
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * Setter del precio seg�n el material.
	 * @param price
	 */
	private void setPrice(int price) {
		this.price = price;
	}
}
