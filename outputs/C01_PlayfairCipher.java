package com.thealgorithms.ciphers;

// Encrypts and decrypts character pairs using a 5-by-5 key matrix.
public class PlayfairCipher {

    private char[][] matrix;
    private String key;

    public PlayfairCipher(String key) {
        this.key = key;
        generateMatrix();
    }

    public String encrypt(String plaintext) {
        // Replace uppercase J before normalizing and preparing the text.
        plaintext = prepareText(plaintext.replace("J", "I"));
        StringBuilder ciphertext = new StringBuilder();

        // Process the prepared text in pairs and locate each character in the matrix.
        for (int i = 0; i < plaintext.length(); i += 2) {
            char char1 = plaintext.charAt(i);
            char char2 = plaintext.charAt(i + 1);
            int[] pos1 = findPosition(char1);
            int[] pos2 = findPosition(char2);
            int row1 = pos1[0];
            int col1 = pos1[1];
            int row2 = pos2[0];
            int col2 = pos2[1];
            if (row1 == row2) {
                // Same row: move each character one column right, wrapping at the edge.
                ciphertext.append(matrix[row1][(col1 + 1) % 5]);
                ciphertext.append(matrix[row2][(col2 + 1) % 5]);
            } else if (col1 == col2) {
                // Same column: move each character one row down, wrapping at the edge.
                ciphertext.append(matrix[(row1 + 1) % 5][col1]);
                ciphertext.append(matrix[(row2 + 1) % 5][col2]);
            } else {
                // Different rows and columns: keep each row and exchange the columns.
                ciphertext.append(matrix[row1][col2]);
                ciphertext.append(matrix[row2][col1]);
            }
        }
        return ciphertext.toString();
    }

    // Expects an even number of characters; no input normalization is performed.
    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 2) {
            char char1 = ciphertext.charAt(i);
            char char2 = ciphertext.charAt(i + 1);
            int[] pos1 = findPosition(char1);
            int[] pos2 = findPosition(char2);
            int row1 = pos1[0];
            int col1 = pos1[1];
            int row2 = pos2[0];
            int col2 = pos2[1];
            if (row1 == row2) {
                // Adding 4 modulo 5 moves one column left with wraparound.
                plaintext.append(matrix[row1][(col1 + 4) % 5]);
                plaintext.append(matrix[row2][(col2 + 4) % 5]);
            } else if (col1 == col2) {
                // Move one row up with wraparound to reverse the encryption shift.
                plaintext.append(matrix[(row1 + 4) % 5][col1]);
                plaintext.append(matrix[(row2 + 4) % 5][col2]);
            } else {
                // Exchanging columns also reverses the rectangle substitution.
                plaintext.append(matrix[row1][col2]);
                plaintext.append(matrix[row2][col1]);
            }
        }
        // Any X characters inserted during preparation remain in the result.
        return plaintext.toString();
    }

    private void generateMatrix() {
        // Preserve unique key characters first, then append unused alphabet characters.
        // The appended alphabet omits J; the key itself is not normalized.
        String keyWithoutDuplicates = removeDuplicateChars(key + "ABCDEFGHIKLMNOPQRSTUVWXYZ");
        matrix = new char[5][5];
        int index = 0;

        // Fill the matrix row by row using the first 25 unique characters.
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = keyWithoutDuplicates.charAt(index);
                index++;
            }
        }
    }

    // Retain only the first occurrence of each character, preserving its order.
    private String removeDuplicateChars(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (result.indexOf(String.valueOf(str.charAt(i))) == -1) {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    private String prepareText(String text) {
        // Convert to uppercase and retain only letters A through Z.
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder preparedText = new StringBuilder();
        char prevChar = '\0';
        for (char c : text.toCharArray()) {
            if (c != prevChar) {
                preparedText.append(c);
                prevChar = c;
            } else {
                // Insert X before a repeated tracked character, then reset tracking.
                preparedText.append('X').append(c);
                prevChar = '\0';
            }
        }

        // Pad odd-length text so encryption can process complete pairs.
        if (preparedText.length() % 2 != 0) {
            preparedText.append('X');
        }
        return preparedText.toString();
    }

    // Return the matching row and column, or the default [0, 0] if not found.
    private int[] findPosition(char c) {
        int[] pos = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }

    public void printMatrix() {
        System.out.println("\nPlayfair Cipher Matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}