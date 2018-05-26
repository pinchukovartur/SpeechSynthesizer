package TTS.controller;

import java.util.ArrayList;
import java.util.List;

public class PhonemeController {

    /**
     * Метод раскладывает предложение на фонемы
     *
     * @param text текст предложения
     * @return лист фонем
     */
    public static List<String> getPhonemes(String text) {
        List<String> phonemes = new ArrayList<>();
        String lowerString = " " + text.toLowerCase() + " ";

        for (int i = 0; i < lowerString.length(); i++) {

            String letter = lowerString.substring(i, i + 1);
            if (letter.equals("ь") || letter.equals("ъ")) {
                continue;
            }
            if (letter.equals(" ")) {
                phonemes.add(letter);
                continue;
            }

            String nextLitter = null;
            String prevLitter = null;

            if (i + 1 < lowerString.length()) {
                nextLitter = lowerString.substring(i + 1, i + 2);
            }
            if (i - 1 >= 0) {
                prevLitter = lowerString.substring(i - 1, i);
            }

            if (!isVowels(letter)) {
                if (letter.equals("ж")) {
                    phonemes.add("ж");
                } else if (letter.equals("ш")) {
                    phonemes.add("ш");
                } else if (letter.equals("ц")) {
                    phonemes.add("ц");
                } else if (letter.equals("ч")) {
                    phonemes.add("ч");
                } else if (letter.equals("щ")) {
                    phonemes.add("щ");
                } else {
                    if (nextLitter != null && isSoftness(nextLitter)) {
                        phonemes.add(letter + "'");
                    } else {
                        phonemes.add(letter);
                    }
                }
                continue;
            }

            if (!isIotovayaConsonant(letter)) {
                if (prevLitter != null && !isVowels(prevLitter)) {
                    phonemes.add("ы");
                } else {
                    phonemes.add(letter);
                }
                continue;
            }

            if (prevLitter != null && (isVowels(prevLitter) ||
                    (prevLitter).equals(" ") || (prevLitter).equals("ъ") || (prevLitter).equals("ь")
            )) {
                if (letter.equals("е")) {
                    phonemes.add("й");
                    phonemes.add("э");
                } else if (letter.equals("ё")) {
                    phonemes.add("й");
                    phonemes.add("о");
                } else if (letter.equals("ю")) {
                    phonemes.add("й");
                    phonemes.add("у");
                } else if (letter.equals("я")) {
                    phonemes.add("й");
                    phonemes.add("а");
                } else if (letter.equals("и")) {
                    phonemes.add("й");
                    phonemes.add("ы");
                }

            } else if (letter.equals("е")) {
                phonemes.add("э");
            } else if (letter.equals("ё")) {
                phonemes.add("о");
            } else if (letter.equals("ю")) {
                phonemes.add("у");
            } else if (letter.equals("я")) {
                phonemes.add("а");
            } else if (letter.equals("и")) {
                phonemes.add("ы");
            }
        }

        return phonemes;
    }

    /**
     * Является ли буква гласной
     *
     * @param letter буква
     * @return true, если является, иначе false
     */
    private static boolean isVowels(String letter) {
        return letter.equals("у") || letter.equals("ы") || letter.equals("а") ||
                letter.equals("о") || letter.equals("э") || isIotovayaConsonant(letter);

    }

    /**
     * Является ли буква смягчающей для буквы перед ней
     *
     * @param letter буква
     * @return true, если смягчает, иначе false
     */
    private static boolean isSoftness(String letter) {
        return isIotovayaConsonant(letter) || letter.equals("ь");
    }


    /**
     * Являеся ли буква йотовой (состовной из й и гласной)
     *
     * @param letter буква
     * @return true, если йотовая, иначе false
     */
    private static boolean isIotovayaConsonant(String letter) {
        return letter.equals("е") || letter.equals("я")
                || letter.equals("ю") || letter.equals("ё") || letter.equals("и");
    }
}
