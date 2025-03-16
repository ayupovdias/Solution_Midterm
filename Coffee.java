
import java.util.Scanner;
public interface Coffee {
    int getCost();
    String getDescription();
}
class Espresso implements Coffee{

    public int getCost(){
        return 650;
    }
    public String getDescription(){
        return "Espresso: hot water, coffee beans";
    }
}
class Cappuccino implements Coffee{

    public int getCost(){
        return 700;
    }
    public String getDescription(){
        return "Cappuccino: hot water, coffee beans, milk, milk foam";
    }
}
class Latte implements Coffee{
    public int getCost(){
        return 770;
    }
    public String getDescription(){
        return "Latte: hot water, coffee beans, milk";
    }
}
class Americano implements Coffee{
    public int getCost(){
        return 800;
    }
    public String getDescription(){
        return "Americano: hot water, coffee beans";
    }
}
class Raf implements Coffee{
    public int getCost(){
        return 590;
    }
    public String getDescription(){
        return "Raf: hot water, coffee beans, cream, sugar";
    }
}
class CoffeeFactory{
    public Coffee createCoffee(String type){
        switch(type.toLowerCase()){
            case "espresso":
                return new Espresso();
            case "cappuccino":
                return new Cappuccino();
            case "latte":
                return new Latte();
            case "americano":
                return new Americano();
            case "raf":
                return new Raf();
            default:
                throw new IllegalArgumentException("There is no such coffee in the menu");
        }
    }
}

class Main{
    public static void main(String[] args){
        System.out.println("You can choose one type of coffee espresso, cappuccino, latte, americano or raf");
        System.out.println("What will you choose?");
        Scanner scanner=new Scanner(System.in);
        String sort=scanner.next();
        CoffeeFactory factory=new CoffeeFactory();
        Coffee coffee=factory.createCoffee(sort);
        System.out.println("You did a order");
        while(true){
            System.out.println(coffee.getDescription());
            System.out.println("Cost:"+coffee.getCost());
            System.out.println("Do you want to add one ingredient in your coffee?. You can answer yes or no");
            String question=scanner.next();
            scanner.nextLine();
            if(question.toLowerCase().equals("no")){
                System.out.println("You answered no");
                break;
            }
            else if(question.toLowerCase().equals("yes")) {
                System.out.println("You answered yes. You can add one of these ingredients milk, caramel syrup, whipped cream, chocolate syrup or sugar");

                System.out.println("What will you choose?");
                String ingredient = scanner.nextLine().trim();

                switch (ingredient.toLowerCase()) {
                    case "milk":
                        coffee = new Milk(coffee);
                        break;
                    case "caramel syrup":
                        coffee = new CaramelSyrup(coffee);
                        break;
                    case "whipped cream":
                        coffee = new WhippedCream(coffee);
                        break;
                    case "chocolate syrup":
                        coffee = new ChocolateSyrup(coffee);
                        break;
                    case "sugar":
                        coffee = new Sugar(coffee);
                        break;
                    default:
                        System.out.println("You wrote an ingredient incorrectly. Try again");
                        break;
                }
            }
            else{
                System.out.println("You can answer only yes or no. Try again");
            }
        }

        System.out.println("Your coffee");
        System.out.println(coffee.getDescription());
        System.out.println("Cost:"+coffee.getCost());
    }
}
class CoffeeDecorator implements Coffee{
    Coffee coffee;
    public CoffeeDecorator(Coffee coffee){
        this.coffee=coffee;
    }
    public int getCost(){
        return coffee.getCost();
    }
    public String getDescription(){
        return coffee.getDescription();
    }
}
class Milk extends CoffeeDecorator{
    public Milk(Coffee coffee){
        super(coffee);
    }
    public int getCost(){
        return super.getCost()+80;
    }
    public String getDescription(){
        return super.getDescription()+", milk";
    }
}
class CaramelSyrup extends CoffeeDecorator{
    public CaramelSyrup(Coffee coffee){
        super(coffee);
    }
    public int getCost(){
        return super.getCost()+75;
    }
    public String getDescription(){
        return super.getDescription()+", caramel syrup";
    }
}
class WhippedCream extends CoffeeDecorator{
    public WhippedCream(Coffee coffee){
        super(coffee);
    }
    public int getCost(){
        return super.getCost()+70;
    }
    public String getDescription(){
        return super.getDescription()+", whipped cream";
    }
}
class ChocolateSyrup extends CoffeeDecorator{
    public ChocolateSyrup(Coffee coffee){
        super(coffee);
    }
    public int getCost(){
        return super.getCost()+60;
    }
    public String getDescription(){
        return super.getDescription()+", chocolate syrup";
    }
}
class Sugar extends CoffeeDecorator{
    public Sugar(Coffee coffee){
        super(coffee);
    }
    public int getCost(){
        return super.getCost()+50;
    }
    public String getDescription(){
        return super.getDescription()+", sugar";
    }
}
