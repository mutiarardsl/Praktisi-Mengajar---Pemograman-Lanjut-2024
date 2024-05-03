public class genericMain {
    public static void main(String[] args) {
        generic<String> stringgeneric = new generic<>();
        stringgeneric.add("tiara");
        System.out.println(stringgeneric.get());
    
        generic<Integer> integergeneric = new generic<>();
        integergeneric.add(23);
        System.out.println(integergeneric.get());
      }
}
