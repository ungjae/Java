package lab6a;
/**
 * <p>Title: Product Class</p>
 *
 * <p>Description: A Product object will store an Id, quantity, and price.
 * The equals method defined in the Object class is overridden here.</p>
 *
 * <p>Copyright: Copyright (c) 2018</p>
 *
 * @author A. Abreu
 * @version 1.0
 */
public class Product implements Comparable<Product>
{
    private String prodId;   //produce id is unique and used as the key-field
    private int quantity;
    private double price;

    /**
     * default constructor -- initializes the id, quantity, and price for the product
     * to an empty String, 0, and 0.0 respectively
     */
    public Product()
    {
        prodId = new String();
        quantity = 0;
        price = 0.0;
    }

    /**
     * parameterized constructor -- initializes id, quantity, and price to the
     * user-specified values
     * @param id to be stored in prodId
     * @param itemQuantity to be stored in quantity
     * @param itemPrice to be stored in price
     */
    public Product(String id, int itemQuantity, double itemPrice)
    {
        prodId = id;
        quantity = itemQuantity;
        price = itemPrice;
    }

    /**
     * setId method -- stores the user-specified value in prodId
     * @param id the id to be stored
     */
    public void setId(String id)
    {
      prodId = new String(id);
    }

    /**
     * setQuantity method -- stores the user-specified value in quantity
     * @param itemQuantity the quantity to be stored
     */
    public void setQuantity(int itemQuantity)
    {
        quantity = itemQuantity;
    }

    /**
     * setPrice method -- stores the user-specified value in price
     * @param itemPrice the price to be stored
     */
    public void setPrice(double itemPrice)
    {
        price = itemPrice;
    }

    /**
     * getId method -- gets the id
     * @return the product id
     */
    public String getId()
    {
      return new String(prodId);
    }

    /**
     * getQuantity method -- gets the quantity
     * @return the quantity
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * getPrice method -- gets the price
     * @return the price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * equals method -- determines if two Products have the same prodId
     * @param otherItem is a reference to a Product object
     * @return true if the two objects contain the same prodId; false otherwise
     */
    @Override
    public boolean equals(Object otherItem)
    {
        Product temp = (Product) otherItem;
        return (prodId.equals(temp.prodId));
    }

    /**
     * toString method -- creates and returns a String which represents the state of the object
     * @return a String containing the current values of prodId, quantity, and price
     */
    public String toString()
    {
        return "Product Id: " + prodId + " Quantity: " + quantity + " Price: " + price;
    }

	@Override
	public int compareTo(Product otherProduct) {
		int result = prodId.compareTo(otherProduct.prodId);
		return result;
	}
}
