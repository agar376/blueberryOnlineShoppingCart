
public class shopping_item 
{
    public int item_code;
    public String item_name;
    public double price;
    public int qty;
    public shopping_item(int i,String s,double d,int q)
    {
        item_code=i;
        item_name=s;
        price=d;
        qty=q;
    }

   
    public int getItem_code() {
        return item_code;
    }

    
    public void setItem_code(int item_code) {
        this.item_code = item_code;
    }

    
    public String getItem_name() {
        return item_name;
    }

    
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    
    public double getPrice() {
        return price;
    }

    
    public void setPrice(double price) {
        this.price = price;
    }

    
    public int getQty() {
        return qty;
    }

    
    public void setQty(int qty) {
        this.qty = qty;
    }
    
    @Override
    public String toString()
    {
                
        return ("<td>"+item_code+"</td><td>"+item_name+"</td><td>"+qty+"<td>"+price+"</td>"+"<td>"+(qty*price)+"</td>");
    }

    
}
