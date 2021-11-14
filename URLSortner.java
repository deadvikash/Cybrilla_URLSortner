import java.util.*;

public class URLSortner {
    static HashMap<String, String> map = new HashMap<>();

    // If longUrl already has a corresponding shortUrl, return that shortUrl
    // If longUrl is new, create a new shortUrl for the longUrl and return it
    public static String registerNewUrl(String longUrl) {

        if (map.containsKey(longUrl))
            return map.get(longUrl);
        else {
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
            char[] s = new char[9];
            for (int i = 0; i < 9; i++) {
                int index = (int) (AlphaNumericString.length() * Math.random());
                s[i] = AlphaNumericString.charAt(index);
            }
            String str = String.valueOf(s);
            String shortUrl = "http://short.url/" + str;
            map.put(longUrl, shortUrl);
            return shortUrl;
        }
    }

    // If shortUrl is already present, return null
    // Else, register the specified shortUrl for the given longUrl
    public static void registerNewUrl(String longUrl, String shortUrl){
        Iterator hmIterator = map.entrySet().iterator();
        boolean check = false;
        while (hmIterator.hasNext()){
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            if(mapElement.getValue() == shortUrl ){
                check = true;
                break;
            }
        }
        if(check)
            return;
        else{
            map.put(longUrl, shortUrl);
        }

    }

    //THis will help to get the long URL, which is value from the sort URL which is Key
    public static <K, V> K getKey(Map<K, V> map, V value)
    {
        for (Map.Entry<K, V> entry: map.entrySet())
        {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    // If shortUrl doesn't have a corresponding longUrl, return null
    // Else, return the corresponding longUrl
    public static String getUrl(String shortUrl){
        if(getKey(map, shortUrl) != null){
            return getKey(map, shortUrl);
        }
        else
            return null;
    }

    


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            System.out.println("Type SortURL if you want to get the SortURL for the LongURL or  type LongURL " +
                    "if you want to get the LongURL for the SortURL : ");
            if (sc.nextLine() == "LongURL") {
                System.out.println("Please enter the SortURL: ");
                String sortUrl = sc.nextLine();
                System.out.println("The corresponding LongURL is: " + getUrl(sortUrl));
            } else {
                System.out.println("Enter the long URL: ");
                String longUrl = sc.nextLine();
                if (map.containsKey(longUrl)) {
                    System.out.println("URL already have a sort URL, which is: " + map.get(longUrl));
                } else {
                    System.out.println("Do you want to register the sort URL yourself ? : Yes or No");
                    if (sc.nextLine() == "Yes") {
                        System.out.println("Enter teh corresponding sort URL: ");
                        String sortUrl = sc.nextLine();
                        registerNewUrl(longUrl, sortUrl);
                        System.out.println("LongUrl : " + longUrl + " is register with " + sortUrl);
                    } else {
                        System.out.println("Your SortURL for the: " + longUrl + " is : " + registerNewUrl(longUrl));
                    }
                }
            }
        

    }
}
