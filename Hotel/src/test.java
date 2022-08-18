enum Herbivores{  
Deer,Elephants,Horse,Sheep,Cow;  
    }  
enum Carnivores{  
Lion,Leopard,Wolf,lizard;  
    }  
enum Omnivores{  
Bear,Dog,Cat;  
    }  
public class test {  
public static void main(String[] args) {  
       Herbivores Cow =Herbivores.Cow;  
       Carnivores Leopard = Carnivores.Leopard;  
       Omnivores Bear = Omnivores.Bear;  
System.out.println("1. "+Cow.name()+" is in "+ Cow.getClass()+" enum class");  
//returns the class object corresponding to the enum constant Wolf  
System.out.println("2. "+Leopard.name()+" is in "+ Leopard.getClass()+" enum class");  
//returns the class object corresponding to the enum constant Bear  
System.out.println("3. "+Bear.name()+" is in "+ Bear.getClass()+" enum class");  
    }  
}  