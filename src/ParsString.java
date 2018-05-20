import java.util.ArrayList;
import java.util.List;

class ParsString {

    ParsString() {
    }


    public List<String> getFonems(String baseString) {

        String nBaseString = " " + baseString.toLowerCase()+" ";

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < nBaseString.length(); i++) {
            String elem = Character.toString(nBaseString.charAt(i));
            if(elem.equals("ь") || elem.equals("ъ")){
                continue;
            }
            if (elem.equals(" ")) {
                result.add(elem);
            } else {
                if (!checkConsonant(elem)) {
                    if(elem.equals("ж")){
                        result.add("ж");
                        continue;
                    }else{
                        if(elem.equals("ш")){
                            result.add("ш");
                            continue;
                        }else{
                            if(elem.equals("ц")){
                                result.add("ц");
                                continue;
                            }else{
                                if(elem.equals("ч")){
                                    result.add("ч");
                                    continue;
                                }else{
                                    if(elem.equals("щ")){
                                        result.add("щ");
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    try {
                        if (checkSoft(Character.toString(nBaseString.charAt(i + 1)))) {
                            result.add(elem + "'");
                        } else {
                            result.add(elem);
                        }
                    } catch (Exception ex) {
                    }
                } else {
                    try {
                        if (checkIotovaia(elem)) {
                            if ((checkConsonant(Character.toString(nBaseString.charAt(i - 1))) ||
                                    (Character.toString(nBaseString.charAt(i - 1))).equals(" ") ||
                                    (Character.toString(nBaseString.charAt(i - 1))).equals("ъ") ||
                                    (Character.toString(nBaseString.charAt(i - 1))).equals("ь")
                            )) {
                                if (elem.equals("е")) {
                                    result.add("й");
                                    result.add("э");
                                } else {
                                    if (elem.equals("ё")) {
                                        result.add("й");
                                        result.add("о");
                                    } else {
                                        if (elem.equals("ю")) {
                                            result.add("й");
                                            result.add("у");
                                        } else {
                                            if (elem.equals("я")) {
                                                result.add("й");
                                                result.add("а");
                                            }
                                            else {
                                                if (elem.equals("и")) {
                                                    result.add("й");
                                                    result.add("ы");
                                                }
                                            }
                                        }
                                    }
                                }
                            }else{
                                if (elem.equals("е")) {
                                    result.add("э");
                                } else {
                                    if (elem.equals("ё")) {
                                        result.add("о");
                                    } else {
                                        if (elem.equals("ю")) {
                                            result.add("у");
                                        } else {
                                            if (elem.equals("я")) {
                                                result.add("а");
                                            }
                                            else {
                                                if (elem.equals("и")) {
                                                    result.add("ы");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }else{
                            if((!checkConsonant(Character.toString(nBaseString.charAt(i - 1))) ||
                                    ((Character.toString(nBaseString.charAt(i - 1))).equals(" "))) && elem.equals("и")
                                    ){
                                result.add("ы");
                            }
                            else{
                                result.add(elem);
                            }
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        }
        //

        return result;
}

    private boolean checkConsonant(String consonant) {
        if (consonant.length() > 1) {
            System.err.println("consonant length > 1");
            return false;
        }
        if (consonant.equals("у") || consonant.equals("е") || consonant.equals("ы") || consonant.equals("а") ||
                consonant.equals("о") || consonant.equals("э") || consonant.equals("я") || consonant.equals("и")
                || consonant.equals("ю") || consonant.equals("ё")) {
            return true;
        } else return false;
    }

    private boolean checkSoft(String consonant) {
        if (consonant.length() > 1) {
            System.err.println("consonant length > 1");
            return false;
        }
        if (consonant.equals("е") || consonant.equals("я") || consonant.equals("и")
                || consonant.equals("ю") || consonant.equals("ё") || consonant.equals("ь")) {
            return true;
        } else return false;
    }

    private boolean checkIotovaia(String consonant) {
        if (consonant.length() > 1) {
            System.err.println("consonant length > 1");
            return false;
        }
        if (consonant.equals("е") || consonant.equals("я")
                || consonant.equals("ю") || consonant.equals("ё") || consonant.equals("и")) {
            return true;
        } else return false;
    }
}
