public class Main {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("12345679");
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("12345679");
        System.out.println(stringBuilder.hashCode());
        System.out.println(stringBuilder1.hashCode());
    }
}
