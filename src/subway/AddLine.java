package subway;

import java.io.*;
import java.util.Scanner;

public class AddLine {
    public void login() {
        String password;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入管理员登录密码：");
        password = scanner.nextLine();
        if (password.equals("123")) {
            try {
                addLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addLine() throws IOException {
        String f = "subway.txt";
        Scanner scanner = new Scanner(System.in);

        String line;
        String lineName;
        System.out.println("请输入线路名称:");
        lineName = scanner.nextLine();
        FileReader fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //判断该线路名称是否已存在
        boolean isexist = false;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains(lineName)) {
                isexist = true;
                break;
            }
        }
        if (!isexist)
            inputtxt("\n" + lineName + "/");
        else {
            System.out.println("输入的线路名已存在，请重新输入");
            addLine();
        }
        System.out.println("请输入该线路的票价:");
        inputtxt(scanner.nextLine() + "%");
        System.out.println("请输入该线路的首班时间:");
        inputtxt(scanner.nextLine() + "%");
        System.out.println("请输入该线路的末班时间:");
        inputtxt(scanner.nextLine() + "- ");

        boolean flag = true;
        int i = 1;
        while (flag) {
            System.out.println("请输入该线路第" + i + "个站点名称：");
            i++;
            String stationName;
            stationName = scanner.nextLine();
            lineName = delete(lineName);
            System.out.println("请输入" + stationName + "是否可以换乘，若可换乘则输入Y，不能则输入N。");
            if (scanner.nextLine().equals("Y")) {
                String oldTransfer = " "+stationName+addTransferStation(stationName, lineName);
                inputtxt(oldTransfer);
            }
            else {
                inputtxt(" #"+stationName);
            }
            System.out.println("是否继续输入站点？Y：N");
            if (scanner.nextLine().equals("N")) {
                flag = false;
            }
        }
    }

    public String addTransferStation(String stationName, String lineName) throws IOException {
        String f = "subway.txt";
        FileReader fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        String new_subaway = "";
        String oldTransfer = "";
        // 用 new_subway 重新编写subway.txt文件
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains(stationName)) {

                String[] tokens = line.split(stationName);
                String[] tokens1 = tokens[0].split("/", 2);
                oldTransfer += "#"+tokens1[0];
                String Line;
                if (tokens.length > 1) {
                    Line = tokens[0] + stationName + "#" + lineName + tokens[1];
                    /*String[] tokens1 = tokens[1].split(" ", 2);

                    if (tokens1.length > 1) {
                        String[] tokens2 = tokens[0].split("/",2);
                        String atLineName = tokens2[0];
                        Line = tokens[0] + stationName + tokens1[0] + "#" +lineName + " " + tokens1[1];
                        oldtransfer += tokens2[0];
                    }
                    else {
                        String[] tokens2 = tokens[0].split("/",2);
                        String atLineName = tokens2[0];
                        oldtransfer = tokens2[0];
                        Line = tokens[0] + stationName + tokens1[0] + "#" +lineName;
                    }*/

                } else {
                    Line = tokens[0] + stationName + "#" + lineName;
                }
                new_subaway += Line + "\n";
            } else {
                new_subaway += line + "\n";
            }
        }
        new_subaway = new_subaway.substring(0,new_subaway.length()-2);

        File file = new File(f);
        Writer writer = new FileWriter(file);
        writer.write(new_subaway);
        writer.flush();
        writer.close();
        return delete(oldTransfer);
    }

    public String delete(String lineName) {
        String newLineName = "";
        for (int i = 0; i < lineName.length(); ++i) {
            if ((lineName.charAt(i) != '号') && (lineName.charAt(i) != '线')) {
                newLineName += lineName.charAt(i);
            }
        }
        return newLineName;
    }
    public void inputtxt(String string) throws IOException{
        String f = "subway.txt";
        File file = new File(f);
        FileOutputStream fos = new FileOutputStream(file, true);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(string);
        osw.flush();
        fos.flush();
        fos.close();
        osw.close();
    }
}
