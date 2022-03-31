import java.util.Random;

/**
 *
 */
public class boosterPull
{
    public static void main(String[] args)
    {
        Random rand = new Random();

        for (int i = 0; i <= 9; i++)
        {
            System.out.println(i);
            if (i < 9)
            {
                int number = rand.nextInt(8);
                System.out.println("Common");
            }
            if (i == 9)
            {
                int number = rand.nextInt(12);
                System.out.println(number);
                if (number < 5 || number > 5 && number < 10)
                {
                    System.out.println("Rare");
                }
                if (number == 5 || number == 10)
                {
                    System.out.println("Super Rare");
                }
                if (number == 11)
                {
                    System.out.println("Ultra Rare");
                }
            }
        }
    }
}
