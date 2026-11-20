package com.thealgorithms.maths;

public final class ComplexNumberMultiply {

    // Prevents instantiation of this utility class.
    private ComplexNumberMultiply() {
    }

    // Parses a complex number into its real and imaginary integer components.
    private static int[] parse(String num) {
        // Requires the format "a+bi"; either integer may be negative, as in "3+-2i".
        if (num == null || !num.matches("-?\\d+\\+-?\\d+i")) {
            throw new IllegalArgumentException("Invalid complex number format: " + num);
        }

        String[] parts = num.split("\\+");
        int real = Integer.parseInt(parts[0]);
        int imaginary = Integer.parseInt(parts[1].replace("i", ""));
        return new int[] {real, imaginary};
    }

    public static String multiply(String num1, String num2) {
        int[] a = parse(num1);
        int[] b = parse(num2);

        // Applies (a + bi)(c + di) = (ac - bd) + (ad + bc)i, using i² = -1.
        int real = a[0] * b[0] - a[1] * b[1];
        int imaginary = a[0] * b[1] + a[1] * b[0];

        // Preserves the "a+bi" format, including "+-" for a negative imaginary part.
        return real + "+" + imaginary + "i";
    }
}