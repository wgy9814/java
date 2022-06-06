public class StringToBasic {
    public static void main(String[] args) {
        //基本数据类型->string
        int n1 = 100;
        float f1 = 1.1F;
        double d1 = 4.5;
        boolean b1 = true;
        String s1 = n1 + "";
        String s2 = f1 + "";
        String s3 = d1 + "";
        String s4 = b1 + "";
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);


        String s5 = "123";
        int num1 = Integer.parseInt(s5);

        double num2 = Double.parseDouble(s5);

        float num3 = Float.parseFloat(s5);

        Long num4 = Long.parseLong(s5);

        Byte num5 = Byte.parseByte(s5);
        Boolean b = Boolean.parseBoolean("true");
        Short num6 = Short.parseShort(s5);


        // 使用基本数据类型对应的包装类的对应方法，得到基本的数据类型
        System.out.println("==============");
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);
        System.out.println(num4);
        System.out.println(num5);
        System.out.println(num6);
        System.out.println(b);

        //怎么把字符串转字符char-》 含义是指 把字符串的第一个字符得到
        //解读 s5.charAt(0) 得到 s5字符串的第一个字符 '1'
        System.out.println(s5.charAt(0));


    }
}
