package xcom.yt.csvdemo;

public class OpenCsvExample {
    public static void main(String[] args) {
        String fileName = "c:\\test\\csv\\country.csv";
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
        }
    }
}
