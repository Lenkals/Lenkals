import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class A2_VThreadPThreadIOOperation {
    public static HashMap<String, List<String>> fetch(String filePath) {
        System.out.println(">>>> File Name" + filePath);
        var map = new HashMap<String, List<String>>();
        try {

            // I/O operation

            Stream<String> s = Files.lines(Paths.get(filePath));
            String cpfNumber = null;
            String tmpCpfNumber = null;
            String empName = null;
            String fullname = "test";
            String mapKey = "test";
            List list = null;
            int counter = -1;
            for (String line : s.toList()) {
        /*        if(cpfNumber!=null||!tmpCpfNumber.equalsIgnoreCase(cpfNumber)){
                    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                        String key = entry.getKey();
                        if(mapKey.equals(key)){
                            var list = entry.getValue();
                        }
                    }
                }*/
                //  System.out.println("LIne -->"+orgLine);
                String str[] = line.split("\\!");
                if (line.startsWith("Name:")) {
                    if (!"test".equals(mapKey)) {
                        if(mapKey==null)
                        System.out.println(">>>>>> GRRRRRRRRRRRRRRRRRRRRRR");
                        map.put(mapKey, list);
                    }
                    cpfNumber = str[0];
                    // System.out.println("---> " + str[0]);
                } else if (line.startsWith("Office:")) {
                    counter = -1;
                    empName = str[1];
                    mapKey = cpfNumber + empName;
                    // System.out.println("---> " + str[1]);
                } else {
                    // System.out.println(">>>>"+str.length);
                    if (str.length == 9) {
                        String numberme = str[2].split("\\s+")[1];
                        if (numberme.equals("Member's") || numberme.equals("Compulsory") || numberme.equals("Rs.")) {
                            continue;
                        }
                        // System.out.println(str[0]+"-"+str[2]);
                        // System.out.println(str[0]+"-"+numberme+"-"+Math.round(Math.round(Double.parseDouble(numberme))/.12));
                        if (str[0].contains("PF")) {
                            continue;
                        }
                        if (counter == -1) {
                            list = new ArrayList<String>();
                            counter++;
                            //mapKey
                        } else {
                            counter++;
                        }
                        // System.out.println(AddOneMonthLocale.main(str[0]) + "-" + numberme + "-" + Math.round(Math.round(Double.parseDouble(numberme)) / .12));
                        String rec = AddOneMonthLocale.main(str[0]) + " | " + numberme + " | " + Math.round(Math.round(Double.parseDouble(numberme)) / .12);
                        //System.out.println("--->"+Math.round(Double.parseDouble(numberme)));

                        // System.out.println("tt "+tt[1]);
                        list.add(rec);

                    }
                }
            }
            if(mapKey==null) {
                System.out.println(">>>>>> GRRRRRRRRRRRRRRRRRRRRRR BBBBBBBBBBB"+list);
            }else{map.put(mapKey, list);}
        } catch (Exception e) {
            System.out.println(e);

        } finally {

        }

        return map;
    }

    public static void main(String[] args) {

        //A2_VThreadPThreadIOOperation.single();
        A2_VThreadPThreadIOOperation.allmap();


        System.out.println("Done");

    }

    public static void single() {
        var mapResult = A2_VThreadPThreadIOOperation.fetch("D:\\temp.TXT");
        System.out.println("map result " + mapResult.size());

        for (Map.Entry<String, List<String>> entry : mapResult.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }

    }

    public static void allmap() {
        String folderPath = "D:\\dpsdearcitizens\\"; // Replace with the actual folder path

        Path dir = Paths.get(folderPath);
        var allList = new ArrayList<HashMap<String, List<String>>>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    System.out.println("File: " + file.getFileName());
                    var mapResult = A2_VThreadPThreadIOOperation.fetch("D:\\dpsdearcitizens\\" + file.getFileName());
                    System.out.println("map result " + mapResult.size());

                    allList.add(mapResult);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        var lastMap = new HashMap<String, List<String>>();
        System.out.println("list of file -->" + allList.size());
        //for (HashMap<String, List<String>> h : allList) {
        for (int i = 0; i < allList.size(); i++) {
            HashMap<String, List<String>> h = allList.get(i);
            if (i == 0) {
                lastMap.putAll(h);
                continue;
            }
            for (Map.Entry<String, List<String>> entry : h.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                //String key2=null;
                String keyFinal = null;
                List<String> valFinal = null;
                boolean flag = false;
                List<String> value2=null;
                for (Map.Entry<String, List<String>> entry2 : lastMap.entrySet()) {
                    var key2 = entry2.getKey();
                    if (key2!=null && key.trim().equalsIgnoreCase(key2.trim())) {
                        flag=true;
                        keyFinal = key2;
                        value2 = entry2.getValue();
                        value2.addAll(value);
                        break;
                    }
                }
                if (!flag) {
                    valFinal=value;
                    lastMap.put(keyFinal, valFinal);
                }

            }

        }
        for (Map.Entry<String,  List<String>> entry : lastMap.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            Collections.sort(value);

            System.out.println( "============================" );
            System.out.println( key );
            System.out.println( "============================" );
            List<String> vv = entry.getValue();
            var dupDAte = new ArrayList<String>();
            for(String str:vv){
                System.out.println(str);
                dupDAte.add(str.split("\\|")[0]);
            }
            Set<String> uniqueStrings = new HashSet<>();
            Set<String> duplicates = new HashSet<>();
            for(String str:dupDAte){
                if (!uniqueStrings.add(str)) {
                    duplicates.add(str);
                }

            }
            System.out.println("Duplicate strings: " + duplicates);
        }
    }
}



